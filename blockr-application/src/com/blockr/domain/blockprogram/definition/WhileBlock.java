package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

/**
 * This class is a description of a block which contains a StatementListBlock and controls the execution order by the use of predicates
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public class WhileBlock extends ControlFlowBlock {
    /**
     * First it pushed itself onto the stack.
     * Steps into the statementListBlock it contains ,if the predicate attached to it is satisfied, by pushing
     * its statementListBlock onto the executionStack.
     * If the predicate is not satisfied it removes itself from the executionStack
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushFrame(this);
        if(isPredicateSatisfied(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
        if(! executionCallStack.isCurrentFrame(statementListBlock)){
            if(isPredicateSatisfied(executionCallStack.getGameWorld())){
                executionCallStack.pushFrame(statementListBlock);
            }else{
                executionCallStack.dropFrame();
            }
        }
    }

    /**
     * makes a copy of this programBlock object
     * @return a ProgramBlock which is the copy of ProgramBlock to which this method is called
     */
    @Override
    public StatementBlock copy() {
        return new WhileBlock();
    }

    /**
     * gives the name of the program block
     * @return the name of the program block as String
     */
    @Override
    public String getName() {
        return "While";
    }

    /**
     * Checks whether the last statement of its statementList was reached
     * @param lineNumber used for checking with the length of the statementList block this block contains
     * @param gameWorld used for checking the amount of block inside the statementList of this block
     * @return True if it was it's last statement ans the predicate satisfied, otherwise False
     */
    @Override
    public boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld) {
        if(statementListBlock.wasLastStatement(lineNumber, gameWorld)){
            return !isPredicateSatisfied(gameWorld);
        }
        return false;
    }

    /**
     * gives the name of the program block
     * @return the name of the program block as String
     */
    @Override
    public String toString() {
        return WhileBlock.class.getSimpleName() + " " +  statementListBlock.toString();
    }
}
