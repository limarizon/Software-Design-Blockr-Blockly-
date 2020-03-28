package com.blockr.domain.block;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.block.interfaces.markers.ReadOnlyMoveForwardBlock;

public class MoveForwardBlock extends StatementBlock implements ReadOnlyMoveForwardBlock {

    public StatementBlock execute(GameWorldApi gameWorld) {
        gameWorld.moveForward();
        return getNext();
    }
}
