package com.ui.event;

public class ResetExecutionHandler implements UiEventHandler<ResetExecutionHandler.ResetExecution, Void> {

    public ResetExecutionHandler() {

    }

    @Override
    public Void handle(ResetExecution resetExecution) {
        return null;
    }

    public static class ResetExecution implements UiEvent<Void> {
        public ResetExecution() {

        }
    }
}
