package com.ui.components.block.graphics;

import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementsListBlock;

import java.awt.*;

/**
 * The type Block sizes.
 */
public class BlockSizes {
    /**
     * The constant BLOCK_WIDTH.
     */
    public static final int BLOCK_WIDTH = 100;
    /**
     * The constant BLOCK_HEIGHT.
     */
    public static final int BLOCK_HEIGHT = 40;
    /**
     * The constant CONTROL_FLOW_INNER_START.
     */
    public static final int CONTROL_FLOW_INNER_START = (int)(BLOCK_WIDTH * 0.45f);
    /**
     * The constant CONDITION_BLOCK_WIDTH.
     */
    public static final int CONDITION_BLOCK_WIDTH = (int)(BLOCK_WIDTH * 0.5f);
    /**
     * The constant CONDITION_BLOCK_HEIGHT.
     */
    public static final int CONDITION_BLOCK_HEIGHT = (int)(BLOCK_HEIGHT * 0.8f);

    /**
     * The constant FONT_SIZE.
     */
    public static final int FONT_SIZE = 10;

    /**
     * The Standard color.
     */
    static final Color STANDARD_COLOR = Color.DARK_GRAY;
    /**
     * The Bg color.
     */
    static final Color BG_COLOR = Color.WHITE;
    /**
     * The Highlight color.
     */
    static final Color HIGHLIGHT_COLOR = Color.GREEN;
    /**
     * The Connection color.
     */
    static final Color CONNECTION_COLOR = Color.orange;
    /**
     * The Font color.
     */
    static final Color FONT_COLOR = Color.BLACK;

    /**
     * Calculates the height of the body in a cfb. Nested loops included. The originator CFB is not calculated in the height
     * This is extra added in ProgramControlFlowBlockComponent.java @ getHeight()
     *
     * @param block : StatementListBlock
     * @return int
     */
    public static int calculateBlockHeight(StatementsListBlock block){
        int height = 0;

        if (block.isEmpty()) return 2*CONDITION_BLOCK_HEIGHT;

        for(StatementBlock statementBlock : block.getStatements()){
            if(statementBlock.canContainStatements()){
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
