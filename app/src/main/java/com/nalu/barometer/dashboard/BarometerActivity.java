package com.nalu.barometer.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nalu.barometer.MainActivity;
import com.nalu.barometer.R;
import com.nalu.barometer.adapters.BarometersAdapter;
import com.nalu.barometer.api.model.Barometer;
import com.nalu.barometer.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarometerActivity extends Activity {

    private ListView lvBarometer;
    private Button btnLogOff;
    private TextView tvOverzichtBarometer;
    private String authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barometer);

        initialiseViews();
        addEventHandlers();

        Bundle bundle = getIntent().getExtras();
        this.authorization = bundle.getString("authorization");

        setTitle("Barometers");

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
        tvOverzichtBarometer.setText("Overzicht Barometers");
        tvOverzichtBarometer.setTypeface(null, Typeface.BOLD_ITALIC);
    }

    private void initialiseViews() {
        lvBarometer = findViewById(R.id.lvBarometer);
        btnLogOff = findViewById(R.id.btnLogoff);
        tvOverzichtBarometer = findViewById(R.id.tvOverzichtBarometers);
    }

    private void addEventHandlers() {
        lvBarometer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Barometer barometer = (Barometer) lvBarometer.getAdapter().getItem(position);
                Intent intent = new Intent(BarometerActivity.this, DashboardActivity.class);
                intent.putExtra("authorization", authorization);
                intent.putExtra("barometer", barometer);
                startActivity(intent);
            }
        });

        btnLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(BarometerActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
