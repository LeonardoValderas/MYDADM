package com.valdroide.gonzalezdanielaadm.main.subcategory;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.subcategory.events.ActivitySubCategoryEvent;
import com.valdroide.gonzalezdanielaadm.main.subcategory.ui.ActivitySubCategoryView;

import org.greenrobot.eventbus.Subscribe;


public class ActivitySubCategoryPresenterImpl implements ActivitySubCategoryPresenter {


    private ActivitySubCategoryView view;
    private EventBus eventBus;
    private ActivitySubCategoryInteractor interactor;

    public ActivitySubCategoryPresenterImpl(ActivitySubCategoryView view, EventBus eventBus, ActivitySubCategoryInteractor interactor) {
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
    public void getListSubCategoryForCategory(int id_category) {
        interactor.getListSubCategoryForCategory(id_category);
    }

    @Override
    public void saveSubCategory(SubCategory subcategory, DateTable dateTable) {
        interactor.saveSubCategory(subcategory, dateTable);
    }

    @Override
    public void editSubCategory(SubCategory subcategory, DateTable dateTable) {
        interactor.editSubCategory(subcategory, dateTable);
    }

    @Override
    public void deleteSubCategory(SubCategory subcategory, DateTable dateTable) {
        interactor.deleteSubCategory(subcategory, dateTable);
    }


    @Override
    @Subscribe
    public void onEventMainThread(ActivitySubCategoryEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case ActivitySubCategoryEvent.GETLISTCATEGORY:
                    view.setCategory(event.getListCategories());
                    break;
                case ActivitySubCategoryEvent.GETLISTSUBCATEGORY:
                    view.setSubCategoryForCategory(event.getListSubCategories());
                    break;
                case ActivitySubCategoryEvent.SAVESUBCATEGORY:
                    view.saveSuccess(event.getSubcategory());
                    break;
                case ActivitySubCategoryEvent.UPDATESUBCATEGORY:
                    view.updateSuccess(event.getSubcategory());
                    break;
                case ActivitySubCategoryEvent.DELETESUBCATEGORY:
                    view.deleteSuccess(event.getSubcategory());
                    break;
                case ActivitySubCategoryEvent.ERROR:
                    view.error(event.getError());
                    break;
            }
        }
    }
}
