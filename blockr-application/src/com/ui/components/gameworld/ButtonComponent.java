package com.ui.components.gameworld;

import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowRegion;
import com.ui.components.text.HorizontalAlign;
import com.ui.components.text.TextComponent;
import com.ui.components.text.VerticalAlign;
import com.ui.event.ExecuteStepHandler;
import com.ui.event.UiEvent;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

/**
 * The Button component class which is used in the SettingsArea for all buttons
 */
public class ButtonComponent extends Component {
    /**
     * The Mediator.
     */
    protected final UiMediator mediator;
    private final UiEvent eventToSend;
    private final String caption;

    /**
     * Instantiates a new Button component.
     *
     * @param mediator    the mediator
     * @param caption     the caption for the button
     * @param eventToSend the event to send when pressed
     */
    public ButtonComponent(UiMediator mediator, String caption, UiEvent eventToSend){
        this.mediator = mediator;
        this.caption = caption;
        this.eventToSend = eventToSend;
    }

    @Override
    public void draw(Graphics graphics) {
        var region = WindowRegion.fromGraphics(graphics);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0,0,region.getMaxX(),region.getMaxY());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,region.getMaxX()-1,region.getMaxY()-1);
        var text= new TextComponent(caption,10, HorizontalAlign.Center, VerticalAlign.Middle);
        text.draw(graphics);
    }

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        if(mouseEvent.getType()== MouseEvent.Type.MOUSE_UP){
            mediator.send(eventToSend);
        }
    }
}