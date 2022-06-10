package com.kalebdutson.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.annotation.Native;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Tests for KeyAction class
     */
    @Test
    public void testKeyAction(){
        int e  = NativeKeyEvent.VC_E;
        float start = 100.0F;
        KeyAction action = new KeyAction(e, start);
        assertEquals(action.getExecutionStartMillis(), start, 0.0);
        assertEquals(action.getKeyCode(), e, 0.0);

        float start2 = 1234.0F;
        int e2 = NativeKeyEvent.VC_B;
        action.setExecutionStartMillis(start2);
        action.setKeyCode(e2);
        assertEquals(action.getKeyCode(), e2, 0.0);
        assertEquals(action.getExecutionStartMillis(), start2, 0.0);
    }

    /**
     * Tests for MouseAction class
     */
    @Test
    public void testMouseAction(){
        int x = 0;
        int y = 1;
        NativeMouseEvent e = new NativeMouseEvent(0, 0, x, y, 1, NativeMouseEvent.BUTTON1);
        float start = 100.0F;

        MouseAction action = new MouseAction(e, start);
        assertEquals(action.getNativeMouseEvent(), e);
        assertEquals(action.getPositionX(), x, 0.0);
        assertEquals(action.getPositionY(), y, 0.0);
        assertEquals(action.getExecutionStartMillis(), start, 0.0);

        int x2 = 100;
        int y2 = 120;
        NativeMouseEvent e2 = new NativeMouseEvent(1, 0, x2, y2, 1, NativeMouseEvent.BUTTON2);
        float start2 = 1234.0F;

        action.setExecutionStartMillis(start2);
        action.setMouseEvent(e2);
        assertEquals(action.getNativeMouseEvent(), e2);
        assertEquals(action.getExecutionStartMillis(), start2, 0.0);

        boolean correctError = false;
        int x3 = -10;
        int y3 = -70;
        try{
            new NativeMouseEvent(2, 0, x3, y3, 1, NativeMouseEvent.BUTTON1);
        } catch(Exception ex){
            assertTrue(ex instanceof IllegalArgumentException);
        }
    }
}
