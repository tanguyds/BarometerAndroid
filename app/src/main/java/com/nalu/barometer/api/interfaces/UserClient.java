package com.nalu.barometer.api.interfaces;

import com.nalu.barometer.api.model.Barometer;
import com.nalu.barometer.api.model.Dashboard;
import com.nalu.barometer.api.model.DashboardNode;
import com.nalu.barometer.api.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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
            @Header("Authorization") String authorization,
            @Query("subDomain") String subDomain
    );

    @GET("api/Dashboard/GetDashboard")
    Call<List<DashboardNode>> getDashboard(
            @Query("dashboardId") int dashboardId
    );

    @POST("api/Dashboard/RegisterDevice")
    Call<Void> sendFirebaseToken(
            @Header("Authorization") String authorization,
            @Body String token
    );
}
