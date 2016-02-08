package com.moduth.hardess.domain.repository;

import android.database.Observable;

import com.moduth.hardess.domain.model.Repository;

import java.util.List;

/**
 * Created by markzhai on 16/2/8
 *
 * @author markzhai
 */
public interface RepositoryRepository {

    /**
     * List user repositories
     *
     * @param username GitHub username
     * @return Observable of List<Repository>
     */
    Observable<List<Repository>> list(String username);
}
