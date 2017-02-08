package com.valdroide.gonzalezdanielaadm.main.category.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.category.ui.ActivityCategory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ActivityCategoryModule.class, LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface ActivityCategoryComponent {
    void inject(ActivityCategory activity);
}
