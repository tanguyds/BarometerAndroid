package com.example.tanguy.barometerandroid.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tanguy.barometerandroid.R;
import com.example.tanguy.barometerandroid.adapters.BarometersAdapter;
import com.example.tanguy.barometerandroid.api.model.Barometer;
import com.example.tanguy.barometerandroid.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarometerActivity extends Activity {

    private ListView lvBarometer;

    private String authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barometer);

        initialiseViews();
        addEventHandlers();

        Bundle bundle = getIntent().getExtras();
        this.authorization = bundle.getString("authorization");

        final Context context = this;
        Util.USER_CLIENT.getBarometers().enqueue(new Callback<List<Barometer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Barometer>> call, @NonNull Response<List<Barometer>> response) {
                List<Barometer> barometerList = response.body();
                lvBarometer.setAdapter(new BarometersAdapter(context, barometerList));
            }
            @Override
            public void onFailure(Call<List<Barometer>> call, Throwable t) {}
        });
    }

    private void initialiseViews() {
        lvBarometer = findViewById(R.id.lvBarometer);
    }

    private void addEventHandlers() {
        lvBarometer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Barometer barometer = (Barometer) lvBarometer.getAdapter().getItem(position);
                Intent intent = new Intent(BarometerActivity.this, DashboardActivity.class);
                intent.putExtra("authorization", authorization);
                intent.putExtra("subdomain", barometer.getSubDomain());
                startActivity(intent);
            }
        });
    }
}
