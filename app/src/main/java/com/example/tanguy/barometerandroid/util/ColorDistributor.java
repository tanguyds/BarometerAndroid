package com.example.tanguy.barometerandroid.util;

import android.graphics.Color;

import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * @author Ilias Dewachter
 * @date 23/05/2018 13:55
 */
public class ColorDistributor {

    private int index = 0;
    private final int[] colors = ColorTemplate.COLORFUL_COLORS;

    public int getNextColor() {
        if (index == colors.length) index = 0;
        return colors[index++];
    }


}
