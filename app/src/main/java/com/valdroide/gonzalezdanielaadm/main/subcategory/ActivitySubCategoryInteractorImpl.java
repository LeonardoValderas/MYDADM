package com.valdroide.gonzalezdanielaadm.main.subcategory;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;


public class ActivitySubCategoryInteractorImpl implements ActivitySubCategoryInteractor {

    private ActivitySubCategoryRepository repository;

    public ActivitySubCategoryInteractorImpl(ActivitySubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getListCategory() {
        repository.getListCategory();
    }

    @Override
    public void saveSubCategory(SubCategory subcategory, DateTable dateTable) {
        repository.saveSubCategory(subcategory, dateTable);
    }

    @Override
    public void deleteSubCategory(SubCategory subcategory, DateTable dateTable) {
        repository.deleteSubCategory(subcategory, dateTable);
    }

    @Override
    public void editSubCategory(SubCategory subcategory, DateTable dateTable) {
        repository.editSubCategory(subcategory, dateTable);
    }

    @Override
    public void getListSubCategoryForCategory(int id_category) {
        repository.getListSubCategoryForCategory(id_category);
    }
}
