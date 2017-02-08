package com.valdroide.gonzalezdanielaadm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;


import com.raizlabs.android.dbflow.config.FlowManager;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.category.di.ActivityCategoryComponent;
import com.valdroide.gonzalezdanielaadm.main.category.di.ActivityCategoryModule;
import com.valdroide.gonzalezdanielaadm.main.category.di.DaggerActivityCategoryComponent;
import com.valdroide.gonzalezdanielaadm.main.category.ui.ActivityCategoryView;
import com.valdroide.gonzalezdanielaadm.main.category.ui.adapters.OnItemClickListener;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.di.DaggerFragmentAddClothesComponent;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.di.FragmentAddClothesComponent;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.di.FragmentAddClothesModule;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.FragmentAddClothesView;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.di.DaggerFragmentEditClothesComponent;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.di.FragmentEditClothesComponent;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.di.FragmentEditClothesModule;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui.FragmentEditClothesView;
import com.valdroide.gonzalezdanielaadm.main.notification.di.DaggerNotificationActivityComponent;
import com.valdroide.gonzalezdanielaadm.main.notification.di.NotificationActivityComponent;
import com.valdroide.gonzalezdanielaadm.main.notification.di.NotificationActivityModule;
import com.valdroide.gonzalezdanielaadm.main.notification.ui.NotificationActivityView;
import com.valdroide.gonzalezdanielaadm.main.subcategory.di.ActivitySubCategoryComponent;
import com.valdroide.gonzalezdanielaadm.main.subcategory.di.ActivitySubCategoryModule;
import com.valdroide.gonzalezdanielaadm.main.subcategory.di.DaggerActivitySubCategoryComponent;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.ActivitySubCategoryView;
/**
 * Created by LEO on 29/1/2017.
 */
public class GonzalezDanielaAdmApp extends Application {
    private LibsModule libsModule;
    private GonzalezDanielaAdmAppModule gonzalezDanielaAdmAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initModules();
        initDB();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }


    private void initModules() {
        libsModule = new LibsModule();
        gonzalezDanielaAdmAppModule = new GonzalezDanielaAdmAppModule(this);
    }

    public FragmentAddClothesComponent getFragmentAddClothesComponent(FragmentAddClothesView view, Fragment fragment) {
        return DaggerFragmentAddClothesComponent
                .builder()
                .gonzalezDanielaAdmAppModule(gonzalezDanielaAdmAppModule)
                .libsModule(new LibsModule(fragment))
                .fragmentAddClothesModule(new FragmentAddClothesModule(view, fragment))
                .build();
    }
    public FragmentEditClothesComponent getFragmentEditClothesComponent(FragmentEditClothesView view, Fragment fragment, com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.OnItemClickListener onItemClickListener) {
        return DaggerFragmentEditClothesComponent
                .builder()
                .gonzalezDanielaAdmAppModule(gonzalezDanielaAdmAppModule)
                .libsModule(new LibsModule(fragment))
                .fragmentEditClothesModule(new FragmentEditClothesModule(view, fragment, onItemClickListener))
                .build();
    }

    public ActivityCategoryComponent getActivityCategoryComponent(ActivityCategoryView view, Activity activity, OnItemClickListener onItemClickListener) {
        return DaggerActivityCategoryComponent
                .builder()
                .gonzalezDanielaAdmAppModule(gonzalezDanielaAdmAppModule)
                .libsModule(new LibsModule(activity))
                .activityCategoryModule(new ActivityCategoryModule(view, onItemClickListener))
                .build();
    }

    public ActivitySubCategoryComponent getActivitySubCategoryComponent(ActivitySubCategoryView view, Activity activity, com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerActivitySubCategoryComponent
                .builder()
                .gonzalezDanielaAdmAppModule(gonzalezDanielaAdmAppModule)
                .libsModule(new LibsModule(activity))
                .activitySubCategoryModule(new ActivitySubCategoryModule(context, view, onItemClickListener))
                .build();
    }

    public NotificationActivityComponent getNotificationActivityComponent(NotificationActivityView view, Activity activity) {
        return DaggerNotificationActivityComponent
                .builder()
                .gonzalezDanielaAdmAppModule(gonzalezDanielaAdmAppModule)
                .libsModule(new LibsModule(activity))
                .notificationActivityModule(new NotificationActivityModule(view))
                .build();
    }
}
