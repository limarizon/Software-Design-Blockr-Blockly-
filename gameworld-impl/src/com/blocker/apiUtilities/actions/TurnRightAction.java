package com.blocker.apiUtilities.actions;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.domain.RobotGameWorld;

/**
 * This class represents the action of turning right in the gameworld
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 1.0
 */
public class TurnRightAction implements Action {
    private RobotGameWorld game;
    /**
     * constructor for the Action
     * @param game an object of type RobotGameWorld
     */
    public TurnRightAction( RobotGameWorld game){
        this.game = game;
    }
    @Override
    public boolean execute() { return game.turnRight();
    }
    @Override
    public String getName() {
        return "Turn Right";
    }
}
