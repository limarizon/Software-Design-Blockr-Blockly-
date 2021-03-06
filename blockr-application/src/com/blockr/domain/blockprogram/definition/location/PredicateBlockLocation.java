package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.*;

/**
 * This class contains the description of the location of a predicate block inside the block program
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class PredicateBlockLocation implements ProgramLocation {
    /**
     * The parent which contains this predicate from this location
     */
    private final ContainingPredicateBlock parent;

    /**
     *  Contructor that sets the attribute of this class
     * @param parent which contains this predicate from this location
     */
    public PredicateBlockLocation(ContainingPredicateBlock parent) {
        this.parent = parent;
    }

    /**
     * A predicate block will be attached to the parent of this location
     * @param block a block that will be attached as a predicate to a parent
     */
    @Override
    public void undo(ProgramBlock block) {
        if(block.isPredicateBlock()){
            parent.setPredicate((PredicateBlock) block);
        }
    }
}
