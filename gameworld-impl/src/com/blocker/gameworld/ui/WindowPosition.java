package com.blocker.gameworld.ui;

/**
 * this is the class that contains the position in the window
 */
public class WindowPosition {

    /**
     * Get x value.
     *
     * @return the x value
     */
    public int getX(){
        return x;
    }

    private final int x;

    /**
     * Get y value.
     *
     * @return the y value
     */
    public int getY(){
        return y;
    }

    private final int y;

    /**
     * Instantiates a new Window position.
     *
     * @param x the x value of the window position
     * @param y the y value of the window position
     */
    public WindowPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * adds the coordinates of an other window position to this window position
     *
     * @param other the other window position
     * @return the new window position
     */
    public WindowPosition plus(WindowPosition other){
        return new WindowPosition(x + other.getX(), y + other.getY());
    }

    /**
     * reduces the coordinates of this window position with that of an other window position.
     *
     * @param other the other window position
     * @return the new window position
     */
    public WindowPosition minus(WindowPosition other){
        return new WindowPosition(x - other.getX(), y - other.getY());
    }
}
