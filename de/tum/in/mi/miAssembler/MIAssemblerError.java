/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi.miAssembler;

import de.tum.in.mi.MI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MIAssemblerError
extends JPanel {
    private JTextArea _txtError;
    MI _oParent;

    public MIAssemblerError(MI oParent, String cError) {
        oParent.getActEditor().getAssemblerPanel().removeAll();
        oParent.getActEditor().getAssemblerPanel().add(this);
        this._oParent = oParent;
        this.initComponents();
        this._txtError.setText(cError);
    }

    private void initComponents() {
        this._txtError = new JTextArea();
        this.setLayout(new BorderLayout());
        this._txtError.setBackground(new Color(174, 178, 195));
        this._txtError.setName("txtError");
        this._txtError.setEditable(false);
        this._txtError.setFont(new Font("Courier", 0, 12));
        this._txtError.setForeground(Color.black);
        this.add((Component)this._txtError, "Center");
    }
}

