package com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.events.FragmentAddClothesEvent;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.ui.FragmentAddClothesView;

import org.greenrobot.eventbus.Subscribe;


public class FragmentAddClothesPresenterImpl implements FragmentAddClothesPresenter {


    private FragmentAddClothesView view;
    private EventBus eventBus;
    private FragmentAddClothesInteractor interactor;

    public FragmentAddClothesPresenterImpl(FragmentAddClothesView view, EventBus eventBus, FragmentAddClothesInteractor interactor) {
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
    public void saveClothe(Context context, Clothes clothe, DateTable dateTable) {
        interactor.saveClothe(context, clothe, dateTable);
    }

    @Override
    public void updateClothe(Context context, Clothes clothe, DateTable dateTable) {
        interactor.updateClothe(context, clothe, dateTable);
    }


    @Override
    @Subscribe
    public void onEventMainThread(FragmentAddClothesEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case FragmentAddClothesEvent.GETLISTCATEGORY:
                    view.setListCategoty(event.getListCategories());
                    break;
                case FragmentAddClothesEvent.GETLISTSUBCATEGORY:
                    view.setListSubCategoryForCategory(event.getListSubCategories());
                    break;
                case FragmentAddClothesEvent.SAVECLOTHE:
                    view.saveSuccess();
                    break;
                case FragmentAddClothesEvent.ERROR:
                    view.error(event.getError());
                    break;
                case FragmentAddClothesEvent.UPDATECLOTHE:
                    view.updateSuccess();
                    break;

            }
        }
    }
}
