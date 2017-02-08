package com.valdroide.gonzalezdanielaadm.main.subcategory.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.ActivitySubCategory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ActivitySubCategoryModule.class, LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface ActivitySubCategoryComponent {
    void inject(ActivitySubCategory activity);
}
