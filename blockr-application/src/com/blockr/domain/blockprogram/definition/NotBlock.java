package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.PredicateBlockLocation;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

public class NotBlock implements PredicateBlock, ContainingPredicateBlock {

    private PredicateBlock predicateToNegate;
    //design-keuze : we gaan ervanuit dat een Not altijd in een ControlFlowBlock zit. Dus geen meerdere nestings hier.
    private ContainingPredicateBlock parent;

    public void setPredicateToNegate(PredicateBlock predicateToNegate) {
        this.predicateToNegate = predicateToNegate;
        this.predicateToNegate.setParent(this);
    }

    public PredicateBlock getPredicateToNegate(){return this.predicateToNegate;}

    @Override
    public void setParent(ContainingPredicateBlock parent) {
        this.parent = parent;
    }

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return ! predicateToNegate.satisfies(gameWorld);
    }

    @Override
    public boolean hasSubPredicate() {
        return predicateToNegate != null;
    }

    @Override
    public ProgramLocation getLocation() {
        return null;
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
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(blockToAdd.isPredicateBlock()){
            setPredicateToNegate((PredicateBlock) blockToAdd);
            return true;
        }
        return false;
    }

    @Override
    public void setPredicate(PredicateBlock predicate) {
        this.predicateToNegate = predicate;
    }

    @Override
    public void removeYourself() {
        this.parent.removePredicate(this);
    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        this.setPredicateToNegate(null);
    }

    @Override
    public ProgramLocation getLocation(PredicateBlock predicateBlock) {
        return new PredicateBlockLocation(this);
    }

    @Override
    public PredicateBlock getPredicate() {
        return predicateToNegate;
    }


}
