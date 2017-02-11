package com.valdroide.gonzalezdanielaadm.main.notification.di;


import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityInteractor;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityInteractorImpl;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityPresenter;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityPresenterImpl;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityRepository;
import com.valdroide.gonzalezdanielaadm.main.notification.NotificationActivityRepositoryImpl;
import com.valdroide.gonzalezdanielaadm.main.notification.ui.NotificationActivityView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationActivityModule {
    NotificationActivityView view;
     public NotificationActivityModule(NotificationActivityView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    NotificationActivityView providesNotificationActivityView() {
        return this.view;
    }

    @Provides
    @Singleton
    NotificationActivityPresenter providesNotificationActivityPresenter( NotificationActivityView view, EventBus eventBus, NotificationActivityInteractor listInteractor) {
        return new NotificationActivityPresenterImpl(view, eventBus, listInteractor);
    }

    @Provides
    @Singleton
    NotificationActivityInteractor providesNotificationActivityInteractor(NotificationActivityRepository repository) {
        return new NotificationActivityInteractorImpl(repository);
    }

    @Provides
    @Singleton
    NotificationActivityRepository providesNotificationActivityRepository(EventBus eventBus, APIService service) {
        return new NotificationActivityRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    APIService provideAPIService () {
        ClothesClient client = new ClothesClient();
        return client.getAPIService();
    }

}
