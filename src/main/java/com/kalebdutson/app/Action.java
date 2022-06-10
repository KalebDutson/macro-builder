package com.kalebdutson.app;

import java.awt.*;

/**
 * Input action from the keyboard or mouse
 */
public abstract class Action {
    private float executionStartMillis;

    /**
     * Action with a specified execution time.
     * @param millis After macro execution, the amount of time until this action is executed.
     */
    public Action(float millis){
        if(millis < 0){
            throw new IllegalArgumentException("Must provide a positive value for \"millis\"");
        }
        this.executionStartMillis = millis;
    }

    public float getExecutionStartMillis() {
        return executionStartMillis;
    }

    public void setExecutionStartMillis(float millis){
        this.executionStartMillis = millis;
    }
}
