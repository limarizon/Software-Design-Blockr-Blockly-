package com.blocker.gameworld.domain;

import java.util.Objects;

public class GridPosition {

    public int getX(){
        return x;
    }

    private final int x;

    public int getY(){
        return y;
    }

    private final int y;

    public GridPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Moves 1 index towards the facing orientation
     * @param offset: Depending on the orientation, will give a x,y offset for moving
     * @return Returns the new Position of the object
     */
    public GridPosition translate(Orientation.Offset offset) {
        return new GridPosition(getX() + offset.getX(), getY() + offset.getY());
    }

    /**
     * Checks if two positions are the same
     * @param o: an object of any type
     * @return
     * false if both position objects are not from the same class type, or if both position.getX() && position.getY() are not equal.
     * true if both objects are the same class type and have the same corresponing getX()&&getY() in both objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridPosition gridPosition = (GridPosition) o;
        return getX() == gridPosition.getX() &&
                getY() == gridPosition.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
