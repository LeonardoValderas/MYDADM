package com.valdroide.gonzalezdanielaadm.main.notification;

public class NotificationActivityInteractorImpl implements NotificationActivityInteractor {

    private NotificationActivityRepository repository;

    public NotificationActivityInteractorImpl(NotificationActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void sendNotification(String title, String content) {
      repository.sendNotification(title, content);
    }
}
