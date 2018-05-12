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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
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
                loginCall();
            }
        });
    }

            private void retrofitCall() {
                OkHttpClient.Builder okhttpclientBuilder = new OkHttpClient.Builder();
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                okhttpclientBuilder.addInterceptor(logging);

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:50562/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okhttpclientBuilder.build());

                Retrofit retrofit = builder.build();
                userClient = retrofit.create(UserClient.class);
            }

            private void loginCall() {
                //"admin@nalubarometer.com","Qwerty123!","password"
                //etEmail.getText().toString(), etPassword.getText().toString()
                /*Map<String, String> map = new HashMap<>();
                map.put("username", "admin@nalubaromter.com");
                map.put("password", "Qwerty123!");
                map.put("grant_type", "password");*/
                userClient.login("password!", "Admin@nalubarometer.com", "Qwerty123!").enqueue(new Callback<User>() {
                    //Login login = new Login("admin@nalubarometer.com", "Qwerty123!","password");
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("password.tostring", etPassword.getText().toString());
                        if (response.isSuccessful()) {
                            Toast.makeText(MainActivity.this, response.body().getAccess_token(), Toast.LENGTH_SHORT).show();
                            token = response.body().getAccess_token();
                        } else {
                            Toast.makeText(MainActivity.this, "Login is not correct!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        // log.d gebruiken in plaats van toast
                        Log.d("error", "", t);
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

