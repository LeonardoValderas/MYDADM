package com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes;

import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.events.FragmentEditClothesEvent;
import com.valdroide.gonzalezdanielaadm.main.fragment_edit_clothes.ui.FragmentEditClothesView;

import org.greenrobot.eventbus.Subscribe;

public class FragmentEditClothesPresenterImpl implements FragmentEditClothesPresenter {


    private FragmentEditClothesView view;
    private EventBus eventBus;
    private FragmentEditClothesInteractor interactor;

    public FragmentEditClothesPresenterImpl(FragmentEditClothesView view, EventBus eventBus, FragmentEditClothesInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void getListCategory() {
        interactor.getListCategory();
    }

    @Override
    public void getListSubCategory(int id_category) {
        interactor.getListSubCategory(id_category);
    }

    @Override
    public void getListClothes(int id_category, int id_sub_category) {
        interactor.getListClothes(id_category, id_sub_category);
    }

    @Override
    public void deleteClothes(Clothes clothes, DateTable dateTable) {
        interactor.deleteClothes(clothes, dateTable);
    }

    @Override
    public void clickSwitch(Clothes clothes, DateTable dateTable) {
        interactor.clickSwitch(clothes, dateTable);
    }

    @Override
    @Subscribe
    public void onEventMainThread(FragmentEditClothesEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case FragmentEditClothesEvent.GETLISTCATEGORY:
                    view.setListCategoty(event.getListCategories());
                    break;
                case FragmentEditClothesEvent.GETLISTSUBCATEGORY:
                    view.setListSubCategoryForCategory(event.getListSubCategories());
                    break;
                case FragmentEditClothesEvent.GETLISTCLOTHES:
                    view.setListClothes(event.getClothesList());
                    break;
                case FragmentEditClothesEvent.ERROR:
                    view.error(event.getError());
                    break;
                case FragmentEditClothesEvent.DELETE:
                    view.deleteSuccess(event.getClothes());
                    break;
                case FragmentEditClothesEvent.ACTIVE:
                    view.clickSwitchSuccess(event.getClothes());
                    break;
            }
        }
    }
}
