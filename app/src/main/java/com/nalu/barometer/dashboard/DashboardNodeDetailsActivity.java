package com.nalu.barometer.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.Chart;
import com.nalu.barometer.R;
import com.nalu.barometer.api.model.DashboardNode;
import com.nalu.barometer.util.ChartDrawer;

public class DashboardNodeDetailsActivity extends Activity {

    private String authorization;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_node_details);

        Bundle bundle = getIntent().getExtras();
        this.authorization = bundle.getString("authorization");
        DashboardNode dashboardNode = (DashboardNode) bundle.get("dashboardnode");

        setTitle(dashboardNode.getTitle());

        initialiseViews();
        addEventHandlers();

        Chart chart = (Chart) View.inflate(this, dashboardNode.getDashboardNodeType().getLayout(), null);
        linearLayout.addView(chart);
        ChartDrawer chartDrawer = new ChartDrawer();
        chartDrawer.createChart(chart, dashboardNode);
        chart.getLayoutParams().height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
        chart.setExtraOffsets(2, 18, 2, 2);
        chart.invalidate();
    }

    private void initialiseViews() {
        linearLayout = findViewById(R.id.linearLayout);
    }

    private void addEventHandlers() {

    }
}
