package com.blockr.domain.blockprogram.execution;

import com.blocker.apiUtilities.Snapshot;
import com.blocker.gameworld.api.GameWorldApi;

import java.util.Stack;

public class UndoMemory {

    private final GameWorldApi gameWorld;
    /**
     * The stack that contains all the gameworld snapshots to be restored when calling a endo
     */
    private Stack<Snapshot> gameWorldSnapshots = new Stack<>();
    private Stack<Stack<ExecutionContext>> executionCallStackSnapshots = new Stack<>();


    /**
     * The number of allowed redo's dependent on the undo's and the occurrence of an original modification
     */
    private int possibleRedos = 0;

    /**
     *
     * @param gameWorld
     */
    public UndoMemory(GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
    }
    public void resetPossibleRedos(){
        this.possibleRedos = 0;
    }
    /**
     *
     * @param stack
     */
    public void pushSnapshot(Stack<ExecutionContext> stack) {
        gameWorldSnapshots.push(gameWorld.createSnapshot());
        executionCallStackSnapshots.push(copyStack(stack));
    }

    /**
     *
     * @param stack
     * @return
     */
    private Stack<ExecutionContext> copyStack(Stack<ExecutionContext> stack) {
        Stack<ExecutionContext> copy = new Stack<>();
        for(int i = 0; i < stack.size(); i++){
            ExecutionContext executionContext = stack.elementAt(i);
            copy.push(executionContext);
        }
        return copy;
    }

    /**
     *
     * @return
     */
    public Stack<ExecutionContext> undoStep() {
        if (gameWorldSnapshots.empty()){
            return new Stack<>();
        }
        gameWorld.restore(gameWorldSnapshots.pop());
        this.possibleRedos++;
        return executionCallStackSnapshots.pop();
    }

    /**
     *
     * @return
     */
    public boolean hasPossibleRedos() {
        return this.possibleRedos != 0;
    }

    /**
     *
     */
    public void undoStepDone() {
        this.possibleRedos--;
    }
}
