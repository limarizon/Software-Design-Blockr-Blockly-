package com.ui;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blockr.domain.GameState;
import com.ui.event.*;
import com.ui.presenter.ProgramCreator;

import java.util.stream.Stream;

public class UiMediator {

    private Pipeline pipelinr;
    private ProgramCreator programCreator = new ProgramCreator();

    public UiMediator(GameState gameState){
        pipelinr = new Pipelinr()
                .with(() -> Stream.of(
                    new DraggingStartedHandler(programCreator),
                    new DraggingStoppedHandler(programCreator),
                    new ExecuteStepHandler(gameState),
                    new ResetExecutionHandler(gameState)
                ));
    }

    public void send(UiEvent uiEvent) {
        pipelinr.send(uiEvent);
    }
}
