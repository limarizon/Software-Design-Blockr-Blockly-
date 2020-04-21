package com.ui.components.container.div;

import java.awt.*;

/**
 * this is a solid border object painted with a color and a width on each side of the border
 */
public class Border extends BoxProperty {

    /**
     * Get the color of this border.
     *
     * @return the color
     */
    public Color getColor(){
        return color;
    }

    private final Color color;

    /**
     * Instantiates a new Border.
     *
     * @param color  the color
     * @param top    the top size
     * @param right  the right size
     * @param bottom the bottom size
     * @param left   the left size
     */
    public Border(Color color, int top, int right, int bottom, int left) {
        super(top, right, bottom, left);
      
        if(color == null){
            throw new IllegalArgumentException("color must be effective");
        }

        this.color = color;
    }

    /**
     * Instantiates a new Border.
     *
     * @param color the color
     * @param width the width
     */
    public Border(Color color, int width){
        //noinspection SuspiciousNameCombination
        this(color, width, width, width, width);
    }
}
