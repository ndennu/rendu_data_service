package com.niconico.dataserviceapp.WebServices;

import android.support.annotation.NonNull;

import com.niconico.dataserviceapp.Consts;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProvider {

    private static UserProvider instance;
    private IUserService storeService;

    private UserProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();

        storeService = retrofit.create(IUserService.class);
    }

    public static UserProvider getInstance() {
        if (instance == null)
            instance = new UserProvider();
        return instance;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        return okBuilder.build();
    }

    public void getUserById(long id, final IAPIListener<APIResult<User>> listener) {
        storeService.getUserById(id).enqueue(new Callback<APIResult<User>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<User>> call, @NonNull Response<APIResult<User>> response) {
                if (response.code() != 200) {
                    listener.onError("Error code " + response.code() + " - " + response.message());
                    return;
                }
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<APIResult<User>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void updateUser(long id, User user, final  IAPIListener<APIResult<Long>> listener) {
        storeService.updateUser(id, user).enqueue(new Callback<APIResult<Long>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<Long>> call, @NonNull Response<APIResult<Long>> response) {
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
            public void onFailure(@NonNull Call<APIResult<Long>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void updateLocation(long idUser, Double latitude, Double longitude, long currentStoreId, final IAPIListener<APIResult<Long>> listener){
        storeService.updateLocation(
                new UpdateLocationParams(
                        idUser,
                        Double.toString(latitude),
                        Double.toString(longitude),
                        currentStoreId == 0 ? null : currentStoreId)).enqueue(new Callback<APIResult<Long>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<Long>> call, @NonNull Response<APIResult<Long>> response) {
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
            public void onFailure(@NonNull Call<APIResult<Long>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
}
