package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.*;

public class PredicateBlockLocation implements ProgramLocation {

    private final ContainingPredicateBlock parent;

    public PredicateBlockLocation(ContainingPredicateBlock parent) {
        this.parent = parent;
    }

    @Override
    public void restore(ProgramBlock block) {
        if(block.isPredicateBlock()){
            parent.setPredicate((PredicateBlock) block);
        }
    }
}
