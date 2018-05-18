package com.example.tanguy.barometerandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.tanguy.barometerandroid.api.interfaces.UserClient;
import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.example.tanguy.barometerandroid.api.model.User;
import com.example.tanguy.barometerandroid.dashboard.DashboardActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private Button btnLogin;
    private Button btnDashboard;
    private static String token;
    private EditText etEmail;
    private EditText etPassword;
    private UserClient userClient;
    private final String API_BASE_URL = "http://10.0.2.2:45455/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofitCall();
        initialiseViews();
        addEventHandlers();
    }

    private void initialiseViews() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnDashboard = (Button) findViewById(R.id.btnApiCall);
    }

    private void addEventHandlers() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginCall();
            }
        });

        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashboardCall();
            }
        });
    }

            private void retrofitCall() {
                OkHttpClient.Builder okhttpclientBuilder = new OkHttpClient.Builder();
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                okhttpclientBuilder.addInterceptor(logging);

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okhttpclientBuilder.build());

                Retrofit retrofit = builder.build();
                userClient = retrofit.create(UserClient.class);
            }

            private void loginCall() {
               // userClient.login(etEmail.getText().toString(), etPassword.getText().toString(), "password").enqueue(new Callback<User>() {
                     userClient.login("admin@nalubarometer.com", "Qwerty123!","password").enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d("password.tostring", etPassword.getText().toString());
                        if (response.isSuccessful()) {
                           // Toast.makeText(MainActivity.this, response.body().getAccess_token(), Toast.LENGTH_SHORT).show();
                            token = response.body().getAccess_token();
                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
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

            private void dashboardCall() {
                userClient.node((1)).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Gson gson = new Gson();
                        Object[][] o = new Object[0][];
                        try {
                            o = gson.fromJson(response.body().string(), Object[][].class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        intent.putExtra("dataobject", o);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
}

