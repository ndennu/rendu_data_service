package com.niconico.dataserviceapp.WebServices;

import android.support.annotation.NonNull;

import com.niconico.dataserviceapp.Consts;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.StoreAvailability;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoreProvider {

    private static StoreProvider instance;
    private IStoreService storeService;

    private StoreProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();

        storeService = retrofit.create(IStoreService.class);
    }

    public static StoreProvider getInstance() {
        if (instance == null)
            instance = new StoreProvider();
        return instance;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        return okBuilder.build();
    }

    public void searchStores(SearchStoreParams params, final IAPIListener<APIResult<ArrayList<StoreAvailability>>> listener) {
        storeService.searchStores(params).enqueue(new Callback<APIResult<ArrayList<StoreAvailability>>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<ArrayList<StoreAvailability>>> call, @NonNull Response<APIResult<ArrayList<StoreAvailability>>> response) {
                if (response.code() != 200) {
                    if (response.body() != null) {
                        listener.onError(response.body().getReturnCode() + ": " + response.body().getErrorMessage());
                    } else {
                        listener.onError("Error " + response.code() + ": " + response.message());
                    }
                } else {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResult<ArrayList<StoreAvailability>>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
