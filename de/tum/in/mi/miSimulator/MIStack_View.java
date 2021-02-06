/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi.miSimulator;

import de.tum.in.mi.MISimulator;
import de.tum.in.mi.miSimulator.MIController;
import de.tum.in.mi.miSimulator.MI_View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MIStack_View
extends MI_View {
    private int offset;
    private int length;
    private int columns;
    private byte[] speicher;
    private JList lstMIData;
    private DefaultListModel lstMIDataModel;

    public MIStack_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this.setTitle("Stack");
        this.setName("Stack");
        this.setSize(200, 200);
        this.initComponents();
    }

    public void init() {
        this.length = 128;
        this.columns = 4;
    }

    public void update() {
        this.offset = this._oSimulator.getSP();
        if (this.offset == 0) {
            return;
        }
        this.speicher = this._oSimulator.getSpeicher(this.offset, this.length);
        if (this.lstMIDataModel.getSize() > 0) {
            this.lstMIData.removeAll();
        }
        for (int i = 0; i < (this.length - 1) / this.columns + 1; ++i) {
            this.lstMIDataModel.addElement(this.formatLine(i * this.columns));
        }
    }

    private String formatAddress(int address) {
        String s = Integer.toHexString(address).toUpperCase();
        return String.valueOf("00000000".substring(0, 8 - s.length())) + s;
    }

    private String formatByte(byte b) {
        int i = b;
        if (i < 0) {
            i += 256;
        }
        String s = Integer.toHexString(i).toUpperCase();
        return String.valueOf("00".substring(0, 2 - s.length())) + s;
    }

    private String formatLine(int adr) {
        String cZeile = String.valueOf(this.formatAddress(adr + this.offset)) + ":";
        for (int j = 0; j < this.columns && adr < this.length; ++j) {
            cZeile = String.valueOf(cZeile) + " " + this.formatByte(this.speicher[adr++]);
        }
        return cZeile;
    }

    public void initComponents() {
        this.lstMIDataModel = new DefaultListModel();
        this.lstMIData = new JList(this.lstMIDataModel);
        JScrollPane listScrollPane = new JScrollPane(this.lstMIData);
        this.getContentPane().setLayout(new BorderLayout());
        this.lstMIData.setFont(new Font("Courier", 0, 12));
        this.lstMIData.setName("lstMIData");
        this.lstMIData.setBackground(new Color(255, 247, 233));
        this.lstMIData.setForeground(Color.black);
        this.getContentPane().add((Component)listScrollPane, "Center");
        super.initComponents();
    }
}

