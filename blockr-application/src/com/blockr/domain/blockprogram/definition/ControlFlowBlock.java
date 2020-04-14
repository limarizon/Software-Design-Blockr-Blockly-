package com.blockr.domain.blockprogram.definition;

public interface ControlFlowBlock extends StatementBlock, ContainingStatementBlock, ContainingPredicateBlock {
    default boolean isControlFlow(){return true;}
}
