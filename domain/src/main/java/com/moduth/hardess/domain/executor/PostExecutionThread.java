package com.moduth.hardess.domain.executor;

import rx.Scheduler;

/**
 * @author from.clean
 * @version 1.0.0
 *
 * Thread abstraction created to change the execution context from any thread to any other thread.
 * Useful to encapsulate a UI Thread for example, since some job will be done in background, an
 * implementation of this interface will change context and update the UI.
 */
public interface PostExecutionThread {
    /**
     *
     * @return Scheduler
     */
    Scheduler getScheduler();
}
