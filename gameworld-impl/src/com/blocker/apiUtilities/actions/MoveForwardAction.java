package com.blocker.apiUtilities.actions;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.domain.RobotGameWorld;


public class MoveForwardAction implements Action {
    private RobotGameWorld game;
    public MoveForwardAction(RobotGameWorld game){
        this.game=game;
    }
    @Override
    public void execute() {
       game.moveForward();
    }

    @Override
    public String getName() {
        return "MoveForward";
    }
}
