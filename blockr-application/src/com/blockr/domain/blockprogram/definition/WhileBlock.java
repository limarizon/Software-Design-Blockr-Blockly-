package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

public class WhileBlock implements ControlFlowBlock {
    private PredicateBlock predicate;
    private StatementListBlock statementListBlock = new StatementListBlock();
    private ControlFlowBlock parent;

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushFrame(this);
        if(predicate.satisfies(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
        if(! executionCallStack.isCurrentFrame(statementListBlock)){
            if(predicate.satisfies(executionCallStack.getGameWorld())){
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
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        switch(attachLocation){
            case NEXT:
            case PREVIOUS:
                if(blockToAdd.isStatementBlock()){
                    parent.addToStatementList((StatementBlock) blockToAdd, this, attachLocation);
                }
                break;
            case CONDITION:
                if(!blockToAdd.isStatementBlock()){
                    setPredicate((PredicateBlock) blockToAdd);
                }
                break;
            case BODY:
                if(blockToAdd.isStatementBlock()){
                    statementListBlock.add((StatementBlock) blockToAdd);
                }
        }
    }

    @Override
    public void removeStatement() {
        this.parent.removeFromStatementList(this);
    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        this.predicate = null;
    }

    @Override
    public void setParent(ControlFlowBlock parent) {
        this.parent = parent;
    }

    public void setPredicate(PredicateBlock predicate) {
        this.predicate = predicate;
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
        return this.predicate;
    }

    @Override
    public void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation) {
        statementListBlock.addToStatementList(blockToAdd, referencedBlock, attachLocation);
    }

    @Override
    public void removeFromStatementList(StatementBlock blockToRemove) {
        statementListBlock.removeFromStatementList(blockToRemove);
    }

    @Override
    public String toString() {
        return WhileBlock.class.getSimpleName() + statementListBlock.toString();
    }
}
