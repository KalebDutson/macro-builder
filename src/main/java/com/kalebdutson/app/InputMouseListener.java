package com.kalebdutson.app;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
public class InputMouseListener implements NativeMouseInputListener{

    @Override
    public void nativeMouseMoved(NativeMouseEvent e){
        if(App.DEBUG && App.MOVE_DEBUG){
            System.out.printf("Mouse MOVED - NativeMouseEvent Pos: (%s, %s)%n", e.getX(), e.getY());
        }
    }

    @Override
    // Drag events are a "Click and Hold while moving the mouse"
    public void nativeMouseDragged(NativeMouseEvent e){
        if(App.DEBUG && App.MOVE_DEBUG){
            System.out.printf("Mouse DRAGGED - NativeMouseEvent: (%s, %s)%n", e.getX(), e.getY());
        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e){
        if(App.DEBUG){
            System.out.println("--- Mouse Press ---");
            System.out.printf("Mouse Button: %s%n", e.getButton());
            System.out.printf("Pos: (%s ,%s)%n", e.getX(), e.getY());
            System.out.printf("When: %s%n", e.getWhen());
            System.out.printf("Click Count: %s%n", e.getClickCount());
            System.out.printf("Point: %s%n%n", e.getPoint());
        }
    }
}
