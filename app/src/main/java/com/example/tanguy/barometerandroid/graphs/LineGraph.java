package com.example.tanguy.barometerandroid.graphs;

import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Created by Tanguy on 16/05/2018.
 */

public class LineGraph {
    private LineGraphSeries<DataPoint> series;

    public LineGraphSeries<DataPoint> maakSeries(Object[][] dataInput) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        DataPoint[] dataPoints = new DataPoint[dataInput.length - 1];
        for (int i = 1; i < dataInput.length; i++) {
            try {
                dataPoints[i - 1] = new DataPoint(format.parse(dataInput[i][0].toString()), Double.parseDouble(dataInput[i][1].toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        series = new LineGraphSeries<>(dataPoints);
        return series;
    }
}
