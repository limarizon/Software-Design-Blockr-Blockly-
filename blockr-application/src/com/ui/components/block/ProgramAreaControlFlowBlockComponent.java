package com.ui.components.block;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;
import com.ui.components.block.program.AttachLocation;
import com.ui.components.block.program.ProgramBlockComponent;

import java.awt.*;

public class ProgramAreaControlFlowBlockComponent extends ProgramBlockComponent<ControlFlowBlock> {

    public ProgramAreaControlFlowBlockComponent(GameState state, ControlFlowBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(state, source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        int bodyheight = 0;
        var body =  source.getStatementListBlock();
        for(StatementBlock statementBlock : body.getStatements()){
            bodyheight += BlockSizes.BLOCK_HEIGHT;
        }
        return BlockSizes.BLOCK_HEIGHT + bodyheight;
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
