package com.nalu.barometer.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:28
 */
public class DashboardNode implements Serializable {

    @SerializedName("DashboardNodeType")
    private DashboardNodeType dashboardNodeType;
    @SerializedName("GroupBy")
    private GroupBy groupBy;
    @SerializedName("DataTable")
    private Object[][] dataTable;
    @SerializedName("Title")
    private String title;

    public DashboardNodeType getDashboardNodeType() {
        return dashboardNodeType;
    }

    public GroupBy getGroupBy() {
        return groupBy;
    }

    public Object[][] getDataTable() {
        return dataTable;
    }

    public String getTitle() {
        return title;
    }
}
