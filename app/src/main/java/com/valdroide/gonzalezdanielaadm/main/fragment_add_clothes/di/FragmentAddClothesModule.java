package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.di;

import android.app.Activity;
import android.support.v4.app.Fragment;


import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesInteractor;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesInteractorImpl;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesPresenter;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesPresenterImpl;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesRepository;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesRepositoryImpl;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.FragmentAddClothesView;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.adapters.AdapterSpinnerSubCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 30/1/2017.
 */
@Module
public class FragmentAddClothesModule {
    FragmentAddClothesView view;
    Fragment fragment;

    public FragmentAddClothesModule(FragmentAddClothesView view, Fragment fragment) {
        this.view = view;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    FragmentAddClothesView provideAddClothesView() {
        return this.view;
    }

    @Provides
    @Singleton
    FragmentAddClothesPresenter provideFragmentAddClothesPresenter(FragmentAddClothesView view, EventBus eventBus, FragmentAddClothesInteractor interactor) {
        return new FragmentAddClothesPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    FragmentAddClothesInteractor provideFragmentAddClothesInteractor(FragmentAddClothesRepository repository) {
        return new FragmentAddClothesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    FragmentAddClothesRepository provideFragmentAddClothesRepository(EventBus eventBus, APIService service) {
        return new FragmentAddClothesRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    AdapterSpinnerCategory provideAdapterSpinner(Activity context, int resource, List<Category> categoriesArray) {
        return new AdapterSpinnerCategory(context, resource, categoriesArray);
    }
    @Provides
    @Singleton
    List<Category> provideCategoryList(){
        return new ArrayList<Category>();
    }
    @Provides
    @Singleton
    AdapterSpinnerSubCategory provideAdapterSpinnerSubCategory(Activity context, int resource, List<SubCategory> subcategoriesArray) {
        return new AdapterSpinnerSubCategory(context, resource, subcategoriesArray);
    }

    @Provides
    @Singleton
    List<SubCategory> provideSubCategoryList(){
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
