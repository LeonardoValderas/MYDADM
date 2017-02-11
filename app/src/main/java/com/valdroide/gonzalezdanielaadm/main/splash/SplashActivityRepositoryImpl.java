package com.valdroide.gonzalezdanielaadm.main.splash;

import android.content.Context;

import com.google.gson.Gson;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.api.ClothesClient;
import com.valdroide.gonzalezdanielaadm.db.ClothesDatabase;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.ResponseWS;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.notification.events.NotificationActivityEvent;
import com.valdroide.gonzalezdanielaadm.main.splash.events.SplashActivityEvent;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashActivityRepositoryImpl implements SplashActivityRepository {
    private EventBus eventBus;
    private APIService service;
    List<ResponseWS> responseWses;
    List<Category> categories;
    List<SubCategory> subCategories;
    List<Clothes> clothes;
    List<DateTable> dateTables;


    public SplashActivityRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getDateTable() {
        try {
            List<DateTable> dateTables = SQLite.select().from(DateTable.class).queryList();
            if (dateTables != null)
                if (dateTables.isEmpty())
                    post(SplashActivityEvent.GETALLDATA);
                else
                    post(SplashActivityEvent.GOTOTAB);
        } catch (Exception e) {
            post(SplashActivityEvent.ERROR, e.getMessage());
        }
    }

    @Override
    public void getAllData(final Context context) {
        try {
            Call<Result> splashService = service.getAllData();
            splashService.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {

                        responseWses = response.body().getResponseData();
                        if (responseWses.get(0).getSuccess().equals("0")) {
                            Delete.table(Category.class);
                            Delete.table(SubCategory.class);
                            Delete.table(Clothes.class);
                            Delete.table(DateTable.class);

                            categories = response.body().getCategory();
                            subCategories = response.body().getSubcategory();
                            clothes = response.body().getClothes();
                            dateTables = response.body().getDate_table();

                            for (DateTable dateTable : dateTables) {
                                dateTable.save();
                            }
                            for (Clothes clothe : clothes) {
                                clothe.save();
                            }
                            for (SubCategory subCategory : subCategories) {
                                subCategory.save();
                            }
                            for (Category category : categories) {
                                category.save();
                            }
                            post(SplashActivityEvent.GOTOTAB);
                        } else {
                            post(SplashActivityEvent.ERROR, responseWses.get(0).getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    post(SplashActivityEvent.ERROR, t.getMessage());
                }
            });
        } catch (Exception e) {
            post(SplashActivityEvent.ERROR, e.getMessage());
        }
    }

    public void post(int type) {
        post(type, null);
    }

    public void post(int type, String error) {
        SplashActivityEvent event = new SplashActivityEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
