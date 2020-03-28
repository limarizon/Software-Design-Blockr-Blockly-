package com.blockr.handlers.ui.input;

import com.ui.components.programblocks.UIBlockComponent;
import com.ui.WindowPosition;

public class PaletteSelection {
    private final WindowPosition selectionPosition;
    private final UIBlockComponent blockType;

    public PaletteSelection(WindowPosition selectionPosition, UIBlockComponent blockType) {
        this.selectionPosition = selectionPosition;
        this.blockType = blockType;
    }

    public WindowPosition getSelectionPosition() {
        return selectionPosition;
    }

    public UIBlockComponent getBlockType() {
        return blockType;
    }
}
