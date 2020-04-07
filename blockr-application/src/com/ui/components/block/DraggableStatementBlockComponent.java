package com.ui.components.block;

import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;

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
        var region = WindowRegion.fromGraphics(graphics);
        graphics.setColor(Color.GRAY);
        graphics.drawRect(0,0,region.getMaxX()-1,region.getMaxY()-1);
    }


    public WindowRegion getWindowRegion() {
        return new WindowRegion(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());
    }

    public void setUpperLeft(WindowPosition upperLeft) {
        this.upperLeft = upperLeft;
    }
}
