package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class BuilderWindow extends JFrame implements NativeKeyListener, WindowListener {
    private Macro m;
    private boolean ctrlHeld = false;
    private boolean shiftHeld = false;
    private boolean altHeld = false;
    private Config config;
    private final boolean debug = false;
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
        Dimension westLabelDims = new Dimension(100, 20);
        // TODO: add macro name to title border once Macro class fully implemented
        TitledBorder titleBorder = BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.RAISED), "Macro1");
        titleBorder.setTitlePosition(TitledBorder.ABOVE_TOP);
        titleBorder.setTitleFont(App.FONT_A_BOLD);
        westPanel.setBorder(titleBorder);

        // TODO: create a new class to make adding these options to the west panel easier
        // Left panel for "Iterations" option
        JPanel iterationsPanel = new JPanel(new GridBagLayout());
        iterationsPanel.setPreferredSize(new Dimension(this.getWidth() / 4, 100));
        TitledBorder iterationsBorder = BorderFactory.createTitledBorder(
                BorderFactory.createBevelBorder(BevelBorder.LOWERED), "Iterations");
        iterationsBorder.setTitleFont(App.FONT_A_PLAIN);
        iterationsPanel.setBorder(iterationsBorder);
        GridBagConstraints panel1Constraints = new GridBagConstraints();
        panel1Constraints.weightx = 1;
        panel1Constraints.weighty = 1;
        panel1Constraints.gridx = 0;
        panel1Constraints.gridy = 0;
        panel1Constraints.fill = GridBagConstraints.HORIZONTAL;
        panel1Constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        // Text field for iterations variable
        JTextField iterationsText = new JTextField();
        iterationsText.setFont(App.FONT_A_PLAIN);
        iterationsText.setPreferredSize(westLabelDims);
        GridBagConstraints wc1 = new GridBagConstraints();
        wc1.weightx = 1;
        wc1.weighty = 1;
        wc1.gridx = 0;
        wc1.gridy = 1;
        wc1.anchor = GridBagConstraints.FIRST_LINE_START;
        wc1.fill = GridBagConstraints.HORIZONTAL;
        iterationsPanel.add(iterationsText, wc1);
        // Add iterations option panel to west panel
        westPanel.add(iterationsPanel, panel1Constraints);

        // TODO:
        // Right panel
        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setBackground(Color.GREEN);
        eastPanel.setPreferredSize(new Dimension(3 * windowWidth / 4, windowHeight));
        GridBagConstraints ec2 = new GridBagConstraints();
        ec2.gridx = 0;
        ec2.gridy = 0;
        ec2.weighty = 1;
        ec2.weightx = 1;
        ec2.anchor = GridBagConstraints.FIRST_LINE_START;
        JLabel test = new JLabel("Testing");
        eastPanel.add(test, ec2);


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

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if(e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
            JOptionPane.showConfirmDialog(this, "Input");
        }
        else if(e.getKeyCode() == NativeKeyEvent.VC_CONTROL){
            this.ctrlHeld = false;
        }
        else if(e.getKeyCode() == NativeKeyEvent.VC_ALT){
            this.altHeld = false;
        }
        else if(e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            this.shiftHeld = false;
        }

    }
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL) {
            this.ctrlHeld = true;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ALT) {
            this.altHeld = true;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
            this.shiftHeld = true;
        } else if (this.ctrlHeld) {
            if (e.getKeyCode() == NativeKeyEvent.VC_1) {
                if (this.debug) {
                    System.out.println("CTRL + 1 pressed");
                }
            }
        }
        if (this.debug) {
            System.out.println("Source Class: NewMacroWindow");
            System.out.printf("KeyCode: %s%n", e.getKeyCode());
            System.out.printf("KeyLocation: %s%n", e.getKeyLocation());
            System.out.printf("Id: %s%n", e.getID());
            System.out.printf("When: %s%n", e.getWhen());
            System.out.printf("KeyText: %s%n\n", NativeKeyEvent.getKeyText(e.getKeyCode()));
        }
    }
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
