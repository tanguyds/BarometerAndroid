package com.example.tanguy.barometerandroid.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tanguy.barometerandroid.R;
import com.example.tanguy.barometerandroid.api.model.Dashboard;

import java.util.List;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 23:35
 */
public class DashboardsAdapter extends ArrayAdapter<Dashboard> {

    public DashboardsAdapter(Context context, List<Dashboard> dashboards) {
        super(context, -1, dashboards);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Dashboard dashboard = getItem(position);
        if (convertView == null){
            LayoutInflater inflater =(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dashboard_item, parent, false);
        }

        if (dashboard == null) return convertView;

        TextView tvDashboardItemName = convertView.findViewById(R.id.tvDashboardItemName);

        tvDashboardItemName.setText("" + dashboard.getName());

        return convertView;
    }
}
