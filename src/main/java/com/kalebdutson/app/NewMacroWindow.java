package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.NativeInputEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

public class NewMacroWindow extends JFrame implements NativeKeyListener, WindowListener {
    private Macro m;
    private boolean ctrlHeld = false;
    private int[] startHotkey = new int [3];
    private int[] stopHotkey = new int [3];

    public NewMacroWindow(){
        this.m = new Macro();
        int windowWidth = 400;
        int windowHeight = 400;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
//        JFrame f = new JFrame();
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

        JButton b3 = new JButton("Register \"Start Recording\" hotkey");
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b3.setPreferredSize(new Dimension(b3.getWidth(), b3.getHeight()));
                b3.setText("Hello");
            }
        });
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        pane.add(b3, c3);

        JButton b4 = new JButton("Register \"Stop Recording\" hotkey");
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        c4.gridy = 3;
        pane.add(b4, c4);

        JLabel t1 = new JLabel("None");
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 1;
        c5.gridy = 2;
        pane.add(t1, c5);

        JTextField t2 = new JTextField("None");
//        t2.
        t2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I am active");
            }
        });
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 1;
        c6.gridy = 3;
        pane.add(t2, c6);

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

    @Override
    public void windowOpened(WindowEvent e) {
        try{
            GlobalScreen.registerNativeHook();
        }
        catch(NativeHookException ex){
            System.err.println("There was a problem registering the hook");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {    }

    @Override
    public void windowClosed(WindowEvent e) {
        // Clean up the native hook
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            throw new RuntimeException(ex);
        }
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
//        NativeKeyListener.super.nativeKeyReleased(nativeEvent);
        if(e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
//            JOptionPane.showMessageDialog(null, "Space has been pressed");
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
