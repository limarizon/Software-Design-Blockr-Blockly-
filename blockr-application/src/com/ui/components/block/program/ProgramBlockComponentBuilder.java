package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.WindowRegion;

import java.util.ArrayList;
import java.util.List;

public class ProgramBlockComponentBuilder {

    public static final int START_POSITION = 50;
    public static final int SPACE_BETWEEN = 0;

    private final java.util.List<ProgramBlockComponent> components = new ArrayList<>();
    private final List<WindowRegion> regionPositions = new ArrayList<>();

    public ProgramBlockComponentBuilder(StatementListBlock programDefinition, GameState state, UiMediator mediator) {
        var rootPos = new WindowPosition(START_POSITION,START_POSITION);

        //TODO: meer gecompliceerde programma's uittekenen hier
        for(StatementBlock statementBlock : programDefinition.getStatements() ){
            if(! statementBlock.isControlFlow()){
                ProgramStatementBlockComponent blockComponent = new ProgramStatementBlockComponent(state, statementBlock, mediator, rootPos);
                components.add(blockComponent);
                regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
                rootPos = rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
            }
        }
    }

    public List<ProgramBlockComponent> getComponents() {
        return components;
    }

    //TODO : binnen gecompliceerde programma's ook nog het juiste component kunnen selecteren (onMouseUp)
    public WindowRegion getChildRegion(Component child) {
        int index = components.indexOf(child);
        if(index == -1) return null;
        return regionPositions.get(index);
    }
}
