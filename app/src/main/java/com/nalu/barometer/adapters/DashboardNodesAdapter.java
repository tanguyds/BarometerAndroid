package com.nalu.barometer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.nalu.barometer.api.model.DashboardNode;
import com.nalu.barometer.util.ChartDrawer;
import com.github.mikephil.charting.charts.Chart;

import java.util.List;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 16:00
 */
public class DashboardNodesAdapter extends ArrayAdapter<DashboardNode> {

    public DashboardNodesAdapter(Context context, List<DashboardNode> dashboardNodes) {
        super(context, -1, dashboardNodes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DashboardNode dashboardNode = getItem(position);
        if (dashboardNode == null) return convertView;
        if (convertView == null){
            LayoutInflater inflater =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate( dashboardNode.getDashboardNodeType().getLayout(), parent, false);
        }

        Chart chart = (Chart) convertView;
        ChartDrawer chartDrawer = new ChartDrawer();
        chartDrawer.createChart(chart, dashboardNode);
        chart.setTouchEnabled(false);
        chart.setExtraOffsets(2, 26, 2, 2);
        chart.invalidate();

        return convertView;
    }
}
