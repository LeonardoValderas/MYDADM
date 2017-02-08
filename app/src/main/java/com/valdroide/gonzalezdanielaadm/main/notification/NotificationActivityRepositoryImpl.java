package com.valdroide.gonzalezdanielaadm.main.notification;

import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.ConditionGroup;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.entities.Category;
import com.valdroide.gonzalezdanielaadm.entities.Clothes;
import com.valdroide.gonzalezdanielaadm.entities.DateTable;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.entities.SubCategory;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.FragmentAddClothesRepository;
import com.valdroide.gonzalezdanielaadm.main.fragment_add_clothes.events.FragmentAddClothesEvent;
import com.valdroide.gonzalezdanielaadm.main.notification.events.NotificationActivityEvent;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationActivityRepositoryImpl implements NotificationActivityRepository {
    private EventBus eventBus;
    private List<Category> categories;
    private List<SubCategory> subCategories;
    private APIService service;
    final String[] success = {""};
    final String[] message = {""};
    final int[] id = {0};

    public NotificationActivityRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void sendNotification(String title, String content) {
        try {
            Call<Result> notificationService = service.sendNotification(title, content);
            notificationService.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful()) {
                        message[0] = response.body().getMessage();
                        success[0] = response.body().getSuccess();
                        if (success[0].equals("0")) {
                            post(NotificationActivityEvent.SENT);
                        } else {
                            post(NotificationActivityEvent.ERROR, message[0]);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    post(NotificationActivityEvent.ERROR, t.getMessage());
                }
            });
        } catch (Exception e) {
            post(NotificationActivityEvent.ERROR, e.getMessage());
        }
    }

    public void post(int type) {
        post(type, null);
    }

    public void post(int type, String error) {
        NotificationActivityEvent event = new NotificationActivityEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }


}
