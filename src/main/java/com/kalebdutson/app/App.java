package com.kalebdutson.app;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;

public class App
{
    public static void main( String[] args ) {
        Config config = new Config();
        // To run jnativehook, use the invokeLater method since jnativehook doesn't
        //  operate on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new NewMacroWindow(config);
                    }
                }
        );
    }
    public static void registerHook(NativeKeyListener listener, String classname){
        try{
            GlobalScreen.registerNativeHook();
        }
        catch(NativeHookException ex){
            System.err.printf("There was a problem registering the hook for class: %s%n", classname);
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(listener);
    }

    public static void unregisterHook(String classname){
        // Clean up the native hook
        try {
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.printf("Encountered error unregistering Nativehook for class: %s%n", classname);
            throw new RuntimeException(ex);
        }
    }
}
