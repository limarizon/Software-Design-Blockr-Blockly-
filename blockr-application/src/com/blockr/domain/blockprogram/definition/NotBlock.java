package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.PredicateBlockLocation;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

/**
 * This class is a description of a block which contains a predicate that will be inverted
 * this class implements PredicateBlock and ContainingPredicateBlock interfaces
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public class NotBlock implements PredicateBlock, ContainingPredicateBlock {
    /**
     * Predicate attached to the not block and that will be negated in the evaluation
     */
    private PredicateBlock predicateToNegate;
    //design-keuze : we gaan ervanuit dat een Not altijd in een ControlFlowBlock zit. Dus geen meerdere nestings hier.
    /**
     * The parent block which contains predicate blocks including this one
     */
    private ContainingPredicateBlock parent;

    /**
     * constructor which sets both attributes of this notblock
     * @param predicateToNegate
     */
    public void setPredicateToNegate(PredicateBlock predicateToNegate) {
        this.predicateToNegate = predicateToNegate;
        this.predicateToNegate.setParent(this);
    }

    /**
     * Gets the predicate that will be negated
     * @return the negated predicate
     */
    public PredicateBlock getPredicateToNegate(){return this.predicateToNegate;}

    /**
     * Sets the parentblock
     * @param parent a block which contains one ore more predicate, including this block
     */
    @Override
    public void setParent(ContainingPredicateBlock parent) {
        this.parent = parent;
    }

    /**
     * Checks whether the negated predicate evaluated positive or negative in de gameworldAPi
     * @param gameWorld to which the evaluation of the negated predicate is called
     * @return a boolean indicating the successful of failure of the evaluation of the negated predicate
     */
    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        if(predicateToNegate == null){
            return false;
        }
        return ! predicateToNegate.satisfies(gameWorld);
    }

    /**
     * Checks whether the predicate has one other predicate attached to it
     * @return a boolean indicating whether the predicate has one other predicate attached to it
     */
    @Override
    public boolean hasSubPredicate() {
        return predicateToNegate != null;
    }

    /**
     * Get the location in the blockprogram of the predicate
     * @return the location of the predicate in the blockprogram
     */
    @Override
    public ProgramLocation getLocation() {
        return null;
    }

    /**
     * Counts the blocks attached to in to the right in the blokprogram
     * @return the number of predicates attached to it
     */
    @Override
    public int countBlocks() {
        return 1 + (predicateToNegate != null ? predicateToNegate.countBlocks() : 0);
    }

    /**
     * Provides a string name of this notBlock object
     * @return a string name of a notBlock
     */
    @Override
    public String toString() {
        return NotBlock.class.getSimpleName();
    }

    /**
     * Provides a string name of this notBlock object
     * @return a string name of a notBlock
     */
    @Override
    public String getName() {
        return "Not";
    }

    /**
     * Makes a copy of this notBlock object
     * @return a new not block object with the same functionality
     */
    @Override
    public ProgramBlock copy() {
        return new NotBlock();
    }

    /**
     * Attaches a block to add as a predicate to the right of this predicate in the block program
     * @param blockToAdd the program block to which this program block is added, must be a predicate block
     * @param attachLocation the location on the blockToAdd program block on which the block is added
     * @return a boolean indicating whether the block is successfully added
     */
    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(blockToAdd.isPredicateBlock()){
            setPredicateToNegate((PredicateBlock) blockToAdd);
            return true;
        }
        return false;
    }

    /**
     * Sets a predicate to the right op the block in the blockprogram
     * @param predicate the predicate that will be set
     */
    @Override
    public void setPredicate(PredicateBlock predicate) {
        this.predicateToNegate = predicate;
    }

    /**
     * Removes itself from the parent block to which its attached
     */
    @Override
    public void removeYourself() {
        this.parent.removePredicate(this);
    }

    /**
     *  Removes a predicate as the predicate that would be negated
     * @param predicate the predicate that will be removed from this block
     */
    @Override
    public void removePredicate(PredicateBlock predicate) {
        this.setPredicateToNegate(null);
    }

    /**
     * Gets the location of this block in block program
     * @param predicateBlock the predicate block from which the location will be provided
     * @return
     */
    @Override
    public ProgramLocation getLocation(PredicateBlock predicateBlock) {
        return new PredicateBlockLocation(this);
    }

    /**
     * Gets the predicate that will be negated
     * @return the predicate that will be negated
     */
    @Override
    public PredicateBlock getPredicate() {
        return predicateToNegate;
    }


}
