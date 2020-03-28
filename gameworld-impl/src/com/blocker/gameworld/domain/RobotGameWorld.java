package com.blocker.gameworld.domain;

import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworld.domain.grid.GameGrid;
import com.blocker.gameworld.domain.robot.Robot;
import com.blocker.gameworld.ui.GameWorldComponent;

import java.awt.*;

public class RobotGameWorld implements GameWorldApi {
    private GameGrid grid = new GameGrid();
    private Location startLocation = new Location(new GridPosition(2,2), Orientation.EAST);
    private GridPosition goal = new GridPosition(4,4);
    private Robot robot;

    @Override
    public void drawOnCanvas(Graphics graphics) {
        new GameWorldComponent().draw(graphics, this);
    }

    @Override
    public void moveForward() {

    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    public boolean isFacingAWall() {
        return false;
    }

    @Override
    public boolean isGoalReached(){
        return false;
    }

    @Override
    public void reset() {
        robot = new Robot(startLocation);
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