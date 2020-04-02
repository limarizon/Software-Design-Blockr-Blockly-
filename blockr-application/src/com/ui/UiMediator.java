package com.ui;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blockr.domain.State;
import com.ui.event.DraggingStartedHandler;
import com.ui.event.DraggingStoppedHandler;
import com.ui.event.UiEvent;
import com.ui.presenter.ProgramCreator;

import java.util.stream.Stream;

public class UiMediator {

    private Pipeline pipelinr = new Pipelinr();
    private ProgramCreator programCreator = new ProgramCreator();

    public UiMediator(State state){
        pipelinr = new Pipelinr()
                .with(() -> Stream.of(
                    new DraggingStartedHandler(programCreator),
                    new DraggingStoppedHandler(programCreator)

                ));
    }

    public void send(UiEvent uiEvent) {
        pipelinr.send(uiEvent);
    }
}
