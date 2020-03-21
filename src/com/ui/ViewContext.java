package com.ui;

import com.blockr.handlers.ui.input.SetPaletteSelection;
import com.ui.mouseevent.MouseEvent;

/**
 * the class is here to give the components information about the canvaswindow, the heigth an the width, to redraw the canvas after an update
 * This way you have information in the domain about the view without altering the view
 * if the way of working of the canvas window changes, you only have to change viewcontext instead of all the components
 */
public class ViewContext {


    public void repaint(){
        myCanvasWindow.update();
    }

    private final MyCanvasWindow myCanvasWindow;

    public ViewContext(MyCanvasWindow myCanvasWindow){
        this.myCanvasWindow = myCanvasWindow;
    }
}
