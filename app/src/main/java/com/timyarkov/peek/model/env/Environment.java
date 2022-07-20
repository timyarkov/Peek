package com.timyarkov.peek.model.env;

import java.util.Map;

/**
 * Acts as the system's own environment
 */
public class Environment {
    public static String getEnv(String key) {
        //!!TODO somethingsomething file somewhere?
        return System.getenv(key); //!!TODO switchy probly
    }
}
