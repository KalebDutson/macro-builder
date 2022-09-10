package com.kalebdutson.app.controllers;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.kalebdutson.app.models.Macro;

/**
 * Controls all data flowing between Views and Models
 */
public class Controller {
    private Macro activeMacro;


    /**
     * Record mouse and keystrokes until termination hotkey is pressed
     */
    // TODO: add termination hotkey listener
    // TODO: create format to save recorded events
    public void record(){
        NativeMouseEvent e = new NativeMouseEvent(1, 0, 0, 0, 0);
        System.out.println(e.getPoint());

    }

    public void stopRecording(){

    }

    /**
     * Playback the recorded mouse and keystrokes.
     */
    // TODO: add playback feature
    public void playback(){

    }
}
