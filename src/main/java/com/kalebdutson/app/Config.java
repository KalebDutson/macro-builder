package com.kalebdutson.app;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class Config {
    private final String START_HOTKEY_KEY = "START_HOTKEY";
    private final String STOP_HOTKEY_KEY = "STOP_HOTKEY";
    private Properties properties;
    private Hotkey startHotkey;
    private Hotkey stopHotkey;
    private final File configFile = new File("config.txt");


    public Config() {
        this.properties = new Properties();
        load();
    }

    /**
     * Load all relevant config data such as hotkey registrations.
     */
    private void load() {
        startHotkey = new Hotkey();
        stopHotkey = new Hotkey();
        try (FileInputStream in = new FileInputStream(configFile);) {
            properties.load(in);
//            startHotkey = new Hotkey(properties.get(START_HOTKEY_KEY));
//            properties.get(STOP_HOTKEY_KEY)
            // TODO: parse string and put to hotkey
            System.out.println(properties.get(START_HOTKEY_KEY));

        } catch (FileNotFoundException ex) {
            System.out.println("LOAD ERR: Couldn't find config file, creating one.");
            createConfigFile();

        } catch (IOException e) {
            System.err.println("LOAD ERR: Caught unhandled IOException when loading config");
            e.printStackTrace();
            System.exit(4);
        }
    }

    /**
     * Generate a new empty config file if one does not already exist.
     */
    private void createConfigFile(){
        try{
            if(!configFile.createNewFile()){
                throw new IOException("The config file already exists");
            }
        } catch (IOException e) {
            System.err.println("Couldn't create config file");
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void setHotkeys(Hotkey startHotkey, Hotkey stopHotkey){
        this.startHotkey = startHotkey;
        this.stopHotkey = stopHotkey;
        properties.put(START_HOTKEY_KEY, Arrays.toString(startHotkey.getKeys().toArray()));
        properties.put(STOP_HOTKEY_KEY, Arrays.toString(stopHotkey.getKeys().toArray()));
    }

    public void setStartHotkey(Hotkey startHotkey){
        this.startHotkey = startHotkey;
        properties.put(START_HOTKEY_KEY, Arrays.toString(startHotkey.getKeys().toArray()));
    }

    public void setStopHotkey(Hotkey stopHotkey){
        this.stopHotkey = stopHotkey;
        properties.put(STOP_HOTKEY_KEY, Arrays.toString(stopHotkey.getKeys().toArray()));
    }

    /**
     * Get hotkey data
     * @return [StartHotkey, StopHotkey]
     */
    public Hotkey[] getHotkeys(){
        return new Hotkey[]{startHotkey, stopHotkey};
    }

    /**
     * Save hotkeys to config file
     */
    public void saveHotkeys(){
        try(FileOutputStream out = new FileOutputStream(configFile)){
            properties.store(out, "Hotkeys for starting and stopping macro recording.");
        } catch(IOException e){
            System.err.println("SAVE ERR: Couldn't save config data");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(5);
        }

    }
}
