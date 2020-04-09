package com.blocker.gameworld.domain;

import com.blocker.apiUtilities.*;
import com.blocker.apiUtilities.actions.MoveForwardAction;
import com.blocker.apiUtilities.actions.TurnLeftAction;
import com.blocker.apiUtilities.actions.TurnRightAction;
import com.blocker.apiUtilities.predicates.RobotIsFacingAWallPredicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworldType.api.GameWorldTypeApi;


public class GameWorldType implements GameWorldTypeApi {
    RobotGameWorld gameWorld;
    private Action[] actions;
    private  Predicate[] predicates;

    public GameWorldType(RobotGameWorld game){
        this.gameWorld = game;
        actions = new Action[]{new MoveForwardAction(gameWorld), new TurnLeftAction(gameWorld), new TurnRightAction(gameWorld)};
        predicates = new Predicate[]{new RobotIsFacingAWallPredicate(gameWorld)};
    }

    @Override
    public Action[] getActions() {
        return actions ;
    }

    @Override
    public Predicate[] getPredicates() {
        return predicates;
    }

    @Override
    public GameWorldApi CreateGameWorldInstance() {
        GameWorldApi gameWorld = (GameWorldApi) this.gameWorld;
        gameWorld.reset();
        return gameWorld;
    }
}
