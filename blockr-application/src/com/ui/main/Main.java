package com.ui.main;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.GameState;
import com.blockr.domain.game.Level;
import com.ui.MyCanvasWindow;
import com.ui.UiMediator;

import javax.swing.*;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args){
        GameState gameState = new GameState(createGameWorld());
        SwingUtilities.invokeLater(
                () -> {
                    UiMediator uiMediator = new UiMediator(gameState);
                    new MyCanvasWindow("Blockr !", uiMediator, BlockrUi.build(uiMediator, gameState)).show();
                }
        );
    }

    private static GameWorldApi createGameWorld() {
        try{
            GameWorldApi gameWorld = (GameWorldApi) Class.forName("com.blocker.gameworld.domain.RobotGameWorld").getDeclaredConstructor().newInstance();
            gameWorld.reset();
            return gameWorld;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
