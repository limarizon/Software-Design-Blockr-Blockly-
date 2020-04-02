package com.ui;

import com.ui.components.div.BoxProperty;

import java.awt.*;

/**
 * the clas represents a box region in the window with four coordinates that which a compontent or containers has.
 * this som useful functions to see if the window contains a position or to shrink te region on each side with a width
 */
public class WindowRegion {

    public static WindowRegion EMPTY = new WindowRegion(0, 0, 0, 0);

    public int getMinX(){
        return x;
    }

    private int x;

    public int getMinY(){
        return y;
    }

    private int y;

    public int getWidth(){
        return width;
    }

    private int width;

    public int getHeight(){
        return height;
    }

    private int height;

    public int getMaxX(){
        return x + width;
    }

    public int getMaxY(){
        return y + height;
    }

    public WindowRegion(int x1, int y1, int x2, int y2){
        this.x = x1;
        this.y = y1;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }

    public boolean contains(WindowPosition position){
        return position.getX() >= getMinX() && position.getX() <= getMaxX() && position.getY() >= getMinY() && position.getY() <= getMaxY();
    }

    public boolean isEmpty(){
        return width <= 0 || height <= 0;
    }

    /**
     * this translates the argument graphic object to a new place and area
     * @param graphics
     * @return
     */
    public Graphics create(Graphics graphics){
        return graphics.create(getMinX(), getMinY(), getWidth(), getHeight());
    }

    public WindowRegion shrinkRegion(BoxProperty property){
        return new WindowRegion(
                getMinX() + property.getLeft(),
                getMinY() + property.getTop(),
                getMaxX() - property.getRight(),
                getMaxY() - property.getBottom());
    }

    /**
     * this converts a graphic object in the region it will take in the window
     * @param graphics
     * @return
     */
    public static WindowRegion fromGraphics(Graphics graphics){
        var clipRect = graphics.getClipBounds();
        return new WindowRegion((int)clipRect.getMinX(), (int)clipRect.getMinY(), (int)clipRect.getMaxX(), (int)clipRect.getMaxY());
    }
}
