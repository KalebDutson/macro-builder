package com.kalebdutson.app;

import java.awt.event.MouseEvent;

public class MouseAction extends Action{
    private float positionX;
    private float positionY;
    private MouseEvent mouseEvent;
    /**
     * Creates a MouseAction with a specific screen location.
     * @param event MouseEvent executed for this action.
     * @param x X position of the Action on the screen.
     * @param y Y position of the Action on the screen.
     * @param millis After macro execution, the amount of time until this action is executed.
     */
    public MouseAction(MouseEvent event, float x, float y, float millis){
        super(millis);
        if(x < 0) {
            throw new IllegalArgumentException("Must provide a positive value for \"x\"");
        }
        if(y < 0){
            throw new IllegalArgumentException("Must provide a positive value for \"y\"");
        }
        this.mouseEvent = event;
        this.positionX = x;
        this.positionY = y;
    }

    public MouseEvent getMouseEvent(){
        return this.mouseEvent;
    }

    public void setMouseEvent(MouseEvent event){
        this.mouseEvent = event;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }
    public void setPositionX(float x){
        this.positionX = x;
    }

    public void setPositionY(float y){
        this.positionY = y;
    }
}
