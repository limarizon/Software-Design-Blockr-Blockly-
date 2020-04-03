package com.blocker.gameworld.domain;

public class Location {
    private GridPosition gridPosition;
    private Orientation orientation;

    public Location(GridPosition gridPosition, Orientation orientation) {
        this.gridPosition = gridPosition;
        this.orientation = orientation;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Location moveForward() {
        return new Location(gridPosition.move(orientation.getOffset()), orientation);
    }

    public Location moveBackward() { return new Location(gridPosition.move(orientation.getOffset().reverseOffset()), orientation);}

    public Location turnLeft() {
        return new Location(gridPosition, orientation.turnLeft());
    }

    public Location turnRight() {
        return new Location(gridPosition, orientation.turnRight());
    }
}
