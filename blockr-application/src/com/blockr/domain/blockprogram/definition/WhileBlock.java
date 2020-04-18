package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.PredicateBlockLocation;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.definition.location.StatementBlockLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

public class WhileBlock implements ControlFlowBlock {
    private PredicateBlock predicate;
    private StatementListBlock statementListBlock = new StatementListBlock();
    private ContainingStatementBlock parent;

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
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        switch(attachLocation){
            case NEXT:
            case PREVIOUS:
                if(blockToAdd.isStatementBlock()){
                    parent.addToStatementList((StatementBlock) blockToAdd, this, attachLocation);
                }
                return true;
            case CONDITION:
                if(!blockToAdd.isStatementBlock()){
                    setPredicate((PredicateBlock) blockToAdd);
                }
                return true;
            case BODY:
                if(blockToAdd.isStatementBlock()){
                    statementListBlock.add((StatementBlock) blockToAdd);
                }
                return true;
        }
        return false;
    }

    @Override
    public void removeYourself() {
        this.parent.removeFromStatementList(this);
    }

    @Override
    public ProgramLocation getLocation() {
        return this.parent.getLocation(this);
    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        this.predicate.setParent(null);
        this.predicate = null;
    }

    @Override
    public ProgramLocation getLocation(PredicateBlock predicateBlock) {
        return new PredicateBlockLocation(this);
    }

    @Override
    public void setParent(ContainingStatementBlock parent) {
        this.parent = parent;
    }

    public void setPredicate(PredicateBlock predicate) {
        this.predicate = predicate;
        this.predicate.setParent(this);
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
    public void addToStatementList(StatementBlock block, int lineNumber) {
        statementListBlock.addToStatementList(block, lineNumber);
    }

    @Override
    public ProgramLocation getLocation(StatementBlock statementBlock) {
        return new StatementBlockLocation(this, statementListBlock.getLineNumber(statementBlock));
    }

    @Override
    public void removeFromStatementList(StatementBlock blockToRemove) {
        statementListBlock.removeFromStatementList(blockToRemove);
    }

    @Override
    public boolean wasLastStatement(int lineNumber) {
        return statementListBlock.wasLastStatement(lineNumber);
    }

    @Override
    public String toString() {
        return WhileBlock.class.getSimpleName() + " " +  statementListBlock.toString();
    }
}
