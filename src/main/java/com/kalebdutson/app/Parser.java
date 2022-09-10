package com.kalebdutson.app;

import com.kalebdutson.app.models.Macro;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.time.LocalDateTime;

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
            JSONArray keyEvents = (JSONArray)data.get("keyEvents");
            JSONArray mouseEvents = (JSONArray)data.get("mouseEvents");

            m.setTitle(title);
            m.setCreateDate(LocalDateTime.parse(createdAt));
            m.setIterations(iterations);
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
