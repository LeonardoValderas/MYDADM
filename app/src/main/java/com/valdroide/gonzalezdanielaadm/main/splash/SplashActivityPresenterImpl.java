package com.valdroide.gonzalezdanielaadm.main.splash;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.splash.events.SplashActivityEvent;
import com.valdroide.gonzalezdanielaadm.main.splash.ui.SplashActivityView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class SplashActivityPresenterImpl implements SplashActivityPresenter {


    private SplashActivityView view;
    private EventBus eventBus;
    private SplashActivityInteractor interactor;

    public SplashActivityPresenterImpl(SplashActivityView view, EventBus eventBus, SplashActivityInteractor interactor) {
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
    public void getDateTable() {
        interactor.getDateTable();
    }

    @Override
    public void getAllData(Context context) {
        interactor.getAllData(context);
    }

    @Override
    @Subscribe
    public void onEventMainThread(SplashActivityEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case SplashActivityEvent.GETALLDATA:
                    view.getDateTableEmpty();
                    break;
                case SplashActivityEvent.GOTOTAB:
                    view.gotToTab();
                    break;
                case SplashActivityEvent.ERROR:
                    view.setError(event.getError());
                    break;
            }
        }
    }
}
