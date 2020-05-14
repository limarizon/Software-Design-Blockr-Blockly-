package com.ui.main;

import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.GameState;
import com.ui.MyCanvasWindow;
import com.ui.UiMediator;

import javax.swing.*;

public class Main {

    // program argument should be "com.blocker.gameworld.domain.RobotGameWorldType"
    public static void main(String[] args){
        if(args == null || args.length < 1){
            throw new IllegalArgumentException("Game world type must be defined, add as a program argument");
        }
        String gameWorldType = args[0];
        GameState gameState = new GameState(createGameWorldType(gameWorldType));
        SwingUtilities.invokeLater(
                () -> {
                    UiMediator uiMediator = new UiMediator(gameState);
                    new MyCanvasWindow("Blockr !", uiMediator, BlockrUi.build(uiMediator, gameState)).show();
                }
        );
    }

    private static GameWorldTypeApi createGameWorldType(String gameWorldImplementation) {
        try{
            GameWorldTypeApi gameWorldType = (GameWorldTypeApi) Class.forName(gameWorldImplementation).getDeclaredConstructor().newInstance();
            return gameWorldType;
        }catch (Exception e){
            System.out.println("Unable to create a game world for this specified implementation");
            System.out.println("ENDING THE PROGRAM");
            throw new RuntimeException(e);
        }

    }
}
