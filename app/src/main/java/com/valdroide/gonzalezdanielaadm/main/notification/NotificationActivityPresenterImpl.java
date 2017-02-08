package com.valdroide.gonzalezdanielaadm.main.notification;

import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.notification.events.NotificationActivityEvent;
import com.valdroide.gonzalezdanielaadm.main.notification.ui.NotificationActivityView;

import org.greenrobot.eventbus.Subscribe;


public class NotificationActivityPresenterImpl implements NotificationActivityPresenter {


    private NotificationActivityView view;
    private EventBus eventBus;
    private NotificationActivityInteractor interactor;

    public NotificationActivityPresenterImpl(NotificationActivityView view, EventBus eventBus, NotificationActivityInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void sendNotification(String title, String content) {
        interactor.sendNotification(title, content);
    }

    @Override
    @Subscribe
    public void onEventMainThread(NotificationActivityEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case NotificationActivityEvent.SENT:
                    view.sentSuccess();
                    break;
                case NotificationActivityEvent.ERROR:
                    view.sentError(event.getError());
                    break;
            }
        }
    }
}
