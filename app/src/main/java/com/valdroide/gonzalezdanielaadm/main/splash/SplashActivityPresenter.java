package com.valdroide.gonzalezdanielaadm.main.splash;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.main.splash.events.SplashActivityEvent;


public interface SplashActivityPresenter {
    void onCreate();
    void onDestroy();
    void getDateTable();
    void getAllData(Context context);
    void onEventMainThread(SplashActivityEvent event);
}
