package com.kalebdutson.app;

import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.awt.event.MouseEvent;

public class MouseAction extends Action{
    private NativeMouseEvent mouseEvent;
    /**
     * Creates a MouseAction with a specific screen location.
     * @param event NativeMouseEvent executed for this action.
     * @param millis After macro execution, the amount of time until this action is executed.
     */
    public MouseAction(NativeMouseEvent event, float millis){
        super(millis);
        if(event.getX() < 0 || event.getY() < 0) {
            throw new IllegalArgumentException("NativeMouseEvent must have positive x and y position.");
        }
        this.mouseEvent = event;
    }

    public NativeMouseEvent getNativeMouseEvent(){
        return this.mouseEvent;
    }

    public void setMouseEvent(NativeMouseEvent event){
        this.mouseEvent = event;
    }

    public int getPositionX() {
        return mouseEvent.getX();
    }

    public int getPositionY() {
        return mouseEvent.getY();
    }
}
