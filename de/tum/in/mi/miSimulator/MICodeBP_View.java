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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MICodeBP_View
extends MI_View {
    private JList lstCodeBP;
    private DefaultListModel lstCodeBPModel;
    private JLabel lblActCode;
    private JButton pshRemoveAll;

    public MICodeBP_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this.setTitle("Breakpoints");
        this.setName("Breakpoints");
        this.initComponents();
    }

    public void update() {
        int iBreakpointCount;
        String cCode = this._oSimulator.getActSource();
        this.lblActCode.setText(cCode);
        if (this.lstCodeBPModel.getSize() > 0) {
            this.lstCodeBPModel.removeAllElements();
        }
        for (int i = iBreakpointCount = this._oSimulator.getAddressBPCount(); i > 0; --i) {
            this.lstCodeBPModel.addElement(this._oSimulator.getAddressBP(i));
        }
    }

    public void initComponents() {
        this.lstCodeBPModel = new DefaultListModel();
        this.lstCodeBP = new JList(this.lstCodeBPModel);
        JScrollPane listScrollPane = new JScrollPane(this.lstCodeBP);
        this.lblActCode = new JLabel();
        this.pshRemoveAll = new JButton();
        this.getContentPane().setLayout(new BorderLayout());
        this.lstCodeBP.setFont(new Font("Dialog", 0, 11));
        this.lstCodeBP.setName("lstCodeBP");
        this.lstCodeBP.setBackground(new Color(255, 247, 233));
        this.lstCodeBP.setForeground(Color.black);
        this.getContentPane().add((Component)listScrollPane, "Center");
        this.lblActCode.setFont(new Font("Dialog", 0, 11));
        this.lblActCode.setName("lblActCode");
        this.lblActCode.setBackground(new Color(204, 204, 204));
        this.lblActCode.setForeground(Color.black);
        this.lblActCode.setText("<aktuelle Codezeile>");
        this.getContentPane().add((Component)this.lblActCode, "North");
        this.pshRemoveAll.setText("Remove all");
        this.pshRemoveAll.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MICodeBP_View.this.pshRemoveAllActionPerformed(evt);
            }
        });
        this.getContentPane().add((Component)this.pshRemoveAll, "South");
        super.initComponents();
    }

    private void pshRemoveAllActionPerformed(ActionEvent evt) {
        this._oSimulator.removeAllBreakpoints();
    }
}

