/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi;

import de.tum.in.mi.MI;
import de.tum.in.mi.MIEditor;
import de.tum.in.mi.MI_Main;
import de.tum.in.mi.miSimulator.MIController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

public class MISimulator {
    private MI _oParent;
    private MIEditor _oEditor;
    private MIController _oController;
    private boolean _bSimulationInitialised;
    private boolean _bFirstSimulationStep;
    private File _oCodeFile;
    private long _lLastModified;
    private String[][] _aListing;
    private Vector _aAddressBreakpoints;
    private Properties _oProperties;
    private Vector _vOldActions;
    private String _cPSW;
    private String _cPC;
    private String _cLastPC;
    private String _cRegister;
    private String _cSpeicher;
    private String _cSP;
    private final int ZEILE = 0;
    private final int LABEL = 1;
    private final int ADDRESS = 2;
    private final int CODE = 3;
    private final int SOURCE = 4;
    private final int MAX_ZEILEN = 512;

    public MISimulator(MI oParent, MIEditor oEditor, Vector vOldActions) {
        this._oParent = oParent;
        this._oEditor = oEditor;
        this._bSimulationInitialised = false;
        this._bFirstSimulationStep = true;
        this._aAddressBreakpoints = new Vector();
        this._vOldActions = vOldActions;
    }

    public void finish() {
        if (this._oController != null) {
            this._oController.dispose();
            this._oController = null;
        }
    }

    public MI_Main getMainWindow() {
        return this._oParent.getMain();
    }

    public Properties getSimulationProperties() {
        return this._oProperties;
    }

    private String formatAddress(int address) {
        String s = Integer.toHexString(address).toUpperCase();
        return String.valueOf("00000000".substring(0, 8 - s.length())) + s;
    }

    public byte[] getSpeicher(int offset, int length) {
        String befehl = "display";
        befehl = String.valueOf(befehl) + " " + this.formatAddress(offset);
        befehl = String.valueOf(befehl) + " " + this.formatAddress(offset + length - 1);
        this._cSpeicher = "";
        this.execCom_(befehl);
        int cnt16 = 15;
        int pos = 0;
        byte[] bytes = new byte[length];
        try {
            for (int i = 0; i < length; ++i) {
                if (++cnt16 >= 16) {
                    cnt16 = 0;
                    pos = this._cSpeicher.indexOf(":", pos) + 1;
                }
                bytes[i] = (byte)Integer.parseInt("0" + this._cSpeicher.substring(pos, pos + 2), 16);
                pos += 2;
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public void setPSW(int iNewPSW) {
        this.setRegister("PSW", Integer.toString(iNewPSW));
    }

    public void setRegister(String cRegister, String cValue) {
        if (this._bSimulationInitialised) {
            this.execCom_("modify " + cRegister + " = " + cValue);
        }
    }

    public String[] getAllRegister() {
        this._cRegister = "";
        this.execCom_("R0 IPL");
        String[] aRegister = new String[26];
        int iOffset = 0;
        for (int i = 0; i < 26; ++i) {
            String cReg = ":";
            iOffset = this._cRegister.indexOf(cReg, iOffset) + cReg.length();
            aRegister[i] = this._cRegister.substring(iOffset, iOffset + 8);
        }
        return aRegister;
    }

    public int getSP() {
        this._cSP = "";
        this.execCom_("SP");
        int iOffset = this._cSP.indexOf("SP:") + 3;
        String cSP = "0x" + this._cSP.substring(iOffset, iOffset + 8);
        int iSP = Long.decode(cSP).intValue();
        return iSP;
    }

    public int getAddressBPCount() {
        return this._aAddressBreakpoints.size();
    }

    public boolean isAddressBP(int iZeile) {
        String cAddress = this._aListing[iZeile - 1][2];
        boolean bFound = false;
        for (int i = this._aAddressBreakpoints.size(); i > 0; --i) {
            if (!((String)this._aAddressBreakpoints.get(i - 1)).equals(cAddress)) continue;
            bFound = true;
            break;
        }
        return bFound;
    }

    public String getAddressBP(int i) {
        return this.getListingZeile((String)this._aAddressBreakpoints.get(i - 1));
    }

    public void removeAllBreakpoints() {
        while (this._aAddressBreakpoints.size() > 0) {
            String cAddress = (String)this._aAddressBreakpoints.get(0);
            this._aAddressBreakpoints.removeElement(cAddress);
            this.execCom_("cbp " + cAddress);
        }
        this.update();
    }

    public void toogleAddressBP(int iZeile) {
        if (this._aListing[iZeile - 1] == null) {
            return;
        }
        String cAddress = this._aListing[iZeile - 1][2];
        if (this.isAddressBP(iZeile)) {
            this._aAddressBreakpoints.removeElement(cAddress);
            this.execCom_("cbp " + cAddress);
        } else {
            this._aAddressBreakpoints.addElement(cAddress);
            this.execCom_("sbp " + cAddress);
        }
        this.update();
    }

    public int getAnzahlListingZeilen() {
        return 512;
    }

    public String getListingZeile(String cAddress) {
        return this.getListingZeile(this.getZeileFromAddress(cAddress));
    }

    public String getListingZeile(int iZeile) {
        String cZeile = this._aListing.length < iZeile ? "-" : (this._aListing[iZeile - 1] == null ? "-" : String.valueOf(this.padRight(String.valueOf(this._aListing[iZeile - 1][0]) + ") ", 5)) + " " + this.padRight(this._aListing[iZeile - 1][1], 10) + " " + this._aListing[iZeile - 1][4]);
        return cZeile;
    }

    private String padRight(String cString, int iLen) {
        String cRueck = cString;
        int nDelta = iLen - cString.length();
        if (nDelta >= 0) {
            for (int i = nDelta; i > 0; --i) {
                cRueck = String.valueOf(cRueck) + " ";
            }
        } else {
            cRueck = cRueck.substring(0, iLen);
        }
        return cRueck;
    }

    public void saveSimulationProperties() {
        String cName = this._oCodeFile.getName();
        String cFile = String.valueOf(this._oCodeFile.getParent()) + File.separator + cName.substring(0, cName.length() - 3) + ".properties";
        try {
            this._oProperties.store(new FileOutputStream(cFile), "MI-Properties");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void restart() {
        this._oParent.restartSimulation(this._oCodeFile, this._aAddressBreakpoints);
    }

    private boolean initSimulation(File oFile) {
        if (!this._bSimulationInitialised || oFile.lastModified() != this._lLastModified) {
            this._lLastModified = oFile.lastModified();
            this._cPSW = "";
            this._oCodeFile = oFile;
            String cName = oFile.getName();
            this._oProperties = new Properties();
            String cFile = String.valueOf(oFile.getParent()) + File.separator + cName.substring(0, cName.length() - 3) + ".properties";
            File oPropFile = new File(cFile);
            if (!oPropFile.exists()) {
                FileInputStream from = null;
                FileOutputStream to = null;
                try {
                    try {
                        int bytes_read;
                        if (!oPropFile.createNewFile()) {
                            System.out.println("Fehler beim Erzeugen der Zieldatei!");
                        }
                        from = new FileInputStream("standard.properties");
                        to = new FileOutputStream(oPropFile);
                        byte[] buf = new byte[16384];
                        while ((bytes_read = from.read(buf)) != -1) {
                            to.write(buf, 0, bytes_read);
                        }
                    }
                    catch (IOException e) {
                        System.out.println(e);
                    }
                }
                catch (Throwable throwable) {
                    Object var10_12 = null;
                    try {
                        from.close();
                        to.close();
                    }
                    catch (Exception e) {
                        // empty catch block
                    }
                    throw throwable;
                }
                Object var10_13 = null;
                try {
                    from.close();
                    to.close();
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
            try {
                FileInputStream oPropertiesStream = new FileInputStream(cFile);
                this._oProperties.load(oPropertiesStream);
                oPropertiesStream.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.erzhd_native(oFile.getParent());
            cFile = String.valueOf(oFile.getParent()) + File.separator + cName.substring(0, cName.length() - 3) + ".listing";
            this.loadListing(cFile);
            cFile = String.valueOf(oFile.getParent()) + File.separator + cName.substring(0, cName.length() - 3) + ".code";
            int iRueck = this.init_native(cFile);
            this._bFirstSimulationStep = true;
            boolean bl = this._bSimulationInitialised = iRueck == 0;
        }
        if (this._oController == null) {
            this._oController = new MIController(this, this._oEditor);
            this._oController.createViews();
        }
        return this._bSimulationInitialised;
    }

    private void loadListing(String cFile) {
        File oFile = new File(cFile);
        if (oFile.canRead()) {
            this._aListing = new String[512][];
            try {
                BufferedReader in = new BufferedReader(new FileReader(oFile));
                String cZeile = in.readLine();
                int iLabelOffset = cZeile.indexOf("Label");
                int iAdressOffset = cZeile.indexOf("Address");
                int iCodeOffset = cZeile.indexOf("Code");
                int iSourceOffset = cZeile.indexOf("Text");
                String[] aZeile = new String[5];
                int iZeile = 0;
                while ((cZeile = in.readLine()) != null) {
                    if (cZeile.substring(1, iLabelOffset).trim().length() < 1) {
                        aZeile[3] = String.valueOf(aZeile[3]) + cZeile.substring(iCodeOffset, iSourceOffset - 2).trim();
                        aZeile[4] = cZeile.length() > iSourceOffset ? cZeile.substring(iSourceOffset).trim() : "";
                    } else {
                        aZeile = new String[]{new Integer(++iZeile).toString(), cZeile.substring(iLabelOffset, iAdressOffset - 2).trim(), cZeile.substring(iAdressOffset, iAdressOffset + 8), cZeile.substring(iCodeOffset, iSourceOffset - 2).trim(), cZeile.length() > iSourceOffset ? cZeile.substring(iSourceOffset).trim() : ""};
                    }
                    this._aListing[iZeile - 1] = aZeile;
                }
                in.close();
            }
            catch (IOException ex) {
                this._oParent.showError("Fehler beim Einlesen von " + cFile);
            }
        } else {
            this._oParent.showError(String.valueOf(cFile) + " nicht vorhanden oder\ndarf nicht gelesen werden.");
        }
    }

    public File getFile() {
        return this._oCodeFile;
    }

    public Vector getBreakpoints() {
        return this._aAddressBreakpoints;
    }

    public void simulateRun(File oFile, Vector aBreakpoints) {
        this._aAddressBreakpoints = aBreakpoints;
        for (int i = 0; i < this._aAddressBreakpoints.size(); ++i) {
            String cAddress = (String)this._aAddressBreakpoints.get(i);
            this.execCom_native("sbp " + cAddress);
        }
        this.simulateRun(oFile);
    }

    public void simulateRun() {
        if (this._oCodeFile != null) {
            this.simulateRun(this._oCodeFile);
        }
    }

    public void simulateStep() {
        if (this._oCodeFile != null) {
            this.simulateStep(this._oCodeFile);
        }
    }

    public void simulateStep(File oFile, Vector aBreakpoints) {
        this._aAddressBreakpoints = aBreakpoints;
        for (int i = 0; i < this._aAddressBreakpoints.size(); ++i) {
            String cAddress = (String)this._aAddressBreakpoints.get(i);
            this.execCom_native("sbp " + cAddress);
        }
        this.simulateStep(oFile);
    }

    public void simulateStep(File oFile) {
        if (!this.initSimulation(oFile)) {
            return;
        }
        if (!this._bFirstSimulationStep) {
            this.execCom_("sss");
            this.execCom_("go");
            this.execCom_("css");
        } else if (this._vOldActions != null) {
            Iterator it = this._vOldActions.iterator();
            while (it.hasNext()) {
                String action = (String)it.next();
                this.execCom_native(action);
            }
        }
        this._oController.show();
        this.update();
        this._bFirstSimulationStep = false;
    }

    public void update() {
        this._oController.updateViews();
    }

    public void simulateRun(File oFile) {
        if (!this.initSimulation(oFile)) {
            return;
        }
        if (!this._bFirstSimulationStep) {
            this.execCom_("go");
        }
        this._oController.show();
        this._oController.updateViews();
        this._bFirstSimulationStep = false;
    }

    public int getZeileFromAddress(String cAddress) {
        if (!this.initSimulation(this._oCodeFile)) {
            return 0;
        }
        int iZeile = 0;
        int iAnz = this._aListing.length;
        for (int i = 0; i < iAnz; ++i) {
            if (this._aListing[i] == null) break;
            if (!this._aListing[i][2].equals(cAddress)) continue;
            iZeile = i + 1;
            break;
        }
        return iZeile;
    }

    public int getActZeile() {
        return this.getZeileFromAddress(this.getPC());
    }

    public String getActSource() {
        return this.getSourceFromAddress(this.getPC());
    }

    public String getSourceFromAddress(String cAddress) {
        if (!this.initSimulation(this._oCodeFile)) {
            return "<kein Code verf\u00fcgbar>";
        }
        String cSource = "<kein Code verf\u00fcgbar>";
        int iAnz = this._aListing.length;
        for (int i = 0; i < iAnz; ++i) {
            if (this._aListing[i] == null) break;
            if (!this._aListing[i][2].equals(cAddress)) continue;
            cSource = this._aListing[i][4];
            break;
        }
        return cSource;
    }

    public String getLastPC() {
        String cPC;
        if (!this.initSimulation(this._oCodeFile)) {
            return "<none>";
        }
        this._cLastPC = "";
        this.execCom_("btr");
        if (this._cLastPC.indexOf("OK") > 0 && this._cLastPC.indexOf("btrOK") <= 0) {
            int iPos = this._cLastPC.indexOf("btr");
            cPC = this._cLastPC.substring(iPos + 3, iPos + 11);
        } else {
            cPC = "00000000";
        }
        return cPC;
    }

    public String getPC() {
        String cPC;
        if (!this.initSimulation(this._oCodeFile)) {
            return "<none>";
        }
        this._cPC = "";
        this.execCom_("PC");
        if (this._cPC.indexOf("OK") > 0) {
            int iPos = this._cPC.indexOf("PC:");
            cPC = this._cPC.substring(iPos + 3, iPos + 11);
        } else {
            cPC = "00000000";
        }
        return cPC;
    }

    public int getPSW() {
        int iPSW;
        if (!this.initSimulation(this._oCodeFile)) {
            return 0;
        }
        this._cPSW = "";
        this.execCom_("PSW");
        if (this._cPSW.indexOf("OK") > 0) {
            int iPos = this._cPSW.indexOf("PSW:");
            String cPSW = "0x" + this._cPSW.substring(iPos + 4, iPos + 12);
            iPSW = Integer.decode(cPSW);
        } else {
            iPSW = 0;
        }
        return iPSW;
    }

    public void execCom_(String cCommand) {
        this._oEditor.addSimulatorAction(cCommand);
        this.execCom_native(cCommand);
    }

    public native void execCom_native(String var1);

    public native int init_native(String var1);

    public native void erzhd_native(String var1);

    public void null_callback(String cMsg) {
        System.out.println("NULL:" + cMsg);
    }

    public void go_callback(String cMsg) {
    }

    public void R0_callback(String cMsg) {
        this._cRegister = String.valueOf(this._cRegister) + this.killWhiteSpace(cMsg);
    }

    public void SP_callback(String cMsg) {
        this._cSP = String.valueOf(this._cSP) + this.killWhiteSpace(cMsg);
    }

    private String killWhiteSpace(String cMsg) {
        int iAnz = cMsg.length();
        String cReturn = "";
        for (int i = 0; i < iAnz; ++i) {
            char c = cMsg.charAt(i);
            if (c <= ' ') continue;
            cReturn = String.valueOf(cReturn) + c;
        }
        return cReturn;
    }

    public void sbp_callback(String cMsg) {
    }

    public void btr_callback(String cMsg) {
        this._cLastPC = String.valueOf(this._cLastPC) + this.killWhiteSpace(cMsg);
    }

    public void quit_callback(String cMsg) {
    }

    public void modify_callback(String cMsg) {
    }

    public void cbp_callback(String cMsg) {
    }

    public void sss_callback(String cMsg) {
    }

    public void css_callback(String cMsg) {
    }

    public void PC_callback(String cMsg) {
        this._cPC = String.valueOf(this._cPC) + this.killWhiteSpace(cMsg);
    }

    public void PSW_callback(String cMsg) {
        this._cPSW = String.valueOf(this._cPSW) + this.killWhiteSpace(cMsg);
    }

    public void display_callback(String cMsg) {
        this._cSpeicher = String.valueOf(this._cSpeicher) + this.killWhiteSpace(cMsg);
    }
}

