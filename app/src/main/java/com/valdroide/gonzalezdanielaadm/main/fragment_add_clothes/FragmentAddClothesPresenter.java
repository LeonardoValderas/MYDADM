package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.events.FragmentAddClothesEvent;

import java.util.List;
import java.util.Locale;

/**
 * Created by LEO on 30/1/2017.
 */
public interface FragmentAddClothesPresenter {
    void onCreate();
    void onDestroy();
    void getListCategory();
    void getListSubCategory(int id_category);
    void saveClothe(Clothes clothe, DateTable dateTable);
    void updateClothe(Clothes clothe, DateTable dateTable);
    void onEventMainThread(FragmentAddClothesEvent event);

}
