package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

/**
 * This class is a description of a block which contains a StatementListBlock and predicates
 * this class implements ControlflowBlock interfaces
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class IfBlock extends ControlFlowBlock{
    /**
     * Steps into the execution of this block, by starting to execute the statementList contained in this block
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if(executionCallStack.isCurrentFrame(this)){
            executionCallStack.dropFrame();
            return;
        }
        executionCallStack.pushFrame(this);
        if(isPredicateSatisfied(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
        if(executionCallStack.isCurrentFrame(this)){
            executionCallStack.dropFrame();
        }
    }

    /**
     * Makes a copy of this ifBlock object
     * @return a new  ifBlock object whit the same functionality
     */
    @Override
    public StatementBlock copy() {
        return new IfBlock();
    }

    /**
     * Provides a string name of this ifBlock object
     * @return a string name of a ifBlock
     */
    @Override
    public String getName() {
        return "If";
    }

    /**
     * Provides a string name of this notBlock object
     * @return a string name of a ifBlock
     */
    @Override
    public String toString() {
        return IfBlock.class.getSimpleName() + statementListBlock.toString();
    }

    /**
     * Gives information of whether a linenumber is the last statement in the statementList of this block
     * @param lineNumber used for checking with the length of the statementList block this block contains
     * @param gameWorld used for checking the amount of block inside the statementList of this block
     * @return true if the given linenumber was the last index of the statementListBlock. false if the given linenumber
     * was not the last index of the statementListBlock
     */
    @Override
    public boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld) {
        return true;
    }

    @Override
    public <B extends ProgramBlock> boolean isNextStepToExecute(int nextLineNumber, B source) {
        return statementListBlock.isNextStepToExecute(nextLineNumber, source);
    }

}
