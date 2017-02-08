package com.valdroide.gonzalezdanielaadm.main.category.ui;

import com.valdroide.gonzalezdanielaadm.entities.Category;

import java.util.List;

/**
 * Created by LEO on 31/1/2017.
 */
public interface ActivityCategoryView {
    void getCategory();
    void setCategory(List<Category> categories);
    void saveSuccess(Category category);
    void updateSuccess(Category category);
    void deleteSuccess(Category category);
    void error(String msg);
}
