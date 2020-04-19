package com.ui;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blockr.domain.GameState;
import com.ui.event.*;
import com.ui.presenter.ProgramCreationController;

import java.util.stream.Stream;

public class UiMediator {

    private Pipeline pipelinr;
    private ProgramCreationController programCreator = new ProgramCreationController();

    public UiMediator(GameState gameState){
        pipelinr = new Pipelinr()
                .with(() -> Stream.of(
                    new DraggingStartedFromPaletteHandler(gameState, programCreator),
                    new DraggingStartedFromProgramAreaHandler(programCreator),
                    new DraggingStoppedHandler(programCreator),
                    new DraggingStoppedInPaletteHandler(programCreator),
                    new ExecuteStepHandler(gameState),
                    new ResetExecutionHandler(gameState),
                        new CtrlZHandler(gameState, programCreator),
                        new CtrlShiftZHandler(gameState, programCreator)
                ));
    }

    public void send(UiEvent uiEvent) {
        pipelinr.send(uiEvent);
    }
}
