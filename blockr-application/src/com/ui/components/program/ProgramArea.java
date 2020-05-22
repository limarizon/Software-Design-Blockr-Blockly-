package com.ui.components.program;

import com.blockr.domain.GameState;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowRegion;
import com.ui.components.block.program.AttachLocation;
import com.ui.components.block.program.ProgramBlockComponentBuilder;
import com.ui.event.DraggingStoppedHandler;
import com.ui.mouseevent.MouseEvent;
import com.ui.presenter.ProgramDefinitionHolder;

import java.awt.*;
import java.util.List;

/**
 * The Program area class containing all components in the blockProgram itself.
 * Registering UiEvents as well as drawing itself when called by BlockrUi.build in the UI main class.
 */
public class ProgramArea extends com.ui.Container {
    private final UiMediator mediator;
    private final GameState gameState;
    private ProgramBlockComponentBuilder blockComponentBuilder;
    private ProgramDefinitionHolder programDefinitionHolder;

    /**
     * Instantiates a new Program area.
     *
     * @param mediator  the mediator
     * @param gameState the game state
     */
    public ProgramArea(UiMediator mediator, GameState gameState) {
        this.mediator = mediator;
        this.gameState = gameState;
        this.programDefinitionHolder = mediator.programDefinitionHolder;
    }

    @Override
    public List<? extends Component> getChildren() {
        blockComponentBuilder = ProgramBlockComponentBuilder.builder(gameState, mediator)
                .addBlockProgram(programDefinitionHolder.getProgramDefinition().getStatements())
                .addBlockProgram(List.of(programDefinitionHolder.getFunctionDefinition()))
                .build();
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
                mediator.send(new DraggingStoppedHandler.Command(programDefinitionHolder.getProgramDefinition(), AttachLocation.BODY));
                break;
        }
    }
}
