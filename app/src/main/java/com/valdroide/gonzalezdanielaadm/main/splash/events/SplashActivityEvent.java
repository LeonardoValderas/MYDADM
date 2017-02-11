package com.valdroide.gonzalezdanielaadm.main.splash.events;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;

import java.util.List;

/**
 * Created by LEO on 30/1/2017.
 */
public class SplashActivityEvent {
    private int type;
    public static final int GETALLDATA = 0;
    public static final int ERROR = 1;
    public static final int GOTOTAB = 2;
    private String error;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
