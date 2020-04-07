package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreator;

public class ExecuteStepHandler implements UiEventHandler<ExecuteStepHandler.ExecuteStep, Void> {

    public ExecuteStepHandler() {

    }

    @Override
    public Void handle(ExecuteStep executeStep) {
        return null;
    }

    public static class ExecuteStep implements UiEvent<Void> {
        public ExecuteStep() {

        }
    }
}
