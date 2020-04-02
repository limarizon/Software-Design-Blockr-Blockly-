package com.ui.event;

import an.awesome.pipelinr.Command;

public interface UiEventHandler<C extends UiEvent<R>,R> extends Command.Handler<C, R> {


}
