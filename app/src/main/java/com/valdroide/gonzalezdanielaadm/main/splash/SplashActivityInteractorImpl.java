package com.valdroide.gonzalezdanielaadm.main.splash;


import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;

import java.util.List;

public class SplashActivityInteractorImpl implements SplashActivityInteractor {

    private SplashActivityRepository repository;

    public SplashActivityInteractorImpl(SplashActivityRepository repository) {
        this.repository = repository;
    }


    @Override
    public void getDateTable() {
        repository.getDateTable();
    }

    @Override
    public void getAllData(Context context) {
        repository.getAllData(context);
    }
}
