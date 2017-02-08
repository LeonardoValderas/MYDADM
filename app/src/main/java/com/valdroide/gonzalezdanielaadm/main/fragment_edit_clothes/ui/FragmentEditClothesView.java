package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui;

import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;

import java.util.List;

/**
 * Created by LEO on 29/1/2017.
 */
public interface FragmentEditClothesView {
    void setListCategoty(List<Category> categories);
    void setListSubCategoryForCategory(List<SubCategory> subcategories);
    void error(String mgs);
    void setListClothes(List<Clothes> clothesList);
    void deleteSuccess(Clothes clothes);
    void clickSwitchSuccess(Clothes clothes);
}
