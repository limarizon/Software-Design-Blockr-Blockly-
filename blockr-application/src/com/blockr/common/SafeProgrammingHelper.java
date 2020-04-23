package com.blockr.common;

/**
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 * This class has some useful functions that are used frequently to check arguments of functions
 */
public class SafeProgrammingHelper {
    /**
     * Checker for not having null objects in the world
     * @param object provide an object of any class
     * @param name the objects name to identify the same object
     */
    public static void throwIfNull(Object object, String name){
        if(object != null)
            return;

        throw new IllegalArgumentException(String.format("%s must be effective", name));
    }
}
