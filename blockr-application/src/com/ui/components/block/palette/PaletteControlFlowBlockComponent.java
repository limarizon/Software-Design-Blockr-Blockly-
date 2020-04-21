package com.ui.components.block.palette;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

/**
 * The PaletteControlFlowBlockComponent is an extension on PaletteBlockComponent.class. It provides basic getters and a overridden draw function
 * for displaying the block in the Palette Area
 */
public class PaletteControlFlowBlockComponent extends PaletteBlockComponent<ControlFlowBlock> {

    /**
     * Instantiates a new Palette control flow block component.
     *
     * @param source       the source
     * @param mediator     the mediator
     * @param rootPosition the upper left corner coordinate
     */
    public PaletteControlFlowBlockComponent(ControlFlowBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        return BlockSizes.BLOCK_HEIGHT;
    }

    @Override
    public int getWidth() {
        return BlockSizes.BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        new BlockGraphics.ControlFlow(source.getName()).draw(graphics, getWidth(), getHeight(), false);
    }

}
