package com.ui.main;

import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.GameState;
import com.ui.MyCanvasWindow;
import com.ui.UiMediator;

import javax.swing.*;

public class Main {

    public static void main(String[] args){
        GameState gameState = new GameState(createGameWorldType());
        SwingUtilities.invokeLater(
                () -> {
                    UiMediator uiMediator = new UiMediator(gameState);
                    new MyCanvasWindow("Blockr !", uiMediator, BlockrUi.build(uiMediator, gameState)).show();
                }
        );
    }

    private static GameWorldTypeApi createGameWorldType() {
        try{
            GameWorldTypeApi gameWorldType = (GameWorldTypeApi) Class.forName("com.blocker.gameworld.domain.RobotGameWorldType").getDeclaredConstructor().newInstance();
            return gameWorldType;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
