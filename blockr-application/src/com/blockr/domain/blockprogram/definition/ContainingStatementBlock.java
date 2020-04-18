package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

public interface ContainingStatementBlock extends SteppableBlock{
    void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation);
    void addToStatementList(StatementBlock block, int lineNumber);
    ProgramLocation getLocation(StatementBlock statementBlock);

    StatementListBlock getStatementListBlock();
    void removeFromStatementList(StatementBlock blockToRemove);
    boolean wasLastStatement(int lineNumber);


}
