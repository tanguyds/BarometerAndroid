package com.example.tanguy.barometerandroid.util;

import com.example.tanguy.barometerandroid.api.model.DashboardNode;
import com.example.tanguy.barometerandroid.api.model.DashboardNodeType;
import com.example.tanguy.barometerandroid.api.model.GroupBy;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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

/**
 * @author Ilias Dewachter
 * @date 23/05/2018 13:49
 */
public class ChartDrawer {

    public void createChart(Chart chart, final DashboardNode dashboardNode) {

        switch (dashboardNode.getDashboardNodeType()) {
            case LINECHARTNODE: {
                chart.setData(getLineData(dashboardNode));
            }
            break;
            case BARCHARTNODE: {
                chart.setData(getBarData(dashboardNode));
            }
            break;
            case AREACHARTNODE: {
                LineData lineData = getLineData(dashboardNode);
                for (ILineDataSet lineDataSet : lineData.getDataSets()) {
                    lineDataSet.setDrawFilled(true);
                }
                chart.setData(lineData);
            }
            break;
            case PIECHARTNODE: {
                chart.setData(getPieData(dashboardNode));
            }
            break;
        }

        chart.getDescription().setEnabled(false);

        if (!dashboardNode.getDashboardNodeType().equals(DashboardNodeType.PIECHARTNODE)) {
            XAxis xAxis = chart.getXAxis();
            xAxis.setGranularity(1);
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return getDateFromDaysSinceEpoch(value, dashboardNode.getGroupBy());
                }
            });
        }
    }

    private BarData getBarData(DashboardNode dashboardNode) {
        Object[][] dataTable = dashboardNode.getDataTable();

        HashMap<String, List<BarEntry>> headers = new LinkedHashMap<>(); //KEEP THE ORDER
        for (int i = 1; i < dataTable[0].length; i++) {
            String header = (String) dataTable[0][i];
            headers.put(header, new ArrayList<BarEntry>());
        }

        for (int i = 1; i < dataTable.length; i++) {
            float date = getDaysSinceEpoch(dashboardNode.getGroupBy(), "" + dataTable[i][0]);
            for (int j = 1; j < dataTable[i].length; j++) {
                String header = (String) dataTable[0][j];
                List<BarEntry> data = headers.get(header);
                data.add(new BarEntry(date, Float.parseFloat("" + dataTable[i][j])));
            }
        }

        ColorDistributor colorDistributor = new ColorDistributor();
        List<IBarDataSet> dataSets = new ArrayList<>();
        for (String header : headers.keySet()) {
            BarDataSet barDataSet = new BarDataSet(headers.get(header), header);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            int color = colorDistributor.getNextColor();
            barDataSet.setColor(color);
            barDataSet.setHighLightColor(color);
            barDataSet.setValueTextColor(color);
            dataSets.add(barDataSet);
        }

        return new BarData(dataSets);
    }

    private LineData getLineData(DashboardNode dashboardNode) {
        Object[][] dataTable = dashboardNode.getDataTable();

        HashMap<String, List<Entry>> headers = new LinkedHashMap<>(); //KEEP THE ORDER
        for (int i = 1; i < dataTable[0].length; i++) {
            String header = (String) dataTable[0][i];
            headers.put(header, new ArrayList<Entry>());
        }

        for (int i = 1; i < dataTable.length; i++) {
            float date = getDaysSinceEpoch(dashboardNode.getGroupBy(), "" + dataTable[i][0]);
            for (int j = 1; j < dataTable[i].length; j++) {
                String header = (String) dataTable[0][j];
                List<Entry> data = headers.get(header);
                data.add(new Entry(date, Float.parseFloat("" + dataTable[i][j])));
            }
        }

        List<ILineDataSet> dataSets = new ArrayList<>();
        ColorDistributor colorDistributor = new ColorDistributor();
        for (String header : headers.keySet()) {
            LineDataSet lineDataSet = new LineDataSet(headers.get(header), header);
            lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            int color = colorDistributor.getNextColor();
            lineDataSet.setColor(color);
            lineDataSet.setCircleColor(color);
            lineDataSet.setFillColor(color);
            lineDataSet.setHighLightColor(color);
            lineDataSet.setValueTextColor(color);
            dataSets.add(lineDataSet);
        }

        return new LineData(dataSets);
    }

    private PieData getPieData(DashboardNode dashboardNode) {
        Object[][] dataTable = dashboardNode.getDataTable();

        String type = "" + dataTable[0][1];
        List<PieEntry> data = new ArrayList<>();
        for (int i = 1; i < dataTable.length; i++) {
            String header = (String) dataTable[i][0];
            data.add(new PieEntry(Float.parseFloat("" + dataTable[i][1]), header));
        }

        PieDataSet pieDataSet = new PieDataSet(data, type);
        pieDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        return new PieData(pieDataSet);
    }


    private final DateFormat dayDateFormat = new SimpleDateFormat("dd/MM/yy");
    private final DateFormat monthDateFormat = new SimpleDateFormat("MM/yyyy");
    private final String[] months = {
            "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december"
    };


    private long getDaysSinceEpoch(GroupBy groupBy, String dateString) {
        switch (groupBy) {
            case DAY: {
                Date date = null;
                try {
                    date = dayDateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar epoch = Calendar.getInstance();
                epoch.setTime(new Date(0L));
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return TimeUnit.MILLISECONDS.toDays(cal.getTimeInMillis() - epoch.getTimeInMillis());
            }
            case WEEK: {
                return Long.parseLong(dateString.replace("Week ", ""));
            }
            case MONTH: {
                Date date = null;

                int month = 0;
                for (int i = 0; i < months.length; i++) {
                    if (dateString.contains(months[i])) month = i + 1;
                }
                String year = dateString.split(" ")[1];

                try {
                    date = monthDateFormat.parse(month + "/" + year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar epoch = Calendar.getInstance();
                epoch.setTime(new Date(0L));
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return TimeUnit.MILLISECONDS.toDays(cal.getTimeInMillis() - epoch.getTimeInMillis());
            }
        }
        return 0L;
    }


    private String getDateFromDaysSinceEpoch(float time, GroupBy groupBy) {
        switch (groupBy) {
            case DAY: {
                Calendar epoch = Calendar.getInstance();
                epoch.setTime(new Date(0L));
                epoch.add(Calendar.DATE, (int) time);
                return dayDateFormat.format(epoch.getTime());
            }
            case WEEK: {
                return "Week " + (int) time;
            }
            case MONTH: {
                Calendar epoch = Calendar.getInstance();
                epoch.setTime(new Date(0L));
                epoch.add(Calendar.DATE, (int) time);
                return monthDateFormat.format(epoch.getTime());
            }
        }
        return null;
    }

}
