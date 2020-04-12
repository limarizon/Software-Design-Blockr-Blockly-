package com.blocker.apiUtilities.actions;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.domain.RobotGameWorld;
import com.blocker.gameworld.domain.robot.Robot;

public class TurnLeftAction implements Action {
    private RobotGameWorld game;
    public TurnLeftAction(RobotGameWorld game){
        this.game=game;
    }
    @Override
    public boolean execute() {
        return game.turnLeft();
    }

    @Override
    public String getName() {
        return "Turn Left";
    }
}
