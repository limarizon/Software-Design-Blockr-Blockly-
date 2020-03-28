package com.ui.components.programblocks;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.Component;
import com.ui.WindowRegion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWorldComponent extends com.ui.Container {
    private final GameWorldApi gameWorld;

    public GameWorldComponent(GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public List<? extends Component> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        return null;
    }

    @Override
    protected void draw(Graphics graphics) {
        gameWorld.drawOnCanvas(graphics);
    }
}
