package com.blockr.domain.blockprogram.execution;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.GameActionBlock;
import com.blockr.domain.blockprogram.definition.GamePredicateBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public abstract class BlockTest {

    private List<Action> actions;
    private List<GameActionBlock> blocks;
    protected GameWorldApi gameWorldApi;
    private List<Predicate> predicates;
    private List<GamePredicateBlock> predicateBlocks;

    @Before
    public void createGameActionBlocks() {
        gameWorldApi = mock(GameWorldApi.class);
        blocks = new ArrayList<>();
        predicateBlocks = new ArrayList<>();
        predicates = new ArrayList<>();
        actions = new ArrayList<>();
        IntStream.range(0,20)
                .forEach(i -> {
                    actions.add(mock(Action.class));
                    blocks.add(new GameActionBlock(actions.get(i)));

                    predicates.add(mock(Predicate.class));
                    predicateBlocks.add(new GamePredicateBlock(predicates.get(i)));
                });
    }


    protected StatementBlock gameActionBlock(int i) {
        return blocks.get(i);
    }

    protected GamePredicateBlock gamePredicateBlock(int i) {
        return predicateBlocks.get(i);
    }

    protected void verifyActionTriggered(int i) {
        verifyActionTriggered(i, 1);
    }

    protected void verifyActionTriggered(int i, int times) {
        verify(gameWorldApi, times(times)).perform(actions.get(i));
    }

    protected void expectPredicateToReturn(int i, Boolean b) {
        when(gameWorldApi.evaluate(predicates.get(i))).thenReturn(b);
    }
}
