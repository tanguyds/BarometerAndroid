package com.nalu.barometer.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.nalu.barometer.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Ilias Dewachter
 * @date 24/05/2018 13:17
 */
public class InstanceIdService extends FirebaseInstanceIdService {
    public InstanceIdService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();

        System.out.println(token);

        /*
        //sends this token to the server
        Util.USER_CLIENT.sendToken(token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println("response");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        */
    }
}