package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;
/**
 * This class is a description of block wraps a predicate interface and can be evaluated by the API
 * This class implements the PredicateBlock interface
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public class GamePredicateBlock implements PredicateBlock {
    /**
     * The parentBlock to which this predicate is attached
     */
    private ContainingPredicateBlock parent;
    /**
     * The Predicate interface received from the API
     */
    private Predicate gamePredicate;

    /**
     * Constructor which sets this attributes of this class
     * @param gamePredicate The Predicate interface received from the API
     */
    public GamePredicateBlock(Predicate gamePredicate) {
        this.gamePredicate = gamePredicate;
    }

    /**
     * Sets the parent containing this block
     * @param parent a block which contains one ore more predicate, including this block
     */
    @Override
    public void setParent(ContainingPredicateBlock parent) {
        this.parent = parent;
    }

    /**
     * Checks whether the predicate evaluated positive or negative in de gameworldAPi
     * @param gameWorld the API to which the evaluation of the predicate is called
     * @return the evaluation of the predicate
     */
    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return gameWorld.evaluate(gamePredicate);
    }

    /**
     * Checks whether the predicate has one other predicate attached to it
     * @return a boolean indicating whether the predicate has one other predicate attached to it
     */
    @Override
    public boolean hasSubPredicate() {
        return false;
    }

    /**
     * Provides a string name of this predicate
     * @return a string name of a predicate
     */
    @Override
    public String toString() {
        return GamePredicateBlock.class.getSimpleName();
    }

    /**
     * Provides a string name of this predicate
     * @return a string name of a predicate
     */
    @Override
    public String getName() {
        return gamePredicate.getName();
    }

    /**
     * Makes a copy of this  object
     * @return a new object with the same functionality
     */
    @Override
    public ProgramBlock copy() {
        return new GamePredicateBlock(gamePredicate);
    }

    /**
     * Attaches a block to add as a predicate to the right of this predicate in the block program
     * @param blockToAdd the program block to which this program block is added
     * @param attachLocation the location on the blockToAdd program block on which the block is added
     * @return a boolean indicating whether the block is successfully added
     */
    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        // no action
        return false;
    }

    /**
     *Removes itself from the parent block to which its attached
     */
    @Override
    public void removeYourself() {
        parent.removePredicate(this);
    }

    /**
     * Checks if this block is a predicate block
     * @return whether this block is a predicate block
     */
    @Override
    public boolean isPredicateBlock() {
        return true;
    }

    /**
     * Get the location in the blockprogram of the predicate
     * @return the location of the predicate in the blockprogram
     */
    @Override
    public ProgramLocation getLocation() {
        return this.parent.getLocation(this);
    }

    /**
     * Counts the blocks attached to in to the right in the blokprogram
     * @return the number of predicates attached to it
     */
    @Override
    public int countBlocks() {
        return 1;
    }
}
