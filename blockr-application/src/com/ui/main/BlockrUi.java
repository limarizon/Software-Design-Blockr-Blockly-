package com.ui.main;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.Container;
import com.ui.UiMediator;
import com.ui.components.div.Border;
import com.ui.components.div.DivComponent;
import com.ui.components.div.FlexAxis;
import com.ui.components.div.Padding;
import com.ui.components.gameworld.GameWorldComponent;
import com.ui.components.palette.PaletteArea;
import com.ui.components.program.ProgramArea;
import com.ui.components.gameworld.SettingsArea;

import java.awt.*;

public class BlockrUi {

    public static com.ui.Container build(UiMediator mediator, GameWorldApi gameWorld){
        com.ui.Container worldDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4, 2, 4, 4))
                        .withPadding(new Padding(0))
                        .addChildren(
                                new GameWorldComponent(gameWorld),
                                new SettingsArea(mediator))
                        .withFlexAxis(FlexAxis.Vertical)
                        .build();

        com.ui.Container palleteDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4, 2, 4, 2))
                        .withPadding(new Padding(0))
                        .addChildren(new PaletteArea(mediator))
                        .build();

        Container programAreaDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4 , 4, 4, 2))
                        .addChildren(new ProgramArea(mediator))
                        .withPadding(new Padding(4))
                        .build();

        return DivComponent
                .builder()
                .addChildren(worldDiv, palleteDiv, programAreaDiv)
                .withFlexAxis(FlexAxis.Horizontal)
                .withPadding(new Padding(0))
                .build();
    }

}
