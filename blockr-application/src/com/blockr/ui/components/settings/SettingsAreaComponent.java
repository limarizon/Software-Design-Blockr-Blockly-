package com.blockr.ui.components.settings;

import an.awesome.pipelinr.Pipeline;
import com.blockr.handlers.blockprogram.executeprogram.ExecuteProgram;
import com.blockr.handlers.blockprogram.getblockprogram.GetBlockProgram;
import com.blockr.ui.components.programblocks.ProgramArea;
import com.blockr.ui.components.programblocks.ProgramBlockComponent;
import com.ui.Component;
import com.ui.WindowRegion;
import com.ui.components.textcomponent.HorizontalAlign;
import com.ui.components.textcomponent.TextComponent;
import com.ui.components.textcomponent.VerticalAlign;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

public class SettingsAreaComponent extends Component {
    protected final Pipeline mediator;
    protected ProgramBlockComponent previousBlock;

    public SettingsAreaComponent(Pipeline mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void draw(Graphics graphics) {
        var region = WindowRegion.fromGraphics(graphics);
        graphics.setColor(Color.YELLOW);
        graphics.fillRect(0,0,region.getMaxX(),region.getMaxY());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0,0,region.getMaxX()-1,region.getMaxY()-1);
        var text= new TextComponent("Run",10, HorizontalAlign.Center, VerticalAlign.Middle);
        text.draw(graphics);
    }

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        super.onMouseEvent(mouseEvent);
        switch (mouseEvent.getType()){
            case MOUSE_UP:
                break;
            case MOUSE_DRAG:
                break;
            case MOUSE_DOWN:
                break;
        }

        switch (mouseEvent.getType()){
            case MOUSE_UP:
                if(previousBlock!=null)
                    previousBlock.resetHighlight();
                var current = ProgramArea.parent.getProgramBlockComponent(mediator.send(new GetBlockProgram()).getActive());
                current.setHighlight();
                mediator.send(new ExecuteProgram());
                getViewContext().repaint();
                previousBlock = current;
                break;
            case MOUSE_DRAG:
                break;
            case MOUSE_DOWN:
                break;
        }
    }



}
