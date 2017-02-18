package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;

public class FragmentEditClothesInteractorImpl implements FragmentEditClothesInteractor {

    private FragmentEditClothesRepository repository;

    public FragmentEditClothesInteractorImpl(FragmentEditClothesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getListCategory() {
        repository.getListCategory();
    }

    @Override
    public void getListSubCategory(int id_category) {
        repository.getListSubCategory(id_category);
    }

    @Override
    public void getListClothes(int id_category, int id_sub_category) {
        repository.getListClothes(id_category, id_sub_category);
    }

    @Override
    public void deleteClothes(Context context, Clothes clothes, DateTable dateTable) {
        repository.deleteClothes(context, clothes, dateTable);
    }

    @Override
    public void clickSwitch(Context context, Clothes clothes, DateTable dateTable) {
        repository.clickSwitch(context, clothes, dateTable);
    }
}
