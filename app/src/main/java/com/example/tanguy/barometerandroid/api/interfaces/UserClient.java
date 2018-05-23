package com.example.tanguy.barometerandroid.api.interfaces;

import com.example.tanguy.barometerandroid.api.model.Barometer;
import com.example.tanguy.barometerandroid.api.model.Dashboard;
import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.example.tanguy.barometerandroid.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
            @Field(value = "password", encoded = true) String password,
            @Field("grant_type") String grant_type);


    @GET("api/Dashboard/GetBarometers")
    Call<List<Barometer>> getBarometers();


    @GET("api/Dashboard/GetDashboards")
    Call<List<Dashboard>> getDashboards(
            @Header("Authorization") String token,
            @Query("subDomain") String subDomain
    );

    @GET("api/Dashboard/GetDashboard")
    Call<List<DashboardNode>> getDashboard(
            @Query("dashboardId") int dashboardId
    );


    // @GET("secretinfo")
    // Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}
