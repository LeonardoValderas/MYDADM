package com.valdroide.gonzalezdanielaadm.main.category;


import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;

public class ActivityCategoryInteractorImpl implements ActivityCategoryInteractor {

    private ActivityCategoryRepository repository;

    public ActivityCategoryInteractorImpl(ActivityCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getListCategory() {
        repository.getListCategory();
    }

    @Override
    public void saveCategory(Context context, Category category, DateTable dateTable) {
        repository.saveCategory(context, category, dateTable);
    }

    @Override
    public void deleteCategory(Context context, Category category, DateTable dateTable) {
        repository.deleteCategory(context, category, dateTable);
    }

    @Override
    public void editCategory(Context context, Category category, DateTable dateTable) {
        repository.editCategory(context, category, dateTable);
    }
}
