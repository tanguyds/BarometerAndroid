package com.example.tanguy.barometerandroid.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:27
 */
public class Barometer {

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