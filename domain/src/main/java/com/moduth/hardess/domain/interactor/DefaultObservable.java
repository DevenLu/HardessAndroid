package com.moduth.hardess.domain.interactor;

import android.util.Log;

import rx.Observer;

/**
 * @param <T> domain object
 *            Default subscriber base class to be used whenever you want default error handling.
 * @author from.clean
 * @version 1.0.0
 */
public class DefaultObservable<T> implements Observer<T> {
    private static final String TAG = "DefaultSubscriber";

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e(TAG, "DefaultSubscriber.onError  " + e.getMessage());
    }

    @Override
    public void onNext(T t) {
    }
}
