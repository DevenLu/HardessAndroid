package com.moduth.hardess.data.repository;

import android.database.Observable;

import com.moduth.hardess.domain.model.Repository;
import com.moduth.hardess.domain.repository.RepositoryRepository;

import java.util.List;

/**
 * Created by markzhai on 16/2/8
 *
 * @author markzhai
 */
public class RepositoryDataRepository implements RepositoryRepository {

    // like https://api.github.com/users/markzhai/repos
    @Override
    public Observable<List<Repository>> list(String username) {
        return null;
    }

}
