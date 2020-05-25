package com.blockr.domain.blockprogram.execution;

import com.blocker.apiUtilities.Snapshot;
import com.blocker.gameworld.api.GameWorldApi;

import java.util.Stack;

/**
 * this class contains the memory of al the undo's made by the user
 */
public class UndoMemory {

    private final GameWorldApi gameWorld;
    /**
     * The stack that contains all the gameworld snapshots to be restored when calling a endo
     */
    private Stack<Snapshot> gameWorldSnapshots = new Stack<>();
    /**
     * this stack contains the executionstack at the time of every undo
     */
    private Stack<Stack<ExecutionContext>> executionCallStackSnapshots = new Stack<>();


    /**
     * The number of allowed redo's dependent on the undo's and the occurrence of an original modification
     */
    private int possibleRedos = 0;

    /**
     * constructor which intialises al the properties of the memory
     * @param gameWorld
     */
    public UndoMemory(GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
    }

    /**
     * resets the possibleredo counter
     */
    public void resetPossibleRedos(){
        this.possibleRedos = 0;
    }
    /**
     * this functions pushes a snapshot and the current executionstack into two stacks
     * @param stack the stack on which executionstakcks are pushed
     */
    public void pushSnapshot(Stack<ExecutionContext> stack) {
        gameWorldSnapshots.push(gameWorld.createSnapshot());
        executionCallStackSnapshots.push(copyStack(stack));
    }

    /**
     * makes a deepcopy of every element on the stack
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
     * does an undo step by popping a snapshot and stack from the memory stack
     * @return the new executiontack to be executed
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
     * checks whether a redo is possible
     * @return a boolean indicating whether a redo is possible
     */
    public boolean hasPossibleRedos() {
        return this.possibleRedos != 0;
    }

    /**
     * decreases the possibleRedo counter
     */
    public void undoStepDone() {
        this.possibleRedos--;
    }
}
