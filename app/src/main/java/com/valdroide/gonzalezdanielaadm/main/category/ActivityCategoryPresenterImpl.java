package com.valdroide.gonzalezdanielaadm.main.category;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.category.events.ActivityCategoryEvent;
import com.valdroide.gonzalezdanielaadm.main.category.ui.ActivityCategoryView;


import org.greenrobot.eventbus.Subscribe;


public class ActivityCategoryPresenterImpl implements ActivityCategoryPresenter {


    private ActivityCategoryView view;
    private EventBus eventBus;
    private ActivityCategoryInteractor interactor;

    public ActivityCategoryPresenterImpl(ActivityCategoryView view, EventBus eventBus, ActivityCategoryInteractor interactor) {
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
    public void saveCategory(Context context, Category category, DateTable dateTable) {
        interactor.saveCategory(context, category, dateTable);
    }

    @Override
    public void editCategory(Context context, Category category, DateTable dateTable) {
        interactor.editCategory(context, category, dateTable);
    }

    @Override
    public void deleteCategory(Context context, Category category, DateTable dateTable) {
        interactor.deleteCategory(context, category, dateTable);
    }


    @Override
    @Subscribe
    public void onEventMainThread(ActivityCategoryEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case ActivityCategoryEvent.GETLISTCATEGORY:
                    view.setCategory(event.getListCategories());
                    break;
                case ActivityCategoryEvent.SAVECATEGORY:
                    view.saveSuccess(event.getCategory());
                    break;
                case ActivityCategoryEvent.UPDATECATEGORY:
                    view.updateSuccess(event.getCategory());
                    break;
                case ActivityCategoryEvent.DELETECATEGORY:
                    view.deleteSuccess(event.getCategory());
                    break;
                case ActivityCategoryEvent.ERROR:
                    view.error(event.getError());
                    break;

            }
        }
    }
}
