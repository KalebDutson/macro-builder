package com.kalebdutson.app;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.kalebdutson.app.models.Macro;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Parses Macro objects to/from JSON format for saving/loading Macros into the MacroBuilder.
 */
public class Parser {
    private static final String[][] jsonKeys =  {{}, {}};
    public static boolean toJsonFile(Macro m){
        return true;
    }

    public static Macro loadMacroFromFile(String path) throws Exception {
        Macro m = new Macro();
        JSONParser p = new JSONParser();
        try{
            Object obj = p.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject)obj;
            String title = (String)jsonObject.get("title");
            String createdAt = (String)jsonObject.get("createdAt");
            JSONObject data = (JSONObject)jsonObject.get("data");
            int iterations = ((Long)data.get("iterations")).intValue();
            JSONArray keyEvents = (JSONArray) data.get("keyEvents");
            JSONArray mouseEvents = (JSONArray)data.get("mouseEvents");

//            m.setTitle(title);
//            m.setCreateDate(LocalDateTime.parse(createdAt));
//            m.setIterations(iterations);
            System.out.print("KeyEvents: ");
            for(int i=0; i<keyEvents.size(); i++){
                System.out.println(keyEvents.get(i));
                JSONArray o = (JSONArray) keyEvents.get(i);

                System.out.printf("[%s, %s, %s, %s, %s, %s]%n", o.get(0), o.get(1), o.get(2), o.get(3), o.get(4), o.get(5));

                KeyAction ke = new KeyAction(Long.parseLong(o.get(0).toString()),
                        new NativeKeyEvent(Integer.parseInt(o.get(1).toString()),
                                Integer.parseInt(o.get(2).toString()), Integer.parseInt(o.get(3).toString()),
                                Integer.parseInt(o.get(4).toString()), o.get(5).toString().charAt(0)));

                System.out.printf("Time: %d, ID: %s, %s%n", ke.getWhen(), ke.getNativeKeyEvent().getID(), ke.getNativeKeyEvent().paramString());
            }


//            m.setKeyEvents();
//            m.setMouseEvents();

        } catch(IOException e){
            throw new IOException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return m;
    }
}
