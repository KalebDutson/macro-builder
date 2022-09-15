package com.kalebdutson.app;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class KeyAction extends Action{
    private NativeKeyEvent ne;

    /**
     * Create a KeyAction instance for a specific keystroke at a specific time
     * @param when Time when macro is executed in milliseconds
     * @param ne NativeKeyEvent for key press info and modifiers
     */
    public KeyAction(long when, NativeKeyEvent ne){
        super(when);
        this.ne = ne;
    }

    public int getKeyCode() {
        return this.ne.getKeyCode();
    }

    // TODO: Implement other methods

}
