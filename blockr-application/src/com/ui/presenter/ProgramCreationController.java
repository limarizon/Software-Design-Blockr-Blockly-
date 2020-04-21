package com.ui.presenter;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;
import com.ui.presenter.command.CommandFactory;
import com.ui.presenter.command.ProgramModificationCommand;

import java.util.Stack;

/**
 * This class creates ui commands in order to control the Program Area environment correctly.
 * It implements the command pattern in order to allow for undo and redo functionality of commands which have been executed successfully prior.
 */
public class ProgramCreationController {
    private CommandFactory commandFactory = new CommandFactory();
    private Stack<ProgramModificationCommand> doneBlockActions = new Stack<>();
    private Stack<ProgramModificationCommand> unDoneBlockActions = new Stack<>();

    /**
     * Creates a command stating that there is a drag that has started from the Palette Area.
     * @param blockToAdd The block which is being dragged from the Palette Area
     */
    public void startDraggingFromPalette(ProgramBlock blockToAdd) {
        commandFactory.startFromPalette(blockToAdd);
    }

    /**
     * Creates a command stating that there is a drag that has started from the Program Area.
     * @param blockToMove The block which is being dragged in the Program Area
     */
    public void startDraggingFromProgramArea(ProgramBlock blockToMove) {
        commandFactory.startFromProgramArea(blockToMove);
    }

    /**
     * Creates a command that handles addition of blocks to the Program Area, including reordering of the blockProgram,
     * once the dragging has stopped.
     * @param destinationBlock the block to which the blockToAdd will be linked to
     * @param attachLocation the location at which the blockToAdd will be linked to the destinationBlock
     */
    public void handleDraggingStoppedForAddingOrMoving(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        execute(commandFactory.createAddOrMoveCommand(destinationBlock, attachLocation));
    }

    /**
     * Creates a command that handles removal of blocks from the Program Area once dragging has stopped.
     */
    public void handleDraggingStoppedForRemoval() {
        execute(commandFactory.createRemovalCommand());
    }

    /**
     * Calls the execution of a given command.
     * If the execution is successful, the command will be added to the donBlockAction stack
     * and the unDoneBlockActions stack will be cleared.
     * @param command a ProgramModificationCommand
     */
    private void execute(ProgramModificationCommand command) {
        if(command.execute()){
            doneBlockActions.push(command);
            unDoneBlockActions.clear();
        }
    }

    /**
     * Undoes the last command placed on the doneBlockActions stack and pops it from that stack.
     * The popped command will be placed on the unDoneBlockActions stack.
     */
    public void undo(){
        if(! doneBlockActions.isEmpty()){
            ProgramModificationCommand toUndo = doneBlockActions.pop();
            toUndo.undoExecution();
            unDoneBlockActions.push(toUndo);
        }
    }

    /**
     * Redoes the last command placed on the unDoneBlockActions stack and pops it from that stack.
     * The popped command will be placed on the doneBlockActions stack.
     */
    public void redo(){
        if(! unDoneBlockActions.isEmpty()){
            ProgramModificationCommand toRedo = unDoneBlockActions.pop();
            toRedo.execute();
            doneBlockActions.push(toRedo);
        }
    }


}
