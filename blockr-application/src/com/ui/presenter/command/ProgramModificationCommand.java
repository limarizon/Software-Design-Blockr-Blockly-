package com.ui.presenter.command;

public interface ProgramModificationCommand {
    boolean isOriginalModification();

    void execute();

    void undoExecution();
}
