package com.kalebdutson.app;

import javax.swing.*;
import java.awt.*;

public class NumberedArea extends JPanel {
    private int rows;

    /**
     * Initialize an empty NumberedArea with a specified number of rows
     * @param rows Number of rows to create
     */
    public NumberedArea(int rows){
        this.setLayout(new GridBagLayout());

        NumberedTextField one = new NumberedTextField(1, "Hello");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
//        this.add(one, c1);

        NumberedTextField two = new NumberedTextField(10, "World");
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.anchor = GridBagConstraints.FIRST_LINE_START;
//        this.add(two, c2);

        NumberedTextField three = new NumberedTextField(100, "");
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.weightx = 0;
        c3.weighty = 0;
        c3.fill = GridBagConstraints.HORIZONTAL;
        c3.anchor = GridBagConstraints.FIRST_LINE_START;
//        this.add(three, c3);

        NumberedTextField four = new NumberedTextField(1000, "");
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        c4.gridy = 3;
        c4.weightx = 0;
        c4.weighty = 0;
        c4.fill = GridBagConstraints.HORIZONTAL;
        c4.anchor = GridBagConstraints.FIRST_LINE_START;
//        this.add(four, c4);

        NumberedTextField five = new NumberedTextField(10000, "");
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 4;
        c5.weightx = 1;
        c5.weighty = 1;
        c5.fill = GridBagConstraints.HORIZONTAL;
        c5.anchor = GridBagConstraints.FIRST_LINE_START;
//        this.add(five, c5);

        for(int i=0; i<rows; i++){
            NumberedTextField numberedTextField = new NumberedTextField(i+1, "");

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = i;
            if(i == rows-1){
                constraints.weightx = 1;
                constraints.weighty = 1;
            } else{
                constraints.weightx = 0;
                constraints.weighty = 0;
            }
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.anchor = GridBagConstraints.FIRST_LINE_START;
            this.add(numberedTextField, constraints);
        }
    }

    /**
     * Get a NumberedTextField child component.
     * @param n Index of child component
     * @return Child component at specified index.
     */
    public NumberedTextField getTextComponent(int n){
        return (NumberedTextField) this.getComponent(n);
    }
}
class NumberedTextField extends JPanel {
    private JTextField numberHeader;
    private JTextField text;

    NumberedTextField(int n, String t){
        this.setLayout(new GridBagLayout());
        numberHeader = new JTextField(String.valueOf(n));
        numberHeader.setBackground(Color.GRAY);
        numberHeader.setFont(App.FONT_WHITE_BOLD);
        numberHeader.setForeground(Color.WHITE);
        numberHeader.setEditable(false);
        text = new JTextField(t);
        text.setFont(App.FONT_A_PLAIN);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.ipadx = calcPadding(n);
        c1.fill = GridBagConstraints.NONE;
        c1.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(numberHeader, c1);

        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 1;
        c2.gridy = 0;
        c2.weightx = 1;
        c2.weighty = 1;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(text, c2);
    }
    private int calcPadding(int i){
        int n = 0;
        boolean nFound = false;
        int base = 10;
        final int BASE_MAX = 100000;
        while(!nFound){
            if(base == BASE_MAX){
                nFound = true;
                n = BASE_MAX / 10;
            }
            else if(i % base == i){
                nFound = true;
                n = base / 10;
            }
            base = base * 10;
        }
        double x = Math.log10(n);
        double y = 0;
        if (n < 10){
            y += 1;
        }
        if (n < 1000) {
            y += 1;
        }
        int padding = (int) Math.floor((4-x)*6 - y);
        System.out.printf("i:%s: n:%s | Pad: %s\n", i, n, padding);
        return padding;
    }

    public void setText(String t) {
        this.text.setText(t);
    }

    public void setNumberHeader(String t) {
        this.numberHeader.setText(t);
    }
    public String getText(){
        return this.text.getText();
    }
    public String getNumberHeader(){
        return this.numberHeader.getText();
    }
}

