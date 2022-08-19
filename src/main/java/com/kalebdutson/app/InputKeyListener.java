package com.kalebdutson.app;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
public class InputKeyListener implements NativeKeyListener {
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (App.DEBUG) {
            System.out.println("--- NativeKeyEvent ---");
            System.out.println("Source Class: NewMacroWindow");
            System.out.printf("KeyCode: %s%n", e.getKeyCode());
            System.out.printf("KeyLocation: %s%n", e.getKeyLocation());
            System.out.printf("Id: %s%n", e.getID());
            System.out.printf("When: %s%n", e.getWhen());
            System.out.printf("KeyText: %s%n\n", NativeKeyEvent.getKeyText(e.getKeyCode()));
        }
    }
}
