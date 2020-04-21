package com.ui.components.block.palette;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.event.DraggingStartedFromPaletteHandler;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

/**
 * The parent class for all Palette Area Block Components. Its an extension on the component.class.
 *
 * @param <B> takes any ProgramBlock as a parameter
 */
public abstract class PaletteBlockComponent<B extends ProgramBlock>  extends Component {
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

    /**
     * Instantiates a new Palette block component.
     *
     * @param source    the source
     * @param mediator  the mediator
     * @param upperLeft the upper left corner coordinate
     */
    public PaletteBlockComponent(B source, UiMediator mediator, WindowPosition upperLeft) {
        this.source = source;
        this.mediator = mediator;
        this.upperLeft = upperLeft;
    }

    /**
     * Gets name.
     *
     * @return the name
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
                mediator.send(new DraggingStartedFromPaletteHandler.Command(source.copy()));
                break;
        }
    }

    @Override
    public abstract void draw(Graphics graphics);

}
