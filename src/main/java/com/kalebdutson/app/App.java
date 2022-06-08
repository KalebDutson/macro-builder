package com.kalebdutson.app;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;

public class App
{
    static final Font FONT_A_PLAIN = new Font("Courier", Font.PLAIN, 10 );
    static final Font FONT_A_BOLD = new Font("Courier", Font.BOLD, 12);
    public static void main( String[] args ) {
        Config config = new Config();
        // To run jnativehook, use the invokeLater method since jnativehook doesn't
        //  operate on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new BuilderWindow(config);
                    }
                }
        );
    }
    public static <T> void registerHook(NativeKeyListener listener, Class<T> classType){
        try{
            GlobalScreen.registerNativeHook();
        }
        catch(NativeHookException ex){
            System.err.printf("There was a problem registering the hook for class: %s%n", classType.toString());
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(listener);
    }

    public static <T> void unregisterHook(Class<T> classType){
        // Clean up the native hook
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.printf("Encountered error unregistering Nativehook for class: %s%n", classType.toString());
            throw new RuntimeException(ex);
        }
    }
}
