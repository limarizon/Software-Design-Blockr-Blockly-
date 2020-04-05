package com.ui.components.program;

import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowRegion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramArea extends com.ui.Container {
    private final UiMediator mediator;

    public ProgramArea(UiMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public List<? extends Component> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        return new WindowRegion(region.getMinX(), region.getMinY(), region.getMaxX(), region.getMaxY());
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
