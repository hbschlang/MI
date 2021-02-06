/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi.miSimulator;

import de.tum.in.mi.MIEditor;
import de.tum.in.mi.MISimulator;
import de.tum.in.mi.MI_Main;
import de.tum.in.mi.miSimulator.MICodeBP_View;
import de.tum.in.mi.miSimulator.MICode_View;
import de.tum.in.mi.miSimulator.MIData_View;
import de.tum.in.mi.miSimulator.MIPSW_View;
import de.tum.in.mi.miSimulator.MIRegister_View;
import de.tum.in.mi.miSimulator.MISpecialRegister_View;
import de.tum.in.mi.miSimulator.MIStack_View;
import de.tum.in.mi.miSimulator.MI_View;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Properties;
import java.util.Vector;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;

public class MIController {
    private MISimulator _oSimulator;
    private MIEditor _oEditor;
    private MI_Main _oMain;
    private Vector _aViews;
    private Properties _oSimulationProperties;
    private JMenu _menMI_menuWindow;

    public MIController(MISimulator oSimulator, MIEditor oEditor) {
        this._oSimulator = oSimulator;
        this._oEditor = oEditor;
        this._oMain = oSimulator.getMainWindow();
        this._aViews = new Vector();
        this._oSimulationProperties = this._oSimulator.getSimulationProperties();
        this._menMI_menuWindow = new JMenu();
        this._menMI_menuWindow.setName("MI_menuWindow");
        this._menMI_menuWindow.setText("Window");
    }

    public int getDezimalStateMI() {
        return this._oMain.getDezimalState();
    }

    public MI_Main getMain() {
        return this._oMain;
    }

    public void createViews() {
        this.registerView(new MIPSW_View(this, this._oSimulator));
        this.registerView(new MIRegister_View(this, this._oSimulator));
        this.registerView(new MISpecialRegister_View(this, this._oSimulator));
        this.registerView(new MICodeBP_View(this, this._oSimulator));
        this.registerView(new MICode_View(this, this._oSimulator));
        this.registerView(new MIData_View(this, this._oSimulator));
        this.registerView(new MIStack_View(this, this._oSimulator));
        this._oMain.getJMenuBar().add(this._menMI_menuWindow);
        this.initViews();
    }

    public MISimulator getSimulator() {
        return this._oSimulator;
    }

    public String getProperty(String cName) {
        return this._oSimulationProperties.getProperty(cName);
    }

    public void setProperty(String cName, String cValue) {
        this._oSimulationProperties.setProperty(cName, cValue);
    }

    public void dispose() {
        int iAnzViews = this._aViews.size();
        for (int i = 0; i < iAnzViews; ++i) {
            MI_View oView = (MI_View)this._aViews.elementAt(i);
            if (oView.isVisible()) {
                this.setProperty(String.valueOf(oView.getName()) + "_Visible", "True");
            } else {
                this.setProperty(String.valueOf(oView.getName()) + "_Visible", "False");
            }
            oView.dispose();
        }
        this._oSimulator.saveSimulationProperties();
        this._oMain.getJMenuBar().remove(this._menMI_menuWindow);
    }

    public void show() {
        int iAnzViews = this._aViews.size();
        for (int i = 0; i < iAnzViews; ++i) {
            if (!((JCheckBoxMenuItem)this._menMI_menuWindow.getItem(i)).getState()) continue;
            ((MI_View)this._aViews.elementAt(i)).show();
        }
    }

    public JDesktopPane getSimulationPanel() {
        return this._oEditor.getSimulationPanel();
    }

    public void registerView(MI_View oView) {
        this._aViews.addElement(oView);
        oView.restoreSettings();
        oView.show();
        oView.init();
        JCheckBoxMenuItem checkboxMenuItem = new JCheckBoxMenuItem();
        checkboxMenuItem.setText(oView.getTitle());
        checkboxMenuItem.setName("menchk" + oView.getName());
        checkboxMenuItem.setState(true);
        checkboxMenuItem.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIController.this.checkboxWindowActionPerformed(evt);
            }
        });
        this._menMI_menuWindow.add(checkboxMenuItem);
    }

    private void checkboxWindowActionPerformed(ItemEvent evt) {
        JCheckBoxMenuItem oChk = (JCheckBoxMenuItem)evt.getSource();
        String cViewName = oChk.getName();
        cViewName = cViewName.substring(6);
        boolean bState = oChk.getState();
        int iAnzViews = this._aViews.size();
        for (int i = 0; i < iAnzViews; ++i) {
            MI_View oView = (MI_View)this._aViews.elementAt(i);
            if (!oView.getName().equals(cViewName)) continue;
            oView.setVisible(bState);
            break;
        }
    }

    public void unregisterView(MI_View oView) {
        this._aViews.removeElement(oView);
    }

    public void updateViews() {
        int iAnzViews = this._aViews.size();
        for (int i = 0; i < iAnzViews; ++i) {
            ((MI_View)this._aViews.elementAt(i)).update();
        }
    }

    public void initViews() {
        int iAnzViews = this._aViews.size();
        for (int i = 0; i < iAnzViews; ++i) {
            MI_View oView = (MI_View)this._aViews.elementAt(i);
            String cVisible = this.getProperty(String.valueOf(oView.getName()) + "_Visible");
            if (cVisible == null || !cVisible.equals("False")) continue;
            oView.setVisible(false);
            ((JCheckBoxMenuItem)this._menMI_menuWindow.getItem(i)).setState(false);
        }
    }
}

