package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

public class IfBlock implements ControlFlowBlock{
    private PredicateBlock predicate;
    private StatementListBlock statementListBlock = new StatementListBlock();
    private ContainingStatementBlock parent;

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if(predicate.satisfies(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
    }

    @Override
    public boolean isControlFlow(){return true;}

    @Override
    public StatementBlock copy() {
        return new IfBlock();
    }

    @Override
    public String getName() {
        return "If";
    }

    //TODO: copy van whileBlock : herstructureren
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
    public void removeYourself() {
        this.parent.removeFromStatementList(this);
    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        this.predicate.setParent(null);
        this.predicate = null;
    }

    @Override
    public void setParent(ContainingStatementBlock parent) {
        this.parent = parent;
    }

    @Override
    public void setPredicate(PredicateBlock predicate) {
        this.predicate = predicate;
        this.predicate.setParent(this);
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


}
