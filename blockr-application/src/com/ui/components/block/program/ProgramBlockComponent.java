package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.event.DraggingStartedFromProgramAreaHandler;
import com.ui.event.DraggingStoppedHandler;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

public abstract class ProgramBlockComponent<B extends ProgramBlock>  extends Component {
    protected final B source;
    protected final WindowPosition upperLeft;
    protected final UiMediator mediator;
    private final GameState state;

    protected boolean isHighlight() {
        return state.isCurrentStep(source);
    }

    public ProgramBlockComponent(GameState state, B source, UiMediator mediator, WindowPosition upperLeft) {
        this.state = state;
        this.source = source;
        this.mediator = mediator;
        this.upperLeft = upperLeft;
    }

    protected String getName() {
        return source.getName();
    }
    public abstract int getWidth();
    public abstract int getHeight();

    @Override
    //TODO: hier differentiatie maken tussen in PA en naar palette
    public void onMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getType()){
            case MOUSE_DOWN:
                mediator.send(new DraggingStartedFromProgramAreaHandler.Command(source));
                break;
            case MOUSE_UP:
                //for me to test location
                if(mouseEvent.getAbsoluteWindowPosition().getX() > 600){
                    System.out.println("im outside");
                }
                mediator.send(new DraggingStoppedHandler.Command(source, getAttachLocation(mouseEvent)));
                break;
        }
    }

    private AttachLocation getAttachLocation(MouseEvent mouseEvent) {
        var relativePosition = mouseEvent.getRelativePosition();
        return translateToAttachLocation(relativePosition);
    }

    protected abstract AttachLocation translateToAttachLocation(WindowPosition relativePosition);

    @Override
    public abstract void draw(Graphics graphics);

}
