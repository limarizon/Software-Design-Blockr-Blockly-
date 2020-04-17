package com.ui.presenter.command;

public interface ProgramCreationCommand {
    boolean isOriginalModification();

    void execute();

    void undoExecution();
}
