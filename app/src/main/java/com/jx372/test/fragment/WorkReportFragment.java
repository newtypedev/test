package com.jx372.test.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.R;
import com.jx372.test.ReportItems;
import com.jx372.test.ReportModifyActivity;
import com.jx372.test.WorkReportActivity;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;

import static android.R.interpolator.linear;

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
    private TextView startclick;
    private ImageView mImage;
    private TextView endclick;

    class DownThread extends Thread {
        String mAddr;

        DownThread(String addr) {
            mAddr = addr;
        }

        public void run() {
            try {
                InputStream is = new URL(mAddr).openStream();
                Bitmap bit = BitmapFactory.decodeStream(is);
                is.close();
                Message message = mAfterDown.obtainMessage();
                message.obj = bit;
                mAfterDown.sendMessage(message);
            } catch (Exception e) {;}
        }
    }
    Handler mAfterDown = new Handler() {
        public void handleMessage(Message msg) {
            Bitmap bit = (Bitmap)msg.obj;
            if (bit == null) {
                Toast.makeText(getActivity(), "이미지가 없습니다", Toast.LENGTH_LONG).show();
            } else {
                mImage.setImageBitmap(bit);
            }
        }
    };


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
        startclick = (TextView)rootView.findViewById(R.id.startclick);


        startclick.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {



                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_imageview);


                mImage = (ImageView) dialog.findViewById(R.id.image);
                (new DownThread("http://www.soen.kr/data/child2.jpg")).start();


                    dialog.show();

//                final LinearLayout linear = (LinearLayout)
//                        View.inflate(getActivity(), R.layout.dialog_weeksale, null);
//
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("목표액 수정")
//                        .setView(linear)
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//
//                            }
//                        })
//                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        })
//                        .show();

            }
        });
        endclick = (TextView)rootView.findViewById(R.id.endclick);

        endclick.setOnClickListener(new TextView.OnClickListener(){
            @Override
            public void onClick(View v) {



                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_imageview);


                mImage = (ImageView) dialog.findViewById(R.id.image);
                (new DownThread("http://www.soen.kr/data/child2222.jpg")).start();


                dialog.show();

//                final LinearLayout linear = (LinearLayout)
//                        View.inflate(getActivity(), R.layout.dialog_weeksale, null);
//
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("목표액 수정")
//                        .setView(linear)
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//
//                            }
//                        })
//                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        })
//                        .show();

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
