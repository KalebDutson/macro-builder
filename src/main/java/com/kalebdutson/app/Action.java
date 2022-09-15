package com.kalebdutson.app;


/**
 * Input action from the keyboard or mouse
 */
public abstract class Action {
    private final long when;    // When action was executed in milliseconds

    public Action(long when){
        this.when = when;
    }
    public float getWhen() {
        return this.when;
    }
}
