package com.kalebdutson.app;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

public class Hotkey {
    private final int MAX_HOTKEY_LENGTH = 3;

    private int[] keys = new int[MAX_HOTKEY_LENGTH];    // Windows key codes

    public Hotkey(int[] keys){
        this.setKeys(keys);
    }

    public void setKeys(int[] keys){
        assert keys.length == 3;
        this.keys = keys;
    }

    public int[] getKeys(){
        return this.keys;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<MAX_HOTKEY_LENGTH; i++){
            if(i != 0){
                sb.append(NativeKeyEvent.getKeyText(keys[i]));
                if(i < MAX_HOTKEY_LENGTH - 1){
                    sb.append("+");
                }
            }
        }
        return sb.toString();
    }
}
