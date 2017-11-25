package com.quandoo.base.repo.cloud;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseCloudRepo {

    protected <T> T create(Class<T> clazz) {
        T service = retrofit().create(clazz);
        return service;
    }

    protected <T> T execute(Call<T> call) throws IOException {
        Response<T> response = call.execute();
        return response.body();
    }

    private Retrofit retrofit() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        return new Retrofit.Builder()
                .baseUrl(CloudConfig.BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
