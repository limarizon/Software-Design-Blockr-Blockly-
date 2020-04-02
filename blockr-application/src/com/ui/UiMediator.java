package com.ui;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blockr.domain.State;

import java.util.stream.Stream;

public class UiMediator {

    private Pipeline pipelinr = new Pipelinr();

    public UiMediator(State state){
                    /*
        new Pipelinr()
                .with(() -> Stream.of(

                        new SetPaletteSelectionHandler(state)
                    , new GetPaletteSelectionHandler(state)
                    , new GetRootBlockHandler(state)
                    , new AddBlockHandler(state)
                    , new GetMouseRecordHandler(state)
                    , new SetRecordMouseHandler(state)
                    , new InsertBlockInProgramHandler(state)
                    , new ResetUIStateHandler(state)
                    , new ExecuteProgramHandler(state)
                    , new GetBlockProgramHandler(state))
                ));
                     */
    }

}
