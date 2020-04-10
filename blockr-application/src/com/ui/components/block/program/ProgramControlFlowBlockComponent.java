package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

public class ProgramControlFlowBlockComponent extends ProgramBlockComponent<ControlFlowBlock> {

    public ProgramControlFlowBlockComponent(GameState state, ControlFlowBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(state, source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        int bodyHeight = 0;
        var body =  source.getStatementListBlock();
        bodyHeight += BlockSizes.calculateBlockHeight(body);
        int whileBlockHeight = BlockSizes.CONDITION_BLOCK_HEIGHT + BlockSizes.BLOCK_HEIGHT - BlockSizes.CONDITION_BLOCK_HEIGHT;

        return bodyHeight +  whileBlockHeight;
    }

    @Override
    protected AttachLocation translateToAttachLocation(WindowPosition relativePosition) {
        if(relativePosition.getX() < 50 && relativePosition.getY() < 20){
            return AttachLocation.PREVIOUS;
        }
        if(relativePosition.getX() < 50 && relativePosition.getY() > 20){
            return AttachLocation.NEXT;
        }
        if(relativePosition.getX() >= 50 && relativePosition.getY() < 20){
            return  AttachLocation.CONDITION;
        }
        if(relativePosition.getX() >= 50 && relativePosition.getY() > 20){
            return  AttachLocation.BODY;
        }
        return AttachLocation.NONE;
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
