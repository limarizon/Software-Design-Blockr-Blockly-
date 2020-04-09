package com.blocker.apiUtilities.predicates;

import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.domain.RobotGameWorld;

public class RobotIsFacingAWallPredicate implements Predicate {
    private RobotGameWorld game;
    public RobotIsFacingAWallPredicate(RobotGameWorld game){
        this.game = game;
    }
    @Override
    public boolean evaluate() {
        return game.isFacingAWall();
    }

    @Override
    public String getName() {
        return "Is Facing A Wall";
    }
}
