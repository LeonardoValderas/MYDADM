package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

import java.util.List;
/**
 * Created by LEO on 29/1/2017.
 */
public interface FragmentAddClothesView {
    void setListCategoty(List<Category> categories);
    void setListSubCategoryForCategory(List<SubCategory> subcategories);
    void getPhoto();
    void error(String mgs);
    void saveSuccess();
    void updateSuccess();

}
