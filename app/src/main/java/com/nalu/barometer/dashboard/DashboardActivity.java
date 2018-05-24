package com.nalu.barometer.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nalu.barometer.R;
import com.nalu.barometer.adapters.DashboardsAdapter;
import com.nalu.barometer.api.model.Barometer;
import com.nalu.barometer.api.model.Dashboard;
import com.nalu.barometer.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends Activity {

    private String authorization;

    private ListView lvDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bundle bundle = getIntent().getExtras();
        this.authorization = bundle.getString("authorization");
        Barometer barometer = (Barometer) bundle.get("barometer");

        setTitle(barometer.getName());

        final Context context = this;
        Util.USER_CLIENT.getDashboards(authorization, barometer.getSubDomain()).enqueue(new Callback<List<Dashboard>>() {
            @Override
            public void onResponse(Call<List<Dashboard>> call, Response<List<Dashboard>> response) {
                List<Dashboard> dashboards = response.body();
                lvDashboard.setAdapter(new DashboardsAdapter(context, dashboards));
            }

            @Override
            public void onFailure(Call<List<Dashboard>> call, Throwable t) {

            }
        });

        initialiseViews();
        addEventHandlers();
    }

    private void initialiseViews() {
        lvDashboard = findViewById(R.id.lvDashboard);
    }

    private void addEventHandlers() {
        lvDashboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Dashboard dashboard = (Dashboard) lvDashboard.getAdapter().getItem(position);
                Intent intent = new Intent(DashboardActivity.this, DashboardNodeActivity.class);
                intent.putExtra("authorization", authorization);
                intent.putExtra("dashboard", dashboard);
                startActivity(intent);
            }
        });
    }
}
