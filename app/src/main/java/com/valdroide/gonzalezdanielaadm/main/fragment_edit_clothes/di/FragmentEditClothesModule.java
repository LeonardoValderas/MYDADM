package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.di;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.valdroide.gonzalezdanielaadm.R;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesInteractor;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesInteractorImpl;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesPresenter;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesPresenterImpl;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesRepository;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesRepositoryImpl;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.ActivityRecyclerAdapter;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.AdapterSpinnerSubCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.adapters.OnItemClickListener;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui.FragmentEditClothesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 30/1/2017.
 */
@Module
public class FragmentEditClothesModule {
    FragmentEditClothesView view;
    Fragment fragment;
    OnItemClickListener onItemClickListener;
    Context context;

    public FragmentEditClothesModule(FragmentEditClothesView view, Fragment fragment, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.fragment = fragment;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    FragmentEditClothesView provideEditClothesView() {
        return this.view;
    }

    @Provides
    @Singleton
    FragmentEditClothesPresenter provideFragmentEditClothesPresenter(FragmentEditClothesView view, EventBus eventBus, FragmentEditClothesInteractor interactor) {
        return new FragmentEditClothesPresenterImpl(view, eventBus, interactor);
    }

    @Provides
    @Singleton
    FragmentEditClothesInteractor provideFragmentEditClothesInteractor(FragmentEditClothesRepository repository) {
        return new FragmentEditClothesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    FragmentEditClothesRepository provideFragmentEditClothesRepository(EventBus eventBus, APIService service) {
        return new FragmentEditClothesRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    AdapterSpinnerCategory provideAdapterSpinner(Activity context, int resource, List<Category> categoriesArray) {
        return new AdapterSpinnerCategory(context, resource, categoriesArray);
    }

    @Provides
    @Singleton
    List<Category> provideCategoryList() {
        return new ArrayList<Category>();
    }

    @Provides
    @Singleton
    AdapterSpinnerSubCategory provideAdapterSpinnerSubCategory(Activity context, int resource, List<SubCategory> subcategoriesArray) {
        return new AdapterSpinnerSubCategory(context, resource, subcategoriesArray);
    }

    @Provides
    @Singleton
    ActivityRecyclerAdapter providesActivityRecyclerAdapter(List<Clothes> clothesList, OnItemClickListener onItemClickListener, Fragment fragment) {
        return new ActivityRecyclerAdapter(clothesList, onItemClickListener, fragment);
    }

    @Provides
    @Singleton
    List<SubCategory> provideSubCategoryList() {
        return new ArrayList<SubCategory>();
    }

    @Provides
    @Singleton
    List<Clothes> provideClothesList() {
        return new ArrayList<Clothes>();
    }

    @Provides
    @Singleton
    int providesResource() {
        return R.layout.support_simple_spinner_dropdown_item;
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    APIService provideAPIService () {
        ClothesClient client = new ClothesClient();
        return client.getAPIService();
    }
}
