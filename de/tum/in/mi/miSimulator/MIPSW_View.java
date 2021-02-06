/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi.miSimulator;

import de.tum.in.mi.MISimulator;
import de.tum.in.mi.miSimulator.MIController;
import de.tum.in.mi.miSimulator.MI_View;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MIPSW_View
extends MI_View {
    private Color _oRed = Color.red;
    private Color _oBlack = Color.black;
    private boolean _inUpdate = false;
    private JCheckBox PSW_chkShort;
    private JCheckBox PSW_chkTracePending;
    private JCheckBox PSW_chkTraceBit;
    private JCheckBox PSW_chkNegative;
    private JCheckBox PSW_chkZero;
    private JCheckBox PSW_chkOverflow;
    private JCheckBox PSW_chkCarry;
    private JCheckBox PSW_chkIV;
    private JLabel PSW_lblCM;
    private JComboBox PSW_cmbCM;
    private JLabel PSW_lplPM;
    private JComboBox PSW_cmbPM;
    private JLabel PSW_lblBEN_MAPEN;
    private JComboBox PSW_cmbBEN_MAPEN;
    private JLabel PSW_lblIPL;
    private JTextField PSW_txtIPL;

    public MIPSW_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this.setTitle("ProcessorStatusWord");
        this.initComponents();
        this.setShortLabels();
    }

    public void update() {
        boolean bNewState;
        this._inUpdate = true;
        int iPSW = this._oSimulator.getPSW();
        String cNewText = Integer.toString(iPSW >> 16 & 0x20);
        if (!cNewText.equals(this.PSW_txtIPL.getText())) {
            this.PSW_txtIPL.setText(cNewText);
            this.PSW_txtIPL.setForeground(this._oRed);
        } else {
            this.PSW_txtIPL.setForeground(this._oBlack);
        }
        int iNewInt = iPSW >> 26 & 1;
        if (iNewInt != this.PSW_cmbBEN_MAPEN.getSelectedIndex()) {
            this.PSW_cmbBEN_MAPEN.setSelectedIndex(iNewInt);
            this.PSW_cmbBEN_MAPEN.setForeground(this._oRed);
        } else {
            this.PSW_cmbBEN_MAPEN.setForeground(this._oBlack);
        }
        iNewInt = iPSW >> 24 & 3;
        if (iNewInt != this.PSW_cmbCM.getSelectedIndex()) {
            this.PSW_cmbCM.setSelectedIndex(iNewInt);
            this.PSW_cmbCM.setForeground(this._oRed);
        } else {
            this.PSW_cmbCM.setForeground(this._oBlack);
        }
        iNewInt = iPSW >> 22 & 3;
        if (iNewInt != this.PSW_cmbPM.getSelectedIndex()) {
            this.PSW_cmbPM.setSelectedIndex(iNewInt);
            this.PSW_cmbPM.setForeground(this._oRed);
        } else {
            this.PSW_cmbPM.setForeground(this._oBlack);
        }
        boolean bl = bNewState = (iPSW >> 31 & 1) == 1;
        if (bNewState != this.PSW_chkTracePending.isSelected()) {
            this.PSW_chkTracePending.setSelected(bNewState);
            this.PSW_chkTracePending.setForeground(this._oRed);
        } else {
            this.PSW_chkTracePending.setForeground(this._oBlack);
        }
        boolean bl2 = bNewState = (iPSW >> 5 & 1) == 1;
        if (bNewState != this.PSW_chkIV.isSelected()) {
            this.PSW_chkIV.setSelected(bNewState);
            this.PSW_chkIV.setForeground(this._oRed);
        } else {
            this.PSW_chkIV.setForeground(this._oBlack);
        }
        boolean bl3 = bNewState = (iPSW >> 4 & 1) == 1;
        if (bNewState != this.PSW_chkTraceBit.isSelected()) {
            this.PSW_chkTraceBit.setSelected(bNewState);
            this.PSW_chkTraceBit.setForeground(this._oRed);
        } else {
            this.PSW_chkTraceBit.setForeground(this._oBlack);
        }
        boolean bl4 = bNewState = (iPSW >> 3 & 1) == 1;
        if (bNewState != this.PSW_chkNegative.isSelected()) {
            this.PSW_chkNegative.setSelected(bNewState);
            this.PSW_chkNegative.setForeground(this._oRed);
        } else {
            this.PSW_chkNegative.setForeground(this._oBlack);
        }
        boolean bl5 = bNewState = (iPSW >> 2 & 1) == 1;
        if (bNewState != this.PSW_chkZero.isSelected()) {
            this.PSW_chkZero.setSelected(bNewState);
            this.PSW_chkZero.setForeground(this._oRed);
        } else {
            this.PSW_chkZero.setForeground(this._oBlack);
        }
        boolean bl6 = bNewState = (iPSW >> 1 & 1) == 1;
        if (bNewState != this.PSW_chkOverflow.isSelected()) {
            this.PSW_chkOverflow.setSelected(bNewState);
            this.PSW_chkOverflow.setForeground(this._oRed);
        } else {
            this.PSW_chkOverflow.setForeground(this._oBlack);
        }
        boolean bl7 = bNewState = (iPSW & 1) == 1;
        if (bNewState != this.PSW_chkCarry.isSelected()) {
            this.PSW_chkCarry.setSelected(bNewState);
            this.PSW_chkCarry.setForeground(this._oRed);
        } else {
            this.PSW_chkCarry.setForeground(this._oBlack);
        }
        this._inUpdate = false;
    }

    private void changePSW() {
        if (this._inUpdate) {
            return;
        }
        int iNewPSW = 0;
        int n = Integer.parseInt(this.PSW_txtIPL.getText());
        if (n >= 0 && n < 32) {
            iNewPSW += (n <<= 16);
        }
        if ((n = this.PSW_cmbBEN_MAPEN.getSelectedIndex()) >= 0 && n < 2) {
            iNewPSW += (n <<= 26);
        }
        if ((n = this.PSW_cmbCM.getSelectedIndex()) >= 0 && n < 4) {
            iNewPSW += (n <<= 24);
        }
        if ((n = this.PSW_cmbPM.getSelectedIndex()) >= 0 && n < 4) {
            iNewPSW += (n <<= 22);
        }
        n = this.PSW_chkTracePending.isSelected() ? 1 : 0;
        iNewPSW += (n <<= 31);
        n = this.PSW_chkIV.isSelected() ? 1 : 0;
        iNewPSW += (n <<= 5);
        n = this.PSW_chkTraceBit.isSelected() ? 1 : 0;
        iNewPSW += (n <<= 4);
        n = this.PSW_chkNegative.isSelected() ? 1 : 0;
        iNewPSW += (n <<= 3);
        n = this.PSW_chkZero.isSelected() ? 1 : 0;
        iNewPSW += (n <<= 2);
        n = this.PSW_chkOverflow.isSelected() ? 1 : 0;
        iNewPSW += (n <<= 1);
        n = this.PSW_chkCarry.isSelected() ? 1 : 0;
        this._oSimulator.setPSW(iNewPSW += n);
    }

    public void setLongLabels() {
        this.PSW_chkTracePending.setText("TracePending");
        this.PSW_chkTraceBit.setText("TraceBit");
        this.PSW_chkNegative.setText("Negative");
        this.PSW_chkZero.setText("Zero");
        this.PSW_chkOverflow.setText("Overflow");
        this.PSW_chkCarry.setText("Carry");
        this.PSW_chkIV.setText("IntegerOverflowTrap");
        this.PSW_lblCM.setText("CurrentMode");
        this.PSW_lplPM.setText("PreviousMode");
        this.PSW_lblBEN_MAPEN.setText("Addressing");
        this.PSW_lblIPL.setText("InterruptPriorityLevel");
        this.pack();
    }

    public void setShortLabels() {
        this.PSW_chkTracePending.setText("TP");
        this.PSW_chkTraceBit.setText("TB");
        this.PSW_chkNegative.setText("N");
        this.PSW_chkZero.setText("Z");
        this.PSW_chkOverflow.setText("O");
        this.PSW_chkCarry.setText("C");
        this.PSW_chkIV.setText("IV");
        this.PSW_lblCM.setText("CM");
        this.PSW_lplPM.setText("PM");
        this.PSW_lblBEN_MAPEN.setText("BEN_MAPEN");
        this.PSW_lblIPL.setText("IPL");
        this.pack();
    }

    public void initComponents() {
        String[] valuesCM = new String[]{"0-System", "1-undef", "2-undef", "3-User"};
        String[] valuesPM = new String[]{"0-System", "1-undef", "2-undef", "3-User"};
        String[] valuesBEN_MAPEN = new String[]{"0-direct", "1-Page"};
        this.PSW_chkShort = new JCheckBox();
        this.PSW_chkTracePending = new JCheckBox();
        this.PSW_chkTraceBit = new JCheckBox();
        this.PSW_chkNegative = new JCheckBox();
        this.PSW_chkZero = new JCheckBox();
        this.PSW_chkOverflow = new JCheckBox();
        this.PSW_chkCarry = new JCheckBox();
        this.PSW_chkIV = new JCheckBox();
        this.PSW_lblCM = new JLabel();
        this.PSW_cmbCM = new JComboBox<String>(valuesCM);
        this.PSW_lplPM = new JLabel();
        this.PSW_cmbPM = new JComboBox<String>(valuesPM);
        this.PSW_lblBEN_MAPEN = new JLabel();
        this.PSW_cmbBEN_MAPEN = new JComboBox<String>(valuesBEN_MAPEN);
        this.PSW_lblIPL = new JLabel();
        this.PSW_txtIPL = new JTextField();
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), 0));
        this.setName("MIPSW_View");
        this.PSW_chkShort.setSelected(true);
        this.PSW_chkShort.setText("short labels");
        this.PSW_chkShort.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_chkShortItemStateChanged(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkShort);
        this.PSW_chkTracePending.setBackground(new Color(204, 204, 204));
        this.PSW_chkTracePending.setName("PSW_chkTracePending");
        this.PSW_chkTracePending.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkTracePending.setForeground(Color.black);
        this.PSW_chkTracePending.setText("TracePending");
        this.PSW_chkTracePending.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkTracePending);
        this.PSW_chkTraceBit.setBackground(new Color(204, 204, 204));
        this.PSW_chkTraceBit.setName("PSW_chkTraceBit");
        this.PSW_chkTraceBit.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkTraceBit.setForeground(Color.black);
        this.PSW_chkTraceBit.setText("TraceBit");
        this.PSW_chkTraceBit.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkTraceBit);
        this.PSW_chkNegative.setBackground(new Color(204, 204, 204));
        this.PSW_chkNegative.setName("PSV_chkNegativ");
        this.PSW_chkNegative.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkNegative.setForeground(Color.black);
        this.PSW_chkNegative.setText("Negative");
        this.PSW_chkNegative.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkNegative);
        this.PSW_chkZero.setBackground(new Color(204, 204, 204));
        this.PSW_chkZero.setName("PSW_chkZero");
        this.PSW_chkZero.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkZero.setForeground(Color.black);
        this.PSW_chkZero.setText("Zero");
        this.PSW_chkZero.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkZero);
        this.PSW_chkOverflow.setBackground(new Color(204, 204, 204));
        this.PSW_chkOverflow.setName("PSW_chkOverflow");
        this.PSW_chkOverflow.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkOverflow.setForeground(Color.black);
        this.PSW_chkOverflow.setText("Overflow");
        this.PSW_chkOverflow.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkOverflow);
        this.PSW_chkCarry.setBackground(new Color(204, 204, 204));
        this.PSW_chkCarry.setName("PSW_chkCarry");
        this.PSW_chkCarry.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkCarry.setForeground(Color.black);
        this.PSW_chkCarry.setText("Carry");
        this.PSW_chkCarry.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkCarry);
        this.PSW_chkIV.setBackground(new Color(204, 204, 204));
        this.PSW_chkIV.setName("PSW_chkIV");
        this.PSW_chkIV.setFont(new Font("Dialog", 0, 11));
        this.PSW_chkIV.setForeground(Color.black);
        this.PSW_chkIV.setText("IntegerOverflowTrap disable");
        this.PSW_chkIV.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_chkIV);
        this.PSW_lblCM.setFont(new Font("Dialog", 0, 11));
        this.PSW_lblCM.setName("lblCM");
        this.PSW_lblCM.setBackground(new Color(204, 204, 204));
        this.PSW_lblCM.setForeground(Color.black);
        this.PSW_lblCM.setText("CurrentMode");
        this.getContentPane().add(this.PSW_lblCM);
        this.PSW_cmbCM.setFont(new Font("Dialog", 0, 11));
        this.PSW_cmbCM.setName("CurrentMode");
        this.PSW_cmbCM.setBackground(new Color(204, 204, 204));
        this.PSW_cmbCM.setForeground(Color.black);
        this.PSW_cmbCM.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_cmbCM);
        this.PSW_lplPM.setFont(new Font("Dialog", 0, 11));
        this.PSW_lplPM.setName("lblPM");
        this.PSW_lplPM.setBackground(new Color(204, 204, 204));
        this.PSW_lplPM.setForeground(Color.black);
        this.PSW_lplPM.setText("PreviousMode");
        this.getContentPane().add(this.PSW_lplPM);
        this.PSW_cmbPM.setFont(new Font("Dialog", 0, 11));
        this.PSW_cmbPM.setName("PreviousMode");
        this.PSW_cmbPM.setBackground(new Color(204, 204, 204));
        this.PSW_cmbPM.setForeground(Color.black);
        this.PSW_cmbPM.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_cmbPM);
        this.PSW_lblBEN_MAPEN.setFont(new Font("Dialog", 0, 11));
        this.PSW_lblBEN_MAPEN.setName("lblBEN_MAPEN");
        this.PSW_lblBEN_MAPEN.setBackground(new Color(204, 204, 204));
        this.PSW_lblBEN_MAPEN.setForeground(Color.black);
        this.PSW_lblBEN_MAPEN.setText("Addressing");
        this.getContentPane().add(this.PSW_lblBEN_MAPEN);
        this.PSW_cmbBEN_MAPEN.setFont(new Font("Dialog", 0, 11));
        this.PSW_cmbBEN_MAPEN.setName("cmbBEN_MAPEN");
        this.PSW_cmbBEN_MAPEN.setBackground(new Color(204, 204, 204));
        this.PSW_cmbBEN_MAPEN.setForeground(Color.black);
        this.PSW_cmbBEN_MAPEN.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MIPSW_View.this.PSW_changed(evt);
            }
        });
        this.getContentPane().add(this.PSW_cmbBEN_MAPEN);
        this.PSW_lblIPL.setFont(new Font("Dialog", 0, 11));
        this.PSW_lblIPL.setName("lblIPL");
        this.PSW_lblIPL.setBackground(new Color(204, 204, 204));
        this.PSW_lblIPL.setForeground(Color.black);
        this.PSW_lblIPL.setText("InterruptPriorityLevel");
        this.getContentPane().add(this.PSW_lblIPL);
        this.PSW_txtIPL.setBackground(new Color(255, 247, 233));
        this.PSW_txtIPL.setName("txtIPL");
        this.PSW_txtIPL.setFont(new Font("Dialog", 0, 11));
        this.PSW_txtIPL.setForeground(Color.black);
        this.PSW_txtIPL.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIPSW_View.this.PSW_txtIPLActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.PSW_txtIPL);
        super.initComponents();
        this.pack();
    }

    private void PSW_txtIPLActionPerformed(ActionEvent evt) {
        this.changePSW();
    }

    private void PSW_changed(ItemEvent evt) {
        this.changePSW();
    }

    private void PSW_chkShortItemStateChanged(ItemEvent evt) {
        if (this.PSW_chkShort.isSelected()) {
            this.setShortLabels();
        } else {
            this.setLongLabels();
        }
    }
}

