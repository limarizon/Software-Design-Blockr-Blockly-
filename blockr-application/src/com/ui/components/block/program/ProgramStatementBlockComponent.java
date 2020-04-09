package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.graphics.BlockGraphics;
import com.ui.components.block.graphics.BlockSizes;

import java.awt.*;

import static com.ui.components.block.graphics.BlockSizes.BLOCK_HEIGHT;

public class ProgramStatementBlockComponent extends ProgramBlockComponent<StatementBlock> {

    public ProgramStatementBlockComponent(GameState state, StatementBlock source, UiMediator mediator, WindowPosition rootPosition) {
        super(state, source, mediator, rootPosition);
    }

    @Override
    public int getHeight() {
        return BlockSizes.BLOCK_HEIGHT;
    }

    @Override
    protected AttachLocation translateToAttachLocation(WindowPosition relativePosition) {
        if(relativePosition.getY() < BLOCK_HEIGHT/2){
            return AttachLocation.PREVIOUS;
        }
        return AttachLocation.NEXT;
    }

    @Override
    public int getWidth() {
        return BlockSizes.BLOCK_WIDTH;
    }

    @Override
    public void draw(Graphics graphics) {
        new BlockGraphics.Statement(source.getName()).draw(graphics, isHighlight());
    }


}
