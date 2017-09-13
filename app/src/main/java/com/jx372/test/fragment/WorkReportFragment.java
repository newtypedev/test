package com.jx372.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx372.test.R;
import com.jx372.test.ReportItems;
import com.jx372.test.ReportModifyActivity;
import com.jx372.test.WorkReportActivity;

import java.text.DecimalFormat;

/**
 * Created by pys on 2017. 9. 5..
 */

public class WorkReportFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private int position;
    private LinearLayout reportLayout;
    private TextView salesAccount;
    private ReportItems reportItems;
    private TextView reporttarget;
    private TextView repstartdis;
    private TextView rependdis;
    private TextView repmemo;

    public String createComma(String num) {

        if(num.equals(""))return "";

        int value = Integer.parseInt(num);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result = (String)Commas.format(value);
        return result;
    }



    public void showData() {
        salesAccount.setText(createComma(reportItems.getSalesAccount()));
       repstartdis.setText(reportItems.getStartDistance());
        rependdis.setText(reportItems.getEndDistance());
        repmemo.setText(reportItems.getContent());


    }


    public static WorkReportFragment newInstance(int position) {
        WorkReportFragment f = new WorkReportFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public void startReportActivity(){
        Intent i = new Intent(getActivity(),ReportModifyActivity.class);
        //  i.putExtra("day",msg);
        startActivity(i);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_report,
                container, false);

        reportLayout = (LinearLayout) rootView.findViewById(R.id.reportLinear);
        salesAccount = (TextView)rootView.findViewById(R.id.reportsales);
        reporttarget = (TextView)rootView.findViewById(R.id.reporttarget);
        repstartdis =  (TextView)rootView.findViewById(R.id.repstartdis);
        rependdis =  (TextView)rootView.findViewById(R.id.rependdis);
        repmemo =  (TextView)rootView.findViewById(R.id.repmemo);
        reportLayout.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startReportActivity();
                return true;
            }
        });



        return rootView;
    }
    @Override
    public void onResume() {

        showData();
        super.onResume();
    }


}
