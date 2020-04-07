package com.ui.components.program;

import com.blockr.domain.GameState;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowRegion;
import com.ui.components.block.AttachLocation;
import com.ui.event.DraggingStoppedHandler;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramArea extends com.ui.Container {
    private final UiMediator mediator;
    private final GameState gameState;

    public ProgramArea(UiMediator mediator, GameState gameState) {
        this.mediator = mediator;
        this.gameState = gameState;
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

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getType()){
            case MOUSE_UP:
                    mediator.send(new DraggingStoppedHandler.DraggingStopped(gameState.getProgramDefinition(), AttachLocation.BODY));
                break;
        }
    }
}
