package com.valdroide.gonzalezdanielaadm.main.notification;


import android.content.Context;

public interface NotificationActivityRepository {
    void sendNotification(Context context, String title, String content);
}
