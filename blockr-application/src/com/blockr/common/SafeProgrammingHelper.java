package com.blockr.common;

public class SafeProgrammingHelper {
    /**
     * Checker for not having null objects in the world
     * @param object: provide an object of any class
     * @param name: the objects name to identify the same object
     */
    public static void throwIfNull(Object object, String name){
        if(object != null)
            return;

        throw new IllegalArgumentException(String.format("%s must be effective", name));
    }
}
