package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.di;

import com.valdroide.gonzalezdanielaadm.GonzalezDanielaAdmAppModule;
import com.valdroide.gonzalezdanielaadm.lib.di.LibsModule;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.FragmentEditClothesPresenter;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui.FragmentEditClothes;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 30/1/2017.
 */
@Singleton
@Component(modules = {FragmentEditClothesModule.class, LibsModule.class, GonzalezDanielaAdmAppModule.class})
public interface FragmentEditClothesComponent {
      void inject(FragmentEditClothes fragment);

    FragmentEditClothesPresenter getPresenter();

    //AdapterSpinnerCategory getAdapterCategory();
  /*
    AdapterSpinnerSubCategory getAdapterSubCategory();
*/
}
