package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesPresenter;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.FragmentAddClothes;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.adapters.AdapterSpinnerCategory;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.adapters.AdapterSpinnerSubCategory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 30/1/2017.
 */
@Singleton
@Component(modules = {FragmentAddClothesModule.class, LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface FragmentAddClothesComponent {
      void inject(FragmentAddClothes fragment);

    FragmentAddClothesPresenter getPresenter();

    //AdapterSpinnerCategory getAdapterCategory();
  /*
    AdapterSpinnerSubCategory getAdapterSubCategory();
*/
}
