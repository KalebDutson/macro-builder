package com.kalebdutson.app;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * PanelOption is a class to make adding new options to the BuilderWindow west panel easier.
 * The class contains a titled border around a JPanel with a textField inside.
 */
public class PanelOption extends JPanel {
    private String title;
    private JFormattedTextField textField;

    public PanelOption(String title, Dimension dim, Font titleFont) {
        this.setLayout(new GridBagLayout());
        this.title = title;
        this.setPreferredSize(dim);
//        TitledBorder titledBorder = BorderFactory.createTitledBorder(
//                BorderFactory.createBevelBorder(BevelBorder.LOWERED), title);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
        titledBorder.setTitleFont(titleFont);
        this.setBorder(titledBorder);

        textField = new JFormattedTextField();
        textField.setFont(titleFont);
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(textField, c);
    }
    public String getTitle(){
        return this.title;
    }
    public JTextField getJFormattedTextField(){
        return this.textField;
    }

    public void setJFormattedTextField(JFormattedTextField jf){
        this.textField = jf;
    }

    public void setJFormattedTextFieldContent(String s){
        this.textField.setText(s);
    }

    private void initTextField(){

    }
}
