package com.valdroide.gonzalezdanielaadm.main.notification;

import android.content.Context;

import com.valdroide.gonzalezdanielaadm.api.APIService;
import com.valdroide.gonzalezdanielaadm.entities.ResponseWS;
import com.valdroide.gonzalezdanielaadm.entities.Result;
import com.valdroide.gonzalezdanielaadm.lib.base.EventBus;
import com.valdroide.gonzalezdanielaadm.main.notification.events.NotificationActivityEvent;
import com.valdroide.gonzalezdanielaadm.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationActivityRepositoryImpl implements NotificationActivityRepository {
    private EventBus eventBus;
    private APIService service;
    List<ResponseWS> responseWses;

    public NotificationActivityRepositoryImpl(EventBus eventBus, APIService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void sendNotification(Context context, String title, String message) {
        if (Utils.isNetworkAvailable(context)) {
            try {
                Call<Result> notificationService = service.sendNotification(title, message);
                notificationService.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()) {
                            responseWses = response.body().getResponseData();
                            if (responseWses.get(0).getSuccess().equals("0")) {
                                post(NotificationActivityEvent.SENT);
                            } else {
                                post(NotificationActivityEvent.ERROR, responseWses.get(0).getMessage());
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
        } else {
            post(NotificationActivityEvent.ERROR, "Verificar su conexión de Internet.");
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
