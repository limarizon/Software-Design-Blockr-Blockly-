package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;

/**
 * This class describes how the execution is controlled of a blockprogram
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public class BlockExecution {
    /**
     * The ExecutionCallStack that can be stepped into
     */
    private final ExecutionCallStack executionCallStack;
    /**
     *  The block program created by the user that will be executed by pushing it onto the ExecutionCallStack
     */
    private final StatementListBlock statementListBlock;

    /**
     * Constructor which initialised the attributes of this class
     * @param statementListBlock The ExecutionCallStack that can be stepped into
     * @param gameWorld The block program created by the user that will be executed by pushing it onto the ExecutionCallStack
     */
    public BlockExecution(StatementListBlock statementListBlock, GameWorldApi gameWorld) {
        this.executionCallStack = new ExecutionCallStack(gameWorld);
        this.statementListBlock = statementListBlock;
        executionCallStack.pushFrame(statementListBlock);
    }

    /**
     * step into the ExecutionCallStack contained in this object
     */
    public void step(){
        this.executionCallStack.step();
    }

    /**
     * reset the ExecutionCallStack contained in this object and push and push the block program onto the executionCallStack
     */
    public void reset(){
        this.executionCallStack.reset();
        executionCallStack.pushFrame(statementListBlock);
    }

    /**
     * Undo a step in the ExecutionCallStack contained in this object
     */
    public void undoStep() { this.executionCallStack.undoStep();}

    /**
     * Redo a step in the ExecutionCallStack contained in this object
     */
    public void redoStep() { this.executionCallStack.redoStep();}

    /**
     * Get the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     * lineNumber
     * @param source a program block
     * @param <B> ProgramBlock subtype
     * @return the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     */
    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return executionCallStack.isCurrentStep(source);
    }

    //public int getStatementListBlockSize(){return this.statementListBlock;}

    /**
     * Indicates whether the ExectionCallStack in this object is executing
     * @return boolean Indicating whether the ExectionCallStack is executing
     */
    public boolean isStepping() {
        return executionCallStack.isStepping();
    }
}
