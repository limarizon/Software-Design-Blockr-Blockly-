package com.ui.keyevent;

import com.ui.ViewContext;
import com.ui.UiMediator;
import com.ui.components.program.ProgramArea;
import com.ui.event.ExecuteStepHandler;
import com.ui.event.ResetExecutionHandler;

import java.awt.event.KeyEvent;

public final class KeyEvents {
    private KeyEvents() {}

    public static void handleKeys(int id, int keyCode, char keyChar, ViewContext view, UiMediator mediator) {
        switch(id) {
            case KeyEvent.KEY_PRESSED:
                if(KeyEvent.VK_F5 ==keyCode){
                    mediator.send(new ExecuteStepHandler.ExecuteStep());
                }
                if(KeyEvent.VK_ESCAPE ==keyCode) {
                    mediator.send(new ResetExecutionHandler.ResetExecution());
                }
                break;
            case KeyEvent.KEY_TYPED:
                break;

        }
    }
}
