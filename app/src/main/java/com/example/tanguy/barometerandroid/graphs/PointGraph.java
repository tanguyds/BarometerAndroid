package com.example.tanguy.barometerandroid.graphs;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * Created by Tanguy on 16/05/2018.
 */

public class PointGraph {
    private PointsGraphSeries<DataPoint> series;
    private double[] xDatapoints = new double[10];
    private double[] yDatapoints = new double[10];

    private double[] vulXDatapoints() {
        for (int i=0;i<5;i++) {
            xDatapoints[i] = i;
        }
        return xDatapoints;
    }
    private double[] vulYDatapoints() {
        yDatapoints[0] = -2;
        yDatapoints[1] = 5;
        yDatapoints[2] = 3;
        yDatapoints[3] = 2;
        yDatapoints[4] = 6;
        return yDatapoints;
    }

    // params ->  int[] xValues, int[] yValues

    public PointsGraphSeries<DataPoint> maakSerie1(){
        vulXDatapoints();
        vulYDatapoints();
        series = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(xDatapoints[0], yDatapoints[0]),
                new DataPoint(xDatapoints[1], yDatapoints[1]),
                new DataPoint(xDatapoints[2], yDatapoints[2]),
                new DataPoint(xDatapoints[3], yDatapoints[3]),
                new DataPoint(xDatapoints[4], yDatapoints[4]),
        });
        return series;
    }

    public PointsGraphSeries<DataPoint> maakSerie2(){
        series = new PointsGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -1),
                new DataPoint(1, 200),
                new DataPoint(2, 2),
                new DataPoint(3, 1),
                new DataPoint(4, 5)
        });
        return series;
    }
}
