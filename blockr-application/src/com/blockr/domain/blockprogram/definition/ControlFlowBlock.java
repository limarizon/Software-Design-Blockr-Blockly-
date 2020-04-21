package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.PredicateBlockLocation;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.definition.location.StatementBlockLocation;
import com.ui.components.block.program.AttachLocation;

public abstract class ControlFlowBlock implements StatementBlock, ContainingStatementBlock, ContainingPredicateBlock {
    protected PredicateBlock predicate;
    protected StatementListBlock statementListBlock = new StatementListBlock();
    protected ContainingStatementBlock parent;

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

    protected boolean isPredicateSatisfied(GameWorldApi gameWorld) {
        if(predicate == null){
            return false;
        }
        return predicate.satisfies(gameWorld);
    }


    @Override
    public void removeYourself() {
        this.parent.removeFromStatementList(this);
    }

    public boolean isControlFlow(){return true;}

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
    public ProgramLocation getLocation() {
        return this.parent.getLocation(this);
    }

    @Override
    public int countBlocks() {
        int sum =1;
        if(predicate != null){
            sum  += predicate.countBlocks();
        }
        return sum + statementListBlock.countBlocks();
    }

}
