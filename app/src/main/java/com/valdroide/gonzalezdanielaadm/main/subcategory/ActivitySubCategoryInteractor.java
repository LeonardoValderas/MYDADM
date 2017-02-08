package com.valdroide.gonzalezdanielaadm.main.subcategory;


import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

/**
 * Created by LEO on 30/1/2017.
 */
public interface ActivitySubCategoryInteractor {
    void getListCategory();
    void saveSubCategory(SubCategory subcategory, DateTable dateTable);
    void deleteSubCategory(SubCategory subcategory, DateTable dateTable);
    void editSubCategory(SubCategory subcategory, DateTable dateTable);
    void getListSubCategoryForCategory(int id_category);
}
