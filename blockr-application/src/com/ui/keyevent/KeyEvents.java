package com.ui.keyevent;

import com.ui.ViewContext;
import com.ui.UiMediator;
import com.ui.event.*;

import java.awt.event.KeyEvent;

public final class KeyEvents {
    private KeyEvents() {}

    public static void handleKeys(int id, int keyCode, char keyChar, int modifiers, ViewContext view, UiMediator mediator) {
        switch(id) {
            case KeyEvent.KEY_PRESSED:
                if(KeyEvent.VK_F5 == keyCode){
                    mediator.send(new ExecuteStepHandler.Command());
                }
                if(KeyEvent.VK_ESCAPE == keyCode) {
                    mediator.send(new ResetExecutionHandler.Command());
                }
                //if()
                //CTRL SHIFT Z
                if ((keyCode == KeyEvent.VK_Z) && ((modifiers & KeyEvent.CTRL_MASK) != 0) && ((modifiers & KeyEvent.SHIFT_MASK) != 0)){
                    //System.out.println("CTRL SHIFT Z 1 is pressed");
                    mediator.send(new ctrlShiftZHandler.Command());
                }
                //CTRL Z
                if ((keyCode == KeyEvent.VK_Z) && ((modifiers & KeyEvent.CTRL_MASK) != 0) && ((modifiers & KeyEvent.SHIFT_MASK) == 0)){
                    //System.out.println("CTRL Z 2 is pressed");
                    mediator.send(new ctrlZHandler.Command());
                }

                break;
            case KeyEvent.KEY_TYPED:
                break;

        }
    }
}
