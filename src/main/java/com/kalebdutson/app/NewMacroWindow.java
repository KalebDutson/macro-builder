package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NewMacroWindow extends JFrame implements NativeKeyListener, WindowListener {
    private Macro m;

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
            JOptionPane.showMessageDialog(null, "Space has been pressed");
        }
    }
}
