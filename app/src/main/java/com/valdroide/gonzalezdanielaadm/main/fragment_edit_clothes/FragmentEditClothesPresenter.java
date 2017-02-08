package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes;

import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.events.FragmentEditClothesEvent;

/**
 * Created by LEO on 30/1/2017.
 */
public interface FragmentEditClothesPresenter {
    void onCreate();
    void onDestroy();
    void getListCategory();
    void getListSubCategory(int id_category);
    void getListClothes(int id_category, int id_sub_category);
    void deleteClothes(Clothes clothes, DateTable dateTable);
    void clickSwitch(Clothes clothes, DateTable dateTable);
    void onEventMainThread(FragmentEditClothesEvent event);

}
