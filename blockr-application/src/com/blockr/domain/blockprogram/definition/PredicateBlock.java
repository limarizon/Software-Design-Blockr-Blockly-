package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

/**
 * This interface contains the description of a predicate block wraps a Predicate interface received from the API
 * and extends the programblock interface
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public interface PredicateBlock extends ProgramBlock {
    /**
     * sets the parent block which contains predicate blocks
     * @param parent a block which contains one ore more predicate, including this block
     */
    void setParent(ContainingPredicateBlock parent);

    /**
     * gives information whether a predicate block is satisfied in the game world implementation of the game world API
     * @param gameWorldApi the API that is the connection between the game world implementation that evaluates the predicate
     * @return a boolean indication a successful evaluation or a failure
     */
    boolean satisfies(GameWorldApi gameWorldApi);

    /**
     * gives information whether this predicate block has another sub predicate to right in the block program
     * @return a boolean indication whether it has a sub predicate
     */
    boolean hasSubPredicate();

    /**
     * gives more specific information about the possible other interfaces the block implements
     * @return returns a boolean indication whether it's a statementBlock interface implementation or not
     */
    default boolean isStatementBlock(){return false;}
}
