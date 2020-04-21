package com.ui.event;

import an.awesome.pipelinr.Command;

/**
 * The interface Ui event handler.
 *
 * @param <C> The command type uiEvent that will need handling
 * @param <R> The response of type handler, which will be used when a <C> is sent to the mediator.
 */
public interface UiEventHandler<C extends UiEvent<R>,R> extends Command.Handler<C, R> {


}
