package com.jx372.test.statistic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jx372.test.R;

import java.util.ArrayList;

/**
 * Created by pys on 2017. 10. 10..
 */

public class TeamStatisticFragment extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position;


    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;

    public static TeamStatisticFragment newInstance(int position) {
        TeamStatisticFragment f = new TeamStatisticFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.fragment_chart,container,false);


        LineChart lineChart = (LineChart) view.findViewById(R.id.chart);


        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(41f, 0));
        entries.add(new Entry(81f, 1));
        entries.add(new Entry(61f, 2));
        entries.add(new Entry(21f, 3));
        entries.add(new Entry(181f,4));
        entries.add(new Entry(91f, 5));
        entries.add(new Entry(4f, 6));
        entries.add(new Entry(8f, 7));
        entries.add(new Entry(61f, 8));
        entries.add(new Entry(2f, 9));
        entries.add(new Entry(18f,10));
        entries.add(new Entry(9f, 11));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(3000);



        //View view = inflater.inflate(R.layout.fragment_team_statistic,container,false);

//        setHasOptionsMenu(true);
//        getActivity().getActionBar();
//
//        chart = (BarChart) view.findViewById(R.id.chart1);
//
//        BARENTRY = new ArrayList<>();
//
//        BarEntryLabels = new ArrayList<String>();
//
//        AddValuesToBARENTRY();
//
//        AddValuesToBarEntryLabels();
//
//
//        Bardataset = new BarDataSet(BARENTRY, "Projects");
//        // Bardataset.setValues(BarEntryLabels);
//        //BARDATA = new BarData(Bardataset);
//
//        BARDATA = new BarData(BarEntryLabels, Bardataset);
//        //Bardataset.setColors(ColorTemplate.JOYFUL_COLORS);
//        int[] color =   {android.graphics.Color.rgb(30, 160, 246), Color.rgb(26, 68, 95), android.graphics.Color.rgb(17, 25, 105),
//                android.graphics.Color.rgb(118, 174, 175), android.graphics.Color.rgb(42, 109, 130)};
//        Bardataset.setColors(ColorTemplate.createColors(color));
//
//
//        chart.setData(BARDATA);
//        chart.animateXY(3000, 3000);



        return view;
    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(2, 0));
        BARENTRY.add(new BarEntry(4, 1));
        BARENTRY.add(new BarEntry(6, 2));
        BARENTRY.add(new BarEntry(8, 3));
        BARENTRY.add(new BarEntry(5, 4));


    }

    public void AddValuesToBarEntryLabels(){



        BarEntryLabels.add("test01");
        BarEntryLabels.add("nonono123");
        BarEntryLabels.add("test03");
        BarEntryLabels.add("test04");
        BarEntryLabels.add("test456");



    }

}
