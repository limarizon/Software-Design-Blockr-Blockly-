package com.blocker.gameworldType.api;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;

import java.util.List;

public interface GameWorldTypeApi {
    List<Action> getActions();

    List<Predicate> getPredicates();

    GameWorldApi createGameWorldInstance();

}
