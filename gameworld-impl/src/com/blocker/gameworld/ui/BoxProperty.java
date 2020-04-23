package com.blocker.gameworld.ui;

/**
 * this class contains the width of each side of a box component; this box consists of a margin,border,padding and content
 */
public abstract class BoxProperty {

    /**
     * Get top side length.
     *
     * @return the top length
     */
    public int getTop(){
        return top;
    }

    private final int top;

    /**
     * Get right side length.
     *
     * @return the right length
     */
    public int getRight(){
        return right;
    }

    private final int right;

    /**
     * Get bottom side length.
     *
     * @return the bottom length
     */
    public int getBottom(){
        return bottom;
    }
  
    private final int bottom;

    /**
     * Get left side length.
     *
     * @return the left length
     */
    public int getLeft(){
        return left;
    }

    private final int left;

    /**
     * Instantiates a new Box property.
     *
     * @param top    the top side length
     * @param right  the right side length
     * @param bottom the bottom side length
     * @param left   the left side length
     */
    public BoxProperty(int top, int right, int bottom, int left){

        throwIfNegative(top, "top");
        throwIfNegative(right, "right");
        throwIfNegative(bottom, "bottom");
        throwIfNegative(left, "left");

        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    /**
     * defensive method to check whether each passed argument is nog a negative integer
     * @param value integer to check
     * @param name a name to correspond given value with
     */
    private void throwIfNegative(int value, String name){
        if(value >= 0)
            return;
        throw new IllegalArgumentException(String.format("%s must not be negative, value: %d", name, value));
    }

    /**
     * Instantiates a new Box property.
     *
     * @param value the value for each side of the square box
     */
    public BoxProperty(int value){
        this(value, value, value, value);
    }

}
