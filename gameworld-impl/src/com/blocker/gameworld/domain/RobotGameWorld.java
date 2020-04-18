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

public class RobotGameWorld implements GameWorldApi {
    private GameGrid grid = new GameGrid();
    private Location startLocation = new Location(new GridPosition(1,1), Orientation.EAST);
    private GridPosition goal = new GridPosition(3,3);
    private Robot robot;

    @Override
    public void draw(Graphics graphics) {
        new GameWorldComponent().draw(graphics, this);
    }

    public boolean moveForward() {
        if(isGoalReached()||isFacingAWall())
            return false;
        robot.moveForward();
        return true;
    }

    public boolean turnLeft() {
        if(isGoalReached())
            return false;
        robot.turnLeft();
        return true;
    }

    public boolean turnRight() {
        if(isGoalReached())
            return false;
        robot.turnRight();
        return true;
    }

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

    public GameGrid getGrid() {
        return grid;
    }

    public GridPosition getGoalPosition() {
        return goal;
    }

    public Robot getRobot() {
        return robot;
    }
}