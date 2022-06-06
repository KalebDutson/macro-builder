package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BuilderWindow extends JFrame implements NativeKeyListener, WindowListener {
    private int windowWidth = 800;
    private int windowHeight = 600;
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
        this.m = new Macro();
        this.recordingActive = false;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        this.setSize(windowWidth, windowHeight);
        this.setLocation(new Point((screenSize.width - windowWidth) / 2,
                (screenSize.height - windowHeight) / 2));
        this.setLayout(new BorderLayout());



        // Initialize menu bar
        buildMenuBar();

        // TODO: Connect buttons to Macro class for recording and playback
//        JButton b1 = new JButton("Record");
//        b1.setPreferredSize(new Dimension(100, 40));
//        GridBagConstraints c1 = new GridBagConstraints();
//        c1.gridx = 0;
//        c1.gridy = 0;
//        pane.add(b1, c1);


        // Left panel
        JPanel westPanel = new JPanel();
        westPanel.setBackground(Color.RED);
        westPanel.setPreferredSize(new Dimension(windowWidth / 4, windowHeight));
//        JLabel iterations = new JLabel("Iterations");
//        JTextField iterationsText = new JTextField();

//        iterations.setPreferredSize(new Dimension(100, 40));
//        GridBagConstraints c1 = new GridBagConstraints();
//        c1.gridx = 0;
//        c1.gridy = 0;

//        iterationsText.setPreferredSize(new Dimension(100, 40));
//        GridBagConstraints c2 = new GridBagConstraints();
//        c2.gridx = 0;
//        c2.gridy = 1;
//        westPanel.add(iterations, c1);
//        westPanel.add(iterationsText, c2);

        // Right panel
        JPanel eastPanel = new JPanel(new GridBagLayout());
        eastPanel.setBackground(Color.GREEN);
        eastPanel.setPreferredSize(new Dimension(3 * windowWidth / 4, windowHeight));

        this.getContentPane().add(BorderLayout.WEST, westPanel);
        this.getContentPane().add(BorderLayout.EAST, eastPanel);
        this.setTitle("New Macro");
//        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Stop program when ui is closed
        addWindowListener(this);
        setWindowListener();
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
     * Build and show a dialog to register new hotkeys for starting and stopping recording
     */
    private void launchDialog(){
        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JPasswordField password = new JPasswordField();
        final JComponent[] inputs = new JComponent[] {
                new JLabel("First"),
                firstName,
                new JLabel("Last"),
                lastName,
                new JLabel("Password"),
                password
        };
        int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.DEFAULT_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("You entered " +
                    firstName.getText() + ", " +
                    lastName.getText() + ", " +
                    password.getText());
        } else {
            System.out.println("User canceled / closed the dialog, result = " + result);
        }
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

        newItem.setFont(new Font("Courier", Font.PLAIN, 10));
        openProjectItem.setFont(new Font("Courier", Font.PLAIN, 10));
        closeProjectItem.setFont(new Font("Courier", Font.PLAIN, 10));
        saveItem.setFont(new Font("Courier", Font.PLAIN, 10));
        saveAsItem.setFont(new Font("Courier", Font.PLAIN, 10));
        settingsItem.setFont(new Font("Courier", Font.PLAIN, 10));

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
