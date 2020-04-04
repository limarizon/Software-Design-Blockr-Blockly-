package com.ui.components.block;

import com.ui.WindowPosition;
import com.ui.UiMediator;
import com.ui.mouseevent.MouseEvent;

import java.awt.*;

public class ProgramBlockComponent extends UIBlockComponent {

    @Override
    public void onMouseEvent(MouseEvent mouseEvent) {
        super.onMouseEvent(mouseEvent);
/*
        switch (mouseEvent.getType()){
            case MOUSE_UP:
                var paletteSelection = mediator.send(new GetPaletteSelection());
                if(paletteSelection!=null) {
                    /*
                    var copy = BlockCreator.build(BlockCreator.BlockType.getType(paletteSelection.getBlockType().getSource()));
                    /**
                     * here you calculate the position in programarea by subtraction the clicked mouseposition
                     * in the blockcomponent, the block always is set in his left upper corner
                     */
                    /*
                    var recordedMouse = mediator.send(new GetMouseRecord());
                    if(recordedMouse == null){
                        recordedMouse = (new WindowPosition(50,50));
                    }else{
                        recordedMouse = (mouseEvent.getWindowPosition().minus(recordedMouse));
                    }

                    var info = getSocketAndPlug(recordedMouse,copy);
                    if(info != null){
                        var pa = ProgramArea.parent;
                        var newRoot = mediator.send(new InsertBlockInProgram(info));
                        var pos= pa.getblockPosition(newRoot,this.getUpperLeft());
                        pa.removeProgramBlockComponentsBaseOnRoot(newRoot);
                        pa.buildProgramBlockComponentFromRoot(newRoot,pos);
                        this.getViewContext().repaint();
                    }
                }
                break;
            case MOUSE_DRAG:
                var recordedMouse = mediator.send(new GetMouseRecord());
                if(recordedMouse == null){
                    mediator.send(new SetRecordMouse(new WindowPosition(mouseEvent.getWindowPosition().getX(),0)));
                }
                break;
            case MOUSE_DOWN:

                System.out.println(source.getName());
                break;
        }

        switch (mouseEvent.getType()){
            case MOUSE_UP:
                mediator.send(new ResetUIState());
                break;
            case MOUSE_DRAG:
                break;
            case MOUSE_DOWN:
                break;
        }
        */
    }

    public ProgramBlockComponent(/*Block source, */UiMediator mediator, WindowPosition rootPosition) {
        super(null, mediator, rootPosition);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
