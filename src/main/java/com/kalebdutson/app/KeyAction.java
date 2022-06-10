package com.kalebdutson.app;

public class KeyAction extends Action{
    private int keyCode;

    /**
     * Create a KeyAction instance for a specific keystroke at a specific time
     * @param keycode NativeKeyEvent keycode
     * @param millis After macro execution, the amount of time until this action is executed.
     */
    public KeyAction(int keycode, float millis){
        super(millis);
        this.keyCode = keycode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int event) {
        this.keyCode = event;
    }

}
