package com.blocker.apiUtilities.predicates;

import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.domain.RobotGameWorld;

public class GoalisReachedPredicate implements Predicate {
    private RobotGameWorld game;
    public GoalisReachedPredicate(RobotGameWorld game){
        this.game = game;
    }

    @Override
    public boolean evaluate() {
        return game.isGoalReached();
    }

    @Override
    public String getName() {
        return "On Goal";
    }
}
