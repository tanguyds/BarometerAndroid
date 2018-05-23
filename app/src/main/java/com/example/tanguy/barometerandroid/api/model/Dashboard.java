package com.example.tanguy.barometerandroid.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:28
 */
public class Dashboard {

    @SerializedName("DashboardId")
    private int dashboardId;
    @SerializedName("DashboardNodeList")
    private List<Integer> dashboardNodeList;
    @SerializedName("Name")
    private String name;

    public int getDashboardId() {
        return dashboardId;
    }

    public List<Integer> getDashboardNodeList() {
        return dashboardNodeList;
    }

    public String getName() {
        return name;
    }
}
