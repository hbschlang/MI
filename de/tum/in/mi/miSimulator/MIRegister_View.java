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

public class MIRegister_View
extends MI_View {
    private boolean _bInUpdate = true;
    private String[] _aOldRegister;
    private int _iOldDezimalState;
    private JTextField[] _aControls;
    private Color _oRed = Color.red;
    private Color _oBlack = Color.black;
    private JLabel lblR0;
    private JLabel lblR1;
    private JLabel lblR2;
    private JLabel lblR3;
    private JLabel lblR4;
    private JLabel lblR5;
    private JLabel lblR6;
    private JLabel lblR7;
    private JTextField txtR0;
    private JTextField txtR1;
    private JTextField txtR2;
    private JTextField txtR3;
    private JTextField txtR4;
    private JTextField txtR5;
    private JTextField txtR6;
    private JTextField txtR7;
    private JLabel lblR8;
    private JLabel lblR9;
    private JLabel lblR10;
    private JLabel lblR11;
    private JLabel lblR12;
    private JLabel lblR13;
    private JLabel lblR14;
    private JLabel lblR15;
    private JTextField txtR8;
    private JTextField txtR9;
    private JTextField txtR10;
    private JTextField txtR11;
    private JTextField txtR12;
    private JTextField txtR13;
    private JTextField txtR14;
    private JTextField txtR15;

    public MIRegister_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this._aOldRegister = new String[16];
        this._aControls = new JTextField[16];
        this.setTitle("Register");
        this.setName("Register");
        this.initComponents();
        for (int i = 0; i < 16; ++i) {
            this._aOldRegister[i] = "0";
        }
        this._bInUpdate = false;
    }

    public void init() {
        super.init();
        this._aControls[0] = this.txtR0;
        this._aControls[1] = this.txtR1;
        this._aControls[2] = this.txtR2;
        this._aControls[3] = this.txtR3;
        this._aControls[4] = this.txtR4;
        this._aControls[5] = this.txtR5;
        this._aControls[6] = this.txtR6;
        this._aControls[7] = this.txtR7;
        this._aControls[8] = this.txtR8;
        this._aControls[9] = this.txtR9;
        this._aControls[10] = this.txtR10;
        this._aControls[11] = this.txtR11;
        this._aControls[12] = this.txtR12;
        this._aControls[13] = this.txtR13;
        this._aControls[14] = this.txtR14;
        this._aControls[15] = this.txtR15;
    }

    public void update() {
        String cAnzeige = "x";
        this._bInUpdate = true;
        int iDezimalState = this._oController.getDezimalStateMI();
        String[] aRegister = this._oSimulator.getAllRegister();
        for (int i = 0; i < 16; ++i) {
            if (!aRegister[i].equals(this._aOldRegister[i]) || this._iOldDezimalState != iDezimalState) {
                switch (iDezimalState) {
                    case 0: {
                        cAnzeige = "x";
                        break;
                    }
                    case 1: {
                        cAnzeige = aRegister[i];
                        break;
                    }
                    case 2: {
                        int iHelp = Integer.parseInt(aRegister[i], 16);
                        cAnzeige = Integer.toBinaryString(iHelp);
                        break;
                    }
                    case 3: {
                        int iHelp = Integer.parseInt(aRegister[i], 16);
                        cAnzeige = Integer.toString(iHelp);
                    }
                }
                this._aControls[i].setText(cAnzeige);
                this._aControls[i].setForeground(this._oRed);
                this._aOldRegister[i] = aRegister[i];
                continue;
            }
            if (this._aControls[i].getForeground() != this._oRed) continue;
            this._aControls[i].setForeground(this._oBlack);
        }
        this._iOldDezimalState = iDezimalState;
        this._bInUpdate = false;
    }

    public void initComponents() {
        this.lblR0 = new JLabel();
        this.lblR1 = new JLabel();
        this.lblR2 = new JLabel();
        this.lblR3 = new JLabel();
        this.lblR4 = new JLabel();
        this.lblR5 = new JLabel();
        this.lblR6 = new JLabel();
        this.lblR7 = new JLabel();
        this.txtR0 = new JTextField();
        this.txtR1 = new JTextField();
        this.txtR2 = new JTextField();
        this.txtR3 = new JTextField();
        this.txtR4 = new JTextField();
        this.txtR5 = new JTextField();
        this.txtR6 = new JTextField();
        this.txtR7 = new JTextField();
        this.lblR8 = new JLabel();
        this.lblR9 = new JLabel();
        this.lblR10 = new JLabel();
        this.lblR11 = new JLabel();
        this.lblR12 = new JLabel();
        this.lblR13 = new JLabel();
        this.lblR14 = new JLabel();
        this.lblR15 = new JLabel();
        this.txtR8 = new JTextField();
        this.txtR9 = new JTextField();
        this.txtR10 = new JTextField();
        this.txtR11 = new JTextField();
        this.txtR12 = new JTextField();
        this.txtR13 = new JTextField();
        this.txtR14 = new JTextField();
        this.txtR15 = new JTextField();
        this.getContentPane().setLayout(new GridLayout(4, 8));
        this.lblR0.setFont(new Font("Dialog", 0, 11));
        this.lblR0.setName("lblR0");
        this.lblR0.setBackground(new Color(204, 204, 204));
        this.lblR0.setForeground(Color.black);
        this.lblR0.setText("R0");
        this.lblR0.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR0);
        this.lblR1.setFont(new Font("Dialog", 0, 11));
        this.lblR1.setName("lblR1");
        this.lblR1.setBackground(new Color(204, 204, 204));
        this.lblR1.setForeground(Color.black);
        this.lblR1.setText("R1");
        this.lblR1.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR1);
        this.lblR2.setFont(new Font("Dialog", 0, 11));
        this.lblR2.setName("lblR2");
        this.lblR2.setBackground(new Color(204, 204, 204));
        this.lblR2.setForeground(Color.black);
        this.lblR2.setText("R2");
        this.lblR2.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR2);
        this.lblR3.setFont(new Font("Dialog", 0, 11));
        this.lblR3.setName("lblR3");
        this.lblR3.setBackground(new Color(204, 204, 204));
        this.lblR3.setForeground(Color.black);
        this.lblR3.setText("R3");
        this.lblR3.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR3);
        this.lblR4.setFont(new Font("Dialog", 0, 11));
        this.lblR4.setName("lblR4");
        this.lblR4.setBackground(new Color(204, 204, 204));
        this.lblR4.setForeground(Color.black);
        this.lblR4.setText("R4");
        this.lblR4.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR4);
        this.lblR5.setFont(new Font("Dialog", 0, 11));
        this.lblR5.setName("lblR5");
        this.lblR5.setBackground(new Color(204, 204, 204));
        this.lblR5.setForeground(Color.black);
        this.lblR5.setText("R5");
        this.lblR5.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR5);
        this.lblR6.setFont(new Font("Dialog", 0, 11));
        this.lblR6.setName("lblR6");
        this.lblR6.setBackground(new Color(204, 204, 204));
        this.lblR6.setForeground(Color.black);
        this.lblR6.setText("R6");
        this.lblR6.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR6);
        this.lblR7.setFont(new Font("Dialog", 0, 11));
        this.lblR7.setName("lblR7");
        this.lblR7.setBackground(new Color(204, 204, 204));
        this.lblR7.setForeground(Color.black);
        this.lblR7.setText("R7");
        this.lblR7.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR7);
        this.txtR0.setText("x");
        this.txtR0.setBackground(new Color(255, 247, 233));
        this.txtR0.setName("txtR0");
        this.txtR0.setFont(new Font("Courier", 0, 12));
        this.txtR0.setForeground(Color.black);
        this.txtR0.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR0ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR0);
        this.txtR1.setText("x");
        this.txtR1.setBackground(new Color(255, 247, 233));
        this.txtR1.setName("txtR1");
        this.txtR1.setFont(new Font("Courier", 0, 12));
        this.txtR1.setForeground(Color.black);
        this.txtR1.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR1ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR1);
        this.txtR2.setText("x");
        this.txtR2.setBackground(new Color(255, 247, 233));
        this.txtR2.setName("txtR2");
        this.txtR2.setFont(new Font("Courier", 0, 12));
        this.txtR2.setForeground(Color.black);
        this.txtR2.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR2ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR2);
        this.txtR3.setText("x");
        this.txtR3.setBackground(new Color(255, 247, 233));
        this.txtR3.setName("txtR3");
        this.txtR3.setFont(new Font("Courier", 0, 12));
        this.txtR3.setForeground(Color.black);
        this.txtR3.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR3ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR3);
        this.txtR4.setText("x");
        this.txtR4.setBackground(new Color(255, 247, 233));
        this.txtR4.setName("txtR4");
        this.txtR4.setFont(new Font("Courier", 0, 12));
        this.txtR4.setForeground(Color.black);
        this.txtR4.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR4ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR4);
        this.txtR5.setText("x");
        this.txtR5.setBackground(new Color(255, 247, 233));
        this.txtR5.setName("txtR5");
        this.txtR5.setFont(new Font("Courier", 0, 12));
        this.txtR5.setForeground(Color.black);
        this.txtR5.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR5ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR5);
        this.txtR6.setText("x");
        this.txtR6.setBackground(new Color(255, 247, 233));
        this.txtR6.setName("txtR6");
        this.txtR6.setFont(new Font("Courier", 0, 12));
        this.txtR6.setForeground(Color.black);
        this.txtR6.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR6ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR6);
        this.txtR7.setText("x");
        this.txtR7.setBackground(new Color(255, 247, 233));
        this.txtR7.setName("txtR7");
        this.txtR7.setFont(new Font("Courier", 0, 12));
        this.txtR7.setForeground(Color.black);
        this.txtR7.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR7ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR7);
        this.lblR8.setFont(new Font("Dialog", 0, 11));
        this.lblR8.setName("lblR8");
        this.lblR8.setBackground(new Color(204, 204, 204));
        this.lblR8.setForeground(Color.black);
        this.lblR8.setText("R8");
        this.lblR8.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR8);
        this.lblR9.setFont(new Font("Dialog", 0, 11));
        this.lblR9.setName("lblR9");
        this.lblR9.setBackground(new Color(204, 204, 204));
        this.lblR9.setForeground(Color.black);
        this.lblR9.setText("R9");
        this.lblR9.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR9);
        this.lblR10.setFont(new Font("Dialog", 0, 11));
        this.lblR10.setName("lblR10");
        this.lblR10.setBackground(new Color(204, 204, 204));
        this.lblR10.setForeground(Color.black);
        this.lblR10.setText("R10");
        this.lblR10.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR10);
        this.lblR11.setFont(new Font("Dialog", 0, 11));
        this.lblR11.setName("lblR11");
        this.lblR11.setBackground(new Color(204, 204, 204));
        this.lblR11.setForeground(Color.black);
        this.lblR11.setText("R11");
        this.lblR11.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR11);
        this.lblR12.setFont(new Font("Dialog", 0, 11));
        this.lblR12.setName("lblR12");
        this.lblR12.setBackground(new Color(204, 204, 204));
        this.lblR12.setForeground(Color.black);
        this.lblR12.setText("R12");
        this.lblR12.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR12);
        this.lblR13.setFont(new Font("Dialog", 0, 11));
        this.lblR13.setName("lblR13");
        this.lblR13.setBackground(new Color(204, 204, 204));
        this.lblR13.setForeground(Color.black);
        this.lblR13.setText("R13");
        this.lblR13.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR13);
        this.lblR14.setFont(new Font("Dialog", 0, 11));
        this.lblR14.setName("lblR14");
        this.lblR14.setBackground(new Color(204, 204, 204));
        this.lblR14.setForeground(Color.black);
        this.lblR14.setText("R14 (SP)");
        this.lblR14.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR14);
        this.lblR15.setFont(new Font("Dialog", 0, 11));
        this.lblR15.setName("lblR15");
        this.lblR15.setBackground(new Color(204, 204, 204));
        this.lblR15.setForeground(Color.black);
        this.lblR15.setText(" R15 (PC)");
        this.lblR15.setAlignmentX(0.0f);
        this.getContentPane().add(this.lblR15);
        this.txtR8.setText("x");
        this.txtR8.setBackground(new Color(255, 247, 233));
        this.txtR8.setName("txtR8");
        this.txtR8.setFont(new Font("Courier", 0, 12));
        this.txtR8.setForeground(Color.black);
        this.txtR8.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR8ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR8);
        this.txtR9.setText("x");
        this.txtR9.setBackground(new Color(255, 247, 233));
        this.txtR9.setName("txtR9");
        this.txtR9.setFont(new Font("Courier", 0, 12));
        this.txtR9.setForeground(Color.black);
        this.txtR9.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR9ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR9);
        this.txtR10.setText("x");
        this.txtR10.setBackground(new Color(255, 247, 233));
        this.txtR10.setName("txtR10");
        this.txtR10.setFont(new Font("Courier", 0, 12));
        this.txtR10.setForeground(Color.black);
        this.txtR10.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR10ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR10);
        this.txtR11.setText("x");
        this.txtR11.setBackground(new Color(255, 247, 233));
        this.txtR11.setName("txtR11");
        this.txtR11.setFont(new Font("Courier", 0, 12));
        this.txtR11.setForeground(Color.black);
        this.txtR11.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR11ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR11);
        this.txtR12.setText("x");
        this.txtR12.setBackground(new Color(255, 247, 233));
        this.txtR12.setName("txtR12");
        this.txtR12.setFont(new Font("Courier", 0, 12));
        this.txtR12.setForeground(Color.black);
        this.txtR12.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR12ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR12);
        this.txtR13.setText("x");
        this.txtR13.setBackground(new Color(255, 247, 233));
        this.txtR13.setName("txtR13");
        this.txtR13.setFont(new Font("Courier", 0, 12));
        this.txtR13.setForeground(Color.black);
        this.txtR13.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR13ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR13);
        this.txtR14.setText("x");
        this.txtR14.setBackground(new Color(255, 247, 233));
        this.txtR14.setName("txtR14");
        this.txtR14.setFont(new Font("Courier", 0, 12));
        this.txtR14.setForeground(Color.black);
        this.txtR14.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR14ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR14);
        this.txtR15.setText("x");
        this.txtR15.setBackground(new Color(255, 247, 233));
        this.txtR15.setName("txtR15");
        this.txtR15.setFont(new Font("Courier", 0, 12));
        this.txtR15.setForeground(Color.black);
        this.txtR15.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIRegister_View.this.txtR15ActionPerformed(evt);
            }
        });
        this.getContentPane().add(this.txtR15);
        super.initComponents();
    }

    private void txtR15ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R15", this.txtR15.getText());
        }
    }

    private void txtR14ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R14", this.txtR14.getText());
        }
    }

    private void txtR13ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R13", this.txtR13.getText());
        }
    }

    private void txtR12ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R12", this.txtR12.getText());
        }
    }

    private void txtR11ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R11", this.txtR11.getText());
        }
    }

    private void txtR10ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R10", this.txtR10.getText());
        }
    }

    private void txtR9ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R9", this.txtR9.getText());
        }
    }

    private void txtR8ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R8", this.txtR8.getText());
        }
    }

    private void txtR7ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R7", this.txtR7.getText());
        }
    }

    private void txtR6ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R6", this.txtR6.getText());
        }
    }

    private void txtR5ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R5", this.txtR5.getText());
        }
    }

    private void txtR4ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R4", this.txtR4.getText());
        }
    }

    private void txtR3ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R3", this.txtR3.getText());
        }
    }

    private void txtR2ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R2", this.txtR2.getText());
        }
    }

    private void txtR1ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R1", this.txtR1.getText());
        }
    }

    private void txtR0ActionPerformed(ActionEvent evt) {
        if (!this._bInUpdate) {
            this._oSimulator.setRegister("R0", this.txtR0.getText());
        }
    }
}

