package com.valdroide.gonzalezdanielaadm.main.splash.ui;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;

import java.util.List;

/**
 * Created by LEO on 8/2/2017.
 */

public interface SplashActivityView {
    void gotToTab();
    void setError(String msg);
    void getDateTableEmpty();
}
