package com.ui.main;

import an.awesome.pipelinr.Pipeline;
import com.blocker.gameworld.api.GameWorldApi;
import com.ui.Container;
import com.ui.components.divcomponent.Border;
import com.ui.components.divcomponent.DivComponent;
import com.ui.components.divcomponent.FlexAxis;
import com.ui.components.divcomponent.Padding;
import com.ui.components.programblocks.GameWorldComponent;
import com.ui.components.programblocks.PaletteArea;
import com.ui.components.programblocks.ProgramArea;
import com.ui.components.settings.SettingsArea;

import java.awt.*;

public class BlockrUi {

    public static com.ui.Container build(Pipeline pipeline, GameWorldApi gameWorld){
        com.ui.Container worldDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4, 2, 4, 4))
                        .withPadding(new Padding(0))
                        .addChildren(
                                new GameWorldComponent(gameWorld),
                                new SettingsArea(pipeline))
                                //DivComponent.builder().build())
                        .withFlexAxis(FlexAxis.Vertical)
                        .build();

        com.ui.Container palleteDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4, 2, 4, 2))
                        .withPadding(new Padding(0))
                        .addChildren(new PaletteArea(pipeline))
                        .build();

        Container programAreaDiv =
                DivComponent.builder()
                        .withBorder(new Border(Color.BLUE, 4 , 4, 4, 2))
                        .addChildren(new ProgramArea(pipeline))
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
