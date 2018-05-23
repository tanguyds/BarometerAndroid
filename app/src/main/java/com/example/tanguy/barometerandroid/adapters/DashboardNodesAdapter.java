package com.example.tanguy.barometerandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.tanguy.barometerandroid.R;
import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.example.tanguy.barometerandroid.util.Util;
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

            int layout = 0;
            switch (dashboardNode.getDashboardNodeType().toLowerCase()) {
                case "linechartnode": {
                    layout = R.layout.linechart_item;
                }
                break;
            }
            if (layout == 0) return convertView;

            convertView = inflater.inflate(layout, parent, false);
        }

        Chart chart = (Chart) convertView;
        Util.createChart(chart, dashboardNode);
        chart.setTouchEnabled(false);

        return convertView;
    }
}
