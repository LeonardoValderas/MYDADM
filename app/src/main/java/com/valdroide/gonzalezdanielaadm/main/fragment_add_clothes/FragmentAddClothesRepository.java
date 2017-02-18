package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.events.FragmentAddClothesEvent;

/**
 * Created by LEO on 30/1/2017.
 */
public interface FragmentAddClothesRepository {
    void getListCategory();
    void getListSubCategory(int id_category);
    void updateClothe(Context context, Clothes clothe, DateTable dateTable);
    void saveClothe(Context context, Clothes clothe, DateTable dateTable);
}
