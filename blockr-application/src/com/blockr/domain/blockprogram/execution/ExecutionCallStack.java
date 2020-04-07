package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.common.SafeProgrammingHelper;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;

import java.util.Stack;

public class ExecutionCallStack {
    private GameWorldApi gameWorld;
    private Stack<ExecutionContext> stack = new Stack<>();
    private Stack<StatementBlock> undoStack = new Stack<>();
    private Stack<StatementBlock> redoStack = new Stack<>();

    public ExecutionCallStack(GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
    }

    public GameWorldApi getGameWorld() {
        return gameWorld;
    }

    public void pushFrame(ControlFlowBlock controlFlowBlock) {
        if(! isCurrentFrame(controlFlowBlock)){
            pushFrame(new ExecutionContext(controlFlowBlock, 0, gameWorld));
        }
    }
    private void pushFrame(ExecutionContext context) {
        this.stack.push(context);
    }

    /**
     * Step for StatementListBlock blocks. Will reset the redo stack
     */
    public void step() {
        var currentContext = this.stack.peek();
        currentContext.getControlFlow().step(this);
        //resetRedo(); reverse comment om enkele testen al te doen slagen
    }

    /**
     * This function is called instead of 'step' for when executing undo's and reverts, as a
     * normal step from the original execution program would have to reset the revert stack.
     */
    public void nonStatementListBlockStep(){
        var currentContext = this.stack.peek();
        currentContext.getControlFlow().step(this);
    }

    @Override
    public String toString() {
        return stack.toString();
    }

    public int getCurrentLineNumber() {
        return stack.peek().getLineNumber();
    }

    public void dropFrame() {
        stack.pop();
        nextLineNumberPreviousFrame();
    }

    private void nextLineNumberPreviousFrame() {
        if(stack.isEmpty()) return;
        var executionContext = stack.pop();
        int lineNumber = executionContext.getLineNumber();
        stack.push(new ExecutionContext(executionContext.getControlFlow(),
                ++lineNumber,
                executionContext.getGameWorld()));
    }

    public void nextLineNumberCurrentFrame(int lineNumberToExecute) {
        var executionContext = stack.pop();
        stack.push(new ExecutionContext(executionContext.getControlFlow(),
                                        lineNumberToExecute,
                                        executionContext.getGameWorld()));
    }

    public boolean isCurrentFrame(ControlFlowBlock controlFlowBlock) {
        return !stack.isEmpty() && stack.peek().getControlFlow().equals(controlFlowBlock);
    }

    public void resetUndo() {undoStack.clear();}
    public void resetRedo() {redoStack.clear();}
    public void reset() {
        stack.clear();
    }

    public void previousLineNumberPreviousFrame(){
        if(stack.isEmpty()) return;
        var executionContext = stack.pop();
        int lineNumber = executionContext.getLineNumber();
        if(lineNumber==0){
            this.previousLineNumberPreviousFrame();
        }
        else{
        stack.push(new ExecutionContext(executionContext.getControlFlow(),
                --lineNumber,
                executionContext.getGameWorld()));
        }
    }
    public void pushOnUndoStack(StatementBlock block){
        SafeProgrammingHelper.throwIfNull(block,block.getName());
        undoStack.push(block);
    }
    public void undo(){
        if(undoStack.empty())
            return;
        var mod = undoStack.pop().invert();
        mod.nonStatementListBlockStep(this);
        undoStack.pop();
        redoStack.push(mod);
        previousLineNumberPreviousFrame();
    }

    public void redo(){
        if(redoStack.empty())
            return;
        var mod = redoStack.pop().invert();
        mod.nonStatementListBlockStep(this);
        undoStack.pop();
        nextLineNumberPreviousFrame();
    }
}
