/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi;

import de.tum.in.mi.MI;
import de.tum.in.mi.MISimulator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class MIEditor
extends JTabbedPane {
    private MI _oParent;
    private File _oFile;
    private boolean _bIsAssembled;
    private String _cTitle;
    private JTextArea _txtMICode;
    private JDesktopPane _panSimulation;
    private JPanel _panAssembler;
    private MISimulator _oSimulator;
    private Vector _vSimulatorActions;

    public MIEditor(MI mI, String string) {
        this._oParent = mI;
        this.setFileName(string);
        this.initComponents();
        mI.getMain().createNewEditorTab(this);
    }

    public MISimulator getSimulator() {
        if (this._oSimulator == null) {
            this._oSimulator = new MISimulator(this._oParent, this, this._vSimulatorActions);
            this._vSimulatorActions = new Vector();
        }
        return this._oSimulator;
    }

    public void addSimulatorAction(String string) {
        this._vSimulatorActions.add(string);
    }

    public void resetSimulatorActions() {
        this._vSimulatorActions = null;
        this._vSimulatorActions = new Vector();
    }

    public void removeSimulator() {
        this._oSimulator.finish();
        this._oSimulator = null;
    }

    public boolean hasSimulator() {
        return this._oSimulator != null;
    }

    public JDesktopPane getSimulationPanel() {
        return this._panSimulation;
    }

    public JPanel getAssemblerPanel() {
        return this._panAssembler;
    }

    public boolean isAssembled() {
        return this._bIsAssembled;
    }

    public void setFlagAssembled(boolean bl) {
        this._bIsAssembled = bl;
        String string = " (assembled)";
        String string2 = this.getTitle();
        if (this._bIsAssembled) {
            if (!string2.endsWith(string)) {
                this.setTitle(string2 + string);
            }
        } else if (string2.endsWith(string)) {
            string2 = string2.substring(0, string2.length() - string.length());
            this.setTitle(string2);
        }
    }

    public String getTitle() {
        return this._cTitle;
    }

    public void setTitle(String string) {
        this._cTitle = string;
        this._oParent.getMain().setEditorTabTitle(this, string);
    }

    public void setFileName(String string) {
        if (!string.endsWith(".mi")) {
            this._oParent.showMessage("Dateiname muss auf '.mi' enden.");
            string = string + ".mi";
            this._oParent.showMessage("Dateiname wurde auf " + string + " ge\u00e4ndert.");
        }
        this.setTitle(string);
        this._oFile = new File(string);
    }

    public File getFile() {
        return this._oFile;
    }

    public void save() {
        if (!this._oFile.getName().toLowerCase().endsWith(".mi")) {
            this._oFile = new File(this._oFile.getPath() + ".mi");
        }
        if (!this._oFile.exists()) {
            try {
                this._oFile.createNewFile();
            }
            catch (IOException iOException) {
                this._oParent.showError("Fehler beim Erstellen von " + this._oFile.getPath() + this._oFile.getName());
                return;
            }
        }
        if (this._oFile.canWrite()) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this._oFile));
                String string = this._txtMICode.getText();
                bufferedWriter.write(string);
                bufferedWriter.close();
                this.setTitle(this._oFile.getPath());
            }
            catch (IOException iOException) {
                this._oParent.showError("Fehler beim Schreiben von " + this._oFile.getPath());
            }
        } else {
            this._oParent.showError(this._oFile.getPath() + " darf nicht geschrieben werden.");
        }
    }

    public void load() {
        if (this._oFile.canRead()) {
            try {
                String string;
                BufferedReader bufferedReader = new BufferedReader(new FileReader(this._oFile));
                this._txtMICode.setText("");
                while ((string = bufferedReader.readLine()) != null) {
                    this._txtMICode.append(string + "\n");
                }
                bufferedReader.close();
            }
            catch (IOException iOException) {
                this._oParent.showError("Fehler beim Einlesen von " + this._oFile.getPath());
            }
        } else {
            this._oParent.showError(this._oFile.getPath() + " nicht vorhanden oder\ndarf nicht gelesen werden.");
        }
    }

    public MIEditor(MI mI) {
        this(mI, "unbenannt.mi");
    }

    private void initComponents() {
        this._txtMICode = new JTextArea();
        this._txtMICode.setBackground(new Color(255, 247, 233));
        this._txtMICode.setName("txtMICode");
        this._txtMICode.setFont(new Font("Courier", 0, 14));
        this._txtMICode.setForeground(Color.black);
        this._txtMICode.addKeyListener(new KeyListener(){

            public void keyTyped(KeyEvent keyEvent) {
                MIEditor.this._txtMICodeTextValueChanged(keyEvent);
            }

            public void keyPressed(KeyEvent keyEvent) {
            }

            public void keyReleased(KeyEvent keyEvent) {
            }
        });
        this.add("Source", new JScrollPane(this._txtMICode));
        this._panAssembler = new JPanel();
        this.add("Assembler", new JScrollPane(this._panAssembler));
        this._panSimulation = new JDesktopPane();
        this.add("Simulation", this._panSimulation);
    }

    private void _txtMICodeTextValueChanged(KeyEvent keyEvent) {
        this.setFlagAssembled(false);
    }

    public void closeDialog() {
        this.setVisible(false);
        this._oParent.getMain().removeEditorTab(this);
    }
}

