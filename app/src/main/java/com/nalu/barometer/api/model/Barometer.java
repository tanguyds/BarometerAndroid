package com.nalu.barometer.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:27
 */
public class Barometer implements Serializable {

    @SerializedName("SubDomain")
    private String subDomain;

    @SerializedName("Name")
    private String name;

    @SerializedName("Image")
    private String image;


    public String getSubDomain() {
        return subDomain;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}