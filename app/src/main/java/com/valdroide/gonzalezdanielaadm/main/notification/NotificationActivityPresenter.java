package com.valdroide.gonzalezdanielaadm.main.notification;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.main.notification.events.NotificationActivityEvent;

public interface NotificationActivityPresenter {
    void onCreate();
    void onDestroy();
    void sendNotification(Context context, String title, String content);
    void onEventMainThread(NotificationActivityEvent event);
}
