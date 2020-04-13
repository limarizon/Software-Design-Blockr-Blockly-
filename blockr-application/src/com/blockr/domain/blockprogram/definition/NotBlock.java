package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;

public class NotBlock implements PredicateBlock{

    private PredicateBlock predicateToNegate;
    private ControlFlowBlock parent;

    public void setPredicateToNegate(PredicateBlock predicateToNegate) {
        this.predicateToNegate = predicateToNegate;
    }

    public PredicateBlock getPredicateToNegate(){return this.predicateToNegate;}

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return ! predicateToNegate.satisfies(gameWorld);
    }

    @Override
    public boolean hasPredicate() {
        return true;
    }

    @Override
    public boolean isNot() {
        return true;
    }

    @Override
    public String toString() {
        return NotBlock.class.getSimpleName();
    }

    @Override
    public String getName() {
        return "Not";
    }

    @Override
    public ProgramBlock copy() {
        return new NotBlock();
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(!blockToAdd.isStatementBlock()){

        }
    }

    @Override
    public void setParent(ProgramBlock programBlock) {
        this.parent = parent;
    }

    @Override
    public void removeStatement() {
        //TODO : to implement

    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        this.setPredicateToNegate(null);
    }
}
