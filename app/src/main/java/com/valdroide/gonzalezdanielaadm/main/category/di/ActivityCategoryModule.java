package com.valdroide.gonzalezdanielaadm.main.category.di;

import android.app.Activity;

import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryInteractor;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryInteractorImpl;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryPresenter;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryPresenterImpl;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryRepository;
import com.valdroide.gonzalezdanielaadm.main.category.ActivityCategoryRepositoryImpl;
import com.valdroide.gonzalezdanielaadm.main.category.ui.ActivityCategoryView;
import com.valdroide.gonzalezdanielaadm.main.category.ui.adapters.ActivityCategoryAdapter;
import com.valdroide.gonzalezdanielaadm.main.category.ui.adapters.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityCategoryModule {
    ActivityCategoryView view;
    OnItemClickListener onItemClickListener;

    public ActivityCategoryModule(ActivityCategoryView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    ActivityCategoryView providesActivityCategoryView() {
        return this.view;
    }

    @Provides
    @Singleton
    ActivityCategoryPresenter providesActivityCategoryPresenter(EventBus eventBus, ActivityCategoryView view, ActivityCategoryInteractor listInteractor) {
        return new ActivityCategoryPresenterImpl(view, eventBus, listInteractor);
    }

    @Provides
    @Singleton
    ActivityCategoryInteractor providesActivityCategoryInteractor(ActivityCategoryRepository repository) {
        return new ActivityCategoryInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ActivityCategoryRepository providesActivityCategoryRepository(EventBus eventBus, APIService service) {
        return new ActivityCategoryRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    ActivityCategoryAdapter providesActivityCategoryAdapter(List<Category> categories, OnItemClickListener onItemClickListener, Activity activity) {
        return new ActivityCategoryAdapter(categories, onItemClickListener, activity);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Category> providesCategoryList() {
        return new ArrayList<Category>();
    }

    @Provides
    @Singleton
    APIService provideAPIService () {
        ClothesClient client = new ClothesClient();
        return client.getAPIService();
    }

}
