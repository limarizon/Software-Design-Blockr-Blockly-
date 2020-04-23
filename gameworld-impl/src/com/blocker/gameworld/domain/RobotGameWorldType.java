package com.blocker.gameworld.domain;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.apiUtilities.actions.MoveForwardAction;
import com.blocker.apiUtilities.actions.TurnLeftAction;
import com.blocker.apiUtilities.actions.TurnRightAction;
import com.blocker.apiUtilities.predicates.GoalisReachedPredicate;
import com.blocker.apiUtilities.predicates.RobotIsFacingAWallPredicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworldType.api.GameWorldTypeApi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class RobotGameWorldType implements GameWorldTypeApi {
    private RobotGameWorld gameWorld = new RobotGameWorld();
    private List<Action> actions;
    private List<Predicate> predicates;

    public RobotGameWorldType(){
        actions = Arrays.asList(new MoveForwardAction(gameWorld), new TurnLeftAction(gameWorld), new TurnRightAction(gameWorld));
        predicates = Arrays.asList(new RobotIsFacingAWallPredicate(gameWorld), new GoalisReachedPredicate(gameWorld));
    }

    @Override
    public List<Action> getActions() {
        return Collections.unmodifiableList(actions) ;
    }

    @Override
    public List<Predicate> getPredicates() {
        return Collections.unmodifiableList(predicates);
    }

    @Override
    public GameWorldApi createGameWorldInstance() {
        gameWorld.reset();
        return gameWorld;
    }
}
