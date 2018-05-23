package com.example.tanguy.barometerandroid.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:28
 */
public class DashboardNode {

    @SerializedName("DashboardNodeType")
    private String dashboardNodeType;
    @SerializedName("DataTable")
    private Object[][] dataTable;

    public String getDashboardNodeType() {
        return dashboardNodeType;
    }

    public Object[][] getDataTable() {
        return dataTable;
    }
}
