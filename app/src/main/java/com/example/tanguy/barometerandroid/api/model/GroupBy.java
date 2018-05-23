package com.example.tanguy.barometerandroid.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ilias Dewachter
 * @date 23/05/2018 16:35
 */
public enum GroupBy {

    @SerializedName("Day")
    DAY,
    @SerializedName("Week")
    WEEK,
    @SerializedName("Month")
    MONTH

}
