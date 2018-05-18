package com.example.tanguy.barometerandroid.graphs;


import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/**
 * Created by Tanguy on 16/05/2018.
 */

public class LineGraph {
    private LineGraphSeries<DataPoint> series;
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

    public LineGraphSeries<DataPoint> maakSeries(Object [][] dataInput){
        //vulXDatapoints();
       //    vulYDatapoints();
        // dataInput[1].length-1
       // DataPoint[] dataPoints = new DataPoint[16];
        /*for (int i = 1; i < 16; i++) {
            dataPoints[i] = new DataPoint(i, Double.parseDouble(dataInput[i][1].toString()));
        }*/
/*                new DataPoint(xDatapoints[0], yDatapoints[0]),
                new DataPoint(xDatapoints[1], yDatapoints[1]),
                new DataPoint(xDatapoints[2], yDatapoints[2]),
                new DataPoint(xDatapoints[3], yDatapoints[3]),
                new DataPoint(xDatapoints[4], yDatapoints[4]),
        series = new LineGraphSeries<>(dataPoints);*/
            series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, Double.parseDouble(dataInput[1][1].toString())),
                    new DataPoint(1, Double.parseDouble(dataInput[2][1].toString())),
                    new DataPoint(2, Double.parseDouble(dataInput[3][1].toString())),
                    new DataPoint(3, Double.parseDouble(dataInput[4][1].toString())),
                    new DataPoint(4, Double.parseDouble(dataInput[5][1].toString())),
            });
            return series;

    }

    public LineGraphSeries<DataPoint> maakSerie2(){
        series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, -1),
                new DataPoint(1, 4),
                new DataPoint(2, 2),
                new DataPoint(3, 1),
                new DataPoint(4, 5)
        });
        return series;
    }
}
