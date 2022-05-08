package com.kalebdutson.app;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;


public class Macro {

    /**
     * Record mouse and keystrokes until termination hotkey is pressed
     */
    // TODO: add termination hotkey listener
    // TODO: create format to save recorded events
    public void record(){
        NativeMouseEvent e = new NativeMouseEvent(1, 0, 0, 0, 0);
        System.out.println(e.getPoint());

    }
    /**
     * Playback the recorded mouse and keystrokes.
     */
    // TODO: add playback feature
    public void playback(){

    }

}
