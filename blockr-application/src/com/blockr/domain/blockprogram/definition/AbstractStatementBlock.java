package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

import static com.ui.components.block.program.AttachLocation.NEXT;
import static com.ui.components.block.program.AttachLocation.PREVIOUS;

public abstract class AbstractStatementBlock implements StatementBlock {
    /**
     * The parentBloch which contains a list of statementBlocks
     */
    private ContainingStatementsBlock parent;

    public AbstractStatementBlock() {
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
    public void setParent(ContainingStatementsBlock parent) {
        this.parent = parent;
    }

    /**
     * Provides a string name of this block
     * @return a string name of a block
     */
    @Override
    public String toString() {
        return AbstractStatementBlock.class.getSimpleName() + "[" + getName() + "]";
    }
}
