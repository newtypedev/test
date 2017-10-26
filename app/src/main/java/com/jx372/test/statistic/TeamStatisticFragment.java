package com.jx372.test.statistic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
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

        lineChart.setDrawGridBackground(false);// this is a must
        lineChart.setBackgroundColor(Color.rgb(0,0,0)); //set whatever color you prefer
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawLabels(false);

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
        for(int i=1;i<13;i++) {
            labels.add(i+"월");
        }
//        labels.add("1");
//        labels.add("2");
//        labels.add("3");
//        labels.add("4");
//        labels.add("5");
//        labels.add("6");
//        labels.add("7");
//        labels.add("8");
//        labels.add("9");
//        labels.add("10");
//        labels.add("11");
//        labels.add("12");
        int[] color2 =   {android.graphics.Color.rgb(16, 54, 179)};
        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.createColors(color2)); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        lineChart.getXAxis().setSpaceBetweenLabels(1);

        lineChart.setData(data);
        lineChart.setBackgroundColor(android.graphics.Color.rgb(255, 255, 255));
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



        PieChart pieChart = (PieChart) view.findViewById(R.id.chart3);

        ArrayList<Entry> entries3 = new ArrayList<>();
        entries3.add(new Entry(41f, 0));
        entries3.add(new Entry(81f, 1));
        entries3.add(new Entry(61f, 2));
        entries3.add(new Entry(21f, 3));
        entries3.add(new Entry(18f,4));
        entries3.add(new Entry(91f, 5));
        entries3.add(new Entry(41f, 6));
        entries3.add(new Entry(81f, 7));
        entries3.add(new Entry(61f, 8));
        entries3.add(new Entry(21f, 9));
        entries3.add(new Entry(18f,10));
        entries3.add(new Entry(91f, 11));

        PieDataSet dataset3 = new PieDataSet(entries3, "# of Calls");


        ArrayList<String> labels3 = new ArrayList<String>();
        for(int i=1;i<13;i++) {
            labels3.add(i+"월");
        }
        int[] color3 =   {android.graphics.Color.rgb(16, 54, 179)};
        PieData data3 = new PieData(labels3, dataset3);
        dataset3.setColors(ColorTemplate.JOYFUL_COLORS); //
        pieChart.setData(data3);
        pieChart.setBackgroundColor(android.graphics.Color.rgb(255, 255, 255));
        pieChart.animateY(3000);




        HorizontalBarChart chart4 = (HorizontalBarChart)view.findViewById(R.id.chart4);

        BarData data2 = new BarData(getXAxisValues(), getDataSet());
        data2.setValueTextColor(Color.rgb(255, 43, 107));
        chart4.setData(data2);
        chart4.setDescription("My Chart");
        chart4.animateXY(2000, 2000);
        chart4.invalidate();

chart4.getXAxis().setTextColor(Color.rgb(255, 43, 107));
        chart4.getAxisLeft().setTextColor(Color.rgb(55, 4, 107));
        chart4.getAxisRight().setTextColor(Color.rgb(25, 43, 107));

        chart4.setDrawGridBackground(false);// this is a must
        chart4.setBackgroundColor(Color.rgb(255,255,255)); //set whatever color you prefer
        chart4.getAxisLeft().setDrawGridLines(false);
        chart4.getAxisRight().setDrawGridLines(false);
        chart4.getXAxis().setDrawGridLines(false);
        chart4.getAxisLeft().setDrawLabels(false);

        return view;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        BarEntry v1e7 = new BarEntry(110.000f, 6); // Jan
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(40.000f, 7); // Feb
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(60.000f, 8); // Mar
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(30.000f, 9); // Apr
        valueSet1.add(v1e10);
        BarEntry v1e11 = new BarEntry(90.000f, 10); // May
        valueSet1.add(v1e11);
        BarEntry v1e12 = new BarEntry(100.000f, 11); // Jun
        valueSet1.add(v1e12);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(81, 172, 242));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
       // dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        for(int i=12;i>0;i--) {
            xAxis.add(i+"월");
        }

//        xAxis.add("JAN");
//        xAxis.add("FEB");
//        xAxis.add("MAR");
//        xAxis.add("APR");
//        xAxis.add("MAY");
//        xAxis.add("JUN");
        return xAxis;
    }



    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(11, 0));
        BARENTRY.add(new BarEntry(14, 1));
        BARENTRY.add(new BarEntry(16, 2));
        BARENTRY.add(new BarEntry(18, 3));
        BARENTRY.add(new BarEntry(15, 4));
        BARENTRY.add(new BarEntry(12, 5));
        BARENTRY.add(new BarEntry(14, 6));
        BARENTRY.add(new BarEntry(16, 7));
        BARENTRY.add(new BarEntry(18, 8));
        BARENTRY.add(new BarEntry(15, 9));
        BARENTRY.add(new BarEntry(18, 10));
        BARENTRY.add(new BarEntry(15, 11));


    }

    public void AddValuesToBarEntryLabels(){



//        BarEntryLabels.add("test01");
//        BarEntryLabels.add("nonono123");
//        BarEntryLabels.add("test03");
//        BarEntryLabels.add("test04");
//        BarEntryLabels.add("test456");
        for(int i=1;i<13;i++) {
            BarEntryLabels.add(i+"월");
        }

    }

}
