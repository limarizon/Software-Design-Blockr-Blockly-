package com.blockr.domain.blockprogram.definition;
/**
 * This interface contains the description of a statement block used in the block program
 * This interface extends the programblock interface and the steppablblock interface
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public interface StatementBlock extends ProgramBlock, SteppableBlock {
    /**
     * sets a parent block which holds this block in it's body in a statementListBlock
     * @param parent a ContainingStatementBlock block which holds this statementblock in its body
     */
    void setParent(ContainingStatementsBlock parent);

    /**
     * gives more specific information about the possible abstract classes the block extends
     * @return returns a boolean indication whether it's a controlflow subclass implementation or not
     */
    default boolean canContainStatements(){return false;}

    default boolean canContainPredicate(){return false;}



    /**
     * gives more specific information about the possible other interfaces the block implements
     * @return returns a boolean indication whether it's a statementBlock interface implementation or not
     */
    default boolean isStatementBlock(){return true;};
}
