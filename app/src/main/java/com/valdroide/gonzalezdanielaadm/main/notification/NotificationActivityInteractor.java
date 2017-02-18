package com.valdroide.gonzalezdanielaadm.main.notification;


import android.content.Context;

public interface NotificationActivityInteractor {
    void sendNotification(Context context, String title, String content);
}
