package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.PredicateBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

/**
 * A type of ProgramBlockComponent which is used in the ProgramControlFlowBlockComponent's condition body.
 */
public class ProgramPredicateBlockComponent extends ProgramBlockComponent<PredicateBlock> {

    /**
     * Instantiates a new Program predicate block component.
     *
     * @param state        the state
     * @param source       the source
     * @param mediator     the mediator
     * @param rootPosition the root position of the block (upperleft corner of the block)
     */
    public ProgramPredicateBlockComponent(GameState state, PredicateBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(state, source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        return BlockSizes.CONDITION_BLOCK_HEIGHT;
    }

    @Override
    protected AttachLocation translateToAttachLocation(WindowPosition relativePosition) {
        return AttachLocation.CONDITION;
    }

    @Override
    public int getWidth() {
        return BlockSizes.CONDITION_BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        new BlockGraphics.Predicate(source.getName()).draw(graphics, getWidth(), getHeight(), isHighlight());

    }
}
