package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes;


import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;

public class FragmentAddClothesInteractorImpl implements FragmentAddClothesInteractor {

    private FragmentAddClothesRepository repository;

    public FragmentAddClothesInteractorImpl(FragmentAddClothesRepository repository) {
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
    public void saveClothe(Context context, Clothes clothe, DateTable dateTable) {
        repository.saveClothe(context, clothe, dateTable);
    }

    @Override
    public void updateClothe(Context context, Clothes clothe, DateTable dateTable) {
        repository.updateClothe(context, clothe, dateTable);
    }


}
