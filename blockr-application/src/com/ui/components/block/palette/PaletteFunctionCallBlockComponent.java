package com.ui.components.block.palette;

import com.blockr.domain.blockprogram.definition.FunctionCallBlock;
import com.blockr.domain.blockprogram.definition.PredicateBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

/**
 * The PalettePredicateBlockComponent is an extension on PaletteBlockComponent.class. It provides basic getters and a overridden draw function
 * for displaying the block in the Palette Area
 */
public class PaletteFunctionCallBlockComponent extends PaletteBlockComponent<FunctionCallBlock> {

    /**
     * Instantiates a new Palette predicate block component.
     *
     * @param source       the source
     * @param mediator     the mediator
     * @param rootPosition the upper left corner coordinate
     */
    public PaletteFunctionCallBlockComponent(FunctionCallBlock source, UiMediator mediator, WindowPosition rootPosition) {
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
        new BlockGraphics.FunctionCall().draw(graphics, getWidth(), getHeight(), false);
    }
}
