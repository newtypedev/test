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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pys on 2017. 10. 16..
 */

public class TeamStaFragment  extends Fragment {
    private static final String ARG_POSITION = "position";
    private int position;
    private ArrayList<Entry> entries3;
    BarChart chart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;
    BarDataSet Bardataset;
    BarData BARDATA;
    int[] sale={3450000,4752000,2467000,5455600,3850000,4950000,2490000,5440000,4410000,2940000,1750000,1250000,2221000};

    public static TeamStatisticFragment newInstance(int position) {
        TeamStatisticFragment f = new TeamStatisticFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        //  int[] sale = Statistic.get().getSales();

        int[] sale={3450000,4752000,2467000,5455600,3850000,4950000,2490000,5440000,4410000,2940000,1750000,1250000,2221000};

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        for(int i=1;i<13;i++){
            BarEntry v1e1 = new BarEntry(sale[13-i], i-1);
            valueSet1.add(v1e1);
        }


        int total=0;
        for(int i=1;i<13;i++){
            total = total+sale[i];
        }
        int percent=0;
        for(int i=1;i<13;i++){
            percent = (int)((double)sale[i]/(double)total*100.0);
            entries3.add(new Entry(percent, i-1));
        }

//        entries3.add(new Entry(41f, 0));
//        entries3.add(new Entry(81f, 1));
//        entries3.add(new Entry(61f, 2));
//        entries3.add(new Entry(21f, 3));
//        entries3.add(new Entry(18f,4));
//        entries3.add(new Entry(91f, 5));
//        entries3.add(new Entry(41f, 6));
//        entries3.add(new Entry(81f, 7));
//        entries3.add(new Entry(61f, 8));
//        entries3.add(new Entry(21f, 9));
//        entries3.add(new Entry(18f,10));
//        entries3.add(new Entry(91f, 11));



        Map<String,Integer> map = new HashMap<String,Integer>();

        for(int i=1;i<13;i++){
            map.put(i+"",sale[i]);
        }




//
//        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
//        valueSet1.add(v1e1);
//        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
//        valueSet1.add(v1e2);
//        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
//        valueSet1.add(v1e3);
//        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
//        valueSet1.add(v1e4);
//        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
//        valueSet1.add(v1e5);
//        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
//        valueSet1.add(v1e6);
//
//        BarEntry v1e7 = new BarEntry(110.000f, 6); // Jan
//        valueSet1.add(v1e7);
//        BarEntry v1e8 = new BarEntry(40.000f, 7); // Feb
//        valueSet1.add(v1e8);
//        BarEntry v1e9 = new BarEntry(60.000f, 8); // Mar
//        valueSet1.add(v1e9);
//        BarEntry v1e10 = new BarEntry(30.000f, 9); // Apr
//        valueSet1.add(v1e10);
//        BarEntry v1e11 = new BarEntry(90.000f, 10); // May
//        valueSet1.add(v1e11);
//        BarEntry v1e12 = new BarEntry(100.000f, 11); // Jun
//        valueSet1.add(v1e12);
//
//        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
//        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
//        valueSet2.add(v2e1);
//        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
//        valueSet2.add(v2e2);
//        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
//        valueSet2.add(v2e3);
//        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
//        valueSet2.add(v2e4);
//        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
//        valueSet2.add(v2e5);
//        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
//        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Sale");
        barDataSet1.setColor(Color.rgb(81, 172, 242));
        //  BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        //barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        entries3 = new ArrayList<>();
        HorizontalBarChart chart4 = (HorizontalBarChart)view.findViewById(R.id.salechart);

        BarData data2 = new BarData(getXAxisValues(), getDataSet());
        data2.setValueTextColor(Color.rgb(255, 43, 107));
        data2.setDrawValues(false );
        chart4.setData(data2);
        chart4.setDescription("Sale");
        chart4.animateXY(2000, 2000);
        chart4.invalidate();

        chart4.getXAxis().setTextColor(Color.rgb(255, 43, 107));
        chart4.getAxisLeft().setTextColor(Color.rgb(55, 4, 107));
        chart4.getAxisRight().setTextColor(Color.rgb(25, 43, 107));

        chart4.setDrawGridBackground(false);// this is a must
        chart4.setBackgroundColor(Color.rgb(255,255,255)); //set whatever color you prefer
        chart4.getAxisLeft().setDrawGridLines(false); //세로줄무늬
        chart4.getAxisRight().setDrawGridLines(false); //세로줄무늬
        chart4.getXAxis().setDrawGridLines(false);  //가로줄무늬
        chart4.getAxisLeft().setDrawLabels(false);  // top value




        int[] color =   {

                android.graphics.Color.rgb(51, 255, 255), Color.rgb(255, 153, 153), android.graphics.Color.rgb(255, 255, 153),
                android.graphics.Color.rgb(255, 204, 000), android.graphics.Color.rgb(153, 153, 153),android.graphics.Color.rgb(255, 153, 000),
                android.graphics.Color.rgb(000, 204, 000), Color.rgb(000, 000, 204), android.graphics.Color.rgb(255, 000, 000),
                android.graphics.Color.rgb(253, 153, 204), android.graphics.Color.rgb(102, 051, 000),android.graphics.Color.rgb(000, 000, 000)


        };



        PieChart pieChart = (PieChart) view.findViewById(R.id.chart3);




        PieDataSet dataset3 = new PieDataSet(entries3, "Month");


        ArrayList<String> labels3 = new ArrayList<String>();
        for(int i=1;i<13;i++) {
            labels3.add(i+"");
        }
        int[] color3 =   {android.graphics.Color.rgb(16, 54, 179)};
        PieData data3 = new PieData(labels3, dataset3);
        dataset3.setColors(ColorTemplate.createColors(color)); //
        pieChart.setData(data3);
        pieChart.setCenterTextColor(android.graphics.Color.rgb(255, 255, 255));
        pieChart.setBackgroundColor(android.graphics.Color.rgb(255, 255, 255));
        pieChart.animateY(3000);





        LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
        lineChart.setDrawGridBackground(false);// this is a must
        lineChart.setBackgroundColor(Color.rgb(0,0,0)); //set whatever color you prefer
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawLabels(false);




        lineChart.setDrawGridBackground(false);// this is a must
        lineChart.setBackgroundColor(Color.rgb(0,0,0)); //set whatever color you prefer
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawLabels(false);
        int[] sale={34500,47520,24670,54556,38500,49500,24900,54400,44100,29400,17500,12500,22210};

        ArrayList<Entry> entries = new ArrayList<>();
        for(int i=1;i<13;i++){
            entries.add(new Entry(sale[i], i-1));
        }

        LineDataSet dataset = new LineDataSet(entries, "#Month");


        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        for(int i=1;i<13;i++){
            BarEntry v1e1 = new BarEntry(sale[13-i], i-1);
            valueSet1.add(v1e1);
        }


        int total=0;
        ArrayList<Entry> entries3 = new ArrayList<>();
        int percent=0;
        for(int i=1;i<13;i++){
            percent = (int)((double)sale[i]/(double)total*100.0);
            entries3.add(new Entry(percent, i-1));
        }



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



        return view;

    }
}