package com.ui.keyevent;

import com.ui.ViewContext;
import com.ui.UiMediator;
import com.ui.components.program.ProgramArea;

import java.awt.event.KeyEvent;

public final class KeyEvents {
    private KeyEvents() {}

    public static void handleKeys(int id, int keyCode, char keyChar, ViewContext view, UiMediator mediator) {
        switch(id) {
            case KeyEvent.KEY_PRESSED:
                if(KeyEvent.VK_F5 ==keyCode){
                    //var current = ProgramArea.parent.getActive();
                    //current.setHighlight();
                    ProgramArea.parent.excecuteProgram();
                    view.repaint();
                }
                if(KeyEvent.VK_ESCAPE ==keyCode) {
                    ProgramArea.parent.getHighlightedBlock().resetHighlight();
                   // mediator.send(new GetWorld()).reset();
                    //mediator.send(new GetBlockProgram()).reset();
                    view.repaint();
                }
                break;
            case KeyEvent.KEY_TYPED:
                break;

        }
    }
}
