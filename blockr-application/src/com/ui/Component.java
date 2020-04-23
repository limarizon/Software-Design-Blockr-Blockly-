package com.ui;

import com.ui.mouseevent.MouseEvent;

import java.awt.*;

/**
 * This component class the will be contained in a container class
 */
public abstract class Component {
    /**
     * the each component is linked with context containing a canvaswindow, so the context is repainted, every
     * component that is linked will be repainted as well
     * @return the viewContext
     */
    protected ViewContext getViewContext(){
        return viewContext;
    }

    public void setViewContext(ViewContext viewContext){
        this.viewContext = viewContext;
    }

    private ViewContext viewContext;

    public abstract void draw(Graphics graphics);

    public void onMouseEvent(MouseEvent mouseEvent){

    }

}
