package com.ui.main;

import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.GameState;
import com.ui.MyCanvasWindow;
import com.ui.UiMediator;

import javax.swing.*;

public class Main {
    // program argument should be "com.blocker.gameworld.domain.RobotGameWorldType"
    public static void main(String[] args){
        GameState gameState = new GameState(createGameWorldType(args[0]));
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
            System.out.println("Unable to create a gameworld for this specified implementation");
            System.out.println("ENDING THE PROGRAM");
            throw new RuntimeException(e);
        }

    }
}
