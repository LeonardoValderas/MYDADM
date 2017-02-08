package com.valdroide.gonzalezdanielaadm.main.notification;

import com.valdroide.gonzalezdanielaadm.main.notification.events.NotificationActivityEvent;

public interface NotificationActivityPresenter {
    void onCreate();
    void onDestroy();
    void sendNotification(String title, String content);
    void onEventMainThread(NotificationActivityEvent event);
}
