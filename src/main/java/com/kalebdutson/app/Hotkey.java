package com.kalebdutson.app;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.Objects;

public class Hotkey {
    private final int MAX_HOTKEY_LENGTH = 3;

    private int[] keys = new int[MAX_HOTKEY_LENGTH];    // Windows key codes
    private int nextIndex;

    public Hotkey(int[] keys){
        this.setAllKeys(keys);
    }
    public Hotkey(){
        keys = new int[3];
        nextIndex = 0;
    }

    public void setAllKeys(int[] keys){
        // TODO: set exception throwing
        assert keys.length == MAX_HOTKEY_LENGTH;
        this.keys = keys;
        nextIndex = MAX_HOTKEY_LENGTH;
    }

    public void addKey(int keyId){
        // TODO: set exception throwing
        if(nextIndex < 3){
            keys[nextIndex] = keyId;
            nextIndex++;
        }
        else{
            System.err.println("Could not add key to Hotkey. Reached limit");
        }
    }

    public int[] getKeys(){
        return this.keys;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<MAX_HOTKEY_LENGTH; i++){
            if(keys[i] != 0){
                sb.append(NativeKeyEvent.getKeyText(keys[i]));
                if(i < MAX_HOTKEY_LENGTH - 1){
                    sb.append("+");
                }
            }
        }
        if(sb.length() == 0){
            sb.append("NONE");
        }
        return sb.toString();
    }
}
