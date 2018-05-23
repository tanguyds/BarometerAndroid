package com.example.tanguy.barometerandroid.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tanguy.barometerandroid.R;
import com.example.tanguy.barometerandroid.adapters.DashboardNodesAdapter;
import com.example.tanguy.barometerandroid.api.model.Dashboard;
import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.example.tanguy.barometerandroid.util.Util;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardNodeActivity extends Activity {

    private ListView lvDashboardNodes;

    private String authorization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_node);

        Bundle bundle = getIntent().getExtras();
        this.authorization = bundle.getString("authorization");
        final int dashboardId = bundle.getInt("dashboardid");

        final Context context = this;
        Util.USER_CLIENT.getDashboard(dashboardId).enqueue(new Callback<List<DashboardNode>>() {
            @Override
            public void onResponse(Call<List<DashboardNode>> call, Response<List<DashboardNode>> response) {
                List<DashboardNode> dashboardNodes = response.body();

                lvDashboardNodes.setAdapter(new DashboardNodesAdapter(context, dashboardNodes));
            }

            @Override
            public void onFailure(Call<List<DashboardNode>> call, Throwable t) {

            }
        });

        initialiseViews();
        addEventHandlers();
    }

    private void initialiseViews() {
        lvDashboardNodes = findViewById(R.id.lvDashboardNode);
    }

    private void addEventHandlers() {
        lvDashboardNodes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DashboardNode dashboardNode = (DashboardNode) lvDashboardNodes.getAdapter().getItem(position);
                /*
                Intent intent = new Intent(DashboardNodeActivity.this, DashboardNodeActivity.class);
                intent.putExtra("authorization", authorization);
                intent.putExtra("dashboardid", dashboard.getDashboardId());
                startActivity(intent);
                */
            }
        });
    }
}
