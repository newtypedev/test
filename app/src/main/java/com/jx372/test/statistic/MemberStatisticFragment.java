package com.jx372.test.statistic;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jx372.test.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by pys on 2017. 10. 14..
 */

public class MemberStatisticFragment extends Fragment {


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
    private TextView upsale;
    private ArrayList<Entry> entries3;



    public static MemberStatisticFragment newInstance(int position) {
        MemberStatisticFragment f = new MemberStatisticFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void updateUI(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    public String createComma(int value) {

        DecimalFormat Commas = new DecimalFormat("#,###");
        String result = (String)Commas.format(value);
        return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_member_statistic, container, false);
        entries3 = new ArrayList<>();
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
        upsale = (TextView)view.findViewById(R.id.upsale);



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


        return view;
    }

    public List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list,new Comparator() {

            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);
            }

        });
        Collections.reverse(list); // 주석시 오름차순
        return list;
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
        m1.setText(createComma(sale[1]) +" 원");
        m2.setText(createComma(sale[2])+" 원");
        m3.setText(createComma(sale[3])+" 원");
        m4.setText(createComma(sale[4])+" 원");
        m5.setText(createComma(sale[5])+" 원");
        m6.setText(createComma(sale[6])+" 원");
        m7.setText(createComma(sale[7])+" 원");
        m8.setText(createComma(sale[8])+" 원");
        m9.setText(createComma(sale[9])+" 원");
        m10.setText(createComma(sale[10])+" 원");
        m11.setText(createComma(sale[11])+" 원");
        m12.setText(createComma(sale[12])+" 원");

        int total=0;
        for(int i=1;i<13;i++){
           total = total+sale[i];
        }
        totalText.setText(createComma(total)+" 원");
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
        Iterator iterator = map.keySet().iterator();

        Iterator it = sortByValue(map).iterator();
String result="";
        while(it.hasNext()) {
            String temp = (String) it.next();
            result = "->"+result+"월";
            //System.out.println(temp + " = " + map.get(temp));

        }
       // upsale.setText(result);




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


}
