package com.ui;

import com.blockr.ui.components.programblocks.ProgramArea;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

/**
 * This component class the will be contained in a container class
 */
public abstract class Component {
    /**
     * the each component is linked with context containing a canvaswindow, so we the context is repainted, every
     * component that is linked will be repainted to
     * @return
     */
    protected ViewContext getViewContext(){
        return viewContext;
    }

    void setViewContext(ViewContext viewContext){
        this.viewContext = viewContext;
    }

    private ViewContext viewContext;

    protected abstract void draw(Graphics graphics);
  
    public void onMouseEvent(MouseEvent mouseEvent){

    }
}
