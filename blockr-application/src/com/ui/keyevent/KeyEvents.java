package com.ui.keyevent;

import com.ui.ViewContext;
import com.ui.UiMediator;
import com.ui.event.*;

import java.awt.event.KeyEvent;

/**
 * The class which will handler all Key events by sending correct uiEvents to the mediator.
 */
public final class KeyEvents {
    private KeyEvents() {}

    /**
     * Send the correct handlers to the UiMediator given the keysEvents their ID's and combinations.
     *
     * @param id        the id of the keyEvent
     * @param keyCode   the key code
     * @param keyChar   the key char
     * @param modifiers the modifiers of the keyEvent
     * @param mediator  the UiMediator
     */
    public static void handleKeys(int id, int keyCode, char keyChar, int modifiers, UiMediator mediator) {
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
                    mediator.send(new CtrlShiftZHandler.Command());
                }
                //CTRL Z
                if ((keyCode == KeyEvent.VK_Z) && ((modifiers & KeyEvent.CTRL_MASK) != 0) && ((modifiers & KeyEvent.SHIFT_MASK) == 0)){
                    //System.out.println("CTRL Z 2 is pressed");
                    mediator.send(new CtrlZHandler.Command());
                }

                break;
            case KeyEvent.KEY_TYPED:
                break;

        }
    }
}
