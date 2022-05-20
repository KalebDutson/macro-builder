package com.kalebdutson.app;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.HashMap;
import java.util.Set;

public class Hotkey {
    private final int MAX_HOTKEY_LENGTH = 3;

//    private int[] keys = new int[MAX_HOTKEY_LENGTH];    // Windows key codes
    private HashMap<Integer, Boolean> keys;
    private int numKeys;

    public Hotkey(int[] keys){
        this.setAllKeys(keys);
    }
    public Hotkey(){
        keys = new HashMap<>();
        numKeys = 0;
    }

    public void setAllKeys(int[] keyIds){
        // TODO: set exception throwing
        assert keyIds.length == MAX_HOTKEY_LENGTH;
        numKeys = 0;
        keys.clear();
        for (int key : keyIds) {
            keys.put(key, true);
            numKeys += 1;
        }
    }

    public void addKey(int keyId){
        // TODO: set exception throwing
        if(numKeys < MAX_HOTKEY_LENGTH){
            keys.put(keyId, true);
            numKeys += 1;
        }
        else{
            System.err.println("Could not add key to Hotkey. Reached limit");
        }
    }

    public void removeKey(int keyId){
        keys.remove(keyId);
        numKeys -= 1;
    }
    public Set<Integer> getKeys(){
        return keys.keySet();
    }

    public boolean hasKey(int keyId){
        return keys.containsKey(keyId);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Object[] keysArray = getKeys().toArray();
        for(int i=0; i<MAX_HOTKEY_LENGTH; i++){
            if((int) keysArray[i] != 0){
                sb.append(NativeKeyEvent.getKeyText((int) keysArray[i]));
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
