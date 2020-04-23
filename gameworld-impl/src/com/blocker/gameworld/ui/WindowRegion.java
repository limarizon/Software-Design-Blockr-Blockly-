package com.blocker.gameworld.ui;

import java.awt.*;

/**
 * the class represents a box region in the window with four coordinates that which a compontent or containers has.
 * this som useful functions to see if the window contains a position or to shrink te region on each side with a width
 */
public class WindowRegion {

    /**
     * The constant EMPTY.
     */
    public static WindowRegion EMPTY = new WindowRegion(0, 0, 0, 0);

    /**
     * Get minimal x value of the region.
     *
     * @return the int
     */
    public int getMinX(){
        return x;
    }

    private int x;

    /**
     * Get minimal y value of the region.
     *
     * @return the int
     */
    public int getMinY(){
        return y;
    }

    private int y;

    /**
     * Get the width value of the region.
     *
     * @return the width
     */
    public int getWidth(){
        return width;
    }

    private int width;

    /**
     * Get the height value of the region.
     *
     * @return the height
     */
    public int getHeight(){
        return height;
    }

    private int height;

    /**
     * Get max x value of the region.
     *
     * @return the minimal x value + width
     */
    public int getMaxX(){
        return x + width;
    }

    /**
     * Get max y value of the region.
     *
     * @return the minimal y value + height
     */
    public int getMaxY(){
        return y + height;
    }

    /**
     * Instantiates a new Window region.
     *
     * @param x1 the x value of the origin
     * @param y1 the y value of the origin
     * @param x2 the x value of the width
     * @param y2 the y value of the height
     */
    public WindowRegion(int x1, int y1, int x2, int y2){
        this.x = x1;
        this.y = y1;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }

    /**
     * A boolean to check if a certain window position is contained in this window region.
     *
     * @param position the position to check
     * @return the boolean
     */
    public boolean contains(WindowPosition position){
        return position.getX() >= getMinX() && position.getX() <= getMaxX() && position.getY() >= getMinY() && position.getY() <= getMaxY();
    }

    /**
     * Is a boolean to check whether a region is empty
     *
     * @return the boolean
     */
    public boolean isEmpty(){
        return width <= 0 || height <= 0;
    }

    /**
     * this translates the argument graphic object to a new place and area
     *
     * @param graphics a java graphics object
     * @return a graphics object with the windowregion instance its measurements
     */
    public Graphics create(Graphics graphics){
        return graphics.create(getMinX(), getMinY(), getWidth(), getHeight());
    }

    /**
     * Shrink region window region.
     *
     * @param property the property
     * @return the window region
     */
    public WindowRegion shrinkRegion(BoxProperty property){
        return new WindowRegion(
                getMinX() + property.getLeft(),
                getMinY() + property.getTop(),
                getMaxX() - property.getRight(),
                getMaxY() - property.getBottom());
    }

    /**
     * this converts a graphic object in the region it will take in the window
     *
     * @param graphics a java graphics object
     * @return a new bounding WindowRegion from the current clipping area
     */
    public static WindowRegion fromGraphics(Graphics graphics){
        var clipRect = graphics.getClipBounds();
        return new WindowRegion((int)clipRect.getMinX(), (int)clipRect.getMinY(), (int)clipRect.getMaxX(), (int)clipRect.getMaxY());
    }
}
