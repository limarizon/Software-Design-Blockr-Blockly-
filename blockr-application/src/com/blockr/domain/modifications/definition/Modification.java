package com.blockr.domain.modifications.definition;

import com.blocker.gameworld.api.GameWorldApi;

public interface Modification {
    void undo(GameWorldApi gameWorldApi);
    void redo(GameWorldApi gameWorldApi);
}
