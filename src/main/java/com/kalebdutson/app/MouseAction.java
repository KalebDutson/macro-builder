package com.kalebdutson.app;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.event.MouseEvent;
public class MouseAction extends Action{
    private NativeMouseEvent me;

    /**
     * Creates a MouseAction with a specific screen location.
     * @param when Time when macro is executed.
     * @param me NativeMouseEvent executed for this action.
     *
     */
    public MouseAction(long when, NativeMouseEvent me){
        super(when);
        this.me = me;
    }

    // TODO: Add methods
}
