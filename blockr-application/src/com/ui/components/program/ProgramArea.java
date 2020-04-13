package com.ui.components.program;

import com.blockr.domain.GameState;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowRegion;
import com.ui.components.block.program.AttachLocation;
import com.ui.components.block.program.ProgramBlockComponentBuilder;
import com.ui.event.DraggingStoppedHandler;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;
import java.util.List;

public class ProgramArea extends com.ui.Container {
    private final UiMediator mediator;
    private final GameState gameState;
    private ProgramBlockComponentBuilder blockComponentBuilder;

    public ProgramArea(UiMediator mediator, GameState gameState) {
        this.mediator = mediator;
        this.gameState = gameState;
    }

    @Override
    public List<? extends Component> getChildren() {
        blockComponentBuilder = new ProgramBlockComponentBuilder(gameState.getProgramDefinition(), gameState, mediator);
        return blockComponentBuilder.getComponents();
    }

    @Override
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        WindowRegion childRegion = blockComponentBuilder.getChildRegion(child);
        if(childRegion == null){
            return new WindowRegion(region.getMinX(), region.getMinY(), region.getMaxX(), region.getMaxY());
        }else{
            return new WindowRegion(600+childRegion.getMinX(), childRegion.getMinY(), 600+childRegion.getMaxX(), childRegion.getMaxY());
        }
    }

    @Override
    public void draw(Graphics graphics) {
    }

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getType()){
            case MOUSE_UP:
                mediator.send(new DraggingStoppedHandler.Command(gameState.getProgramDefinition(), AttachLocation.BODY));
                break;
        }
    }
}
