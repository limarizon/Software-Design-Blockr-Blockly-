package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ContainingStatementsBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;

import java.util.Stack;

/**
 * This class describes how a predefined block program will be executed according to the statement blocks it holds by the use of a stack
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class ExecutionCallStack {
    /**
     * The gameworld API to which actions will be performed and predicates evaluated that are on the execution stack
     */
    private GameWorldApi gameWorld;
    private UndoMemory undoMemory;

    /**
     * The stack of ExecutionContext objects which will determine the order of exection of the gameworld actions
     */
    private Stack<ExecutionContext> stack = new Stack<>();

    /**
     * constructor which initialises the attributes of this class
     * @param gameWorld gameWorldAPI on which actions will be performed and predicates evaluated
     */
    public ExecutionCallStack(GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
        this.undoMemory = new UndoMemory(gameWorld);
    }

    /**
     * gets the gameworld on which actions will be performed and predicates evaluated that are on the execution stack
     * @return gameWorldAPI on which actions will be performed and predicates evaluated
     */
    public GameWorldApi getGameWorld() {
        return gameWorld;
    }

    /**
     * Pushes a ContainingStatementBlock onto the stack if it's not already on top of the stack
     * @param controlFlowBlock to be pushed as an ExecutionContext on the stack
     */
    public void pushFrame(ContainingStatementsBlock controlFlowBlock) {
        if (!isCurrentFrame(controlFlowBlock)) {
            pushFrame(new ExecutionContext(controlFlowBlock, 0, gameWorld));
        }
            if(controlFlowBlock.getParent() != null &&controlFlowBlock.getParent().getName().equals("Function")){
                if (!controlFlowBlock.getStatementListBlock().isEmpty() && controlFlowBlock.getStatementListBlock().getStatements().get(getCurrentLineNumber()).getName().equals("Function Call")){
                    pushFrame(new ExecutionContext(controlFlowBlock, 0, gameWorld));
                }
            }

    }

    /**
     * Pushes a ExectutionContext unto the stack
     * @param context to be pushed on the stack
     */
    private void pushFrame(ExecutionContext context) {
        this.stack.push(context);
    }

    /**
     * Executes the actionBlock on top of the executionStack, the block is the first block
     * in a statemenListBlock contained in the body of a ContainingStatementBlock which is on top of the stack.
     * after stepping into the StatementListBlock of the toplevel ContainingStatementBlock, the linenumber are updated.
     * If the stack is empty or the goal is reached then a message is printed onto the console
     */
    public void step() {
        if (this.stack.isEmpty()){
            System.out.println("Block Program finished executing");
            return;
        }

        undoMemory.pushSnapshot(this.stack);
        undoMemory.resetPossibleRedos();
        stepInExecution();
    }

    /**
     *
     */
    private void stepInExecution() {
        var currentContext = this.stack.peek();
        currentContext.getStatementContainer().step(this);
        if(gameWorld.isGoalReached()){
            System.out.println("Goal state is reached! Congratulations!");
        }
    }

    /**
     * converts the ExecutionStack object to a String object
     * @return the converted Stack object
     */
    @Override
    public String toString() {
        return stack.toString();
    }

    /**
     * Gets the linenumber of the StatementListBlock contained in the ContainingStatementBlock on top of the stack.
     * @return the linenumber of the StatementListBlock contained in the ContainingStatementBlock on top of the stack.
     */
    public int getCurrentLineNumber() {
        return stack.peek().getLineNumber();
    }

    /**
     * Pops the ExecutionContext on top of the stack if the stack is not empty and
     * increases the linenumber of the new ExecutionContext on top
     * on top of the stack
     */
    public void dropFrame() {
        if(stack.isEmpty()) return;
        stack.pop();
        nextLineNumberPreviousFrame();
    }

    /**
     * Increases the lineNumber in the ExecutionContext on top of the stack or removes the toplevel
     * ExecutionContext if it's last statement block in it's StatementListBlock is executed
     */
    private void nextLineNumberPreviousFrame() {
        if (stack.isEmpty()) return;
        var executionContext = stack.peek();
        int lineNumber = executionContext.getLineNumber();

        if (executionContext.getStatementContainer().wasLastStatement(lineNumber, executionContext.getGameWorld())) {
            dropFrame();
        } else {
            nextLineNumberCurrentFrame(++lineNumber);
        }
    }

    /**
     * Sets a lineNumber in the ExecutionContext on top of the stack
     * @param lineNumberToExecute linenumber to be set
     */
    public void nextLineNumberCurrentFrame(int lineNumberToExecute) {
        var executionContext = stack.pop();
        stack.push(new ExecutionContext(executionContext.getStatementContainer(),
                lineNumberToExecute,
                executionContext.getGameWorld()));
    }

    /**
     * Checks whether a ContainingStatementBlock is executed next by the stack
     * @param controlFlowBlock the ContainingStatementBlock that should be executed next
     * @return a boolean indicating whether a ContainingStatementBlock is executed next by the stack
     */
    public boolean isCurrentFrame(ContainingStatementsBlock controlFlowBlock) {
        return !stack.isEmpty() && stack.peek().getStatementContainer().equals(controlFlowBlock);
    }

    /**
     * resets the ExecutionStack and the gameworld
     */
    public void reset() {
        gameWorld.reset();
        stack.clear();
    }


    /**
     * Undo a step if the undoStack is not empty by restoring the gameworld snapshot on top of the
     * undo stack and by resetting line numbers.
     * The redo counter is also increased
     */
    public void undoStep() {
        this.stack = undoMemory.undoStep();
    }

    /**
     *  Redo a step by executing the next ExecutionContext on top of the stack, decrementing the redo
     *  counter and indicating that it was not a original modification
     */
    public void redoStep() {
        if (undoMemory.hasPossibleRedos()) {
            this.stepInExecution();
            undoMemory.undoStepDone();
        }
        /*
            var currentContext = this.stack.peek();
            this.originalModification = false;
            currentContext.getStatementContainer().step(this);

            this.originalModification = true;
            this.possibleRedos--;
        }

         */
    }

    /**
     * Get the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     * lineNumber
     * @param source a program block
     * @param <B> ProgramBlock subtype
     * @return the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     */
    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        if (stack.isEmpty()) return false;
        ExecutionContext currentContext = stack.peek();
        return currentContext.isCurrentStep(source);
    }

    /**
     * Indicates whether the ExecutionCallStack is executing
     * @return boolean Indicating whether the ExectionCallStack is executing
     */
    public boolean isStepping() {
        return !(stack.size() == 1 && stack.peek().getLineNumber() == 0);
    }
}
