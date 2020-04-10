package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

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
    public boolean isControlFlow(){return true;}

    @Override
    public StatementBlock copy() {
        return new WhileBlock();
    }

    @Override
    public String getName() {
        return "While";
    }

    @Override
    public void add(StatementBlock blockToAdd, AttachLocation attachLocation) {
        //TODO nog implementeren
    }

    public void setPredicateBlock(PredicateBlock predicateBlock) {
        this.predicateBlock = predicateBlock;
    }

    public void addStatementBlock(StatementBlock statementBlock) {
        statementListBlock.add(statementBlock);
    }

    @Override
    public StatementListBlock getStatementListBlock() {
        return statementListBlock;
    }

    @Override
    public PredicateBlock getPredicate() {
        return this.predicateBlock;
    }


    @Override
    public String toString() {
        return WhileBlock.class.getSimpleName() + statementListBlock.toString();
    }
}
