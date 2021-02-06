/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi;

import de.tum.in.mi.MIAssembler;
import de.tum.in.mi.MIEditor;
import de.tum.in.mi.MI_Main;
import de.tum.in.mi.miAssembler.MIAssemblerError;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class MI
extends FileFilter {
    MI_Main _oMain;
    Vector _vEditors;
    MIEditor _oActEditor;
    MIAssembler _oAssembler;

    public static void main(String[] args) {
        MI oMI = new MI();
        if (oMI.init()) {
            oMI.run();
        }
    }

    private boolean init() {
        this.initNativeLibraries();
        this._vEditors = new Vector();
        this._oMain = new MI_Main(this);
        this._oAssembler = new MIAssembler(this);
        return true;
    }

    private void initNativeLibraries() {
        String cLib = "MI_LIB";
        String cLibFile = System.mapLibraryName(cLib);
        System.loadLibrary(cLib);
    }

    private void run() {
        this._oMain.setVisible(true);
    }

    void finish() {
        if (this._oAssembler != null) {
            this._oAssembler.finish();
        }
        if (this._oActEditor != null) {
            this._oActEditor.getSimulator().finish();
        }
        System.exit(0);
    }

    void closeSimulation() {
        this._oActEditor.getSimulator().finish();
        System.gc();
        this._oActEditor.getSimulator();
    }

    void restartSimulation() {
        if (this._oActEditor != null) {
            this._oActEditor.resetSimulatorActions();
            if (this._oActEditor.getSimulator().getFile() != null && this._oActEditor.getSimulator().getBreakpoints() != null) {
                this.restartSimulation(this._oActEditor.getSimulator().getFile(), this._oActEditor.getSimulator().getBreakpoints());
            }
        }
    }

    void restartSimulation(File oFile, Vector aBreakpoints) {
        this._oActEditor.getSimulator().finish();
        this._oActEditor.removeSimulator();
        System.gc();
        if (!this._oActEditor.isAssembled()) {
            this.assembleActEditor();
        }
        this._oActEditor.getSimulator().simulateStep(oFile, aBreakpoints);
    }

    void simulateRunActEditor() {
        if (this._oActEditor == null) {
            return;
        }
        if (!this._oActEditor.isAssembled()) {
            this.assembleActEditor();
            if (!this._oActEditor.isAssembled()) {
                return;
            }
        }
        this._oActEditor.getSimulator().simulateRun(this._oActEditor.getFile());
    }

    void updateSimulation() {
        if (this._oActEditor.getSimulator() != null) {
            this._oActEditor.getSimulator().update();
        }
    }

    void simulateStepActEditor() {
        if (this._oActEditor == null) {
            return;
        }
        if (!this._oActEditor.isAssembled()) {
            this.assembleActEditor();
            if (!this._oActEditor.isAssembled()) {
                return;
            }
        }
        this._oActEditor.getSimulator().simulateStep(this._oActEditor.getFile());
    }

    void assembleActEditor() {
        if (this._oActEditor != null) {
            this.saveFile();
            File oFile = this._oActEditor.getFile();
            if (this._oAssembler.assemble(oFile)) {
                this._oActEditor.setFlagAssembled(true);
            }
        }
    }

    void loadFile() {
        String cFile;
        JFileChooser oFileDlg = new JFileChooser("MI-File laden");
        oFileDlg.setMultiSelectionEnabled(false);
        oFileDlg.addChoosableFileFilter(this);
        if (oFileDlg.showOpenDialog(this._oMain) == 0 && (cFile = oFileDlg.getSelectedFile().getAbsolutePath()) != null) {
            MIEditor oNewEditor = new MIEditor(this, cFile);
            oNewEditor.load();
            this._vEditors.addElement(oNewEditor);
            this._oActEditor = oNewEditor;
            this._oMain.focusEditorTab(this._oActEditor);
        }
    }

    void saveFile() {
        if (this._oActEditor != null) {
            this._oActEditor.save();
        }
    }

    void saveAsFile() {
        if (this._oActEditor != null) {
            String cFile;
            JFileChooser oFileDlg = new JFileChooser("MI-File sichern");
            oFileDlg.addChoosableFileFilter(this);
            oFileDlg.setCurrentDirectory(this._oActEditor.getFile().getParentFile());
            oFileDlg.setMultiSelectionEnabled(false);
            if (oFileDlg.showSaveDialog(this._oMain) == 0 && (cFile = oFileDlg.getSelectedFile().getAbsolutePath()) != null) {
                this._oActEditor.setFileName(cFile);
                this.saveFile();
            }
        }
    }

    void closeFile() {
        if (this._oActEditor != null) {
            this._oActEditor.closeDialog();
            this._vEditors.removeElement(this._oActEditor);
            this._oActEditor = null;
            if (!this._vEditors.isEmpty()) {
                MIEditor oEditor;
                this._oActEditor = oEditor = (MIEditor)this._vEditors.get(0);
                this._oMain.focusEditorTab(this._oActEditor);
            }
        }
    }

    void newFile() {
        MIEditor oNewEditor = new MIEditor(this);
        this._vEditors.addElement(oNewEditor);
        this._oActEditor = oNewEditor;
        this._oMain.focusEditorTab(this._oActEditor);
    }

    public void showMessage(String cMsg) {
        this._oMain.showMessage(cMsg);
    }

    public void showAssemblerError(String cFehler) {
        this.showMessage("Beim \u00dcbersetzen sind Fehler aufgetreten (vgl. Assembler-Tab)");
        MIAssemblerError oDlgError = new MIAssemblerError(this, cFehler);
    }

    public void showError(String cFehler) {
        this._oMain.showError(cFehler);
    }

    public void setActEditor(MIEditor oEditor) {
        this._oActEditor = oEditor;
        if (this._oActEditor.hasSimulator()) {
            this.restartSimulation(this._oActEditor.getFile(), new Vector());
        }
    }

    public MIEditor getActEditor() {
        return this._oActEditor;
    }

    public MI_Main getMain() {
        return this._oMain;
    }

    public boolean accept(File oDir) {
        if (oDir.isDirectory()) {
            return true;
        }
        return oDir.getName().toLowerCase().endsWith(".mi");
    }

    public String getDescription() {
        return "MI-Sourcedateien";
    }

    public void showAssemblerSuccess() {
        this.showMessage("Erfolgreich \u00fcbersetzt");
        this.getActEditor().getAssemblerPanel().removeAll();
    }
}

