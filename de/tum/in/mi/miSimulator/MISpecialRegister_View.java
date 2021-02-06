/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi.miSimulator;

import de.tum.in.mi.MISimulator;
import de.tum.in.mi.miSimulator.MIController;
import de.tum.in.mi.miSimulator.MI_View;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MISpecialRegister_View
extends MI_View {
    private boolean _bInUpdate = true;
    private String[] _aOldRegister;
    private JTextField[] _aControls;
    private Color _oRed = Color.red;
    private Color _oBlack = Color.black;
    private JLabel lblP0B;
    private JLabel lblP0L;
    private JLabel lblP1B;
    private JLabel lblP1L;
    private JLabel lblSB;
    private JLabel lblSL;
    private JLabel lblSCBADR;
    private JLabel lblPCBADR;
    private JLabel lblIPL;
    private JTextField txtP0B;
    private JTextField txtP0L;
    private JTextField txtP1B;
    private JTextField txtP1L;
    private JTextField txtSB;
    private JTextField txtSL;
    private JTextField txtSCBADR;
    private JTextField txtPCBADR;
    private JTextField txtIPL;

    public MISpecialRegister_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this._aOldRegister = new String[9];
        this._aControls = new JTextField[9];
        this.setTitle("Specialregister");
        this.setName("Specialregister");
        this.initComponents();
        this._bInUpdate = false;
    }

    public void init() {
        super.init();
        this._aControls[0] = this.txtP0B;
        this._aControls[1] = this.txtP0L;
        this._aControls[2] = this.txtP1B;
        this._aControls[3] = this.txtP1L;
        this._aControls[4] = this.txtSB;
        this._aControls[5] = this.txtSL;
        this._aControls[6] = this.txtSCBADR;
        this._aControls[7] = this.txtPCBADR;
        this._aControls[8] = this.txtIPL;
    }

    public void update() {
        this._bInUpdate = true;
        String[] aRegister = this._oSimulator.getAllRegister();
        for (int i = 0; i < 9; ++i) {
            if (!aRegister[i + 17].equals(this._aOldRegister[i])) {
                this._aControls[i].setText(aRegister[i + 17]);
                this._aControls[i].setForeground(this._oRed);
                this._aOldRegister[i] = aRegister[i + 17];
                continue;
            }
            if (this._aControls[i].getForeground() != this._oRed) continue;
            this._aControls[i].setForeground(this._oBlack);
        }
        this._bInUpdate = false;
    }

    public void initComponents() {
        this.lblP0B = new JLabel();
        this.lblP0L = new JLabel();
        this.lblP1B = new JLabel();
        this.lblP1L = new JLabel();
        this.lblSB = new JLabel();
        this.lblSL = new JLabel();
        this.lblSCBADR = new JLabel();
        this.lblPCBADR = new JLabel();
        this.lblIPL = new JLabel();
        this.txtP0B = new JTextField();
        this.txtP0L = new JTextField();
        this.txtP1B = new JTextField();
        this.txtP1L = new JTextField();
        this.txtSB = new JTextField();
        this.txtSL = new JTextField();
        this.txtSCBADR = new JTextField();
        this.txtPCBADR = new JTextField();
        this.txtIPL = new JTextField();
        this.getContentPane().setLayout(new GridLayout(2, 9));
        this.lblP0B.setFont(new Font("Dialog", 0, 11));
        this.lblP0B.setName("lblP0B");
        this.lblP0B.setBackground(new Color(204, 204, 204));
        this.lblP0B.setForeground(Color.black);
        this.lblP0B.setText("P0B");
        this.lblP0B.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblP0B);
        this.lblP0L.setFont(new Font("Dialog", 0, 11));
        this.lblP0L.setName("lblP0L");
        this.lblP0L.setBackground(new Color(204, 204, 204));
        this.lblP0L.setForeground(Color.black);
        this.lblP0L.setText("P0L");
        this.lblP0L.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblP0L);
        this.lblP1B.setFont(new Font("Dialog", 0, 11));
        this.lblP1B.setName("lblP1B");
        this.lblP1B.setBackground(new Color(204, 204, 204));
        this.lblP1B.setForeground(Color.black);
        this.lblP1B.setText("P1B");
        this.lblP1B.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblP1B);
        this.lblP1L.setFont(new Font("Dialog", 0, 11));
        this.lblP1L.setName("lblP1L");
        this.lblP1L.setBackground(new Color(204, 204, 204));
        this.lblP1L.setForeground(Color.black);
        this.lblP1L.setText("P1L");
        this.lblP1L.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblP1L);
        this.lblSB.setFont(new Font("Dialog", 0, 11));
        this.lblSB.setName("lblSB");
        this.lblSB.setBackground(new Color(204, 204, 204));
        this.lblSB.setForeground(Color.black);
        this.lblSB.setText("SB");
        this.lblSB.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblSB);
        this.lblSL.setFont(new Font("Dialog", 0, 11));
        this.lblSL.setName("lblSL");
        this.lblSL.setBackground(new Color(204, 204, 204));
        this.lblSL.setForeground(Color.black);
        this.lblSL.setText("SL");
        this.lblSL.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblSL);
        this.lblSCBADR.setFont(new Font("Dialog", 0, 11));
        this.lblSCBADR.setName("lblSCBADR");
        this.lblSCBADR.setBackground(new Color(204, 204, 204));
        this.lblSCBADR.setForeground(Color.black);
        this.lblSCBADR.setText("SCBADR");
        this.lblSCBADR.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblSCBADR);
        this.lblPCBADR.setFont(new Font("Dialog", 0, 11));
        this.lblPCBADR.setName("lblPCBADR");
        this.lblPCBADR.setBackground(new Color(204, 204, 204));
        this.lblPCBADR.setForeground(Color.black);
        this.lblPCBADR.setText("PCBADR");
        this.lblPCBADR.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblPCBADR);
        this.lblIPL.setFont(new Font("Dialog", 0, 11));
        this.lblIPL.setName("lblIPL");
        this.lblIPL.setBackground(new Color(204, 204, 204));
        this.lblIPL.setForeground(Color.black);
        this.lblIPL.setText("IPL");
        this.lblIPL.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblIPL);
        this.txtP0B.setText("x");
        this.txtP0B.setBackground(new Color(255, 247, 233));
        this.txtP0B.setName("txtP0B");
        this.txtP0B.setFont(new Font("Courier", 0, 12));
        this.txtP0B.setForeground(Color.black);
        this.txtP0B.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtP0BActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtP0B);
        this.txtP0L.setText("x");
        this.txtP0L.setBackground(new Color(255, 247, 233));
        this.txtP0L.setName("txtP0L");
        this.txtP0L.setFont(new Font("Courier", 0, 12));
        this.txtP0L.setForeground(Color.black);
        this.txtP0L.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtP0LActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtP0L);
        this.txtP1B.setText("x");
        this.txtP1B.setBackground(new Color(255, 247, 233));
        this.txtP1B.setName("txtP1B");
        this.txtP1B.setFont(new Font("Courier", 0, 12));
        this.txtP1B.setForeground(Color.black);
        this.txtP1B.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtP1BActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtP1B);
        this.txtP1L.setText("x");
        this.txtP1L.setBackground(new Color(255, 247, 233));
        this.txtP1L.setName("txtP1L");
        this.txtP1L.setFont(new Font("Courier", 0, 12));
        this.txtP1L.setForeground(Color.black);
        this.txtP1L.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtP1LActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtP1L);
        this.txtSB.setText("x");
        this.txtSB.setBackground(new Color(255, 247, 233));
        this.txtSB.setName("txtSB");
        this.txtSB.setFont(new Font("Courier", 0, 12));
        this.txtSB.setForeground(Color.black);
        this.txtSB.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtSBActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtSB);
        this.txtSL.setText("x");
        this.txtSL.setBackground(new Color(255, 247, 233));
        this.txtSL.setName("txtSL");
        this.txtSL.setFont(new Font("Courier", 0, 12));
        this.txtSL.setForeground(Color.black);
        this.txtSL.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtSLActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtSL);
        this.txtSCBADR.setText("x");
        this.txtSCBADR.setBackground(new Color(255, 247, 233));
        this.txtSCBADR.setName("txtSCBADR");
        this.txtSCBADR.setFont(new Font("Courier", 0, 12));
        this.txtSCBADR.setForeground(Color.black);
        this.txtSCBADR.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtSCBADRActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtSCBADR);
        this.txtPCBADR.setText("x");
        this.txtPCBADR.setBackground(new Color(255, 247, 233));
        this.txtPCBADR.setName("txtPCBADR");
        this.txtPCBADR.setFont(new Font("Courier", 0, 12));
        this.txtPCBADR.setForeground(Color.black);
        this.txtPCBADR.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtPCBADRActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtPCBADR);
        this.txtIPL.setText("x");
        this.txtIPL.setBackground(new Color(255, 247, 233));
        this.txtIPL.setName("txtIPL");
        this.txtIPL.setFont(new Font("Courier", 0, 12));
        this.txtIPL.setForeground(Color.black);
        this.txtIPL.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MISpecialRegister_View.this.txtIPLActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtIPL);
        super.initComponents();
    }

    private void txtPCBADRActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("PCBADR", this.txtPCBADR.getText());
        }
    }

    private void txtSCBADRActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("SCBADR", this.txtSCBADR.getText());
        }
    }

    private void txtIPLActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("IPL", this.txtIPL.getText());
        }
    }

    private void txtSLActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("SL", this.txtSL.getText());
        }
    }

    private void txtSBActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("SB", this.txtSB.getText());
        }
    }

    private void txtP1LActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("P1L", this.txtP1L.getText());
        }
    }

    private void txtP1BActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("P1B", this.txtP1B.getText());
        }
    }

    private void txtP0LActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("P0L", this.txtP0L.getText());
        }
    }

    private void txtP0BActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("P0B", this.txtP0B.getText());
        }
    }
}

