package com.ui.main;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.State;
import com.blockr.domain.game.Level;
import com.ui.MyCanvasWindow;
import com.ui.UiMediator;

import javax.swing.*;

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

    public static void main(String[] args){
        SwingUtilities.invokeLater(
                () -> {
                    UiMediator uiMediator = new UiMediator();
                    new MyCanvasWindow("Hello World", uiMediator, BlockrUi.build(uiMediator, state.getGameWorld())).show();
                }
        );
    }
}
