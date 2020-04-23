package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;

/**
 * This interface contains the description of a block which contains a PredicateBlock
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public interface ContainingPredicateBlock {
    /**
     * gives the first predicate this block contains
     * @return a predicate block contained in this block
     */
    PredicateBlock getPredicate();

    /**
     * sets a predicate as the first predicate of this block
     * @param predicate the predicate that will be set
     */
    void setPredicate(PredicateBlock predicate);

    /**
     * removes a predicate in this block
     * @param predicate the predicate that will be removed from this block
     */
    void removePredicate(PredicateBlock predicate);

    /**
     * provides information about the location where a certain predicate is set
     * @param predicateBlock the predicate block from which the location will be provided
     * @return the location of the predicateBlock
     */
    ProgramLocation getLocation(PredicateBlock predicateBlock);
}
