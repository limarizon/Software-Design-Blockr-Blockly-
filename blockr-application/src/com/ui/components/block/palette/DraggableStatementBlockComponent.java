package com.ui.components.block.palette;

import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

/**
 * The DraggableStatementBlockComponent is an extension on Components.class. It provides basic getters and a overridden draw function
 * for displaying the block in the Palette Area
 */
public class DraggableStatementBlockComponent extends Component {

    private WindowPosition upperLeft;

    /**
     * Instantiates a new Draggable statement block component.
     *
     * @param upperLeft the upper left of the block
     */
    public DraggableStatementBlockComponent(WindowPosition upperLeft) {
        this.upperLeft = upperLeft;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return BlockSizes.BLOCK_HEIGHT;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return BlockSizes.BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        new BlockGraphics.DraggableRect().draw(graphics, getWidth(), getHeight());
    }


    /**
     * Gets window region.
     *
     * @return the window region
     */
    public WindowRegion getWindowRegion() {
        return new WindowRegion(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());
    }

    /**
     * Sets upper left.
     *
     * @param upperLeft the upper left
     */
    public void setUpperLeft(WindowPosition upperLeft) {
        this.upperLeft = upperLeft;
    }
}
