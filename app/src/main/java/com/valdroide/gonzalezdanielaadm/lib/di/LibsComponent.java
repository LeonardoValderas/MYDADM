package com.valdroide.gonzalezdanielaadm.lib.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 29/1/2017.
 */
@Singleton
@Component(modules = {LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface LibsComponent {
}
