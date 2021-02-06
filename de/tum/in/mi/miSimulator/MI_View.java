/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi.miSimulator;

import de.tum.in.mi.MISimulator;
import de.tum.in.mi.miSimulator.MIController;
import javax.swing.JInternalFrame;

public class MI_View
extends JInternalFrame {
    protected MIController _oController;
    protected MISimulator _oSimulator;
    private String _cTitle;

    public MI_View(MIController oController, MISimulator oSimulator) {
        super("", true, true, true, true);
        this._oController = oController;
        this._oSimulator = oSimulator;
    }

    public void initComponents() {
        this.pack();
        this._oController.getSimulationPanel().add(this);
    }

    public void restoreSettings() {
        String cName = this.getName();
        String cProp = this._oController.getProperty(String.valueOf(cName) + "_WIDTH");
        if (cProp != null) {
            this.setSize(Integer.parseInt(cProp), this.getSize().height);
        }
        if ((cProp = this._oController.getProperty(String.valueOf(cName) + "_HEIGHT")) != null) {
            this.setSize(this.getSize().width, Integer.parseInt(cProp));
        }
        if ((cProp = this._oController.getProperty(String.valueOf(cName) + "_X")) != null) {
            this.setLocation(Integer.parseInt(cProp), this.getLocation().y);
        }
        if ((cProp = this._oController.getProperty(String.valueOf(cName) + "_Y")) != null) {
            this.setLocation(this.getLocation().x, Integer.parseInt(cProp));
        }
        this.validate();
    }

    public void saveSettings() {
        String cName = this.getName();
        String cProp = Integer.toString(this.getSize().width);
        this._oController.setProperty(String.valueOf(cName) + "_WIDTH", cProp);
        cProp = Integer.toString(this.getSize().height);
        this._oController.setProperty(String.valueOf(cName) + "_HEIGHT", cProp);
        cProp = Integer.toString(this.getLocation().x);
        this._oController.setProperty(String.valueOf(cName) + "_X", cProp);
        cProp = Integer.toString(this.getLocation().y);
        this._oController.setProperty(String.valueOf(cName) + "_Y", cProp);
    }

    public void update() {
    }

    public void dispose() {
        this.saveSettings();
    }

    public void init() {
        this.restoreSettings();
    }
}

