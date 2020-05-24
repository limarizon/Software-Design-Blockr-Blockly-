package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.definition.location.StatementBlockLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a description of a block which contains a StatementListBlock
 * this class implements StatementBlock, ContainingStatementBlock and SteppableBlock interfaces
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class StatementListBlock implements ContainingStatementsBlock, StatementBlock, SteppableBlock{
    /**
     * A list containing a nesting of statements to be executed sequentially
     */
    private List<StatementBlock> statements = new ArrayList<>();

    /**
     * Executes a statement in the list according to the current line Number.
     * Before executing it pushes itself onto the stack and steps into the corresponding statementblock
     * If the last statement is executed, it removes itself from the execution stack
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    @Override
    public void step(ExecutionCallStack executionCallStack) {
        //if(statements.size() == 0){return;}
        executionCallStack.pushFrame(this);
        int lineNumberToExecute = executionCallStack.getCurrentLineNumber();
        var statement = statements.get(lineNumberToExecute);
        statement.step(executionCallStack);

        if(executionCallStack.isCurrentFrame(this)){
            if(wasLastStatement(lineNumberToExecute, executionCallStack.getGameWorld())){
                executionCallStack.dropFrame();
            }else{
                executionCallStack.nextLineNumberCurrentFrame(++lineNumberToExecute);
            }
        }
    }

    /**
     * makes a copy by creating a new StatementListBlock
     * @return a copy of the StatementListBlock
     */
    @Override
    public StatementBlock copy() {
        return new StatementListBlock();
    }

    /**
     * Attaches a block to add to statement block in the list according to the attachLocation
     * @param blockToAdd the program block to which this program block is added
     * @param attachLocation the location on the blockToAdd program block on which the block is added
     * @return a boolean indication whether the attachment is succeeded
     */
    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(!blockToAdd.isStatementBlock()) return false;

        if (attachLocation == AttachLocation.BODY) {
            addStatement((StatementBlock) blockToAdd, statements.size());
            return true;
        }
        return false;
    }

    /**
     * Not needed for top level lists
     */
    @Override
    public void removeYourself() {
        //top-level list, niet nodig
    }

    /**
     * not needed for top level lists
     * @return null
     */
    @Override
    public ProgramLocation getLocation() {
        //top-level list, niet nodig
        return null;
    }

    /**
     * Counts the number of used blocks inside the body
     * @return the number of used blocks inside the body
     */
    @Override
    public int countBlocks() {
        int sum = 0;
        for(StatementBlock statementBlock : getStatements()){
            sum += statementBlock.countBlocks();
        }
        return sum;
    }

    /**
     *
     * @param parent a ContainingStatementBlock block which holds this statementblock in its body
     */
    @Override
    public void setParent(ContainingStatementsBlock parent) {
    }

    /**
     * Adds statement block to another location in the statementListBlock according to the attach Location
     * @param blockToAdd the block which is added from the the referencedBlock
     * @param referencedBlock the block where the blockToAdd was added previously
     * @param attachLocation the location on this block where the blockToAdd will be added
     */
    @Override
    public void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation) {
        if(! attachLocation.isContainedIn(AttachLocation.PREVIOUS, AttachLocation.NEXT)) return;

        var indexReference = getLineNumber(referencedBlock);
        if(attachLocation == AttachLocation.NEXT){
            indexReference++;
        }
        addStatement(blockToAdd, indexReference);
    }

    /**
     * Adds a statementListblock on specific index in the list of the body
     * @param block the block that is added to the statementList
     * @param lineNumber the index in the statementlist where it will be added
     */
    @Override
    public void addToStatementList(StatementBlock block, int lineNumber) {
        addStatement(block, lineNumber);
    }

    /**
     * Gets the location of a statementBlock in the list of the body
     * @param statementBlock a StatementBlock instance
     * @return The location of a statementBlock
     */
    @Override
    public ProgramLocation getLocation(StatementBlock statementBlock) {
        return new StatementBlockLocation(this, getLineNumber(statementBlock));
    }

    /**
     * Gets the line number a statement block in the list of the body
     * @param referencedBlock a StatementBlock instance
     * @return the line number a statement block in the list of the body
     */
    public int getLineNumber(StatementBlock referencedBlock) {
        return statements.indexOf(referencedBlock);
    }

    /**
     * Removes a statement block from the list in de body
     * @param statementBlock a statement block te be removed
     */
    @Override
    public void removeFromStatementList(StatementBlock statementBlock) {
        statementBlock.setParent(null);
        statements.remove(statementBlock);
    }

    /**
     * Adds a statement block on an index in the list of the body
     * @param blockToAdd the statement block to be added
     * @param indexReference an index in the list of the body
     */
    private void addStatement(StatementBlock blockToAdd, int indexReference) {
        statements.add(indexReference, blockToAdd);
        blockToAdd.setParent(this);
    }

    /**
     * gets the name of a StatementListBlock
     * @return the name of a StatementListBlock
     */
    @Override
    public String getName() {
        return "List";
    }

    /**
     * Checks whether the last statement of the statement list is executed
     * @param executedLineNumber the current line number of the statementListBlock
     * @param gameWorld used for checking the amount of block inside the statementList of this block
     * @return a boolean indicating whether the last statement of the statement list is executed
     */
    public boolean wasLastStatement(int executedLineNumber, GameWorldApi gameWorld) {
        return executedLineNumber >= statements.size()-1;
    }

    @Override
    public <B extends ProgramBlock> boolean isNextStepToExecute(int nextLineNumber, B source) {
        List<StatementBlock> statements = getStatements();
        if(nextLineNumber >= statements.size()){
            return false;
        }
        return statements.get(nextLineNumber) == source;
    }

    /**
     * Adds a block to the statement list
     * @param statementBlock the block that will be added
     */
    public void add(StatementBlock statementBlock) {
        addStatement(statementBlock, statements.size());
    }

    /**
     * Converts this object to a string representation
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return StatementListBlock.class.getSimpleName() + "" + statements.toString();
    }

    /**
     * Get the reference to this object
     * @return this object by reference
     */
    @Override
    public StatementListBlock getStatementListBlock() {
        return this;
    }

    /**
     * Gets the statement list of this block
     * @return the statement list of this block
     */
    public List<StatementBlock> getStatements(){
        return statements;
    }

    /**
     * Checks whether the statement list is empty or not
     * @return a boolean indicating whether the statement list is empty or not
     */
    public boolean isEmpty() {
        return statements.isEmpty();
    }

}
