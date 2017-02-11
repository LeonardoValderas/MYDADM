package com.valdroide.gonzalezdanielaadm.main.splash.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.splash.ui.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SplashActivityModule.class, LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface SplashActivityComponent {
    void inject(SplashActivity activity);
}
