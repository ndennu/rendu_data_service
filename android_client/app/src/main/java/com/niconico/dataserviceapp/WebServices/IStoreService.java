package com.niconico.dataserviceapp.WebServices;

import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.StoreAvailability;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IStoreService {
    @POST("stores/searchV2/")
    Call<APIResult<ArrayList<StoreAvailability>>> searchStores(@Body SearchStoreParams params);
}
