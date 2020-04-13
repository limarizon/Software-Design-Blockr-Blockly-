package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.program.AttachLocation;

public interface ControlFlowBlock extends StatementBlock {
    StatementListBlock getStatementListBlock();
    PredicateBlock getPredicate();
    void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation);

}
