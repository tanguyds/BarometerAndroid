package com.example.tanguy.barometerandroid.util;

import android.content.Context;

import com.example.tanguy.barometerandroid.api.interfaces.UserClient;
import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Ilias Dewachter
 * @date 22/05/2018 14:08
 */
public class Util {

    private static final String API_BASE_URL = "http://10.0.2.2:45457";
    public static final UserClient USER_CLIENT = setupUserClient();

    private static UserClient setupUserClient() {
        OkHttpClient.Builder okhttpclientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpclientBuilder.addInterceptor(logging);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpclientBuilder.build());

        Retrofit retrofit = builder.build();
        return retrofit.create(UserClient.class);
    }

    public static long getDaysSinceEpoch(Date date) {
        Calendar epoch = Calendar.getInstance();
        epoch.setTime(new Date(0L));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return TimeUnit.MILLISECONDS.toDays(cal.getTimeInMillis() - epoch.getTimeInMillis());
    }

    public static Date getDateFromDaysSinceEpoch(int days) {
        Calendar epoch = Calendar.getInstance();
        epoch.setTime(new Date(0L));
        epoch.add(Calendar.DATE, days);
        return epoch.getTime();
    }

    public static void createChart(Chart lineChart, DashboardNode dashboardNode) {
        Object[][] dataTable = dashboardNode.getDataTable();

        HashMap<String, List<Entry>> headers = new LinkedHashMap<>(); //KEEP THE ORDER
        for (int i = 1; i < dataTable[0].length; i++) {
            String header = (String) dataTable[0][i];
            headers.put(header, new ArrayList<Entry>());
        }

        final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        for (int i = 1; i < dataTable.length; i++) {
            float x = 0;
            try {
                x = Util.getDaysSinceEpoch(dateFormat.parse("" + dataTable[i][0]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (int j = 1; j < dataTable[i].length; j++) {
                String header = (String) dataTable[0][j];
                List<Entry> data = headers.get(header);
                data.add(new Entry(x, Float.parseFloat("" + dataTable[i][j])));
            }
        }

        List<ILineDataSet> dataSets = new ArrayList<>();
        for (String header : headers.keySet()) {
            LineDataSet lineDataSet = new LineDataSet(headers.get(header), header);
            lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            dataSets.add(lineDataSet);
        }

        lineChart.setData(new LineData(dataSets));

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateFormat.format(Util.getDateFromDaysSinceEpoch((int) value));
            }
        });

        lineChart.invalidate();
    }

}
