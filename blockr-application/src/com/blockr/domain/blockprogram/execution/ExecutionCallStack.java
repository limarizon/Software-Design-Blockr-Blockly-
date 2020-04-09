package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.snapshot.api.Snapshot;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;

import java.util.Stack;

public class ExecutionCallStack {
    private GameWorldApi gameWorld;
    private Stack<ExecutionContext> stack = new Stack<>();
    private Stack<Snapshot> undoStack = new Stack<>();


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

    public void step() {
        if(this.stack.isEmpty()) return;
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

    public void reset() {
        gameWorld.reset();
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
    public void pushSnapshot(){
        if(originalMod)
             possibleRedos =0;
        undoStack.push(gameWorld.createSnapshot());
    }

    public void undoStep() {
        if(undoStack.empty())
        return;
        gameWorld.restore(undoStack.pop());
        previousLineNumberPreviousFrame();
        this.possibleRedos++;
    }
    private int possibleRedos = 0;
    private boolean originalMod =true;

    public void redoStep() {
        if(possibleRedos!=0) {
            var currentContext = this.stack.peek();
            this.originalMod = false;
            currentContext.getControlFlow().step(this);
            this.originalMod = true;
            this.possibleRedos--;
        }
    }
    
    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        if(stack.isEmpty()) return false;
        ExecutionContext currentContext = stack.peek();
        return currentContext.isCurrentStep(source);
    }
}
