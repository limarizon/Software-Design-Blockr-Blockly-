package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.program.AttachLocation;

public interface ContainingStatementBlock extends SteppableBlock{
    void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation);
    StatementListBlock getStatementListBlock();
    void removeFromStatementList(StatementBlock blockToRemove);
}
