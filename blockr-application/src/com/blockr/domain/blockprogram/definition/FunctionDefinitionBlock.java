package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.definition.location.StatementBlockLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

/**
 * This class is a description of block with a function
 * this class implements StatementBlock, ContainingStatementBlock interfaces
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class FunctionDefinitionBlock implements StatementBlock, ContainingStatementsBlock {
    /**
     * The statementListBlock contained in this block
     */
    protected StatementListBlock statementListBlock = new StatementListBlock();
    /**
     *  The parent block which contains this block in its statementListBlock
     */
    protected ContainingStatementsBlock parent;

    @Override
    public String getName() {
        return "Function";
    }

    @Override
    public ProgramBlock copy() {
        return new FunctionDefinitionBlock();
    }

    /**
     * Adds a block to add as previous or next statement block, as a predicate if its a predicateblock
     * or as statementblock on a index in the statementListBlock contained in this block according to the right
     * attachLocation
     * @param blockToAdd the program block to which this program block is added
     * @param attachLocation the location on the blockToAdd program block on which the block is added
     * @return a boolean indicating whether the block is successfully added
     */
    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        switch(attachLocation){
            case BODY:
                if(blockToAdd.isStatementBlock()){
                    statementListBlock.add((StatementBlock) blockToAdd);
                }
                return true;
        }
        return false;
    }

    /**
     * Removes itself from the statementListBlock of the parent block
     */
    @Override
    public void removeYourself() {
        this.parent.removeFromStatementList(this);
    }

    /**
     * checks of this block is a controlflow Block
     * @return whether his block is a controlflowB lock
     */
    public boolean canContainStatements(){return true;}

    /**
     *  Sets the parent block which contains this block in its statementListBlock in its body
     * @param parent a ContainingStatementBlock block which holds this statementblock in its body
     */
    @Override
    public void setParent(ContainingStatementsBlock parent) {
        this.parent = parent;
    }

    /**
     * Adds a statementBlock to the statementListBlock of the body of this blok
     * @param statementBlock the block to be added
     */
    public void addStatementBlock(StatementBlock statementBlock) {
        statementListBlock.add(statementBlock);
    }

    /**
     * Gets the statementListBlock from the body of this block
     * @return the statementListBlock
     */
    @Override
    public StatementListBlock getStatementListBlock() {
        return statementListBlock;
    }

    /**
     * Adds a block to add in the statementLisBlock in the body on a certain location from a certain
     * location in the statementListBlock according to an attach location
     * @param blockToAdd the block which is added from the the referencedBlock
     * @param referencedBlock the block where the blockToAdd was added previously
     * @param attachLocation the location on this block where the blockToAdd will be added
     */
    @Override
    public void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation) {
        statementListBlock.addToStatementList(blockToAdd, referencedBlock, attachLocation);
    }

    /**
     * Adds a block to the StatementListBlock of this body on a certain index
     * @param block the block that is added to the statementList
     * @param lineNumber the index in the statementListBlock where it will be added
     */
    @Override
    public void addToStatementList(StatementBlock block, int lineNumber) {
        statementListBlock.addToStatementList(block, lineNumber);
    }

    /**
     * Gets the location of a statement block in the statementListBlock of the body of this block
     * @param statementBlock a StatementBlock instance
     * @return the location of a statement block in the statementListBlock of the body of this block
     */
    @Override
    public ProgramLocation getLocation(StatementBlock statementBlock) {
        return new StatementBlockLocation(this, statementListBlock.getLineNumber(statementBlock));
    }

    /**
     * Removes a block from the statementListBlock of the body of this block
     * @param blockToRemove the block that will be removed
     */
    @Override
    public void removeFromStatementList(StatementBlock blockToRemove) {
        statementListBlock.removeFromStatementList(blockToRemove);
    }

    @Override
    public boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld) {
        return false;
    }

    @Override
    public <B extends ProgramBlock> boolean isNextStepToExecute(int nextLineNumber, B source) {
        return false;
    }

    /**
     * Get the location of this block in the block program
     * @return the location this block in its parent
     */
    @Override
    public ProgramLocation getLocation() {
        return this.parent.getLocation(this);
    }

    /**
     * Counts the number of blocks this block contains in it's body and as predicates including itself
     * @return the count of the blocks it contains
     */
    @Override
    public int countBlocks() {
        int sum = 0;
        return sum + statementListBlock.countBlocks();
    }

    @Override
    public void step(ExecutionCallStack executionCallStack) {

    }
}