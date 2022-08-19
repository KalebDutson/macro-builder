package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class BuilderWindow extends JFrame implements NativeKeyListener, WindowListener {
    private Macro m;
    private boolean ctrlHeld = false;
    private boolean shiftHeld = false;
    private boolean altHeld = false;
    private Config config;
    private final boolean debug = true;
    // TODO: implement this to stop menubar accelerators from executing when recording keyboard input
    private boolean recordingActive;

    public BuilderWindow(Config config){
        GlobalScreen.setEventDispatcher(new SwingDispatchService());
        this.config = config;
        // TODO: Load config file for hotkeys
        // loadConfig();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.m = new Macro();
        this.recordingActive = false;
        int windowWidth = 800;
        int windowHeight = 600;
        this.setSize(windowWidth, windowHeight);
        this.setLayout(new BorderLayout());

        // Initialize menu bar
        buildMenuBar();

        // Entire Left panel
        JPanel westPanel = new JPanel(new GridBagLayout());
        westPanel.setPreferredSize(new Dimension(this.getWidth() / 4, this.getHeight()));
        // TODO: add macro name to title border once Macro class fully implemented
        TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Macro1");
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        titleBorder.setTitleFont(App.FONT_A_BOLD);
        westPanel.setBorder(titleBorder);

        // "Iterations" option panel
        PanelOption iterationsOption = new PanelOption("Iterations",
                new Dimension(this.getWidth() / 4, 100), App.FONT_A_PLAIN);
        GridBagConstraints optionConstraints1 = new GridBagConstraints();
        optionConstraints1.weightx = 1;
        optionConstraints1.weighty = 1;
        optionConstraints1.gridx = 0;
        optionConstraints1.gridy = 0;
        optionConstraints1.fill = GridBagConstraints.HORIZONTAL;
        optionConstraints1.anchor = GridBagConstraints.FIRST_LINE_START;
        // Add iterations option panel to west panel
        westPanel.add(iterationsOption, optionConstraints1);

        // Right panel - Holds Spacer, ScrollArea and Lower Button area
        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setPreferredSize(new Dimension(3 * windowWidth / 4, windowHeight));

        // Top Spacer to separate scroll area from top of window
        JPanel topEastSpacer = new JPanel();
        topEastSpacer.setPreferredSize(new Dimension(eastPanel.getWidth(), 16));
        GridBagConstraints ec1 = new GridBagConstraints();
        ec1.gridx = 0;
        ec1.gridy = 0;
        ec1.weightx = 0;
        ec1.weighty = 0;
        ec1.anchor = GridBagConstraints.FIRST_LINE_START;
        ec1.fill = GridBagConstraints.HORIZONTAL;
        eastPanel.add(topEastSpacer, ec1);

        // Middle east panel - scrollbar
        JPanel subpanelEastCenter = new JPanel(new GridBagLayout());
        subpanelEastCenter.setBorder(new BevelBorder(BevelBorder.LOWERED));
        GridBagConstraints ec2 = new GridBagConstraints();
        ec2.gridx = 0;
        ec2.gridy = 1;
        ec2.weightx = 1;
        ec2.weighty = 1;
        ec2.anchor = GridBagConstraints.FIRST_LINE_START;
        ec2.fill = GridBagConstraints.BOTH;

        // Scroll pane for macro actions
        JScrollPane scrollPane =  new JScrollPane();
        NumberedTextArea numberedTextArea = new NumberedTextArea(1);
        numberedTextArea.setBackground(Color.white);
        scrollPane.getViewport().add(numberedTextArea);

        // Add scroll pane to window
        GridBagConstraints scrollConstraints = new GridBagConstraints(); // scroll panel constraints
        scrollConstraints.gridx = 0;
        scrollConstraints.gridy = 0;
        scrollConstraints.weightx = 1;
        scrollConstraints.weighty = 1;
        scrollConstraints.fill = GridBagConstraints.BOTH;
        scrollConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        subpanelEastCenter.add(scrollPane, scrollConstraints); // Add scroll bar to center sub-panel
        eastPanel.add(subpanelEastCenter, ec2); // add  center sub-panel to east panel

        // Bottom east panel - home of buttons
        JPanel subpanelEastBottom = new JPanel(new GridBagLayout());
        subpanelEastBottom.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        subpanelEastBottom.setPreferredSize(new Dimension(eastPanel.getWidth(), 40));
        GridBagConstraints ec3 = new GridBagConstraints();
        ec3.gridx = 0;
        ec3.gridy = 2;
        ec3.weightx = 0;
        ec3.weighty = 0;
        ec3.anchor = GridBagConstraints.FIRST_LINE_START;
        ec3.fill = GridBagConstraints.HORIZONTAL;
        // Todo: Insert action before button - add functionality
        JButton iBeforeBtn = new JButton("Insert Before");
        GridBagConstraints bc1 = new GridBagConstraints();
        bc1.gridx = 0;
        bc1.gridy = 0;
        bc1.weightx = 0;
        bc1.weighty = 0;
        bc1.anchor = GridBagConstraints.FIRST_LINE_START;
        bc1.insets = new Insets(3, 3, 0, 0);
        subpanelEastBottom.add(iBeforeBtn, bc1);
        // Todo: Insert action after button - add functionality
        JButton iAfterBtn = new JButton("Insert After");
        GridBagConstraints bc2 = new GridBagConstraints();
        bc2.gridx = 1;
        bc2.gridy = 0;
        bc2.weightx = 1;
        bc2.weighty = 0;
        bc2.anchor = GridBagConstraints.FIRST_LINE_START;
        bc2.insets = new Insets(3, 3, 0, 0);
        subpanelEastBottom.add(iAfterBtn, bc2);
        // Todo: Run Macro button - add functionality
        JButton runBtn = new JButton("Run Macro");
        GridBagConstraints bc3 = new GridBagConstraints();
        bc3.gridx = 2;
        bc3.gridy = 0;
        bc3.weightx = 0;
        bc3.weighty = 0;
        bc3.anchor = GridBagConstraints.FIRST_LINE_END;
        bc3.insets = new Insets(3, 0, 0, 3);
        subpanelEastBottom.add(runBtn, bc3);

        eastPanel.add(subpanelEastBottom, ec3);
        this.getContentPane().add(BorderLayout.WEST, westPanel);
        this.getContentPane().add(BorderLayout.EAST, eastPanel);

        addWindowListener(this);
        setWindowListener();
        // TODO: add macro name to title once Macro class fully implemented
        this.setTitle("Macro1");
        this.pack();
        this.setLocation(new Point((screenSize.width - this.getWidth()) / 2,
                (screenSize.height - this.getHeight()) / 2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Stop program when ui is closed
        this.setResizable(false);
        this.setVisible(true); // make the frame visible
        this.toFront();
        this.requestFocus();
    }
    public void recordInput(){
        // TODO: connect to Macro class record function
    }

    public void stopRecording(){
        // TODO: connect to Macro class stopRecording function
    }

    @Override
    public void windowOpened(WindowEvent e) {
        registerHook();
    }
    @Override
    public void windowClosing(WindowEvent e) {
        unregisterHook();
    }
    @Override
    public void windowClosed(WindowEvent e) {
        System.runFinalization();
        System.exit(0);
    }
    @Override
    public void windowIconified(WindowEvent e) {    }
    @Override
    public void windowDeiconified(WindowEvent e) {    }
    @Override
    public void windowActivated(WindowEvent e) {    }
    @Override
    public void windowDeactivated(WindowEvent e) {    }

    private void unregisterHook(){
        App.unregisterHook(this.getClass());
    }
    private void registerHook(){
        App.registerHook(this, this.getClass());
    }

    /**
     * Listener to focus the main window when clicked. This takes focus out
     * of subcomponents after they are clicked.
     */
    private void setWindowListener(){
        JFrame mainWindow = this;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainWindow.requestFocus();
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    /**
     * Initialize the menu bar for the window and associated accelerators and mnemonics
     */
    private void buildMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu;
        JMenuItem newItem, openProjectItem, closeProjectItem, saveItem, saveAsItem, settingsItem;

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newItem = new JMenuItem("New", KeyEvent.VK_N);
        openProjectItem = new JMenuItem("Open", KeyEvent.VK_O);
        closeProjectItem = new JMenuItem("Close Project", KeyEvent.VK_J);
        saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveAsItem = new JMenuItem("Save as", KeyEvent.VK_E);
        settingsItem = new JMenuItem("Settings", KeyEvent.VK_T);

        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        openProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        closeProjectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        settingsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));

        newItem.setFont(App.FONT_A_PLAIN);
        openProjectItem.setFont(App.FONT_A_PLAIN);
        closeProjectItem.setFont(App.FONT_A_PLAIN);
        saveItem.setFont(App.FONT_A_PLAIN);
        saveAsItem.setFont(App.FONT_A_PLAIN);
        settingsItem.setFont(App.FONT_A_PLAIN);

        fileMenu.add(newItem);
        fileMenu.add(openProjectItem);
        fileMenu.add(closeProjectItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(settingsItem);

        // TODO
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!recordingActive) {
                    System.out.println("newItem Action launched!");
                }
            }
        });
        // TODO
        openProjectItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!recordingActive){
                    System.out.println("openProjectItem Action launched!");
                }
            }
        });
        // TODO
        closeProjectItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!recordingActive){
                    System.out.println("closeProjectItem Action launched!");
                }
            }
        });
        //TODO
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!recordingActive){
                    System.out.println("saveItem Action launched!");
                }
            }
        });
        //TODO
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!recordingActive){
                    System.out.println("saveAsItem Action launched!");
                }
            }
        });
        //TODO
        settingsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!recordingActive){
                    System.out.println("settings Action launched!");
                }
            }
        });

        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }
}
