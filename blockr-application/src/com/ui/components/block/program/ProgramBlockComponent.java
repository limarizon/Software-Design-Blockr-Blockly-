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

/**
 * The class Program block component is the ui component from any block that can be added into a program in the Program Area.
 * This class is mainly used to detect correct AttachLocations as well as sending commands to the UiMediator when UiEvent
 * occurs on a given ProgramBlockComponent (meaning a programBlock in the Program Area).
 * @param <B> Any valid programBlock
 */
public abstract class ProgramBlockComponent<B extends ProgramBlock>  extends Component {
    /**
     * The Source.
     */
    protected final B source;
    /**
     * The Upper left.
     */
    protected final WindowPosition upperLeft;
    /**
     * The Mediator.
     */
    protected final UiMediator mediator;
    private final GameState state;

    /**
     * A boolean stating if the current block is highlighted or not
     *
     * @return state.isCurrentStep(source)
     */
    protected boolean isHighlight() {
        return state.isCurrentStep(source);
    }

    /**
     * Instantiates a new Program block component.
     *
     * @param state     the game state
     * @param source    the programBlock source
     * @param mediator  the UiMediator
     * @param upperLeft the upper left of the Window
     */
    public ProgramBlockComponent(GameState state, B source, UiMediator mediator, WindowPosition upperLeft) {
        this.state = state;
        this.source = source;
        this.mediator = mediator;
        this.upperLeft = upperLeft;
    }

    /**
     * Gets name.
     *
     * @return source.getName()
     */
    protected String getName() {
        return source.getName();
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public abstract int getWidth();

    /**
     * Gets height.
     *
     * @return the height
     */
    public abstract int getHeight();

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getType()){
            case MOUSE_DOWN:
                mediator.send(new DraggingStartedFromProgramAreaHandler.Command(source));
                break;
            case MOUSE_UP:
                mediator.send(new DraggingStoppedHandler.Command(source, getAttachLocation(mouseEvent)));
                break;
        }
    }

    /**
     * get the AttachLocation on mouseEvent
     * @param mouseEvent instance of MouseEvent class which has an ID and a position
     * @return {@link #translateToAttachLocation(WindowPosition)}
     */
    private AttachLocation getAttachLocation(MouseEvent mouseEvent) {
        var relativePosition = mouseEvent.getRelativePosition();
        return translateToAttachLocation(relativePosition);
    }

    /**
     * Translate windowposition to a valid attach location.
     *
     * @param relativePosition the relative position
     * @return the attach location
     */
    protected abstract AttachLocation translateToAttachLocation(WindowPosition relativePosition);

    @Override
    public abstract void draw(Graphics graphics);

}
