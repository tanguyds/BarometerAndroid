package com.example.tanguy.barometerandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanguy.barometerandroid.api.interfaces.UserClient;
import com.example.tanguy.barometerandroid.api.model.Login;
import com.example.tanguy.barometerandroid.api.model.User;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private Button btnLogin;
    private Button btnToken;
    private static String token;
    private EditText etEmail;
    private EditText etPassword;
    private UserClient userClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitCall();
        initialiseViews();
        addEventHandlers();
    }

    private void initialiseViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnToken = (Button) findViewById(R.id.btnGetToken);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    private void addEventHandlers() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void retrofitCall() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:50562/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        userClient = retrofit.create(UserClient.class);
    }

    private void login() {
        Login login = new Login(etEmail.getText().toString(), etPassword.getText().toString());
        Call<User> call = userClient.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                } else {
                    Toast.makeText(MainActivity.this, "Login is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                // log.d gebruiken in plaats van toast
                Log.d("error","", t  );
            }
        });
    }

        private void getSecret() {
            Call<ResponseBody> call = userClient.getSecret(token);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        try {
                            Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Login is not correct!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
            });
    }
    }


