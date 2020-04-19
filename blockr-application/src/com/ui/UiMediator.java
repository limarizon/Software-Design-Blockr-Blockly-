package com.ui;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blockr.domain.GameState;
import com.ui.event.*;
import com.ui.presenter.ProgramCreationController;

import java.util.stream.Stream;

public class UiMediator {

    private Pipeline pipelinr;
    private ProgramCreationController programCreationController = new ProgramCreationController();

    public UiMediator(GameState gameState){
        pipelinr = new Pipelinr()
                .with(() -> Stream.of(
                    new DraggingStartedFromPaletteHandler(programCreationController),
                    new DraggingStartedFromProgramAreaHandler(programCreationController),
                    new DraggingStoppedHandler(programCreationController),
                    new DraggingStoppedInPaletteHandler(programCreationController),
                    new ExecuteStepHandler(gameState),
                    new ResetExecutionHandler(gameState),
                        new CtrlZHandler(gameState, programCreationController),
                        new CtrlShiftZHandler(gameState, programCreationController)
                ));
    }

    public void send(UiEvent uiEvent) {
        pipelinr.send(uiEvent);
    }
}
