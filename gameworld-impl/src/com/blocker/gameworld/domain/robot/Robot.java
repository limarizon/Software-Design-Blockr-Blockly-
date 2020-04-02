package com.blocker.gameworld.domain.robot;

import com.blocker.gameworld.domain.GridPosition;
import com.blocker.gameworld.domain.Location;
import com.blocker.gameworld.domain.Orientation;

public class Robot {
    private Location location;

    public Robot(Location startPosition) {
        this.location = startPosition;
    }

    public GridPosition getPosition() {
        return location.getGridPosition();
    }

    public Orientation getOrientation() {
        return location.getOrientation();
    }

    public void moveForward() {
        location = location.move();
    }

    public void turnLeft() {
        location = location.turnLeft();
    }

    public void turnRight() {
        location = location.turnRight();
    }
}
