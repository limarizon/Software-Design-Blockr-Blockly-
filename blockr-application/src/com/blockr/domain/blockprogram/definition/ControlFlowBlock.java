package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.graphics.BlockGraphics;

public interface ControlFlowBlock extends StatementBlock {

    StatementListBlock getStatementListBlock();
    PredicateBlock getPredicate();
}
