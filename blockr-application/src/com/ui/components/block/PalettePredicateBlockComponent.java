package com.ui.components.block;

import com.blockr.domain.blockprogram.definition.PredicateBlock;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.UiMediator;
import com.ui.components.text.HorizontalAlign;
import com.ui.components.text.TextComponent;
import com.ui.components.text.VerticalAlign;

import java.awt.*;

public class PalettePredicateBlockComponent extends UIBlockComponent {

    public PalettePredicateBlockComponent(PredicateBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        return BlockSizes.CONDITION_BLOCK_HEIGHT;
    }

    @Override
    public int getWidth() {
        return BlockSizes.CONDITION_BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        var region = WindowRegion.fromGraphics(graphics);
        if(isHighlight())
            graphics.setColor(Color.BLACK);
        else{
            graphics.setColor(Color.PINK);}
        graphics.fillRect(0,0,region.getMaxX(),region.getMaxY());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, getWidth()-1, getHeight()-1);

        var text= new TextComponent(source.getName(),10, HorizontalAlign.Center, VerticalAlign.Middle);
        text.draw(graphics);
    }
}
