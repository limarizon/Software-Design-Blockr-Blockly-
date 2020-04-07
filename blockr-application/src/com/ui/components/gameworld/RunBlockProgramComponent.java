package com.ui.components.gameworld;

import com.ui.Component;
import com.ui.WindowRegion;
import com.ui.UiMediator;
import com.ui.components.text.HorizontalAlign;
import com.ui.components.text.TextComponent;
import com.ui.components.text.VerticalAlign;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

public class RunBlockProgramComponent extends Component {
    protected final UiMediator mediator;

    public RunBlockProgramComponent(UiMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void draw(Graphics graphics) {
        var region = WindowRegion.fromGraphics(graphics);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(0,0,region.getMaxX(),region.getMaxY());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,region.getMaxX()-1,region.getMaxY()-1);
        var text= new TextComponent("Run",10, HorizontalAlign.Center, VerticalAlign.Middle);
        text.draw(graphics);
    }


}