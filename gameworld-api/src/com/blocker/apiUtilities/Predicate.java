package com.blocker.apiUtilities;
/**
 * This interface represents the predicates that can be implemented by a game world Type implementation. The predicates can be
 * used by a game world API client to be evaluated so it can determine the flow of actions in the game world API client.
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 1.0
 */
public interface Predicate {
    /**
     * To evaluate a predicate that is dependent on the state of the game world API implementation.
     * Its gives back an indication whether the game world API implementation is about to go in an illegal state or success state.
     * @return a boolean indicating whether the game world API implementation is about to go in an illegal state or success state
     */
    boolean evaluate();

    /**
     * Gives the short but clear name that describes the kind predicate to be evaluated
     * @return the name that describes the predicate
     */
    String getName();
}
