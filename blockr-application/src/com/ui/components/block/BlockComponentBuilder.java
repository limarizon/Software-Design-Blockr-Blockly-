package com.ui.components.block;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.WindowRegion;

import java.util.ArrayList;
import java.util.List;

public class BlockComponentBuilder {

    public static final int START_POSITION = 50;
    public static final int SPACE_BETWEEN = 0;

    private final java.util.List<UIBlockComponent> components = new ArrayList<>();
    private final List<WindowRegion> regionPositions = new ArrayList<>();

    public BlockComponentBuilder(StatementListBlock programDefinition, GameState state, UiMediator mediator) {
        var rootPos = new WindowPosition(START_POSITION,START_POSITION);

        for(StatementBlock statementBlock : programDefinition.getStatements() ){
            if(! statementBlock.isControlFlow()){
                PaletteStatementBlockComponent blockComponent = new PaletteStatementBlockComponent(state, statementBlock, mediator, rootPos);
                components.add(blockComponent);
                regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
                rootPos = rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
            }
        }
    }

    public List<UIBlockComponent> getComponents() {
        return components;
    }

    public WindowRegion getChildRegion(Component child) {
        int index = components.indexOf(child);
        if(index == -1) return null;
        return regionPositions.get(index);
    }
}
