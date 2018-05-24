package com.nalu.barometer.util;

import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * @author Ilias Dewachter
 * @date 23/05/2018 13:55
 */
public class ColorDistributor {

    private int index = 0;
    public final static int[] colors = ColorTemplate.MATERIAL_COLORS;

    public int getNextColor() {
        if (index == colors.length) index = 0;
        return colors[index++];
    }
}
