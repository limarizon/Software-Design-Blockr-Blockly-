package com.ui;

import com.ui.components.container.FreePositionComponent;
import com.ui.event.RequestGameStateProgramDefinition;
import com.ui.kul.CanvasWindow;
import com.ui.keyevent.KeyEvents;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;
import java.util.ArrayList;

/**
 * this class has a tree-like hieriarchy containing only the rootcomponent and the viewcontext to which is linked
 * this is also subclass of the provided canvaswindow class with the paint,handleKeyevent,handlemouseevent methods overwritten
 */
public class MyCanvasWindow extends CanvasWindow {
    private final FreePositionComponent rootComponent;
    private final ViewContext viewContext;
    private final UiMediator mediator;

    public MyCanvasWindow(String title, UiMediator mediator, FreePositionComponent rootComponent) {
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
     * this methode draws all the components in the the canvascomponent tree
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
     * here the componentAction interface is implemented in argument itself by using a lambda function
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


    private class ComponentAtLocation{
        private Component component;
        private WindowPosition relativePosition;

        public ComponentAtLocation(Component component, WindowPosition relativePosition) {
            this.component = component;
            this.relativePosition = relativePosition;
        }

        public void onMouseEvent(WindowPosition absolutePosition, MouseEvent.Type type) {
            component.onMouseEvent(new MouseEvent(type, absolutePosition, relativePosition));
        }
    }

    /**
     * this method gets the component at a certain position in the window,
     * it takes always the mast deep component in the tree
     * @param position
     * @return
     */
    private ComponentAtLocation getComponentAt(WindowPosition position){
        var windowRegions = new ArrayList<WindowRegion>();

        var region = new WindowRegion(0, 0, getWidth(), getHeight());
        Component component = rootComponent;
        while(true){

            if(!(component instanceof Container)){
                return new ComponentAtLocation(component, computeRelativePosition(position, region));
            }

            var container = (Container)component;

            boolean inChild = false;

            for(var child : container.getChildren()){

                var childRegion = container.getChildRegion(region, child);

                if(!childRegion.contains(position))
                    continue;

                inChild = true;
                component = child;
                region = childRegion;
                windowRegions.add(region);
                break;
            }

            if(inChild)
                continue;

            return new ComponentAtLocation(component, computeRelativePosition(position, region));
        }
    }

    private WindowPosition computeRelativePosition(WindowPosition position, WindowRegion windowRegion) {
        return new WindowPosition(position.getX() - windowRegion.getMinX(), position.getY() - windowRegion.getMinY());
    }

    /**
     * this method is called when the listener hears a mouse-event en redirect this to a mouseventhandler of a
     * component corresponding to the coodinates of the mouseventoccurence
     * @param id id of the mouse event
     * @param x x-value of the mouse event
     * @param y y-value of the mouse event
     * @param clickCount amount of clicks
     */
    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        MouseEvent.Type type = MouseEvent.Type.getTypeById(id);
        switch (type){
            case MOUSE_DRAG:
                rootComponent.moveDraggable(new WindowPosition(x, y));
                repaint();
                break;
            case MOUSE_UP:
                rootComponent.stopDraggable();
                mediator.send(new RequestGameStateProgramDefinition.Command());
                repaint();
            case MOUSE_DOWN:
                WindowPosition mousePosition = new WindowPosition(x, y);
                getComponentAt(mousePosition).onMouseEvent(mousePosition, type);
                mediator.send(new RequestGameStateProgramDefinition.Command());
                break;
        }

    }

    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar, int modifiers) {
       KeyEvents.handleKeys(id, keyCode, keyChar, modifiers, mediator);
       repaint();
    }

    interface ComponentAction {
        void execute(Component component, WindowRegion windowRegion);
    }
}
