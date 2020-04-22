package com.blocker.gameworld.api;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.apiUtilities.Snapshot;

import java.awt.*;

/**
 * This interface describes behaviour of the game world API implementation. All the listed function must be implemented
 * by the the game world API implementation and are available for the game world API client to use.
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 1.0
 */
public interface GameWorldApi {
    /**
     * Draws the current state of the Game world API implementation on a given graphics object that can be visualised or altered
     * by the Game world API client
     * @param graphics  a graphics object te be drawn on
     */
    void draw(Graphics graphics);

    /**
     * Resets the current state of the Game World API implementation to its initial state by resetting all its components.
     */
    void reset();

    /**
     * Restore the current state of the Game World API implementation to a state at a certain point in time represented
     * by an opaque, immutable snapshot interface
     * @param snapshot a snapshot of the game world state
     */
    void restore(Snapshot snapshot);

    /**
     * Create a snapshot of the current state of the Game World API implementation as
     * an opaque, immutable snapshot interface
     * @return a snapshot object that can be stored and restored
     */
    Snapshot createSnapshot();

    /**
     * To execute an action and changing the state of the gameWorld implementation. It also gives an indication
     * whether the action is performed or illegal.
     * @param action a way of changing the state of the game world API implementation
     * @return a boolean indicating whether the action is performed or illegal
     */
    boolean perform(Action action);

    /**
     * To evaluate a predicate that is dependent on the state of the game world API implementation.
     * @param predicate a condition than can be evaluated and is dependent on the state of the game world API implementation
     * @return a boolean indicating whether the game world API implementation is about to go in an illegal state or success state
     */
    boolean evaluate(Predicate predicate);

    /**
     * Evaluate whether the game world API implementation has reached his final legal succes state.
     * @return a boolean indicating whether the game world API implementation has reached is final legal state
     */
    boolean isGoalReached();
}
