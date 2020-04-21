package com.ui.components.gameworld;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.Component;
import com.ui.WindowRegion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game world component class that is needed to further differentiate in the gameWorld objects.
 * Provides methods to get its children (of a component) and also the WindowRegion of a certain child.
 * Provides the method to draw itself
 */
public class GameWorldComponent extends com.ui.Container {
    private final GameWorldApi gameWorld;

    /**
     * Instantiates a new Game world component.
     *
     * @param gameWorld the game world
     */
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
    public void draw(Graphics graphics) {
        gameWorld.draw(graphics);
    }
}
