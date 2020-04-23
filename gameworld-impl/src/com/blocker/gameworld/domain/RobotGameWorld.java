package com.blocker.gameworld.domain;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworld.domain.grid.GameGrid;
import com.blocker.gameworld.domain.grid.TileType;
import com.blocker.gameworld.domain.robot.Robot;
import com.blocker.gameworld.ui.GameWorldComponent;
import com.blocker.apiUtilities.GameWorldSnapshot;
import com.blocker.apiUtilities.Snapshot;
import com.blocker.gameworldType.api.GameWorldTypeApi;

import java.awt.*;

/**
 * The RobotGameWorld which is an implementation of the GameWorldApi
 * It holds all the functionality needed to make a client act and process information on this gameworld impl.
 *
 */
public class RobotGameWorld implements GameWorldApi {
    private GameGrid grid = new GameGrid();
    private Location startLocation = new Location(new GridPosition(1,1), Orientation.EAST);
    private GridPosition goal = new GridPosition(3,3);
    private Robot robot;

    @Override
    public void draw(Graphics graphics) {
        new GameWorldComponent().draw(graphics, this);
    }

    /**
     * Move robot forward.
     *
     * @return true or false depending on the action being not illegal or illegal respectably
     */
    public boolean moveForward() {
        if(isGoalReached()||isFacingAWall())
            return false;
        robot.moveForward();
        return true;
    }

    /**
     * Turn robot left
     *
     * @return true or false depending on the action being not illegal or illegal respectably
     */
    public boolean turnLeft() {
        if(isGoalReached())
            return false;
        robot.turnLeft();
        return true;
    }

    /**
     * Turn robot right.
     *
     * @return true or false depending on the action being not illegal or illegal respectably
     */
    public boolean turnRight() {
        if(isGoalReached())
            return false;
        robot.turnRight();
        return true;
    }

    /**
     * boolean to check whether the robot is facing a wall or not.
     *
     * @return true if the robot is facing a wall
     * false if the robot is not facing of a wall
     */
    public boolean isFacingAWall() {
        return grid.getTileType(robot.getPosition().move(robot.getOrientation().getOffset())) == TileType.BLOCKED;
    }

    @Override
    public boolean isGoalReached(){
        return robot.getPosition().equals(goal);
    }

    @Override
    public void reset() {
        robot = new Robot(startLocation);
    }

    @Override
    public void restore(Snapshot snapshot){ robot = new Robot(((GameWorldSnapshot) snapshot).getLocation());}

    @Override
    public Snapshot createSnapshot() {
        return new GameWorldSnapshot(robot.getLocation());
    }

    @Override
    public boolean perform(Action action) {
        return action.execute();
    }

    @Override
    public boolean evaluate(Predicate predicate) {
        return predicate.evaluate();
    }

    /**
     * Gets the gameworld grid.
     *
     * @return the grid
     */
    public GameGrid getGrid() {
        return grid;
    }

    /**
     * Gets goal position.
     *
     * @return the goal gridposition
     */
    public GridPosition getGoalPosition() {
        return goal;
    }

    /**
     * Gets the robot.
     *
     * @return the robot
     */
    public Robot getRobot() {
        return robot;
    }
}