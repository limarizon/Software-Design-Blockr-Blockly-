package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.AttachLocation;

public class IfBlock implements ControlFlowBlock {
    private PredicateBlock predicateBlock;
    private StatementListBlock statementListBlock = new StatementListBlock();

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if(predicateBlock.satisfies(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public void add(StatementBlock blockToAdd, AttachLocation attachLocation) {
        //TODO: nog te implementeren
    }

    public void setPredicateBlock(PredicateBlock predicateBlock) {
        this.predicateBlock = predicateBlock;
    }

    public void addStatementBlock(StatementBlock statementBlock) {
        statementListBlock.add(statementBlock);
    }

    @Override
    public String toString() {
        return IfBlock.class.getSimpleName() + statementListBlock.toString();
    }

    @Override
    public StatementListBlock getStatementListBlock() {
        return statementListBlock;
    }
}
