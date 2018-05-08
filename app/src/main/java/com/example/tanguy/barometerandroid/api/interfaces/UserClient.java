package com.example.tanguy.barometerandroid.api.interfaces;

import com.example.tanguy.barometerandroid.api.model.Login;
import com.example.tanguy.barometerandroid.api.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Tanguy on 8/05/2018.
 */

public interface UserClient {
    @POST("token")
    Call<User> login(@Body Login login);

    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String authToken);
}
