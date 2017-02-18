package com.valdroide.gonzalezdanielaadm.main.subcategory;

import android.content.Context;

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
    public void saveSubCategory(Context context, SubCategory subcategory, DateTable dateTable) {
        repository.saveSubCategory(context, subcategory, dateTable);
    }

    @Override
    public void deleteSubCategory(Context context, SubCategory subcategory, DateTable dateTable) {
        repository.deleteSubCategory(context, subcategory, dateTable);
    }

    @Override
    public void editSubCategory(Context context, SubCategory subcategory, DateTable dateTable) {
        repository.editSubCategory(context, subcategory, dateTable);
    }

    @Override
    public void getListSubCategoryForCategory(int id_category) {
        repository.getListSubCategoryForCategory(id_category);
    }
}
