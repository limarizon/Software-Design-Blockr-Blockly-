package com.blocker.gameworld.domain;

/**
 * The class Location which takes a gridlocation and a orientation to define all information needed for a game world object
 * for example a robot.
 */
public class Location {
    private GridPosition gridPosition;
    private Orientation orientation;

    /**
     * Instantiates a new Location.
     *
     * @param gridPosition the grid position
     * @param orientation  the orientation
     */
    public Location(GridPosition gridPosition, Orientation orientation) {
        this.gridPosition = gridPosition;
        this.orientation = orientation;
    }

    /**
     * Gets grid position.
     *
     * @return the grid position
     */
    public GridPosition getGridPosition() {
        return gridPosition;
    }

    /**
     * Gets orientation.
     *
     * @return the orientation
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * Move location.
     *
     * @return the new location
     */
    public Location move() {
        return new Location(gridPosition.move(orientation.getOffset()), orientation);
    }

    /**
     * Turn left.
     *
     * @return the new location
     */
    public Location turnLeft() {
        return new Location(gridPosition, orientation.turnLeft());
    }

    /**
     * Turn right.
     *
     * @return the new location
     */
    public Location turnRight() {
        return new Location(gridPosition, orientation.turnRight());
    }
}
