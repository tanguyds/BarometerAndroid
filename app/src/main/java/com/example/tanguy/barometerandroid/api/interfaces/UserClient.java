package com.example.tanguy.barometerandroid.api.interfaces;

import com.example.tanguy.barometerandroid.api.model.Login;
import com.example.tanguy.barometerandroid.api.model.User;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
   // Call<User> login(@Body Login login);
    //Call<User> login(@FieldMap(encoded = true) Map<String, String> fields);
    Call<User> login(
            @Field(value = "username", encoded = true) String username,
            @Field( value = "password", encoded = true) String password,
            @Field("grant_type") String grant_type);


    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}
