package com.ui.components.gameworld;

import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.UiMediator;
import com.ui.event.ExecuteStepHandler;
import com.ui.event.ResetExecutionHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsArea extends com.ui.Container {
    private final List<ButtonComponent> components = new ArrayList<>();
    private final List<WindowPosition> regionPositions = new ArrayList<>();

    private final UiMediator mediator;

    public SettingsArea(UiMediator mediator) {
        this.mediator = mediator;
        init(mediator);
    }

    private void init(UiMediator mediator) {
        var rootPos = new WindowPosition(50,50);
        components.add(new ButtonComponent(mediator, "Step", new ExecuteStepHandler.Command()));
        regionPositions.add(rootPos);
        
        rootPos = new WindowPosition(50,100);
        components.add(new ButtonComponent(mediator, "Reset", new ResetExecutionHandler.Command()));
        regionPositions.add(rootPos);
    }


    @Override
    public List<? extends com.ui.Component> getChildren() {
        return components;
    }

    @Override
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        int index = components.indexOf(child);

        if (index == -1) {
            return null;
        }
        WindowPosition blockPosition = regionPositions.get(index);
        blockPosition = new WindowPosition(blockPosition.getX() + region.getMinX(), blockPosition.getY() + region.getMinY());
        WindowRegion childRegion = new WindowRegion(blockPosition.getX(), blockPosition.getY(), blockPosition.getX() + 100, blockPosition.getY() + 50);

        return new WindowRegion(childRegion.getMinX(), childRegion.getMinY(), Math.min(region.getMaxX(), childRegion.getMaxX()), Math.min(region.getMaxY(), childRegion.getMaxY()));
    }

    @Override
    public void draw(Graphics graphics) {
        // TODO :
    }
}
