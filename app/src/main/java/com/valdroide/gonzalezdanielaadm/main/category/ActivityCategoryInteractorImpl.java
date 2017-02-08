package com.valdroide.gonzalezdanielaadm.main.category;


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
    public void saveCategory(Category category, DateTable dateTable) {
        repository.saveCategory(category, dateTable);
    }

    @Override
    public void deleteCategory(Category category, DateTable dateTable) {
        repository.deleteCategory(category, dateTable);
    }

    @Override
    public void editCategory(Category category, DateTable dateTable) {
        repository.editCategory(category, dateTable);
    }
}
