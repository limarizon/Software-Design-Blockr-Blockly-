package com.ui.components.block;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.UiMediator;
import com.ui.components.program.ProgramArea;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

public abstract class UIBlockComponent extends Component {
    protected final ProgramBlock source;
    protected final WindowPosition upperLeft;
    protected final UiMediator mediator;

    private enum ClickLocations {PREVIOUS, NEXT, CFB_BODY, CFB_CONDITION, C_LEFT, C_RIGHT, INVALID}
    private boolean highlight;

    protected boolean isHighlight() {
        return highlight;
    }

    public UIBlockComponent(ProgramBlock source, UiMediator mediator, WindowPosition rootPosition) {
        this.source = source;
        this.mediator = mediator;
        this.upperLeft = rootPosition;
        this.highlight = false;
    }

    public void setHighlight(){
        var current = ProgramArea.parent.getHighlightedBlock();
        if(current!=null){
        current.resetHighlight();}
        this.highlight = true;
        ProgramArea.parent.setHighlightedBlock((ProgramBlockComponent) this);
    }

    public void resetHighlight(){
        this.highlight = false;
    }

    protected String getName() {
        return source.getName();
    }
    public abstract int getWidth();
    public abstract int getHeight();

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        switch (mouseEvent.getType()){
            case MOUSE_UP:
                break;
            case MOUSE_DRAG:
                break;
            case MOUSE_DOWN:
                //mediator.send(new StartDragging(source));
                break;
        }

        switch (mouseEvent.getType()){
            case MOUSE_UP:
                //mediator.stopDragging();
                break;
            case MOUSE_DRAG:
                break;
            case MOUSE_DOWN:
                break;
        }
    }

    /*
    public ProgramBlockInsertInfo getSocketAndPlug(WindowPosition mousePosition, Block blockToAdd){
        if(blockToAdd == null || mousePosition == null){
            return null;
        }

        var clickLocation = getClickLocation(mousePosition,blockToAdd);

        switch (clickLocation){
            case INVALID:
                return null;
            case NEXT:
                return new ProgramBlockInsertInfo(source,blockToAdd, ProgramBlockInsertInfo.PlugLocation.OTHER);
            case PREVIOUS:
                return new ProgramBlockInsertInfo(blockToAdd, source, ProgramBlockInsertInfo.PlugLocation.OTHER);
            case C_RIGHT:
                return new ProgramBlockInsertInfo(source,blockToAdd, ProgramBlockInsertInfo.PlugLocation.OTHER);
            case C_LEFT:
                return new ProgramBlockInsertInfo(blockToAdd, source, ProgramBlockInsertInfo.PlugLocation.OTHER);
            case CFB_BODY:
                return new ProgramBlockInsertInfo(source,blockToAdd, ProgramBlockInsertInfo.PlugLocation.BODY);
            case CFB_CONDITION:
                return new ProgramBlockInsertInfo(source,blockToAdd, ProgramBlockInsertInfo.PlugLocation.OTHER);
        }

        return null;
    }

    private ClickLocations getClickLocation(WindowPosition mousePostion, Block blockToAdd){
        var relativePosition = mousePostion.minus(upperLeft); //dit berekent de relatieve positie in de geklikte blok
        relativePosition = relativePosition.minus(new WindowPosition(-4,7)); //??
        if(source instanceof ControlFlowBlock){
            if(blockToAdd instanceof ConditionBlock){
                var region = new WindowRegion(BlockSizes.BLOCK_WIDTH - BlockSizes.CONDITION_BLOCK_WIDTH, 0, BlockSizes.BLOCK_WIDTH, BlockSizes.CONDITION_BLOCK_HEIGHT);
                if(region.contains(relativePosition)){
                    //TODO: this if clause prevents certain problems with wallinfront
                    if(((ControlFlowBlock) source).getCondition() != null && blockToAdd instanceof WallInFrontBlock){
                        return ClickLocations.INVALID;
                    }
                    return ClickLocations.CFB_CONDITION;
                }
            }else if(blockToAdd instanceof StatementBlock){
                var region = new WindowRegion(BlockSizes.CONTROL_FLOW_INNER_START, BlockSizes.CONDITION_BLOCK_HEIGHT/2, BlockSizes.BLOCK_WIDTH, BlockSizes.CONDITION_BLOCK_HEIGHT);
                if(region.contains(relativePosition)){
                    return ClickLocations.CFB_BODY;
                }
                region = new WindowRegion(0,0, BlockSizes.BLOCK_WIDTH, BlockSizes.CONDITION_BLOCK_HEIGHT/2);
                if(region.contains(relativePosition)){
                    return ClickLocations.PREVIOUS;
                }
                region = new WindowRegion(0,getHeight(source) - (BlockSizes.BLOCK_HEIGHT - BlockSizes.CONDITION_BLOCK_HEIGHT), BlockSizes.BLOCK_WIDTH,getHeight(source));
                if(region.contains(relativePosition)){
                    return ClickLocations.NEXT;
                }
            }
        }else if(source instanceof StatementBlock){
            var region = new WindowRegion(0,0, BlockSizes.BLOCK_WIDTH, BlockSizes.BLOCK_HEIGHT/2);
            if(region.contains(relativePosition)){
                return ClickLocations.PREVIOUS;
            }
            region = new WindowRegion(0, BlockSizes.BLOCK_HEIGHT/2, BlockSizes.BLOCK_WIDTH, BlockSizes.BLOCK_HEIGHT);
            if(region.contains(relativePosition)){
                return ClickLocations.NEXT;
            }
        }else if(source instanceof ConditionBlock){
            if(source instanceof WallInFrontBlock){
                var region = new WindowRegion(0,0, BlockSizes.CONDITION_BLOCK_WIDTH/2, BlockSizes.CONDITION_BLOCK_HEIGHT);
                if(blockToAdd instanceof NotBlock && region.contains(relativePosition)){
                    return ClickLocations.C_LEFT;
                }else{
                    return ClickLocations.INVALID;
                }
            }else if(source instanceof NotBlock){
                var region = new WindowRegion(0,0, BlockSizes.CONDITION_BLOCK_WIDTH/2, BlockSizes.CONDITION_BLOCK_HEIGHT);
                if(blockToAdd instanceof NotBlock && region.contains(relativePosition)){
                    return ClickLocations.C_LEFT;
                }
                region = new WindowRegion(BlockSizes.CONDITION_BLOCK_WIDTH/2,0, BlockSizes.CONDITION_BLOCK_WIDTH, BlockSizes.CONDITION_BLOCK_HEIGHT);
                if(region.contains(relativePosition)){
                    return ClickLocations.C_RIGHT;
                }
            }
        }
        return ClickLocations.INVALID;
    }
*/


    @Override
    protected abstract void draw(Graphics graphics);

    /*
    private void drawTextComponent(Graphics graphics) {
        //The code below fixes the text position since it's based on the Clip Region of Graphics
        var w = WindowRegion.fromGraphics(graphics);
        int width = source instanceof ConditionBlock ? BlockData.CONDITION_BLOCK_WIDTH : BlockData.BLOCK_WIDTH;
        {
            Polygon poly = new Polygon(new int[]{0,width,width,Math.min(w.getWidth(),width),Math.min(w.getWidth(),width),0},
                    new int[]{0,0,1,1,w.getHeight(),w.getHeight()}, 6);

            graphics.setClip(poly);
            if(highlight)
                graphics.setColor(Color.WHITE);
            else{
            graphics.setColor(BlockData.FONT_COLOR);}
            this.titleComponent.draw(graphics);
        }
    }
    */
/*
    public Block getSource(){return source;}
*/
    public WindowPosition getUpperLeft(){ return upperLeft;}


}
