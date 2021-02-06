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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MIData_View
extends MI_View {
    int _iStartAddress;
    int _iEndAddress;
    int offset;
    int length;
    int _iColumns;
    byte[] speicher;
    String[] _sLines;
    private DefaultListModel lstMIDataModel;
    private JList lstMIData;
    private JPanel panel1;
    private JLabel lblStartAddress;
    private JTextField txtStartAddress;
    private JLabel lblEndAddress;
    private JTextField txtEndAddress;
    private JButton pshNewWindow;
    static final int MAX_SPEICHER = 256;
    static int nDataViewCount = 1;

    public MIData_View(MIController oController, MISimulator oSimulator) {
        super(oController, oSimulator);
        this.setTitle("Memory");
        this.setName("Memory");
        this.initComponents();
    }

    public void init() {
        this._iColumns = 8;
        this._iStartAddress = 0;
        this._iEndAddress = 256;
    }

    public void update() {
        this.offset = this._iStartAddress;
        this.length = this._iEndAddress - this._iStartAddress + 1;
        if (this.length > 256) {
            this.length = 256;
            this._iEndAddress = this._iStartAddress + this.length - 1;
            this.txtEndAddress.setText(this.formatAddress(this._iEndAddress));
        }
        if (this.length <= 0) {
            this.length = 1;
            this._iEndAddress = this._iStartAddress;
            this.txtEndAddress.setText(this.formatAddress(this._iEndAddress));
        }
        this.speicher = this._oSimulator.getSpeicher(this.offset, this.length);
        if (this.lstMIDataModel.getSize() > 0) {
            int iAktZeile = this.lstMIData.getSelectedIndex();
            for (int i = 0; i < (this.length - 1) / this._iColumns + 1; ++i) {
                String cZeile = this.formatLine(i * this._iColumns);
                if (cZeile.equals(this.lstMIDataModel.getElementAt(i))) continue;
                this.lstMIDataModel.removeElementAt(i);
                this.lstMIDataModel.insertElementAt(cZeile, i);
            }
            this.lstMIData.setSelectedIndex(iAktZeile);
        } else {
            for (int i = 0; i < (this.length - 1) / this._iColumns + 1; ++i) {
                this.lstMIDataModel.addElement(this.formatLine(i * this._iColumns));
            }
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
        for (int j = 0; j < this._iColumns && adr < this.length; ++j) {
            cZeile = String.valueOf(cZeile) + " " + this.formatByte(this.speicher[adr++]);
        }
        return cZeile;
    }

    public void initComponents() {
        this.lstMIDataModel = new DefaultListModel();
        this.lstMIData = new JList(this.lstMIDataModel);
        JScrollPane listScrollPane = new JScrollPane(this.lstMIData);
        this.panel1 = new JPanel();
        this.lblStartAddress = new JLabel();
        this.txtStartAddress = new JTextField();
        this.lblEndAddress = new JLabel();
        this.txtEndAddress = new JTextField();
        this.pshNewWindow = new JButton();
        this.getContentPane().setLayout(new BorderLayout());
        this.lstMIData.setFont(new Font("Courier", 0, 12));
        this.lstMIData.setName("lstMIData");
        this.lstMIData.setBackground(new Color(255, 247, 233));
        this.lstMIData.setForeground(Color.black);
        this.getContentPane().add((Component)listScrollPane, "Center");
        this.lblStartAddress.setText("Start:");
        this.panel1.add(this.lblStartAddress);
        this.txtStartAddress.setText("00000000");
        this.txtStartAddress.setColumns(8);
        this.txtStartAddress.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIData_View.this.txtStartAddressActionPerformed(evt);
            }
        });
        this.txtStartAddress.addKeyListener(new KeyAdapter(){

            public void keyTyped(KeyEvent evt) {
                MIData_View.this.txtStartAddressKeyTyped(evt);
                MIData_View.this.txtStartAddressTextValueChanged(evt);
            }
        });
        this.panel1.add(this.txtStartAddress);
        this.lblEndAddress.setText("End:");
        this.panel1.add(this.lblEndAddress);
        this.txtEndAddress.setText("000000FF");
        this.txtEndAddress.setColumns(8);
        this.txtEndAddress.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIData_View.this.txtEndAddressActionPerformed(evt);
            }
        });
        this.txtEndAddress.addKeyListener(new KeyAdapter(){

            public void keyTyped(KeyEvent evt) {
                MIData_View.this.txtEndAddressKeyTyped(evt);
                MIData_View.this.txtEndAddressTextValueChanged(evt);
            }
        });
        this.panel1.add(this.txtEndAddress);
        this.pshNewWindow.setText("New");
        this.pshNewWindow.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MIData_View.this.pshNewWindowActionPerformed(evt);
            }
        });
        this.panel1.add(this.pshNewWindow);
        this.getContentPane().add((Component)this.panel1, "North");
        super.initComponents();
    }

    private void pshNewWindowActionPerformed(ActionEvent evt) {
        MIData_View oDataView = new MIData_View(this._oController, this._oSimulator);
        oDataView.setName(String.valueOf(oDataView.getName()) + nDataViewCount);
        oDataView.setTitle(String.valueOf(oDataView.getTitle()) + nDataViewCount);
        ++nDataViewCount;
        this._oController.registerView(oDataView);
        oDataView.update();
    }

    private void txtEndAddressKeyTyped(KeyEvent evt) {
        if (!this.isHexChar(evt.getKeyChar())) {
            evt.consume();
        }
    }

    private void txtStartAddressKeyTyped(KeyEvent evt) {
        if (!this.isHexChar(evt.getKeyChar())) {
            evt.consume();
        }
    }

    private void txtEndAddressActionPerformed(ActionEvent evt) {
        this._iEndAddress = Integer.decode("0x" + this.txtEndAddress.getText());
        this.lstMIData.removeAll();
        this.update();
    }

    private void txtStartAddressActionPerformed(ActionEvent evt) {
        this._iStartAddress = Integer.decode("0x" + this.txtStartAddress.getText());
        this.lstMIData.removeAll();
        this.update();
    }

    private void txtEndAddressTextValueChanged(KeyEvent evt) {
        String cText = this.txtEndAddress.getText();
        if (cText.length() > 8) {
            cText = cText.substring(0, 8);
            this.txtEndAddress.setText(cText);
        }
    }

    private void txtStartAddressTextValueChanged(KeyEvent evt) {
        String cText = this.txtStartAddress.getText().toUpperCase();
        if (cText.length() > 8) {
            cText = cText.substring(0, 8);
            this.txtStartAddress.setText(cText);
        }
    }

    private boolean isHexChar(char cZeichen) {
        return cZeichen <= ' ' || cZeichen >= '0' && cZeichen <= '9' || cZeichen >= 'A' && cZeichen <= 'F' || cZeichen >= 'a' && cZeichen <= 'f';
    }
}

