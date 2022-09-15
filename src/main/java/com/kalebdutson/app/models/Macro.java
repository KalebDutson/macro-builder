package com.kalebdutson.app.models;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.kalebdutson.app.KeyAction;
import com.kalebdutson.app.MouseAction;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Macro {

    private int iterations;
    private ArrayList<MouseAction> mouseActions;
    private ArrayList<KeyAction> keyActions;
    private String title;
    private LocalDateTime createDate;

    // Getters
    public String getTitle(){
        return this.title;
    }
    public LocalDateTime getCreateDate(){
        return this.createDate;
    }
    public int getIterations(){
        return this.iterations;
    }
    public ArrayList<KeyAction> getKeyActions(){
        return this.keyActions;
    }
    public ArrayList<MouseAction> getMouseActions(){
        return this.mouseActions;
    }

    // Setters
    public void setTitle(String title){
        this.title = title;
    }
    public void setCreateDate(LocalDateTime dateTime){
        this.createDate = dateTime;
    }
    public void setIterations(int i){
        this.iterations = i;
    }
    public void setKeyActions(ArrayList<KeyAction> keyActions){
        this.keyActions = keyActions;
    }
    public void setMouseActions(ArrayList<MouseAction> mouseActions){
        this.mouseActions = mouseActions;
    }

    // Add items
    public void addKeyAction(KeyAction keyAction){
        this.keyActions.add(keyAction);
    }

    public void addKeyAction(NativeKeyEvent ke){
        this.keyActions.add(new KeyAction(ke.getWhen(), ke));
    }
    public void addMouseAction(MouseAction mouseAction){
        this.mouseActions.add(mouseAction);
    }
    
    public void addMouseAction(NativeMouseEvent me){
        this.mouseActions.add(new MouseAction(me.getWhen(), me));
    }

}
