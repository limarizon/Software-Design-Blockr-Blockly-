package com.ui.main;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.State;
import com.blockr.domain.game.Level;
import com.blockr.handlers.blockprogram.addblock.AddBlockHandler;
import com.blockr.handlers.blockprogram.executeprogram.ExecuteProgramHandler;
import com.blockr.handlers.blockprogram.getblockprogram.GetBlockProgramHandler;
import com.blockr.handlers.blockprogram.getrootblock.GetRootBlockHandler;
import com.blockr.handlers.blockprogram.insertBlockInProgram.InsertBlockInProgramHandler;
import com.blockr.handlers.ui.input.GetPaletteSelectionHandler;
import com.blockr.handlers.ui.input.SetPaletteSelectionHandler;
import com.blockr.handlers.ui.input.recordMousePos.GetMouseRecordHandler;
import com.blockr.handlers.ui.input.recordMousePos.SetRecordMouseHandler;
import com.blockr.handlers.ui.input.resetuistate.ResetUIStateHandler;
import com.ui.MyCanvasWindow;

import javax.swing.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Main {

    private static final State state = new State(asList(new Level(createGameWorld(), 5)));

    private static GameWorldApi createGameWorld() {
        try{
            GameWorldApi gameWorld = (GameWorldApi) Class.forName("com.blocker.gameworld.domain.RobotGameWorld").getDeclaredConstructor().newInstance();
            gameWorld.reset();
            return gameWorld;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private static final Pipeline pipeline = new Pipelinr()
            .with(() -> Stream.of(
                    new SetPaletteSelectionHandler(state)
                    , new GetPaletteSelectionHandler(state)
                    , new GetRootBlockHandler(state)
                    , new AddBlockHandler(state)
                    , new GetMouseRecordHandler(state)
                    , new SetRecordMouseHandler(state)
                    , new InsertBlockInProgramHandler(state)
                    , new ResetUIStateHandler(state)
                    ,new ExecuteProgramHandler(state)
                    ,new GetBlockProgramHandler(state)));

    public static void main(String[] args){
        SwingUtilities.invokeLater(
                () -> new MyCanvasWindow("Hello World", pipeline, BlockrUi.build(pipeline, state.getGameWorld())).show()
        );
    }
}
