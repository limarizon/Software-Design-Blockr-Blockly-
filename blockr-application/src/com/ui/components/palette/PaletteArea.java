package com.ui.components.palette;

import com.blockr.domain.blockprogram.definition.*;
import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.UiMediator;
import com.ui.components.block.palette.PaletteBlockComponent;
import com.ui.components.block.palette.PaletteControlFlowBlockComponent;
import com.ui.components.block.palette.PalettePredicateBlockComponent;
import com.ui.components.block.palette.PaletteStatementBlockComponent;
import com.ui.components.block.program.ProgramBlockComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PaletteArea extends com.ui.Container {

    private final List<PaletteBlockComponent> components = new ArrayList<>();
    private final List<WindowPosition> regionPositions = new ArrayList<>();

    private void initComponents(UiMediator mediator) {
        int spaceBetween = 30;
        int block_height = 40;

        var rootPos = new WindowPosition(50,50);
        components.add(new PaletteStatementBlockComponent(new MoveForwardBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        components.add(new PaletteStatementBlockComponent( new TurnLeftBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        components.add(new PaletteStatementBlockComponent( new TurnRightBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0, spaceBetween + block_height));
        components.add(new PaletteControlFlowBlockComponent(new IfBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        components.add(new PaletteControlFlowBlockComponent(new WhileBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween/2 + block_height));
        components.add(new PalettePredicateBlockComponent(new NotBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        //rootPos = rootPos.plus(new WindowPosition(0,spaceBetween/2 + block_height));
        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        components.add(new PalettePredicateBlockComponent(new WallInFrontBlock(), mediator, rootPos));
        regionPositions.add(rootPos);
    }

    public PaletteArea(UiMediator mediator) {
        initComponents(mediator);
    }

    @Override
    public List<? extends com.ui.Component> getChildren() {
        return components;
    }

    @Override
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        int index = components.indexOf(child);

        WindowPosition blockPosition = regionPositions.get(index);
        blockPosition = new WindowPosition(blockPosition.getX() + region.getMinX(), blockPosition.getY() + region.getMinY());
        WindowRegion childRegion = new WindowRegion(blockPosition.getX(),
                                                    blockPosition.getY(),
                                                blockPosition.getX() + ((PaletteBlockComponent)child).getWidth(),
                                                blockPosition.getY() + ((PaletteBlockComponent)child).getHeight()
                                                    );

        return new WindowRegion(childRegion.getMinX(),childRegion.getMinY(),Math.min(region.getMaxX(),childRegion.getMaxX()),Math.min(region.getMaxY(),childRegion.getMaxY()));
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
