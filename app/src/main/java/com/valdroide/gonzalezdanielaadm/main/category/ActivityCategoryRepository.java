package com.valdroide.gonzalezdanielaadm.main.category;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;

/**
 * Created by LEO on 30/1/2017.
 */
public interface ActivityCategoryRepository {
    void getListCategory();
    void editCategory(Context context, Category category, DateTable dateTable);
    void saveCategory(Context context, Category category, DateTable dateTable);
    void deleteCategory(Context context, Category category, DateTable dateTable);
}
