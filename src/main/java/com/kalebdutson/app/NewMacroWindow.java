package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;
import java.util.Set;

public class NewMacroWindow extends JFrame implements NativeKeyListener, WindowListener {
    private Macro m;
    private boolean ctrlHeld = false;
    private boolean shiftHeld = false;
    private boolean altHeld = false;
    private Config config;
    private final boolean debug = false;
    // TODO: implement this to stop accelerators from menubar to execute when recording keyboard input
    private boolean recordingActive;

    public NewMacroWindow(Config config){
        GlobalScreen.setEventDispatcher(new SwingDispatchService());
        this.config = config;
        // TODO: Load config file for hotkeys
        // loadConfig();
        this.m = new Macro();
        this.recordingActive = false;
        int windowWidth = 400;
        int windowHeight = 400;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        JPanel pane = new JPanel(new GridBagLayout());

        // Initialize menu bar
        buildMenuBar();

        // TODO: Connect buttons to Macro class for recording and playback
        JButton b1 = new JButton("Record");
        b1.setPreferredSize(new Dimension(100, 40));
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        pane.add(b1, c1);

        JButton b2 = new JButton("Play Recording");
        b2.setPreferredSize(new Dimension(150,40));
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        pane.add(b2, c2);

        JButton b3 = new JButton("Edit Hotkeys");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                requestFocus();
                // Open new window to change hotkeys
                // TODO: change to dialog instead of window
//                new SetHotkeysWindow(mainWindow, config);
                launchDialog();
            }
        });
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.gridwidth = 2;
        pane.add(b3, c3);

        this.add(pane);
        this.setTitle("New Macro");
        this.pack();
        this.setSize(windowWidth,windowHeight);
        Point p = new Point((screenSize.width - windowWidth) / 2,
                (screenSize.height - windowHeight) / 2);
        this.setLocation(p);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // Stop program when ui is closed
        addWindowListener(this);
        setWindowListener();
        this.requestFocus();
        this.setVisible(true); // make the frame visible
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
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        openProjectItem = new JMenuItem("Open", KeyEvent.VK_O);
        closeProjectItem = new JMenuItem("Close Project", KeyEvent.VK_J);
        saveItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveAsItem = new JMenuItem("Save as", KeyEvent.VK_A);
        settingsItem = new JMenuItem("Settings", KeyEvent.VK_T);

        fileMenu.add(newItem);
        fileMenu.add(openProjectItem);
        fileMenu.add(closeProjectItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(settingsItem);

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has the accelerator worked?");
            }
        });
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }
}
