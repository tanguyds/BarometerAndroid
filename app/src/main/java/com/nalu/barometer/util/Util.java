package com.nalu.barometer.util;

import com.nalu.barometer.api.interfaces.UserClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:08
 */
public class Util {

    private static final String API_BASE_URL = "http://10.0.2.2:45455";
    public static final UserClient USER_CLIENT = setupUserClient();

    private static UserClient setupUserClient() {
        OkHttpClient.Builder okhttpclientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpclientBuilder.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpclientBuilder.build());

        Retrofit retrofit = builder.build();
        return retrofit.create(UserClient.class);
    }

}
