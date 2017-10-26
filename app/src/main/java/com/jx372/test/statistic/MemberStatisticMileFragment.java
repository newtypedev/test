package com.jx372.test.statistic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
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
 * Created by pys on 2017. 10. 15..
 */

public class MemberStatisticMileFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    private TextView m1;
    private TextView m2;
    private TextView m3;
    private TextView m4;
    private TextView m5;
    private TextView m6;
    private TextView m7;
    private TextView m8;
    private TextView m9;
    private TextView m10;
    private TextView m11;
    private TextView m12;
    private TextView totalText;

    public static MemberStatisticMileFragment newInstance(int position) {
        MemberStatisticMileFragment f = new MemberStatisticMileFragment();
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
    public void updateUI(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_chart_mile, container, false);

        LineChart lineChart = (LineChart) view.findViewById(R.id.chart);
        lineChart.setDrawGridBackground(false);// this is a must
        lineChart.setBackgroundColor(Color.rgb(0,0,0)); //set whatever color you prefer
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawLabels(false);

        m1 = (TextView)view.findViewById(R.id.month1);
        m2 = (TextView)view.findViewById(R.id.month2);
        m3 = (TextView)view.findViewById(R.id.month3);
        m4 = (TextView)view.findViewById(R.id.month4);
        m5 = (TextView)view.findViewById(R.id.month5);
        m6 = (TextView)view.findViewById(R.id.month6);
        m7 = (TextView)view.findViewById(R.id.month7);
        m8 = (TextView)view.findViewById(R.id.month8);
        m9 = (TextView)view.findViewById(R.id.month9);
        m10 = (TextView)view.findViewById(R.id.month10);
        m11 = (TextView)view.findViewById(R.id.month11);
        m12 = (TextView)view.findViewById(R.id.month12);
        totalText = (TextView)view.findViewById(R.id.total);


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




        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        for(int i=1;i<13;i++){
            BarEntry v1e1 = new BarEntry(sale[13-i], i-1);
            valueSet1.add(v1e1);
        }
        m1.setText(sale[1]+" km");
        m2.setText(sale[2]+" km");
        m3.setText(sale[3]+" km");
        m4.setText(sale[4]+" km");
        m5.setText(sale[5]+" km");
        m6.setText(sale[6]+" km");
        m7.setText(sale[7]+" km");
        m8.setText(sale[8]+" km");
        m9.setText(sale[9]+" km");
        m10.setText(sale[10]+" km");
        m11.setText(sale[11]+" km");
        m12.setText(sale[12]+" km");


        int total=0;
        for(int i=1;i<13;i++){
            total = total+sale[i];
        }
        totalText.setText(total+" km");
        ArrayList<Entry> entries3 = new ArrayList<>();
        int percent=0;
        for(int i=1;i<13;i++){
            percent = (int)((double)sale[i]/(double)total*100.0);
            entries3.add(new Entry(percent, i-1));
        }
        LineDataSet dataset = new LineDataSet(entries, "#Month");


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



        return view;
    }

}
