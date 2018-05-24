package com.nalu.barometer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nalu.barometer.api.model.User;
import com.nalu.barometer.dashboard.BarometerActivity;
import com.nalu.barometer.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseViews();
        addEventHandlers();
    }

    private void initialiseViews() {
        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
    }

    private void addEventHandlers() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCall();
            }
        });
    }


    private void loginCall() {
        // TODO: 22/05/2018 FIX PLS
        // userClient.login(etEmail.getText().toString(), etPassword.getText().toString(), "password").enqueue(new Callback<User>() {
        Util.USER_CLIENT.login("testuser@nalubarometer.com", "Qwerty123!", "password").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("password.tostring", etPassword.getText().toString());
                if (response.isSuccessful()) {
                    User user = response.body();

                    Intent intent = new Intent(MainActivity.this, BarometerActivity.class);
                    intent.putExtra("authorization", String.format("%s %s", user.getToken_type(), user.getAccess_token()));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Login is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                Log.d("error", "", t);
            }
        });
    }
}

