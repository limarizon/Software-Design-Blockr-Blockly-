package com.blocker.apiUtilities.actions;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.domain.RobotGameWorld;

/**
 * This class represents the action of moving forward in the gameworld
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 1.0
 */
public class MoveForwardAction implements Action {
    private RobotGameWorld game;

    /**
     * constructor for the Action
     * @param game an object of type RobotGameWorld
     */
    public MoveForwardAction(RobotGameWorld game){
        this.game=game;
    }
    @Override
    public boolean execute() {
       return game.moveForward();
    }

    @Override
    public String getName() {
        return "MoveForward";
    }
}
