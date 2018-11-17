package com.niconico.dataserviceapp.WebServices;

import com.niconico.dataserviceapp.Models.APIResult;
import com.niconico.dataserviceapp.Models.Brand;
import com.niconico.dataserviceapp.Models.Category;
import com.niconico.dataserviceapp.Models.Items;
import com.niconico.dataserviceapp.Models.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ISearchService {
    @GET("brands/typeCategory/{typeId}")
    Call<APIResult<ArrayList<Brand>>> getAllBrandWithTypeId(@Path("typeId") long categoryId);

    @GET("categories/brand/{brandId}")
    Call<APIResult<ArrayList<Category>>> getAllCategoryWithBrandId(@Path("brandId") long brandId);

    @POST("items/search")
    Call<APIResult<ArrayList<Items>>> getItemsWithBrandIdAndCategoryId(@Body HashMap<String, Long> user);
}
