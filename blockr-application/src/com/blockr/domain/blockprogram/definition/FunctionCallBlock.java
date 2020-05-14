package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

public class FunctionCallBlock extends AbstractStatementBlock{

    private final FunctionDefinitionBlock functionDefinition;

    public FunctionCallBlock(FunctionDefinitionBlock functionDefinition) {
        this.functionDefinition = functionDefinition;
    }

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
        return new FunctionCallBlock(functionDefinition);
    }

    @Override
    public int countBlocks() {
        return 1;
    }

    /**
     * Provides a string name of this ifBlock object
     * @return a string name of a ifBlock
     */
    @Override
    public String getName() {
        return "Function Call";
    }

    /**
     * Provides a string name of this notBlock object
     * @return a string name of a ifBlock
     */
    @Override
    public String toString() {
        return FunctionCallBlock.class.getSimpleName();
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
