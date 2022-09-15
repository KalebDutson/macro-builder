package com.kalebdutson.app;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class KeyAction extends Action{
    private NativeKeyEvent ke;

    /**
     * Create a KeyAction instance for a specific keystroke at a specific time
     * @param when Time when macro is executed in milliseconds
     * @param ke NativeKeyEvent for key press info and modifiers
     */
    public KeyAction(long when, NativeKeyEvent ke){
        super(when);
        this.ke = ke;
    }

    public int getKeyCode() {
        return this.ke.getKeyCode();
    }

    // TODO: Implement other methods

}
