package com.ui.event;

import an.awesome.pipelinr.Command;

/**
 * An interface defining what the mediator expects from a command that needs handling
 * @param <R> A self implemented command
 */
public interface UiEvent<R> extends Command<R> {
}
