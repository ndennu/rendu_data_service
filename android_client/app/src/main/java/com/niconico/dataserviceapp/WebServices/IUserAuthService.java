package com.niconico.dataserviceapp.WebServices;

import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IUserAuthService {
    @POST("auth/login")
    Call<APIResult<User>> authLogin(@Body HashMap<String, String> user);
}
