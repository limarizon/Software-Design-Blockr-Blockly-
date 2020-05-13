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
import purecollections.PList;


/**
 * The class Robot game world type which is the implementation of the GameWorldTypeApi.
 *
 */
public class RobotGameWorldType implements GameWorldTypeApi {
    private RobotGameWorld gameWorld = new RobotGameWorld();
    
    @Override
    public PList<Action> getActions() {
        return PList.<Action>empty().plus(new MoveForwardAction(gameWorld)).plus(new TurnLeftAction(gameWorld)).plus(new TurnRightAction(gameWorld));
    }

    @Override
    public PList<Predicate> getPredicates() {
        return PList.<Predicate>empty().plus(new RobotIsFacingAWallPredicate(gameWorld)).plus(new GoalisReachedPredicate(gameWorld));
    }

    @Override
    public GameWorldApi createGameWorldInstance() {
        gameWorld.reset();
        return gameWorld;
    }
}
