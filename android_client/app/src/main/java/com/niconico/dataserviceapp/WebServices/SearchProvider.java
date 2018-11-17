package com.niconico.dataserviceapp.WebServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.niconico.dataserviceapp.Consts;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.Brand;
import com.niconico.dataserviceapp.Models.Category;
import com.niconico.dataserviceapp.Models.Items;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchProvider {
    private static SearchProvider instance;
    private ISearchService searchService;

    private SearchProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();

        searchService = retrofit.create(ISearchService.class);
    }

    public static SearchProvider getInstance() {
        if (instance == null)
            instance = new SearchProvider();
        return instance;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        return okBuilder.build();
    }

    public void getAllBrandWithTypeId(long categoryId, final IAPIListener<APIResult<ArrayList<Brand>>> listener) {
        searchService.getAllBrandWithTypeId(categoryId).enqueue(new Callback<APIResult<ArrayList<Brand>>>() {

            @Override
            public void onResponse(@NonNull Call<APIResult<ArrayList<Brand>>> call, @NonNull Response<APIResult<ArrayList<Brand>>> response) {
                Log.d("TEST", "GET BRAND OK");
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
            public void onFailure(@NonNull Call<APIResult<ArrayList<Brand>>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void getAllCategoryWithBrandId(long brandId, final IAPIListener<APIResult<ArrayList<Category>>> listener) {
        searchService.getAllCategoryWithBrandId(brandId).enqueue(new Callback<APIResult<ArrayList<Category>>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<ArrayList<Category>>> call, @NonNull Response<APIResult<ArrayList<Category>>> response) {
                Log.d("TEST", "GET CATEGORY OK");
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
            public void onFailure(@NonNull Call<APIResult<ArrayList<Category>>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void getItemsWithBrandIdAndCategoryId(long brandId, long categoryId, final IAPIListener<APIResult<ArrayList<Items>>> listener) {

        HashMap<String, Long> body = new HashMap<>();
        body.put("brandId", brandId);
        body.put("categoryId", categoryId);

        searchService.getItemsWithBrandIdAndCategoryId(body).enqueue(new Callback<APIResult<ArrayList<Items>>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<ArrayList<Items>>> call, @NonNull Response<APIResult<ArrayList<Items>>> response) {
                Log.d("TEST", "GET ITEMS OK");
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
            public void onFailure(@NonNull Call<APIResult<ArrayList<Items>>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
