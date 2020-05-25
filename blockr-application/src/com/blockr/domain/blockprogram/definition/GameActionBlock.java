package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import static com.ui.components.block.program.AttachLocation.NEXT;
import static com.ui.components.block.program.AttachLocation.PREVIOUS;

/**
 * This class is a description of block wraps a Action interface and can be performed by the API to change the state of a game world
 * This class implements the StatementBlock interface
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class GameActionBlock extends AbstractStatementBlock {
    /**
     * the action interface received from the API
     */
    private Action gameAction;

    /**
     * Constructor which sets this attributes of this class
     * @param gameAction the action interface received from the API
     */
    public GameActionBlock(Action gameAction) {
        this.gameAction = gameAction;
    }

    /**
     * Executes the gameActionBlock by calling the API to perform the action wrapped in this block
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if( executionCallStack.getGameWorld().perform(gameAction)){
            System.out.println("Successful action in game world");
        }else{
            System.out.println("Failed action in game world");
        }
    }

    /**
     * Makes a copy of this  object
     * @return a new object with the same functionality
     */
    @Override
    public StatementBlock copy() {
        return new GameActionBlock(gameAction);
    }

    /**
     * Provides a string name of this block
     * @return a string name of a block
     */
    @Override
    public String getName() {
        return gameAction.getName();
    }

    /**
     * Provides a string name of this block
     * @return a string name of a block
     */
    @Override
    public String toString() {
        return GameActionBlock.class.getSimpleName() + "[" + getName() + "]";
    }

    @Override
    public ContainingStatementsBlock getParent() {
        return null;
    }
}
