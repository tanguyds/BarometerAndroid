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
import com.example.tanguy.barometerandroid.api.model.User;
import com.example.tanguy.barometerandroid.dashboard.DashboardActivity;

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
    private Button btnToken;
    private Button btnDashboard;
    private static String token;
    private EditText etEmail;
    private EditText etPassword;
    private UserClient userClient;
    private final String API_BASE_URL = "http://10.0.2.2:50562/";

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
        btnDashboard = (Button) findViewById(R.id.btnDashboard);
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
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

            private void retrofitCall() {
                OkHttpClient.Builder okhttpclientBuilder = new OkHttpClient.Builder();
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                okhttpclientBuilder.addInterceptor(logging)
                        .readTimeout(100, TimeUnit.SECONDS)
                        .connectTimeout(100, TimeUnit.SECONDS)
                        .writeTimeout(100, TimeUnit.SECONDS);

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okhttpclientBuilder.build());

                Retrofit retrofit = builder.build();
                userClient = retrofit.create(UserClient.class);
            }

            private void loginCall() {
                //"admin@nalubarometer.com","Qwerty123!","password"
                //etEmail.getText().toString(), etPassword.getText().toString()
                userClient.login("admin@nalubarometer.com","Qwerty123!","password").enqueue(new Callback<User>() {
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

