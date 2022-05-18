package com.kalebdutson.app;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.nio.channels.spi.AbstractInterruptibleChannel;

public class SetHotkeysWindow extends JDialog  implements NativeKeyListener, WindowListener{
    private int windowWidth = 200;
    private int windowHeight = 200;
    private Hotkey startHotkey;
    private Hotkey stopHotkey;
    private boolean startActive = false;
    private boolean stopActive = false;

    public SetHotkeysWindow(Hotkey startHotkey, Hotkey stopHotkey) {
        this.startHotkey = startHotkey;
        this.stopHotkey = stopHotkey;
        setDialogListener(this);
        JPanel pane = new JPanel(new GridBagLayout());

        JLabel l1 = new JLabel("Start");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        pane.add(l1, c1);

        JLabel l2 = new JLabel("Stop");
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        pane.add(l2, c2);

        JTextField t1 = new JTextField(startHotkey.toString());
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 1;
        pane.add(t1, c3);
        setTextFieldListeners(t1);

        JTextField t2 = new JTextField(stopHotkey.toString());
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 1;
        c4.gridy = 1;
        pane.add(t2, c4);
        setTextFieldListeners(t2);

        JButton b1 = new JButton("Cancel");
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 2;
        pane.add(b1, c5);
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        JButton b2 = new JButton("Save");
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 1;
        c6.gridy = 2;
        pane.add(b2, c6);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Save hotkeys to config
                dispose();
            }
        });
        this.add(pane);
        this.setTitle("Register Hotkeys");
        this.pack();
        this.setSize(200, 200);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Point p = new Point((screenSize.width - windowWidth) / 2,
                (screenSize.height - windowHeight) / 2);
        this.setLocation(p);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    // Stop program when ui is closed
        addWindowListener(this);
        this.setVisible(true); // make the frame visible
        this.requestFocus();
    }

    private void setTextFieldListeners(JTextField t) {
        t.setBackground(Color.white);
        t.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t.setSelectedTextColor(Color.white);
                t.setCaretPosition(0);
                t.setText(null);
                t.setForeground(Color.white);
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
        t.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                t.setCaretPosition(0);
                t.setText(null);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                t.setCaretPosition(0);
                t.setText(null);
            }
        });
        // TODO: Save hotkey after it's been entered
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("I am active");
                System.out.printf("Text: %s%n", t.getText());
                t.getParent().requestFocus();
            }

        });
    }

    private void setDialogListener(JDialog d){
        d.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Frame requesting focus");
                d.requestFocus();
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

    @Override
    public void windowOpened(WindowEvent e) {
//        App.registerHook(this, "SetHotkeysWindow");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Window closing");
        this.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Window closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        // TODO: Implement
        //  Check which hotkey is active
        System.out.println("SetHotkeysWindow");
        System.out.printf("KeyCode: %s%n", e.getKeyCode());
        System.out.printf("KeyLocation: %s%n", e.getKeyLocation());
        System.out.printf("Id: %s%n", e.getID());
        System.out.printf("When: %s%n", e.getWhen());
        System.out.printf("KeyText: %s%n\n", NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
    }

    private void disposeParentWindow(ActionEvent e){
        Component currParent = (Component) e.getSource();
        while(currParent != null){
            if (currParent instanceof SetHotkeysWindow){
                ((Window) currParent).dispose();
                currParent = null;
            }
            else{
                currParent = currParent.getParent();
            }
        }
    }
}
