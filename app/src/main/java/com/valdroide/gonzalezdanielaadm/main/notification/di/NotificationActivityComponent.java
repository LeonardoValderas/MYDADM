package com.valdroide.gonzalezdanielaadm.main.notification.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.notification.ui.NotificationActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NotificationActivityModule.class, LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface NotificationActivityComponent {
    void inject(NotificationActivity activity);
}
