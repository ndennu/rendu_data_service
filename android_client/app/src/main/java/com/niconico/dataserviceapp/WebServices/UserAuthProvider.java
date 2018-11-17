package com.niconico.dataserviceapp.WebServices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.niconico.dataserviceapp.Consts;
import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.User;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAuthProvider {

    private static UserAuthProvider instance;
    private IUserAuthService userService;

    private UserAuthProvider() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Consts.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();

        userService = retrofit.create(IUserAuthService.class);
    }

    public static UserAuthProvider getInstance() {
        if (instance == null)
            instance = new UserAuthProvider();
        return instance;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        return okBuilder.build();
    }

    public void login(String email, String password, final IAPIListener<Long> listener) {
        HashMap<String, String> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);

        userService.authLogin(body).enqueue(new Callback<APIResult<User>>() {
            @Override
            public void onResponse(@NonNull Call<APIResult<User>> call, @NonNull Response<APIResult<User>> response) {
                Log.d("TEST", "AUTH OK");
                if (response.code() != 200) {
                    if (response.body() != null) {
                        listener.onError(response.body().getReturnCode() + ": " + response.body().getErrorMessage());
                    } else {
                        listener.onError("Error " + response.code() + ": " + response.message());
                    }
                } else {
                    listener.onSuccess(response.body().getData().getId());
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResult<User>> call, @NonNull Throwable t) {
                Log.d("TEST", "AUTH NOK: " + t.getMessage() + ", " + t.getCause());
                listener.onError("AUTH NOK: " + t.getMessage() + ", " + t.getCause());
            }
        });
    }
}
