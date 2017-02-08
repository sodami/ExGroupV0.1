package com.google.slashb410.exgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ExActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

        LineChart lineChart = (LineChart) findViewById(R.id.chart_ex);

        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(3f, 0));
        entries.add(new Entry(4f, 1));
        entries.add(new Entry(5f, 2));
        entries.add(new Entry(6f, 3));

        LineDataSet dataSet = new LineDataSet(entries, "슬비꺼");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1주차");
        labels.add("2주차");
        labels.add("3주차");
        labels.add("4주차");

        LineData data = new LineData(labels, dataSet);
        lineChart.setData(data);
    }
}
