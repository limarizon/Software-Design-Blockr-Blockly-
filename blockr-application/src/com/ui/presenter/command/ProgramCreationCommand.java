package com.ui.presenter.command;

public interface ProgramCreationCommand {
    void execute();

    void undoExecution();
}
