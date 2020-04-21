package com.ui.components.palette;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.*;
import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.UiMediator;
import com.ui.components.block.palette.PaletteBlockComponent;
import com.ui.components.block.palette.PaletteControlFlowBlockComponent;
import com.ui.components.block.palette.PalettePredicateBlockComponent;
import com.ui.components.block.palette.PaletteActionBlockComponent;
import com.ui.components.block.program.AttachLocation;
import com.ui.event.DraggingStoppedHandler;
import com.ui.event.DraggingStoppedInPaletteHandler;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Palette area class containing all blocks that can be used by the user in the Program Area
 */
public class PaletteArea extends com.ui.Container {

    private final List<PaletteBlockComponent> components = new ArrayList<>();
    private final List<WindowPosition> regionPositions = new ArrayList<>();
    private final UiMediator mediator;

    /**
     * Initialisation of the Palette Area its blocks
     * @param mediator the UiMediator
     * @param gameState the Game State
     */
    private void initComponents(UiMediator mediator, GameState gameState) {
        int spaceBetween = 30;
        int block_height = 40;

        var rootPos = new WindowPosition(50,50);
        for(Action action : gameState.getGameWorldType().getActions()){
            components.add(new PaletteActionBlockComponent(new GameActionBlock(action), mediator, rootPos));
            regionPositions.add(rootPos);

            rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        }

        for(Predicate predicate : gameState.getGameWorldType().getPredicates()){
            components.add(new PalettePredicateBlockComponent(new GamePredicateBlock(predicate), mediator, rootPos));
            regionPositions.add(rootPos);

            rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        }

        components.add(new PaletteControlFlowBlockComponent(new IfBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween + block_height));
        components.add(new PaletteControlFlowBlockComponent(new WhileBlock(), mediator, rootPos));
        regionPositions.add(rootPos);

        rootPos = rootPos.plus(new WindowPosition(0,spaceBetween/2 + block_height));
        components.add(new PalettePredicateBlockComponent(new NotBlock(), mediator, rootPos));
        regionPositions.add(rootPos);
    }

    /**
     * Instantiates a new Palette area.
     *
     * @param mediator  the UiMediator
     * @param gameState the game state
     */
    public PaletteArea(UiMediator mediator, GameState gameState) {
        this.mediator = mediator;
        initComponents(mediator, gameState);
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
    public void onMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getType()){
            case MOUSE_UP:
                mediator.send(new DraggingStoppedInPaletteHandler.Command());
                break;
        }
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
