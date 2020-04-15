package com.ui.main;

import com.blockr.domain.GameState;
import com.ui.Container;
import com.ui.UiMediator;
import com.ui.WindowRegion;
import com.ui.components.container.FreePositionComponent;
import com.ui.components.container.div.Border;
import com.ui.components.container.div.DivComponent;
import com.ui.components.container.div.FlexAxis;
import com.ui.components.container.div.Padding;
import com.ui.components.gameworld.GameWorldComponent;
import com.ui.components.gameworld.SettingsArea;
import com.ui.components.palette.PaletteArea;
import com.ui.components.program.ProgramArea;

import java.awt.*;

public class BlockrUi {

    public static FreePositionComponent build(UiMediator mediator, GameState gameState){
        com.ui.Container worldDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4, 2, 4, 4))
                        .withPadding(new Padding(0))
                        .addChildren(
                                new GameWorldComponent(gameState.getGameWorld()),
                                new SettingsArea(mediator))
                        .withFlexAxis(FlexAxis.Vertical)
                        .build();

        com.ui.Container palleteDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4, 2, 4, 2))
                        .withPadding(new Padding(0))
                        .addChildren(new PaletteArea(mediator, gameState))
                        .build();

        Container programAreaDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4 , 4, 4, 2))
                        .addChildren(new ProgramArea(mediator, gameState))
                        .withPadding(new Padding(4))
                        .build();

        DivComponent parent = DivComponent
                .builder()
                .addChildren(worldDiv, palleteDiv, programAreaDiv)
                .withFlexAxis(FlexAxis.Horizontal)
                .withPadding(new Padding(0))
                .build();

        FreePositionComponent topComponent = new FreePositionComponent();
        topComponent.putChild(parent, new WindowRegion(0,0, 900,600));
        return topComponent;
    }

}
