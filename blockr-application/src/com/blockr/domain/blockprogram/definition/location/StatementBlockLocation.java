package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.ContainingStatementBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;

public class StatementBlockLocation implements ProgramLocation {

    private final ContainingStatementBlock parent;
    private final int lineNumber;

    public StatementBlockLocation(ContainingStatementBlock parent, int lineNumber) {
        this.parent = parent;
        this.lineNumber = lineNumber;
    }

    @Override
    public void undo(ProgramBlock block) {
        if(block.isStatementBlock()){
            parent.addToStatementList((StatementBlock) block, lineNumber);
        }
    }
}
