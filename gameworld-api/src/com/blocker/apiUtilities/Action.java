package com.blocker.apiUtilities;

/**
 * This interface represents the actions that can be implemented by a game world Type implementation. The actions can be
 * used by a game world API client to change the state of the game world implementation.
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 1.0
 */
public interface Action {
    /**
     * To execute an action and changing the state of the gameWorld implementation. It also gives an indication
     * whether the action is performed or illegal.
     * @return a boolean indicating whether the action is performed or illegal
     */
    boolean execute();

    /**
     * Gives the short but clear name that describes the kind of action to be executed
     * @return the name that describes the action
     */
    String getName();
}
