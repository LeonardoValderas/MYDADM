package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes;

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
    public void deleteClothes(Clothes clothes, DateTable dateTable) {
        repository.deleteClothes(clothes, dateTable);
    }

    @Override
    public void clickSwitch(Clothes clothes, DateTable dateTable) {
        repository.clickSwitch(clothes, dateTable);
    }
}
