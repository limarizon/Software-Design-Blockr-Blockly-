package com.ui;

import an.awesome.pipelinr.Pipeline;
import com.ui.kul.CanvasWindow;
import com.ui.keyevent.KeyEvents;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

/*
this class has a tree-like hieriarchy containing only the rootcomponent and the viewcontext to which is linked
this is also subclass of the provided canvaswindow class with the paint,handleKeyevent,handlemouseevent methods overwritten
 */
public class MyCanvasWindow extends CanvasWindow {
    private final Container rootComponent;
    private final ViewContext viewContext;
    private final Pipeline mediator;

    public MyCanvasWindow(String title,Pipeline mediator, Container rootComponent) {
        super(title);

        if(rootComponent == null){
            throw new IllegalArgumentException("rootComponent must be effective");
        }
        this.mediator = mediator;
        this.rootComponent = rootComponent;
        this.viewContext = new ViewContext(this);
        initializeViewContext(rootComponent);
    }

    /**
     * this sets all the connections with the viewContext all of its chillcomponents
     * iterative deepening style, going down the tree of components,recursive
     * @param component
     */
    private void initializeViewContext(Component component){

        component.setViewContext(viewContext);

        if(component instanceof Container){

            var container = (Container)component;

            for(var child : container.getChildren()){
                initializeViewContext(child);
            }
        }
    }

    /**
     * this metod draws all the components in the the canvascomponent tree
     * @param g This object offers the methods that allow you to paint on the canvas.
     */
    @Override
    protected void paint(Graphics g) {
        drawComponentTree(rootComponent, WindowRegion.fromGraphics(g), g);
    }

    /**
     * this method updates the canvaswindow panel container
     */
    public void update(){
        this.repaint();
    }

    /**
     * this method draw the componentree of each tree
     * here the componentaction interface is implemented in argument itself by using a lamda function
     * @param component
     * @param windowRegion
     * @param g
     */
    private void drawComponentTree(Component component, WindowRegion windowRegion, Graphics g){
        traverseComponentTree(component, windowRegion, (c, w) -> c.draw(w.create(g)));
    }

    /**
     * here they call the draw method for every component of the component tree,using recursive iterative deepening method
     * @param component
     * @param windowRegion
     * @param componentAction
     */
    private void traverseComponentTree(Component component, WindowRegion windowRegion, ComponentAction componentAction){

        componentAction.execute(component, windowRegion);

        if(component instanceof Container){

            var container = (Container)component;

            for(var child : container.getChildren()){
                var childRegion = container.getChildRegion(windowRegion, child);
                traverseComponentTree(child, childRegion, componentAction);
            }
        }
    }

    /**
     * this method gets the component at a certain position in the window,it takes always the mast deep component in the tree
     * @param position
     * @return
     */
    private Component getComponentAt(WindowPosition position){

        var region = new WindowRegion(0, 0, getWidth(), getHeight());

        Component component = rootComponent;
        while(true){

            if(!(component instanceof Container))
                return component;

            var container = (Container)component;

            boolean inChild = false;

            for(var child : container.getChildren()){

                var childRegion = container.getChildRegion(region, child);

                if(!childRegion.contains(position))
                    continue;

                inChild = true;
                component = child;
                region = childRegion;
                break;
            }

            if(inChild)
                continue;

            return component;
        }
    }

    /**
     * this method is called when the listener hears a mouse-event en redirect this to a mouseventhandler of a
     * component corresponding to the coodinates of the mouseventoccurence
     * @param id
     * @param x
     * @param y
     * @param clickCount
     */
    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {

        var type = MouseEvent.Type.getTypeById(id);

        if(type == null)
            return;

        var component = getComponentAt(new WindowPosition(x, y));
        component.onMouseEvent(new MouseEvent(type,new WindowPosition(x, y)));
    }
     //TO-DO
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
       KeyEvents.handleKeys(id,keyCode,keyChar,viewContext,mediator);
    }

    interface ComponentAction {
        void execute(Component component, WindowRegion windowRegion);
    }
}