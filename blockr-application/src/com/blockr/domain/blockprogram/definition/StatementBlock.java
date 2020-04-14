package com.blockr.domain.blockprogram.definition;

public interface StatementBlock extends ProgramBlock, SteppableBlock {
    void setParent(ContainingStatementBlock parent);

    default boolean isControlFlow(){return false;}
    default boolean isStatementBlock(){return true;};
}
