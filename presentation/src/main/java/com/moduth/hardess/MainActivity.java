package com.moduth.hardess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moduth.hardess.domain.model.Repository;
import com.moduth.hardess.listener.RepositoriesListener;
import com.moduth.hardess.presenter.RepositoriesPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RepositoriesListener{

    RepositoriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new RepositoriesPresenter(this);
    }

    @Override
    public void setData(List<Repository> repositories) {

    }
}
