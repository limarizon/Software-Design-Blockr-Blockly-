package com.ui;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blockr.domain.GameState;
import com.ui.event.*;
import com.ui.presenter.ProgramCreationController;

import java.util.stream.Stream;

/**
 * The class for which we use the Pipelinr Library. This class creates an instance of the mediator element in our design.
 * Pipelinr needs a stream of handlers upon which it can rely to find the solution for every event that can occur in the UI.
 * We can see this as following workflow: Event occurs, send event to UiMediator, correct handler will be assigned to the event,
 * Event being handled and domain is executing the solution, Ui updated through a updated Domain.
 */
public class UiMediator {

    private Pipeline pipelinr;
    private ProgramCreationController programCreator = new ProgramCreationController();

    /**
     * Instantiates a new UiMediator, we provide all handlers with its corresponding commands(requests) as a stream to the
     * pipelinr constructor.
     *
     * @param gameState the game state
     */
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

    /**
     * A method needed to send events to the mediator that need handling.
     * If given a valid uiEvent, the correct handler will be assigned and so can a solution be found.
     *
     * @param uiEvent the ui event
     */
    public void send(UiEvent uiEvent) {
        pipelinr.send(uiEvent);
    }
}
