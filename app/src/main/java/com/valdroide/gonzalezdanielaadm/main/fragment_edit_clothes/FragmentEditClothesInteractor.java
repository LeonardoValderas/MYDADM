package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;

/**
 * Created by LEO on 30/1/2017.
 */
public interface FragmentEditClothesInteractor {
    void getListCategory();
    void getListSubCategory(int id_category);
    void getListClothes(int id_category, int id_sub_category);
    void deleteClothes(Context context, Clothes clothes, DateTable dateTable);
    void clickSwitch(Context context, Clothes clothes, DateTable dateTable);
}
