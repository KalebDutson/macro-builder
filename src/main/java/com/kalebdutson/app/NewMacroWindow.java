package com.kalebdutson.app;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;

public class NewMacroWindow extends JFrame implements NativeKeyListener, WindowListener {
    private Macro m;
    private boolean ctrlHeld = false;
//    private boolean hookRegistered;

    public NewMacroWindow(Config config){
        // TODO: Load config file for hotkeys
        // loadConfig();
        this.m = new Macro();
        int windowWidth = 400;
        int windowHeight = 400;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        JPanel pane = new JPanel(new GridBagLayout());

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
                b3.setPreferredSize(new Dimension(b3.getWidth(), b3.getHeight()));
                // TODO: Open new window to change hotkeys
                App.unregisterHook("NewMacroWindow");
//
//                new SetHotkeysWindow(config.getHotkeys()[0], config.getHotkeys()[1]);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new SetHotkeysWindow(config.getHotkeys()[0], config.getHotkeys()[1] );
                    }
                });
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
        this.setVisible(true); // make the frame visible
    }
    public void recordInput(){
        this.m.record();
    }

    public void stopRecording(){
    }

    @Override
    public void windowOpened(WindowEvent e) {
        App.registerHook(this, "NewMacroWindow");
    }
    @Override
    public void windowClosing(WindowEvent e) {
        App.unregisterHook("NewMacroWindow");
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
    }
    @Override
    public void nativeKeyPressed(NativeKeyEvent e){
        if(e.getKeyCode() == NativeKeyEvent.VC_CONTROL){
            this.ctrlHeld = true;
        }
        else if(this.ctrlHeld) {
            if (e.getKeyCode() == NativeKeyEvent.VC_1) {
                System.out.println("CTRL + 1 pressed");
            }
        }
        System.out.printf("KeyCode: %s%n", e.getKeyCode());
        System.out.printf("KeyLocation: %s%n", e.getKeyLocation());
        System.out.printf("Id: %s%n", e.getID());
        System.out.printf("When: %s%n", e.getWhen());
        System.out.printf("KeyText: %s%n\n", NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

}
