package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
/**
 * This class is a description of a block which call predefine function definition block
 * This class  extends an AbstractStatementBlock
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class FunctionCallBlock extends AbstractStatementBlock{
    /**
     * he function definition block which is called by this block
     */
    private final FunctionDefinitionBlock functionDefinition;

    /**
     * Constructor which initialises the function definition block which is called by this block
     * @param functionDefinition
     */
    public FunctionCallBlock(FunctionDefinitionBlock functionDefinition) {
        this.functionDefinition = functionDefinition;
    }

    /**
     * Steps into the execution of this block, by starting to execute the statementList contained in this block
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushFrame(functionDefinition);
    }

    /**
     * Makes a copy of this FunctionCallBlock object
     * @return a new  FunctionCallBlock object whit the same functionality
     */
    @Override
    public StatementBlock copy() {
        return new FunctionCallBlock(functionDefinition);
    }

    /**
     * Provides the number of blocks this block consists of
     * @return an integer which represents the number of blocks this block consists of
     */
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

}
