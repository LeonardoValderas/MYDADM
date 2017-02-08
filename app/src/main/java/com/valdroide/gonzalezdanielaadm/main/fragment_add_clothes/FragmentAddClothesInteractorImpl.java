package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes;


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
    public void saveClothe(Clothes clothe, DateTable dateTable) {
        repository.saveClothe(clothe, dateTable);
    }

    @Override
    public void updateClothe(Clothes clothe, DateTable dateTable) {
        repository.updateClothe(clothe, dateTable);
    }


}
