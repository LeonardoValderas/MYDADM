package com.valdroide.gonzalezdanielaadm.main.notification.events;

/**
 * Created by LEO on 30/1/2017.
 */
public class NotificationActivityEvent {
    private int type;
    public static final int SENT = 0;
    public static final int ERROR = 1;
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
