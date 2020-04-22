package com.blocker.gameworldType.api;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;

import java.util.List;

/**
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 1.0
 * This interface offers methods to retrieve the list of Actions supported by the GameWorldType and
 * retrieve the list of Predicates supported by the GameWorldType.
 * It can also create a new game world instance, which implements interface GameWorld
 */
public interface GameWorldTypeApi {
    /**
     * To retrieve a list of Actions interfaces implemented by the GameWorldType
     * @return A list of Action interfaces
     */
    List<Action> getActions();

    /**
     * retrieve the list of Predicate interfaces implemented by the GameWorldType
     * @return a list of Predicate interfaces
     */
    List<Predicate> getPredicates();

    /**
     * create a new game world instance, which implements interface GameWorld
     * @return a new game world instance
     */
    GameWorldApi createGameWorldInstance();

}
