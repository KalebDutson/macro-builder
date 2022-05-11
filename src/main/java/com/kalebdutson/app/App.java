package com.kalebdutson.app;

import javax.swing.*;

public class App
{
    public static void main( String[] args ) {
        // To run jnativehook, use the invokeLater method since jnativehook doesn't
        //  operate on the event dispatch thread
        SwingUtilities.invokeLater(NewMacroWindow::new);
    }
}
