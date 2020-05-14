package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

/**
 * This class is a description of a block which can call a FunctionDefinitionBlock
 * this class implements ControlFlowBlock interfaces
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class FunctionCallBlock implements StatementBlock{

    /**
     *  The parent block which contains this block in its statementListBlock
     */
    protected ContainingStatementsBlock parent;

    /**
     * Steps into the execution of this block, by starting to execute the statementList contained in this block
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    @Override
    public void step(ExecutionCallStack executionCallStack) {
    }

    /**
     * Makes a copy of this ifBlock object
     * @return a new  ifBlock object whit the same functionality
     */
    @Override
    public StatementBlock copy() {
        return new FunctionCallBlock();
    }

    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        return false;
    }

    @Override
    public void removeYourself() {

    }

    @Override
    public ProgramLocation getLocation() {
        return null;
    }

    @Override
    public int countBlocks() {
        return 0;
    }

    /**
     * Provides a string name of this ifBlock object
     * @return a string name of a ifBlock
     */
    @Override
    public String getName() {
        return "Call";
    }

    /**
     * Provides a string name of this notBlock object
     * @return a string name of a ifBlock
     */
    @Override
    public String toString() {
        return FunctionCallBlock.class.getSimpleName();
    }

    @Override
    public void setParent(ContainingStatementsBlock parent) {

    }

    /**
     * Gives information of whether a linenumber is the last statement in the statementList of this block
     * @param lineNumber used for checking with the length of the statementList block this block contains
     * @param gameWorld used for checking the amount of block inside the statementList of this block
     * @return true if the given linenumber was the last index of the statementListBlock. false if the given linenumber
     * was not the last index of the statementListBlock
     */
    //@Override
    //public boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld) {
    //    return statementListBlock.wasLastStatement(lineNumber, gameWorld);
    //}

}
