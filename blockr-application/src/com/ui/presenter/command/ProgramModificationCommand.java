package com.ui.presenter.command;

/**
 * Public interface that provides basic functionality for modification commands in the program area.
 */
public interface ProgramModificationCommand {
    /**
     * Will execute the command. The method call will the necessary methods to add the correct blocks.
     * @return true if the command has been executed. false if the command could not be executed due to an illegalState
     * or any other restriction that has been predefined in the implementation of the GameState.
     *
     */
    boolean execute();

    /**
     * Will undo the prior execution of a certain command.
     */
    void undoExecution();
}
