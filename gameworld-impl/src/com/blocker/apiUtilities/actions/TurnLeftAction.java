package com.blocker.apiUtilities.actions;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.domain.RobotGameWorld;
import com.blocker.gameworld.domain.robot.Robot;

/**
 * This class represents the action of turning left in the gameworld
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 1.0
 */
public class TurnLeftAction implements Action {
    private RobotGameWorld game;
    /**
     * constructor for the Action
     * @param game an object of type RobotGameWorld
     */
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
