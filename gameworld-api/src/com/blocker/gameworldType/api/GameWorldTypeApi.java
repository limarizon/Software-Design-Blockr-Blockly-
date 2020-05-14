package com.blocker.gameworldType.api;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;

import java.awt.*;
import java.util.List;
import purecollections.PList;

/**
 * This interface offers methods to retrieve the list of Actions supported by the GameWorldType and
 * retrieve the list of Predicates supported by the GameWorldType.
 * It can also create a new game world instance, which implements interface GameWorld
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 1.0
 */
public interface GameWorldTypeApi {
    /**
     * To retrieve an immutable list of Actions interfaces implemented by the GameWorldType
     * @return An immutable list of Action interfaces
     */
    PList<Action> getActions();

    /**
     * To retrieve an immutable list of Predicate interfaces implemented by the GameWorldType
     * @return an immutable list of Predicate interfaces
     */
    PList<Predicate> getPredicates();

    /**
     * create a new game world instance, which implements interface GameWorld
     * @return a new game world instance
     */
    GameWorldApi createGameWorldInstance();

}
