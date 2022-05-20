package com.kalebdutson.app;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.dispatcher.SwingDispatchService;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.PrivateKey;

public class RegisterHotkeysDialog extends JDialog implements NativeKeyListener, ActionListener, PropertyChangeListener {

    private Hotkey startHotkey;
    private Hotkey stopHotkey;
    private String b1String = "Cancel";
    private String b2String = "Save";
    private JOptionPane optionPane;
//    private boolean startActive = false;
//    private boolean stopActive = false;

    public RegisterHotkeysDialog(Frame aFrame, Config config) {
        super(aFrame, true);
        TextField textField = new TextField(10);
        Object[] array = {"Hello", "World", textField};
        Object[] options = {b1String, b2String};
        optionPane = new JOptionPane(array,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                options,
                options[0]);
        // Make dialog display it
        setContentPane(optionPane);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

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
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                textField.requestFocusInWindow();
            }
        });
        textField.addActionListener(this);

        optionPane.addPropertyChangeListener(this);
//        GlobalScreen.setEventDispatcher(new SwingDispatchService());
//        this.startHotkey = config.getHotkeys()[0];
//        this.stopHotkey = config.getHotkeys()[1];
//        setDialogListener(this);
//        JPanel pane = new JPanel(new GridBagLayout());
//
//        JLabel l1 = new JLabel("Start");
//        GridBagConstraints c1 = new GridBagConstraints();
//        c1.gridx = 0;
//        c1.gridy = 0;
//        pane.add(l1, c1);
//
//        JLabel l2 = new JLabel("Stop");
//        GridBagConstraints c2 = new GridBagConstraints();
//        c2.gridx = 1;
//        c2.gridy = 0;
//        pane.add(l2, c2);
//
//        // Start hotkey text field
//        JTextField t1 = new JTextField(startHotkey.toString());
//        GridBagConstraints c3 = new GridBagConstraints();
//        c3.gridx = 0;
//        c3.gridy = 1;
//        pane.add(t1, c3);
//        setTextFieldListeners(t1);
//        t1.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
////                System.out.println("Set hotkey");
////                System.out.println(e.getKeyChar());
////                System.out.println(e.getKeyCode());
////                NativeKeyEvent.getKeyText(e.getKeyCode());
//            }
//        });


//        // Stop hotkey text field
//        JTextField t2 = new JTextField(stopHotkey.toString());
//        GridBagConstraints c4 = new GridBagConstraints();
//        c4.gridx = 1;
//        c4.gridy = 1;
//        pane.add(t2, c4);
//        setTextFieldListeners(t2);
//
//        JButton b1 = new JButton("Cancel");
//        GridBagConstraints c5 = new GridBagConstraints();
//        c5.gridx = 0;
//        c5.gridy = 2;
//        pane.add(b1, c5);
//        b1.addActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e){
//                dispose();
//            }
//        });
//
//        JButton b2 = new JButton("Save");
//        GridBagConstraints c6 = new GridBagConstraints();
//        c6.gridx = 1;
//        c6.gridy = 2;
//        pane.add(b2, c6);
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // TODO: Save hotkeys to config
//                dispose();
//            }
//        });
//        this.add(pane);
//        this.setTitle("Register Hotkeys");
//        this.pack();
//        this.setSize(200, 200);
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = toolkit.getScreenSize();
//        Point p = new Point((screenSize.width - windowWidth) / 2,
//                (screenSize.height - windowHeight) / 2);
//        this.setLocation(p);
//        this.setLayout(null);
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);    // Stop program when ui is closed
//        addWindowListener(this);
//        this.setAlwaysOnTop(true);
//        this.setVisible(true); // make the frame visible
//        this.requestFocus();
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
    public void nativeKeyReleased(NativeKeyEvent e) {
        // TODO: Implement
        //  Check which hotkey is active
        System.out.println("Source Class: SetHotkeysWindow");
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
            if (currParent instanceof RegisterHotkeysDialog){
                ((Window) currParent).dispose();
                currParent = null;
            }
            else{
                currParent = currParent.getParent();
            }
        }
    }
    private void registerHook(){
        App.registerHook(this, this.getClass());
    }

    private void unregisterHook(){
        App.unregisterHook(this.getClass());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(b1String);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        if(isVisible()
                && (evt.getSource() == optionPane)
                && (JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))){
            Object value = optionPane.getValue();
            if(value == JOptionPane.UNINITIALIZED_VALUE){
                // ignore reset
                return;
            }
            else{
                System.out.println("Not really sure whats going here yet");
            }
        }
    }
}
