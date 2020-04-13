package com.ui.components.block.palette;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.event.DraggingStartedFromPaletteHandler;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

public abstract class PaletteBlockComponent<B extends ProgramBlock>  extends Component {
    protected final B source;
    protected final WindowPosition upperLeft;
    protected final UiMediator mediator;

    public PaletteBlockComponent(B source, UiMediator mediator, WindowPosition upperLeft) {
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
