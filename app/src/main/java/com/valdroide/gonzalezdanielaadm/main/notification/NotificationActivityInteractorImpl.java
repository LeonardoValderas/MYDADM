package com.valdroide.gonzalezdanielaadm.main.notification;

import android.content.Context;

public class NotificationActivityInteractorImpl implements NotificationActivityInteractor {

    private NotificationActivityRepository repository;

    public NotificationActivityInteractorImpl(NotificationActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void sendNotification(Context context, String title, String content) {
      repository.sendNotification(context, title, content);
    }
}
