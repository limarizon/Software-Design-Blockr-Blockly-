package com.blockr.domain;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.blockr.domain.blockprogram.execution.BlockExecution;
import com.blockr.domain.game.Level;
import com.blockr.handlers.ui.input.PaletteSelection;
import com.ui.WindowPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class State {
    private Level activeLevel;
    private BlockExecution blockExecution;

    public GameWorldApi getGameWorld(){
        return activeLevel.getGameWorld();
    }

    public BlockExecution getBlockExecution(){
        return blockExecution;
    }

    public void resetBlockProgram(){
        this.getGameWorld().reset();
        this.blockExecution.reset();
    }

    public List<Level> getLevels(){
        return Collections.unmodifiableList(levels);
    }

    private final List<Level> levels;

    public Level getActiveLevel(){
        return activeLevel;
    }

    public void setActiveLevel(Level level){

        if(level == null){
            throw new IllegalArgumentException("level must be effective");
        }

        this.activeLevel = level;
    }



    public State(List<Level> levels){

        if(levels.size() == 0){
            throw new IllegalArgumentException("There must be at least one level");
        }

        this.levels = new ArrayList<>(levels);
        this.activeLevel = levels.get(0);

        this.blockExecution = new BlockExecution(new StatementListBlock(), getGameWorld());
    }

    /*UI STUFF*/
    private PaletteSelection paletteSelection;
    public void setPaletteSelection(PaletteSelection paletteSelection) {
        this.paletteSelection = paletteSelection;
    }
    public PaletteSelection getPaletteSelection() {
        return paletteSelection;
    }

    private WindowPosition recordMouse = null;
    public void setRecordMouse(WindowPosition recordMouse) {
        this.recordMouse = recordMouse;
    }
    public WindowPosition getRecordMouse() {
        return recordMouse;
    }

    public void resetUIState() {
        paletteSelection = null;
        recordMouse = null;
    }
}