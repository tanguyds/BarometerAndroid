package com.nalu.barometer.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nalu.barometer.R;
import com.nalu.barometer.adapters.DashboardNodesAdapter;
import com.nalu.barometer.api.model.Dashboard;
import com.nalu.barometer.api.model.DashboardNode;
import com.nalu.barometer.util.Util;

import java.util.ArrayList;
import java.util.List;

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
        Dashboard dashboard = (Dashboard) bundle.get("dashboard");

        setTitle(dashboard.getName());

        final Context context = this;
        Util.USER_CLIENT.getDashboard(dashboard.getDashboardId()).enqueue(new Callback<List<DashboardNode>>() {
            @Override
            public void onResponse(Call<List<DashboardNode>> call, Response<List<DashboardNode>> response) {
                List<DashboardNode> dashboardNodes = response.body();

                List<DashboardNode> newList = new ArrayList<>();
                for (DashboardNode dashboardNode : dashboardNodes) {
                    if (dashboardNode.getDashboardNodeType() != null)
                        newList.add(dashboardNode);
                }

                lvDashboardNodes.setAdapter(new DashboardNodesAdapter(context, newList));
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
                Intent intent = new Intent(DashboardNodeActivity.this, DashboardNodeDetailsActivity.class);
                intent.putExtra("authorization", authorization);
                intent.putExtra("dashboardnode", dashboardNode);
                startActivity(intent);
            }
        });
    }
}
