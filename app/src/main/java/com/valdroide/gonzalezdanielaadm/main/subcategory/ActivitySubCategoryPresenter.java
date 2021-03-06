package com.valdroide.gonzalezdanielaadm.main.subcategory;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.main.category.events.ActivityCategoryEvent;
import com.valdroide.gonzalezdanielaadm.main.subcategory.events.ActivitySubCategoryEvent;

/**
 * Created by LEO on 30/1/2017.
 */
public interface ActivitySubCategoryPresenter {
    void onCreate();
    void onDestroy();
    void getListCategory();
    void getListSubCategoryForCategory(int id_category);
    void saveSubCategory(Context context, SubCategory subcategory, DateTable dateTable);
    void editSubCategory(Context context, SubCategory subcategory, DateTable dateTable);
    void deleteSubCategory(Context context, SubCategory subcategory, DateTable dateTable);
    void onEventMainThread(ActivitySubCategoryEvent event);
}
