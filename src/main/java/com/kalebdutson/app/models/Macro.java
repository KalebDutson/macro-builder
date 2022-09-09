package com.kalebdutson.app.models;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Macro {

    private int iterations;
    private ArrayList<NativeMouseEvent> mouseEvents;
    private ArrayList<NativeKeyEvent> keyEvents;
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
    public ArrayList<NativeKeyEvent> getKeyEvents(){
        return this.keyEvents;
    }
    public ArrayList<NativeMouseEvent> getMouseEvents(){
        return this.mouseEvents;
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
    public void setKeyEvents(ArrayList<NativeKeyEvent> keyEvents){
        this.keyEvents = keyEvents;
    }
    public void setMouseEvents(ArrayList<NativeMouseEvent> mouseEvents){
        this.mouseEvents = mouseEvents;
    }
    // Add items
    public void addKeyEvent(NativeKeyEvent keyEvent){
        this.keyEvents.add(keyEvent);
    }
    public void addMouseEvent(NativeMouseEvent mouseEvent){
        this.mouseEvents.add(mouseEvent);
    }

}
