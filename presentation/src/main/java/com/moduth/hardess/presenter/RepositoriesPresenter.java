package com.moduth.hardess.presenter;

import com.moduth.hardess.data.repository.RepositoryDataRepository;
import com.moduth.hardess.domain.interactor.DefaultObservable;
import com.moduth.hardess.domain.interactor.DefaultSubscriber;
import com.moduth.hardess.domain.model.Repository;
import com.moduth.hardess.domain.repository.RepositoryRepository;
import com.moduth.hardess.listener.RepositoriesListener;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Abner on 16/2/8.
 * Email nimengbo@gmail.com
 * github https://github.com/nimengbo
 */
public class RepositoriesPresenter  {

    private RepositoriesSubscriber subscriber;

    public RepositoriesPresenter(RepositoriesListener listener) {

        subscriber = new RepositoriesSubscriber(listener);

    }



    public void getRepositories(String username){

        RepositoryDataRepository.getInstance().list(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public class RepositoriesSubscriber extends DefaultObservable<List<Repository>> {

        private RepositoriesListener mListener;

        public RepositoriesSubscriber(RepositoriesListener listener) {
            this.mListener = listener;
        }

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Repository> repositories) {
            super.onNext(repositories);
            if(mListener != null){
                mListener.setData(repositories);
            }
        }
    }
}
