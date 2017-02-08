package com.valdroide.gonzalezdanielaadm.main.subcategory.di;

import android.app.Activity;
import android.content.Context;

import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryInteractor;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryInteractorImpl;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryPresenter;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryPresenterImpl;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryRepository;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ActivitySubCategoryRepositoryImpl;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.ActivitySubCategoryView;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.ActivitySubCategoryAdapter;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.adapters.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivitySubCategoryModule {
    ActivitySubCategoryView view;
    OnItemClickListener onItemClickListener;
    Context context;

    public ActivitySubCategoryModule(Context context, ActivitySubCategoryView view, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    ActivitySubCategoryView providesActivitySubCategoryView() {
        return this.view;
    }

    @Provides
    @Singleton
    ActivitySubCategoryPresenter providesActivitySubCategoryPresenter(EventBus eventBus, ActivitySubCategoryView view, ActivitySubCategoryInteractor listInteractor) {
        return new ActivitySubCategoryPresenterImpl(view, eventBus, listInteractor);
    }

    @Provides
    @Singleton
    ActivitySubCategoryInteractor providesActivitySubCategoryInteractor(ActivitySubCategoryRepository repository) {
        return new ActivitySubCategoryInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ActivitySubCategoryRepository providesActivitySubCategoryRepository(EventBus eventBus, APIService service) {
        return new ActivitySubCategoryRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    ActivitySubCategoryAdapter providesActivitySubCategoryAdapter(List<SubCategory> subcategories, OnItemClickListener onItemClickListener, Activity activity) {
        return new ActivitySubCategoryAdapter(subcategories, onItemClickListener, activity);
    }
    @Provides
    @Singleton
    AdapterSpinnerCategory providesAdapterSpinnerCategory(Context context, int resource, List<Category> categoriesArray) {
        return new AdapterSpinnerCategory(context, resource, categoriesArray);
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
    List<SubCategory> providesSubCategoryList() {
        return new ArrayList<SubCategory>();
    }

    @Provides
    @Singleton
    int providesResource() {
        return R.layout.support_simple_spinner_dropdown_item;
    }

    @Provides
    @Singleton
    APIService provideAPIService () {
        ClothesClient client = new ClothesClient();
        return client.getAPIService();
    }
}
