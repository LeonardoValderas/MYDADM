package com.valdroide.gonzalezdanielaadm.lib.di;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 29/1/2017.
 */
@Module
public class LibsModule {
    private Activity activity;
    private Fragment fragment;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }
    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }
    @Provides
    @Singleton
    Activity providesActivity() {
        return this.activity;
    }
    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }
}
