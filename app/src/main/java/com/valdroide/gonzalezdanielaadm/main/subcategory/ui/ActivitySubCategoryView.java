package com.valdroide.gonzalezdanielaadm.main.subcategory.ui;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

import java.util.List;

/**
 * Created by LEO on 31/1/2017.
 */
public interface ActivitySubCategoryView {
    void getCategory();
    void setCategory(List<Category> categories);
    void setSubCategoryForCategory(List<SubCategory> subcategories);
    void saveSuccess(SubCategory subcategory);
    void updateSuccess(SubCategory subcategory);
    void deleteSuccess(SubCategory subcategory);
    void error(String msg);
}
