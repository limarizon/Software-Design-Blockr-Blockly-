package com.ui.components.block;

import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.UiMediator;
import com.ui.components.text.HorizontalAlign;
import com.ui.components.text.TextComponent;
import com.ui.components.text.VerticalAlign;

import java.awt.*;

public class PaletteControlFlowBlockComponent extends UIBlockComponent {

    public PaletteControlFlowBlockComponent(ControlFlowBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        int bodyheight = 0;
        //TODO : fixen
//        var body = ((ControlFlowBlock) source).getBody();
//        while (body != null) {
//            bodyheight += getHeight(body);
//            body = body.getNext();
//        }
        return BlockSizes.BLOCK_HEIGHT + bodyheight;
    }

    @Override
    public int getWidth() {
        return BlockSizes.BLOCK_WIDTH;
    }

    @Override
    protected void draw(Graphics graphics) {
        var region = WindowRegion.fromGraphics(graphics);

        //Since I technically draw the CFB on 'TOP' of it's body I only draw the part that is supposed to be below it thanks to a custom poly shape.
        {
            int x0 = 0;
            int x1 = Math.min(BlockSizes.BLOCK_WIDTH,region.getWidth());
            int x2 = x1;
            int x3 = Math.min(BlockSizes.CONTROL_FLOW_INNER_START, region.getWidth());
            int x4 = x3;
            int x5 = x1;
            int x6 = x1;
            int x7 = x0;

            int y0 = 0;
            int y1 = y0;
            int y2 = Math.min(BlockSizes.CONDITION_BLOCK_HEIGHT, region.getHeight());
            int y3 = Math.min(BlockSizes.CONDITION_BLOCK_HEIGHT, region.getHeight());
            int y4 = Math.min(getHeight()-(BlockSizes.BLOCK_HEIGHT - BlockSizes.CONDITION_BLOCK_HEIGHT), region.getHeight());
            int y5 = y4;
            int y6 = Math.min(getHeight(), region.getHeight());
            int y7 = y6;

            Polygon flowShape = new Polygon(new int[]{x0,x1,x2,x3,x4,x5,x6,x7}, new int[]{y0,y1,y2,y3,y4,y5,y6,y7},8);

            graphics.setClip(flowShape);
            if(isHighlight())
                graphics.setColor(Color.BLACK);
            else{
                graphics.setColor(Color.green);}
            graphics.fillRect(0, 0, getWidth(), getHeight());

            flowShape = new Polygon(new int[]{Math.max(0,x0),Math.max(0,x1-1),Math.max(0,x2-1),Math.max(0,x3-1),Math.max(0,x4-1),Math.max(0,x5-1),Math.max(0,x6-1),Math.max(0,x7)}
                    , new int[]{Math.max(0,y0),Math.max(0,y1),Math.max(0,y2-1),Math.max(0,y3-1),Math.max(0,y4),Math.max(0,y5),Math.max(0,y6-1),Math.max(0,y7-1)},8);
            graphics.setColor(Color.black);
            graphics.drawPolygon(flowShape);
        }
        var text= new TextComponent(source.getName(),10, HorizontalAlign.Center, VerticalAlign.Middle);
        text.draw(graphics);
    }

}
