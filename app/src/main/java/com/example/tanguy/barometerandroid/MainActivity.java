package com.example.tanguy.barometerandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private Button btnLoginScherm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseViews();
        addEventHandlers();
    }

    private void initialiseViews() {
        btnLoginScherm = (Button) findViewById(R.id.btnLoginActivity);
    }

    private void addEventHandlers() {
        btnLoginScherm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);            }
        });
    }
}
