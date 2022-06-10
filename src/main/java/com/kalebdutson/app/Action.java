package com.kalebdutson.app;

import java.awt.*;

/**
 * Input action from the keyboard or mouse
 */
public class Action {
    private float positionX;
    private float positionY;
    private float executionStartMillis;

    /**
     * Creates an Action instance with a specified execution time.
     * @param millis After macro execution, the amount of time until this action is executed.
     */
    public Action(float millis){
        if(millis < 0){
            throw new IllegalArgumentException("Must provide a positive value for \"millis\"");
        }
        this.executionStartMillis = millis;
    }

    /**
     * Creates an Action instance with a specific screen location.
     * @param x X position of the Action on the screen.
     * @param y Y position of the Action on the screen.
     */
    public Action(float x, float y){
        if(x < 0) {
            throw new IllegalArgumentException("Must provide a positive value for \"x\"");
        }
        if(y < 0){
            throw new IllegalArgumentException("Must provide a positive value for \"y\"");
        }
        this.positionX = x;
        this.positionY = y;
    }

    /**
     * Creates an Action instance with a specific screen location and execution time.
     * @param x X position of the Action on the screen.
     * @param y Y position of the Action on the screen.
     * @param millis After macro execution, the amount of time until this action is executed.
     */
    public Action(float x, float y, float millis){
        if(millis < 0){
            throw new IllegalArgumentException("Must provide a positive value for \"millis\"");
        }
        if(x < 0) {
            throw new IllegalArgumentException("Must provide a positive value for \"x\"");
        }
        if(y < 0){
            throw new IllegalArgumentException("Must provide a positive value for \"y\"");
        }
        this.positionX = x;
        this.positionY = y;
        this.executionStartMillis = millis;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getExecutionStartMillis() {
        return executionStartMillis;
    }

    public void setPositionX(float x){
        this.positionX = x;
    }

    public void setPositionY(float y){
        this.positionY = y;
    }

    public void setExecutionStartMillis(float millis){
        this.executionStartMillis = millis;
    }
}
