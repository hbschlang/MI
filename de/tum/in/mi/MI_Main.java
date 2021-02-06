/*
 * Decompiled with CFR 0.150.
 */
package de.tum.in.mi;

import de.tum.in.mi.MI;
import de.tum.in.mi.MIEditor;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MI_Main
extends JFrame {
    private MI _oParent;
    private static final String _cVERSION = "2.00";
    private boolean _bInChange = false;
    private JMenuBar menMI_menuBar;
    private JMenu menMI_menuFile;
    private JMenuItem mitMI_File_Exit;
    private JMenuItem mitMI_Code_New;
    private JMenuItem mitMI_Code_Load;
    private JMenuItem mitMI_Code_Save;
    private JMenuItem mitMI_Code_SaveAs;
    private JMenuItem mitMI_Code_Close;
    private JMenu menMI_menuAssembler;
    private JMenuItem mitMI_Assembler_Start;
    private JMenu menMI_menuSimulate;
    private JMenuItem mitMI_Simulate_Run;
    private JMenuItem mitMI_Simulate_Step;
    private JMenu menMI_menuProperties;
    private JMenu menMI_PropertiesView;
    private JCheckBoxMenuItem menMI_PropertiesView_HEX;
    private JCheckBoxMenuItem menMI_PropertiesView_BIN;
    private JCheckBoxMenuItem menMI_PropertiesView_DEZ;
    private JToolBar panToolbar;
    private JSplitPane panWorkArea;
    private DefaultListModel panConsole;
    private JTabbedPane tabEditorArea;
    private JButton pshFileNew;
    private JButton pshFileLoad;
    private JButton pshFileSave;
    private JButton pshAssemblerStart;
    private JButton pshSimulatorRun;
    private JButton pshSimulatorStep;
    private JButton pshSimulatorRestart;
    private JButton pshSimulatorClose;

    public MI_Main(MI oParent) {
        this._oParent = oParent;
        this.initComponents();
        this.setSize(1024, 768);
        this.setTitle(String.valueOf(this.getTitle()) + " Version " + _cVERSION);
    }

    public void showMessage(String cMsg) {
        this.panConsole.addElement(cMsg);
    }

    public void showError(String cMsg) {
        this.panConsole.insertElementAt(cMsg, 0);
    }

    public void createNewEditorTab(MIEditor editor) {
        this.tabEditorArea.addTab(editor.getTitle(), editor);
    }

    public void removeEditorTab(MIEditor editor) {
        this.tabEditorArea.remove(editor);
    }

    public void focusEditorTab(MIEditor editor) {
        this.tabEditorArea.setSelectedComponent(editor);
    }

    public void setEditorTabTitle(MIEditor editor, String cTitle) {
        int i = this.tabEditorArea.indexOfComponent(editor);
        if (i >= 0) {
            this.tabEditorArea.setTitleAt(i, cTitle);
        }
    }

    public int getDezimalState() {
        if (this.menMI_PropertiesView_HEX.getState()) {
            return 1;
        }
        if (this.menMI_PropertiesView_BIN.getState()) {
            return 2;
        }
        if (this.menMI_PropertiesView_DEZ.getState()) {
            return 3;
        }
        return 0;
    }

    private void initComponents() {
        this.menMI_menuBar = new JMenuBar();
        this.menMI_menuFile = new JMenu();
        this.mitMI_File_Exit = new JMenuItem();
        this.mitMI_Code_New = new JMenuItem();
        this.mitMI_Code_Load = new JMenuItem();
        this.mitMI_Code_Save = new JMenuItem();
        this.mitMI_Code_SaveAs = new JMenuItem();
        this.mitMI_Code_Close = new JMenuItem();
        this.menMI_menuAssembler = new JMenu();
        this.mitMI_Assembler_Start = new JMenuItem();
        this.menMI_menuSimulate = new JMenu();
        this.mitMI_Simulate_Run = new JMenuItem();
        this.mitMI_Simulate_Step = new JMenuItem();
        this.menMI_menuProperties = new JMenu();
        this.menMI_PropertiesView = new JMenu();
        this.menMI_PropertiesView_HEX = new JCheckBoxMenuItem();
        this.menMI_PropertiesView_BIN = new JCheckBoxMenuItem();
        this.menMI_PropertiesView_DEZ = new JCheckBoxMenuItem();
        this.panToolbar = new JToolBar();
        this.pshFileNew = new JButton();
        this.pshFileLoad = new JButton();
        this.pshFileSave = new JButton();
        this.pshAssemblerStart = new JButton();
        this.pshSimulatorRun = new JButton();
        this.pshSimulatorStep = new JButton();
        this.pshSimulatorRestart = new JButton();
        this.pshSimulatorClose = new JButton();
        this.menMI_menuFile.setName("MI_menuFile");
        this.menMI_menuFile.setText("File");
        this.mitMI_File_Exit.setName("MI_menuItem_File_Exit");
        this.mitMI_File_Exit.setText("Exit");
        this.mitMI_File_Exit.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_File_ExitActionPerformed(evt);
            }
        });
        this.menMI_menuFile.add(this.mitMI_File_Exit);
        this.menMI_menuFile.addSeparator();
        this.mitMI_Code_New.setName("MI_menuItem_Code_New");
        this.mitMI_Code_New.setText("New");
        this.mitMI_Code_New.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Code_NewActionPerformed(evt);
            }
        });
        this.menMI_menuFile.add(this.mitMI_Code_New);
        this.mitMI_Code_Load.setName("MI_menuItem_Code_Load");
        this.mitMI_Code_Load.setText("Load...");
        this.mitMI_Code_Load.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Code_LoadActionPerformed(evt);
            }
        });
        this.menMI_menuFile.add(this.mitMI_Code_Load);
        this.mitMI_Code_Save.setName("MI_menuItem_Code_Save");
        this.mitMI_Code_Save.setText("Save");
        this.mitMI_Code_Save.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Code_SaveActionPerformed(evt);
            }
        });
        this.menMI_menuFile.add(this.mitMI_Code_Save);
        this.mitMI_Code_SaveAs.setName("MI_MenuItem_Code_SaveAs");
        this.mitMI_Code_SaveAs.setText("Save As...");
        this.mitMI_Code_SaveAs.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Code_SaveAsActionPerformed(evt);
            }
        });
        this.menMI_menuFile.add(this.mitMI_Code_SaveAs);
        this.mitMI_Code_Close.setName("MI_menuItem_Code_Close");
        this.mitMI_Code_Close.setText("Close");
        this.mitMI_Code_Close.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Code_CloseActionPerformed(evt);
            }
        });
        this.menMI_menuFile.add(this.mitMI_Code_Close);
        this.menMI_menuBar.add(this.menMI_menuFile);
        this.menMI_menuAssembler.setName("MI_menuAssembler");
        this.menMI_menuAssembler.setText("Assembler");
        this.mitMI_Assembler_Start.setName("MI_menuItem_Assembler_Start");
        this.mitMI_Assembler_Start.setText("Start");
        this.mitMI_Assembler_Start.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Assembler_StartActionPerformed(evt);
            }
        });
        this.menMI_menuAssembler.add(this.mitMI_Assembler_Start);
        this.menMI_menuBar.add(this.menMI_menuAssembler);
        this.menMI_menuSimulate.setName("MI_menuSimulate");
        this.menMI_menuSimulate.setText("Simulate");
        this.mitMI_Simulate_Run.setName("MI_menuItem_Simulate_Run");
        this.mitMI_Simulate_Run.setText("Run");
        this.mitMI_Simulate_Run.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Simulate_RunActionPerformed(evt);
            }
        });
        this.menMI_menuSimulate.add(this.mitMI_Simulate_Run);
        this.mitMI_Simulate_Step.setName("MI_menuItem_Simulate_Step");
        this.mitMI_Simulate_Step.setText("Step ");
        this.mitMI_Simulate_Step.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.mitMI_Simulate_StepActionPerformed(evt);
            }
        });
        this.menMI_menuSimulate.add(this.mitMI_Simulate_Step);
        this.menMI_menuProperties.setText("Properties");
        this.menMI_PropertiesView.setText("Register View");
        this.menMI_PropertiesView_HEX.setState(true);
        this.menMI_PropertiesView_HEX.setText("Hexadecimal");
        this.menMI_PropertiesView_HEX.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MI_Main.this.menMI_PropertiesView_HEXItemStateChanged(evt);
            }
        });
        this.menMI_PropertiesView.add(this.menMI_PropertiesView_HEX);
        this.menMI_PropertiesView_BIN.setText("Binary");
        this.menMI_PropertiesView_BIN.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MI_Main.this.menMI_PropertiesView_BINItemStateChanged(evt);
            }
        });
        this.menMI_PropertiesView.add(this.menMI_PropertiesView_BIN);
        this.menMI_PropertiesView_DEZ.setText("Decimal");
        this.menMI_PropertiesView_DEZ.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent evt) {
                MI_Main.this.menMI_PropertiesView_DEZItemStateChanged(evt);
            }
        });
        this.menMI_PropertiesView.add(this.menMI_PropertiesView_DEZ);
        this.menMI_menuProperties.add(this.menMI_PropertiesView);
        this.menMI_menuSimulate.add(this.menMI_menuProperties);
        this.menMI_menuBar.add(this.menMI_menuSimulate);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add("North", this.panToolbar);
        this.panWorkArea = new JSplitPane(0);
        this.tabEditorArea = new JTabbedPane();
        this.tabEditorArea.addChangeListener(new ChangeListener(){

            public void stateChanged(ChangeEvent evt) {
                MIEditor editor = (MIEditor)((JTabbedPane)evt.getSource()).getSelectedComponent();
                if (editor != null) {
                    MI_Main.this._oParent.setActEditor(editor);
                }
            }
        });
        this.panConsole = new DefaultListModel();
        JList list = new JList(this.panConsole);
        JScrollPane scroll = new JScrollPane(list);
        this.panWorkArea.add(this.tabEditorArea);
        this.panWorkArea.add(scroll);
        this.panWorkArea.setDividerLocation(600);
        this.getContentPane().add("Center", this.panWorkArea);
        this.setResizable(false);
        this.setTitle("MI Workbench");
        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent evt) {
                MI_Main.this.exitForm(evt);
            }
        });
        this.pshFileNew.setText("New");
        this.pshFileNew.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshFileNewActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshFileNew);
        this.pshFileLoad.setText("Load");
        this.pshFileLoad.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshFileLoadActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshFileLoad);
        this.pshFileSave.setText("Save");
        this.pshFileSave.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshFileSaveActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshFileSave);
        this.pshAssemblerStart.setText("Assemble");
        this.pshAssemblerStart.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshAssemblerStartActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshAssemblerStart);
        this.pshSimulatorRun.setText("Run");
        this.pshSimulatorRun.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshSimulatorRunActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshSimulatorRun);
        this.pshSimulatorStep.setText("Step");
        this.pshSimulatorStep.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshSimulatorStepActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshSimulatorStep);
        this.pshSimulatorRestart.setText("Restart");
        this.pshSimulatorRestart.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent evt) {
                MI_Main.this.pshSimulatorRestartActionPerformed(evt);
            }
        });
        this.panToolbar.add(this.pshSimulatorRestart);
        this.setJMenuBar(this.menMI_menuBar);
    }

    private void menMI_PropertiesView_DEZItemStateChanged(ItemEvent evt) {
        if (!this._bInChange) {
            this._bInChange = true;
            this.menMI_PropertiesView_HEX.setState(false);
            this.menMI_PropertiesView_BIN.setState(false);
            this._bInChange = false;
            this._oParent.updateSimulation();
        }
    }

    private void menMI_PropertiesView_BINItemStateChanged(ItemEvent evt) {
        if (!this._bInChange) {
            this._bInChange = true;
            this.menMI_PropertiesView_HEX.setState(false);
            this.menMI_PropertiesView_DEZ.setState(false);
            this._bInChange = false;
            this._oParent.updateSimulation();
        }
    }

    private void menMI_PropertiesView_HEXItemStateChanged(ItemEvent evt) {
        if (!this._bInChange) {
            this._bInChange = true;
            this.menMI_PropertiesView_BIN.setState(false);
            this.menMI_PropertiesView_DEZ.setState(false);
            this._bInChange = false;
            this._oParent.updateSimulation();
        }
    }

    private void pshSimulatorRestartActionPerformed(ActionEvent evt) {
        this._oParent.restartSimulation();
    }

    private void pshSimulatorStepActionPerformed(ActionEvent evt) {
        this._oParent.simulateStepActEditor();
    }

    private void pshSimulatorRunActionPerformed(ActionEvent evt) {
        this._oParent.simulateRunActEditor();
    }

    private void pshAssemblerStartActionPerformed(ActionEvent evt) {
        this._oParent.assembleActEditor();
    }

    private void pshFileSaveActionPerformed(ActionEvent evt) {
        this._oParent.saveFile();
    }

    private void pshFileLoadActionPerformed(ActionEvent evt) {
        this._oParent.loadFile();
    }

    private void pshFileNewActionPerformed(ActionEvent evt) {
        this._oParent.newFile();
    }

    private void mitMI_Simulate_StepActionPerformed(ActionEvent evt) {
        this._oParent.simulateStepActEditor();
    }

    private void mitMI_Simulate_RunActionPerformed(ActionEvent evt) {
        this._oParent.simulateRunActEditor();
    }

    private void mitMI_Assembler_StartActionPerformed(ActionEvent evt) {
        this._oParent.assembleActEditor();
    }

    private void mitMI_Code_NewActionPerformed(ActionEvent evt) {
        this._oParent.newFile();
    }

    private void mitMI_Code_CloseActionPerformed(ActionEvent evt) {
        this._oParent.closeFile();
    }

    private void mitMI_Code_SaveAsActionPerformed(ActionEvent evt) {
        this._oParent.saveAsFile();
    }

    private void mitMI_Code_SaveActionPerformed(ActionEvent evt) {
        this._oParent.saveFile();
    }

    private void mitMI_Code_LoadActionPerformed(ActionEvent evt) {
        this._oParent.loadFile();
    }

    private void mitMI_File_ExitActionPerformed(ActionEvent evt) {
        this._oParent.finish();
    }

    private void exitForm(WindowEvent evt) {
        this._oParent.finish();
    }
}

