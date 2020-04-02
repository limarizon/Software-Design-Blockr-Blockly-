package com.blockr.domain.modifications.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public interface Modification {
    void undo(GameWorldApi gameWorldApi, ExecutionCallStack stack);
    void redo(GameWorldApi gameWorldApi,ExecutionCallStack stack);
}
