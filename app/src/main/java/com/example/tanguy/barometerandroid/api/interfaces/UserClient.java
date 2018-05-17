package com.example.tanguy.barometerandroid.api.interfaces;

import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.example.tanguy.barometerandroid.api.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Tanguy on 8/05/2018.
 */

//
public interface UserClient {
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("token")
    Call<User> login(
            @Field(value = "username", encoded = true) String username,
            @Field( value = "password", encoded = true) String password,
            @Field("grant_type") String grant_type);


    @GET("api/dashboard/{id}")
    Call<ResponseBody> node (@Query("id") int id);

    // @GET("secretinfo")
    // Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}
