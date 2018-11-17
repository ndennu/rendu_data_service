package com.niconico.dataserviceapp.WebServices;

import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserService {
    @GET("users/{id}") Call<APIResult<User>> getUserById(@Path("id") long id);
    @PUT("users/{id}") Call<APIResult<Long>> updateUser(@Path("id") long id, @Body User user);
    @POST("users/locationV2") Call<APIResult<Long>> updateLocation(@Body UpdateLocationParams params);
}
