package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.slashb410.exgroup.R;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class GroupGraphFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

   //     ButterKnife.bind(getActivity());

        View view = inflater.inflate(R.layout.fragment_group_graph, container, false);

        LineChart lineChart = (LineChart) view.findViewById(R.id.group_graph);


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

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.setDescription("체중감량표");



        return view;

    }
}
