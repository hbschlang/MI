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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MICode_View
extends MI_View {
    private int aktZeile = 1;
    private int altZeile = 1;
    private JList lstMICode;
    private DefaultListModel lstMICodeModel;
    private JButton btnAddressBP;

    public MICode_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this.setTitle("Code to execute");
        this.setName("CodeToExecute");
        this.initComponents();
    }

    public void init() {
        this.getCodeListing(0);
    }

    public void update() {
        int iAktZeile = this._oSimulator.getActZeile();
        this.getCodeListing(iAktZeile);
        this.getCodeListing(this.altZeile);
        this.lstMICode.setSelectedIndex(iAktZeile - 1);
        this.altZeile = iAktZeile;
    }

    private void getCodeListing(int iAktZeile) {
        if (this.lstMICodeModel.getSize() > 0) {
            if (iAktZeile > 0) {
                String cZeile = this._oSimulator.getListingZeile(iAktZeile);
                if (cZeile.equals("-")) {
                    return;
                }
                cZeile = this._oSimulator.isAddressBP(iAktZeile) ? "* " + cZeile : "  " + cZeile;
                cZeile = iAktZeile == this._oSimulator.getActZeile() ? "> " + cZeile : "  " + cZeile;
                this.lstMICodeModel.removeElementAt(iAktZeile - 1);
                this.lstMICodeModel.insertElementAt(cZeile, iAktZeile - 1);
            }
        } else {
            int iAnz = this._oSimulator.getAnzahlListingZeilen();
            for (int i = 1; i <= iAnz; ++i) {
                String cZeile = this._oSimulator.getListingZeile(i);
                if (cZeile.equals("-")) break;
                cZeile = this._oSimulator.isAddressBP(i) ? "* " + cZeile : "  " + cZeile;
                cZeile = i == iAktZeile ? "> " + cZeile : "  " + cZeile;
                this.lstMICodeModel.addElement(cZeile);
            }
            this.aktZeile = iAktZeile;
        }
    }

    public void initComponents() {
        this.lstMICodeModel = new DefaultListModel();
        this.lstMICode = new JList(this.lstMICodeModel);
        JScrollPane listScrollPane = new JScrollPane(this.lstMICode);
        this.btnAddressBP = new JButton();
        this.getContentPane().setLayout(new BorderLayout());
        this.lstMICode.setBackground(new Color(255, 247, 233));
        this.lstMICode.setFont(new Font("Courier", 0, 12));
        this.lstMICode.setForeground(Color.black);
        this.lstMICode.setName("lstMICode");
        this.getContentPane().add((Component)listScrollPane, "Center");
        this.btnAddressBP.setBackground(new Color(204, 204, 204));
        this.btnAddressBP.setFont(new Font("Dialog", 0, 11));
        this.btnAddressBP.setForeground(Color.black);
        this.btnAddressBP.setText("Breakpoint");
        this.btnAddressBP.setName("btnAddressBP");
        this.btnAddressBP.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MICode_View.this.btnAddressBPActionPerformed(evt);
            }
        });
        this.getContentPane().add((Component)this.btnAddressBP, "South");
        super.initComponents();
    }

    private void btnAddressBPActionPerformed(ActionEvent evt) {
        this._oSimulator.toogleAddressBP(this.lstMICode.getSelectedIndex() + 1);
        this._oSimulator.update();
    }
}

