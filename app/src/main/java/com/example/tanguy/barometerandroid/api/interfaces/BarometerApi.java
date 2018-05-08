package com.example.tanguy.barometerandroid.api.interfaces;

import com.example.tanguy.barometerandroid.api.model.TokenRequest;
import com.example.tanguy.barometerandroid.api.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Tanguy on 7/05/2018.
 */

public interface BarometerApi {
    @POST("user")
    Call<TokenResponse> getTokenAccess(@Body TokenRequest tokenRequest);
}
