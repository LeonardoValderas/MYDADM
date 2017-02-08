package com.valdroide.gonzalezdanielaadm.main.category;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;

/**
 * Created by LEO on 30/1/2017.
 */
public interface ActivityCategoryRepository {
    void getListCategory();
    void editCategory(Category category, DateTable dateTable);
    void saveCategory(Category category, DateTable dateTable);
    void deleteCategory(Category category, DateTable dateTable);
}