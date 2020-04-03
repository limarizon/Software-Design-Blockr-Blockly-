package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class WhileBlock implements ControlFlowBlock {
    private PredicateBlock predicateBlock;
    private StatementListBlock statementListBlock = new StatementListBlock();

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushFrame(this);
        if(predicateBlock.satisfies(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
        if(! executionCallStack.isCurrentFrame(statementListBlock)){
            if(predicateBlock.satisfies(executionCallStack.getGameWorld())){
                executionCallStack.pushFrame(statementListBlock);
            }else{
                executionCallStack.dropFrame();
            }
        }
    }

    @Override
    public StatementBlock invert() {
        return this;
    }

    @Override
    public String getName() {
        return "While";
    }

    public void setPredicateBlock(PredicateBlock predicateBlock) {
        this.predicateBlock = predicateBlock;
    }

    public void addStatementBlock(StatementBlock statementBlock) {
        statementListBlock.add(statementBlock);
    }

    @Override
    public String toString() {
        return WhileBlock.class.getSimpleName() + statementListBlock.toString();
    }
}
