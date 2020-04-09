package com.blocker.apiUtilities.actions;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.domain.RobotGameWorld;


public class TurnRightAction implements Action {
    private RobotGameWorld game;
    public TurnRightAction( RobotGameWorld game){
        this.game = game;
    }
    @Override
    public void execute() { game.turnRight();
    }

    @Override
    public String getName() {
        return "Turn Right";
    }
}
