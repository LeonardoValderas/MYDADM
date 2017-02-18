package com.valdroide.gonzalezdanielaadm.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClothesClient {
    private Retrofit retrofit;
    //private final static String BASE_URL = "http://10.0.2.2:8080/md/";
    private final static String BASE_URL = "http://myd.esy.es/myd/";

    public ClothesClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public APIService getAPIService() {
        return retrofit.create(APIService.class);
    }
}
