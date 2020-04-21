package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

import static com.ui.components.block.graphics.BlockSizes.BLOCK_HEIGHT;
import static com.ui.components.block.graphics.BlockSizes.CONDITION_BLOCK_HEIGHT;
import static com.ui.components.block.program.AttachLocation.*;

/**
 * A type of ProgramBlockComponent which can have predicateBlocks and a StatementListBlock in its body.
 */
public class ProgramControlFlowBlockComponent extends ProgramBlockComponent<ControlFlowBlock> {

    /**
     * Instantiates a new ProgramControlFlow block component.
     *
     * @param state        the state
     * @param source       the source
     * @param mediator     the UiMediator
     * @param rootPosition the root position of the block (upperleft corner of the block)
     */
    public ProgramControlFlowBlockComponent(GameState state, ControlFlowBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(state, source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        int bodyHeight = 0;
        var body =  source.getStatementListBlock();
        bodyHeight += BlockSizes.calculateBlockHeight(body);
        int whileBlockHeight = CONDITION_BLOCK_HEIGHT + BLOCK_HEIGHT - CONDITION_BLOCK_HEIGHT;

        return bodyHeight +  whileBlockHeight;
    }

    @Override
    protected AttachLocation translateToAttachLocation(WindowPosition relativePosition) {
        if(relativePosition.getX() < 50 && relativePosition.getY() < 20){
            return PREVIOUS;
        }
        if(relativePosition.getX() < 50 && relativePosition.getY() > 20){
            return NEXT;
        }
        if(relativePosition.getX() >= 50 && relativePosition.getY() < 20){
            return  CONDITION;
        }
        if(relativePosition.getX() >= 50 && relativePosition.getY() > 20){
            return  BODY;
        }
        return NONE;
    }

    @Override
    public int getWidth() {
        return BlockSizes.BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        new BlockGraphics.ControlFlow(source.getName()).draw(graphics, getWidth(), getHeight(), isHighlight());
    }

}
