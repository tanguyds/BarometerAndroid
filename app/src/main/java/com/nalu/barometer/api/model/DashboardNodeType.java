package com.nalu.barometer.api.model;

import com.example.tanguy.barometerandroid.R;
import com.google.gson.annotations.SerializedName;

/**
 * @author Ilias Dewachter
 * @date 23/05/2018 13:26
 */
public enum DashboardNodeType {

    @SerializedName("LineChartNode")
    LINECHARTNODE(R.layout.linechart_item),
    @SerializedName("BarChartNode")
    BARCHARTNODE(R.layout.barchart_item),
    @SerializedName("AreaChartNode")
    AREACHARTNODE(R.layout.linechart_item),
    @SerializedName("PieChartNode")
    PIECHARTNODE(R.layout.piechart_item);

    private int layout;
    DashboardNodeType(int layout) {
        this.layout = layout;
    }

    public int getLayout() {
        return layout;
    }
}
