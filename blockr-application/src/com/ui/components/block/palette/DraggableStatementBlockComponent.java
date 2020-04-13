package com.ui.components.block.palette;

import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

public class DraggableStatementBlockComponent extends Component {

    private WindowPosition upperLeft;

    public DraggableStatementBlockComponent(WindowPosition upperLeft) {
        this.upperLeft = upperLeft;
    }

    public int getHeight() {
        return BlockSizes.BLOCK_HEIGHT;
    }

    public int getWidth() {
        return BlockSizes.BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        new BlockGraphics.DraggableRect().draw(graphics, getWidth(), getHeight());
    }


    public WindowRegion getWindowRegion() {
        return new WindowRegion(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());
    }

    public void setUpperLeft(WindowPosition upperLeft) {
        this.upperLeft = upperLeft;
    }
}
