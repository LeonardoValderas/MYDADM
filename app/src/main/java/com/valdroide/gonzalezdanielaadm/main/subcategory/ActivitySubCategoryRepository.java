package com.valdroide.gonzalezdanielaadm.main.subcategory;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

/**
 * Created by LEO on 30/1/2017.
 */
public interface ActivitySubCategoryRepository {
    void getListCategory();
    void editSubCategory(Context context, SubCategory subcategory, DateTable dateTable);
    void saveSubCategory(Context context, SubCategory subcategory, DateTable dateTable);
    void deleteSubCategory(Context context, SubCategory subcategory, DateTable dateTable);
    void getListSubCategoryForCategory(int id_category);
}
