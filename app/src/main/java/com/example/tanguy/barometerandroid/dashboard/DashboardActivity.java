package com.example.tanguy.barometerandroid.dashboard;

import android.app.Activity;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;
import com.example.tanguy.barometerandroid.ContactFragment;
import com.example.tanguy.barometerandroid.AlertFragment;
import com.example.tanguy.barometerandroid.PrivacyFragment;
import com.example.tanguy.barometerandroid.R;
import com.example.tanguy.barometerandroid.graphs.BarChart;
import com.example.tanguy.barometerandroid.graphs.LineGraph;
import com.example.tanguy.barometerandroid.graphs.PointGraph;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.Date;

public class DashboardActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private GraphView graphView;
    private LineGraph lineGraph;
    private BarChart barChart;
    private PointGraph pointGraph;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */

    private void initialiseViews() {
        graphView = (GraphView) findViewById(R.id.graph);
    }

    private void addEventhandlers() {

    }

    private CharSequence mTitle;

    private void createLineGraph() {
        Object[][] value;
        Bundle bundle = getIntent().getExtras();
       // if (bundle != null) {
            value = (Object[][]) bundle.get("dataobject");
        //}
        lineGraph = new LineGraph();
        LineGraphSeries<DataPoint> series = lineGraph.maakSeries(value);
        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        //graphView.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space
        /*Date d = new Date(2018,1,5);
        Date d2 = new Date(2018,10,5);
        graphView.getViewport().setMinX(d.getTime());
        graphView.getViewport().setMaxX(d2.getTime());
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getGridLabelRenderer().setHumanRounding(false);*/

        graphView.setTitle("Testgrafiek");
        graphView.addSeries(series);
    }

    private void createBarChart() {
        barChart = new BarChart();
        BarGraphSeries<DataPoint> series = barChart.maakSerie1();
        graphView.addSeries(series);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });
        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
//series.setValuesOnTopSize(50);
    }

    private void createPointGraph() {
        pointGraph = new PointGraph();
        PointsGraphSeries<DataPoint> series = pointGraph.maakSerie1();
        PointsGraphSeries<DataPoint> serie2 = pointGraph.maakSerie2();
        graphView.addSeries(series);
        graphView.addSeries(serie2);
        series.setShape(PointsGraphSeries.Shape.POINT);
        serie2.setShape(PointsGraphSeries.Shape.RECTANGLE);
        serie2.setColor(Color.RED);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Back press disabled!", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        initialiseViews();
        addEventhandlers();
        createLineGraph();
         //createBarChart();
        //createPointGraph();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;
        switch (position + 1) {
            case 1:
                fragment = new ContactFragment();
                break;
            case 2:
                fragment = new AlertFragment();
                break;
            case 3:
                fragment = new PrivacyFragment();
                break;
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment/*PlaceholderFragment.newInstance(position + 1)*/)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.contact);
                break;
            case 2:
                mTitle = getString(R.string.alert);
                break;
            case 3:
                mTitle = getString(R.string.privacy);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((DashboardActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
