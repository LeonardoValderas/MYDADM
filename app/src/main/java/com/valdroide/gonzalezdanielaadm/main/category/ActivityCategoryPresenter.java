package com.valdroide.gonzalezdanielaadm.main.category;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.main.category.events.ActivityCategoryEvent;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.events.FragmentAddClothesEvent;

/**
 * Created by LEO on 30/1/2017.
 */
public interface ActivityCategoryPresenter {
    void onCreate();
    void onDestroy();
    void getListCategory();
    void saveCategory(Context context, Category category, DateTable dateTable);
    void editCategory(Context context, Category category, DateTable dateTable);
    void deleteCategory(Context context, Category category, DateTable dateTable);
    void onEventMainThread(ActivityCategoryEvent event);
}
