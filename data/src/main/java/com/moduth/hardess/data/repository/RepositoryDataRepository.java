package com.moduth.hardess.data.repository;


import com.moduth.hardess.domain.model.Repository;
import com.moduth.hardess.domain.repository.RepositoryRepository;
import com.moduth.hardess.http.MdthRequest;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by markzhai on 16/2/8
 *
 * @author markzhai
 */
public class RepositoryDataRepository implements RepositoryRepository {


    private static RepositoryDataRepository mInstance;

    public static RepositoryDataRepository getInstance() {
        if (mInstance == null) {
            synchronized (RepositoryDataRepository.class) {
                if (mInstance == null) {
                    mInstance = new RepositoryDataRepository();
                }
            }
        }
        return mInstance;
    }


    // like https://api.github.com/users/markzhai/repos
    @Override
    public Observable<List<Repository>> list(String username) {
        return MdthRequest.getInstance().getMdthHttpApi().getRepositoriesByUserName(username).subscribeOn(Schedulers.io());
    }

}
