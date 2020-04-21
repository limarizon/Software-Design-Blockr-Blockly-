package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import static com.ui.components.block.program.AttachLocation.NEXT;
import static com.ui.components.block.program.AttachLocation.PREVIOUS;

/**
 * @author
 * @version
 */
public class GameActionBlock implements StatementBlock {
    /**
     * The parentBloch which contains a list of statementBlocks
     */
    private ContainingStatementBlock parent;
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
        executionCallStack.pushSnapshot();
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
     * Attaches a block to add as previous block or a next block of this block in the statementListblock
     * @param blockToAdd the program block to which this program block is added
     * @param attachLocation the location on the blockToAdd program block on which the block is added
     * @return a boolean indicating whether the block is successfully added
     */
    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(attachLocation.isContainedIn(NEXT, PREVIOUS) && blockToAdd.isStatementBlock()){
            parent.addToStatementList((StatementBlock) blockToAdd, this, attachLocation);
            return true;
        }
        return false;
    }

    /**
     * Removes itself from the statementListBlock in which it was contained
     */
    @Override
    public void removeYourself() {
        parent.removeFromStatementList(this);
    }

    /**
     * Gets the location in the statementListblok in the blockprogram
     * @return the location in the statementListblok in the blockprogram
     */
    @Override
    public ProgramLocation getLocation() {
        return parent.getLocation(this);
    }

    /**
     * Counts itself as block
     * @return the count of blocks
     */
    @Override
    public int countBlocks() {
        return 1;
    }

    /**
     * Sets the parent containing this block
     * @param parent a ContainingStatementBlock block which holds this statementblock in its body
     */
    @Override
    public void setParent(ContainingStatementBlock parent) {
        this.parent = parent;
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
}
