package com.ui.components.container.div;

/**
 * this class contains the width of each side of a box component; this box consists of a margin,border,padding and content
 */
public abstract class BoxProperty {

    /**
     * Get top int.
     *
     * @return top
     */
    public int getTop(){
        return top;
    }

    private final int top;

    /**
     * Get right int.
     *
     * @return right
     */
    public int getRight(){
        return right;
    }

    private final int right;

    /**
     * Get bottom int.
     *
     * @return bottom
     */
    public int getBottom(){
        return bottom;
    }
  
    private final int bottom;

    /**
     * Get left int.
     *
     * @return left
     */
    public int getLeft(){
        return left;
    }

    private final int left;

    /**
     * Instantiates a new Box property.
     *
     * @param top    the top
     * @param right  the right
     * @param bottom the bottom
     * @param left   the left
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
     * TODO: complete this documentation, what is Value???
     * Throws an exception if a certain boxproperty is negative.
     * @param value an int being
     * @param name
     */
    private void throwIfNegative(int value, String name){
        if(value >= 0)
            return;
        throw new IllegalArgumentException(String.format("%s must not be negative, value: %d", name, value));
    }

    /**
     * Instantiates a new Box property.
     *
     * @param value the value for all sides of the boxproperty
     */
    public BoxProperty(int value){
        this(value, value, value, value);
    }

}
