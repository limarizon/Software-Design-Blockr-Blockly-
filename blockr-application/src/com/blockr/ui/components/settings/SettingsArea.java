package com.blockr.ui.components.settings;

import an.awesome.pipelinr.Pipeline;
import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsArea extends com.ui.Container {
    private static final List<SettingsAreaComponent> settingsAreaComponents = new ArrayList<>();
    private static final List<WindowPosition> regionPositions = new ArrayList<>();

    private final Pipeline mediator;

    public SettingsArea(Pipeline mediator) {
        this.mediator = mediator;
        init(mediator);
    }

    private static void init(Pipeline mediator) {
        var rootPos = new WindowPosition(50,50);
        settingsAreaComponents.add(new SettingsAreaComponent(mediator));
        regionPositions.add(rootPos);
    }


    @Override
    public List<? extends com.ui.Component> getChildren() {
        return settingsAreaComponents;
    }

    @Override
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        if (!(child instanceof SettingsAreaComponent)) {
            return null;
        }
        int index = settingsAreaComponents.indexOf(child);

        if (index == -1) {
            return null;
        }
        WindowPosition blockPosition = regionPositions.get(index);
        blockPosition = new WindowPosition(blockPosition.getX() + region.getMinX(), blockPosition.getY() + region.getMinY());
        WindowRegion childRegion = new WindowRegion(blockPosition.getX(), blockPosition.getY(), blockPosition.getX() + 100, blockPosition.getY() + 50);

        return new WindowRegion(childRegion.getMinX(), childRegion.getMinY(), Math.min(region.getMaxX(), childRegion.getMaxX()), Math.min(region.getMaxY(), childRegion.getMaxY()));
    }
    @Override
    protected void draw(Graphics graphics) {

    }
}
