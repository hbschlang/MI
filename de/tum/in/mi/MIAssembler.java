/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi;

import de.tum.in.mi.MI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MIAssembler {
    private MI _oParent;

    public MIAssembler(MI oParent) {
        this._oParent = oParent;
    }

    public void finish() {
    }

    public boolean assemble(File oFile) {
        String cSourceFile = oFile.getPath();
        int iRueck = this.native_assemble(cSourceFile);
        if (iRueck != 0) {
            String cFehler = this.generateErrorMsg(oFile);
            this._oParent.showAssemblerError("Beim \u00dcbersetzen sind Fehler aufgetreten:\n" + cFehler);
        } else {
            this._oParent.showAssemblerSuccess();
        }
        return iRueck == 0;
    }

    private String generateErrorMsg(File oFile) {
        String cName = oFile.getName();
        File oListFile = new File(oFile.getParent(), String.valueOf(cName.substring(0, cName.length() - 3)) + ".listing");
        String cError = "Keine Fehlerangabe m\u00f6glich";
        if (oListFile.canRead()) {
            try {
                String cZeile;
                BufferedReader in = new BufferedReader(new FileReader(oListFile));
                cError = "";
                while ((cZeile = in.readLine()) != null) {
                    cError = String.valueOf(cError) + cZeile + "\n";
                }
                in.close();
            }
            catch (IOException ex) {
                this._oParent.showError("Fehler beim Einlesen von " + oListFile.getPath() + oListFile.getName());
            }
        } else {
            this._oParent.showError(String.valueOf(oListFile.getPath()) + oListFile.getName() + " nicht vorhanden oder\ndarf nicht gelesen werden.");
        }
        return cError;
    }

    private native int native_assemble(String var1);
}

