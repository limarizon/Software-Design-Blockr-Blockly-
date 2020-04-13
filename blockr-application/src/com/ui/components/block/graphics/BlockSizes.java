package com.ui.components.block.graphics;

import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;

import java.awt.*;

public class BlockSizes {
    public static final int BLOCK_WIDTH = 100;
    public static final int BLOCK_HEIGHT = 40;
    public static final int CONTROL_FLOW_INNER_START = (int)(BLOCK_WIDTH * 0.45f);
    public static final int CONDITION_BLOCK_WIDTH = (int)(BLOCK_WIDTH * 0.5f);
    public static final int CONDITION_BLOCK_HEIGHT = (int)(BLOCK_HEIGHT * 0.8f);

    public static final int FONT_SIZE = 10;

    static final Color STANDARD_COLOR = Color.DARK_GRAY;
    static final Color BG_COLOR = Color.WHITE;
    static final Color HIGHLIGHT_COLOR = Color.GREEN;
    static final Color CONNECTION_COLOR = Color.orange;
    static final Color FONT_COLOR = Color.BLACK;

    /**
     * Calculates the height of the body in a cfb. Nested loops included. The originator CFB is not calculated in the height
     * This is extra added in ProgramControlFlowBlockComponent.java @ getHeight()
     * @param block: StatementListBlock
     * @return
     */
    public static int calculateBlockHeight(StatementListBlock block){
        int height = 0;

        if (block.isEmpty()) return 2*CONDITION_BLOCK_HEIGHT;

        for(StatementBlock statementBlock : block.getStatements()){
            if(statementBlock.isControlFlow()){
                var body = ((ControlFlowBlock) statementBlock).getStatementListBlock();
                height += calculateBlockHeight(body) +  CONDITION_BLOCK_HEIGHT + (BLOCK_HEIGHT - CONDITION_BLOCK_HEIGHT);
            }
            else{
                height += BLOCK_HEIGHT;
            }
        }
        return height;
    }

}
