package com.blockr.domain.block.interfaces;
/**
 * CompositeBlock is only used by controlflowblocks for when looping over the body is needed.
 * This by using reset or checking if current is ready
 */
public interface CompositeBlock {

    boolean isReady();

    void reset();
}
