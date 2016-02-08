package com.moduth.hardess.domain.interactor;

import com.moduth.hardess.domain.executor.PostExecutionThread;
import com.moduth.hardess.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * @author from.clean
 * @version 1.0.0
 *
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * @param <T> the presentation, will get rx.Observable<T>
 */
public abstract class UseCase<T> {
    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private static ThreadExecutor sDefaultExecutor;
    private static PostExecutionThread sPostExecutionThread;

    private Subscription mSubscription = Subscriptions.empty();

    protected UseCase(ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        this.mThreadExecutor = threadExecutor;
        this.mPostExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable<T> buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
     * @return self
     */
    @SuppressWarnings("unchecked")
    public UseCase execute(Subscriber<T> useCaseSubscriber) {
        this.mSubscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
        return this;
    }

    /**
     * Unsubscribes from current {@link Subscription}.
     */
    public void unSubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    /**
     * init
     * @param defaultExecutor     default task executor
     * @param postExecutionThread default post thread
     */
    public static void init(ThreadExecutor defaultExecutor, PostExecutionThread postExecutionThread) {
        sDefaultExecutor = defaultExecutor;
        sPostExecutionThread = postExecutionThread;
    }

    protected static ThreadExecutor getDefaultExecutor() {
        return sDefaultExecutor;
    }


    protected static PostExecutionThread getPostExecutionThread() {
        return sPostExecutionThread;
    }
}
