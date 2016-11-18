package com.example.kirthanaaraghuraman.chartstrial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarChart = (BarChart) findViewById(R.id.chart);
        createChart();
    }

    private static void createChart() {
        final String[] labels = {"Drug Store", "Street Vendors", "All Others", "Superette", "Full Service"
        , "Other"};

        float[] augustData = {(float) 314.0, (float) 54.004, (float) 90.0, (float) 3762.0, (float) 79.0, (float) 7.0};
        float[] septemberData = {(float)274.0, (float)17.0, (float)27.0, (float)3573.0, (float)82.0, (float)3.0};

        List<BarEntry> augList = new ArrayList<>();
        List<BarEntry> septList = new ArrayList<>();

        for (int i = 0; i < augustData.length; i++){
            augList.add(new BarEntry(i, augustData[i]));
            septList.add(new BarEntry(i, septemberData[i]));
        }

        BarDataSet set1 = new BarDataSet(augList, "August");
        BarDataSet set2 = new BarDataSet(septList, "September");

        set1.setColors(ColorTemplate.COLORFUL_COLORS);
        set2.setColors(ColorTemplate.LIBERTY_COLORS);

        float groupSpace = 0.06f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.45f; // x2 dataset

        BarData data = new BarData(set1, set2);
        data.setBarWidth(barWidth); // set the width of each bar

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return labels[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        mBarChart.setData(data);
        mBarChart.groupBars(0f, groupSpace, barSpace); // perform the "explicit" grouping
        mBarChart.invalidate(); // refresh
    }

}
