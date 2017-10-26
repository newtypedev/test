package com.jx372.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by pys on 2017. 9. 13..
 */

public class ReportModifyActivity extends AppCompatActivity {

    public static final String REPORT_ID="";
    private  UUID reportId;
    private Editor reportmemo;
    private EditText startdis;
    private EditText enddis;
    private EditText salesAccount;
    private String tempContent="";
    private ReportItems reportItems;
    private String state="";
    private boolean menuState;

// 업무보고 삭제 콜백
    Callback2 mCallback4 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    // updateData(jsonbody.getString("data"));
                    refreshUI();
                      Toast.makeText(ReportModifyActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(ReportModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    //업무보고 콜백
    Callback2 mCallback3 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson",msg);
                if(jsonbody.getString("result").equals("success")){


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));


                    // WorkReportList wr = WorkReportList.get(WorkReportActivity.this);
                    //JSONObject datajson = jsonbody.getJSONObject("data");
                  //  Toast.makeText(ReportModifyActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
                    //createReportItem(datajson);

                    for(int i=0;i<size;i++){
                        ReportItems r = new ReportItems(datas.getJSONObject(i).getString("report_no"),datas.getJSONObject(i).getString("title"),
                                datas.getJSONObject(i).getString("report_sale"),
                                datas.getJSONObject(i).getString("content"),
                                datas.getJSONObject(i).getString("achive_rank"),
                                datas.getJSONObject(i).getString("approval"),
                                datas.getJSONObject(i).getString("opinion"),
                                datas.getJSONObject(i).getString("goal_sale"),
                                datas.getJSONObject(i).getString("start_gauge"),
                                datas.getJSONObject(i).getString("end_gauge"),
                                datas.getJSONObject(i).getString("mile"),
                                datas.getJSONObject(i).getString("id"),
                                datas.getJSONObject(i).getString("date"));
                        WorkReportList.get(ReportModifyActivity.this).addReport(r);
                    }

                    onBackPressed();
                    // replaceFragment();
//                    Handler mHandler = new Handler(Looper.getMainLooper());
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//
//
//                        }
//                    }, 2000);



                }
                else if(jsonbody.getString("result").equals("fail")){
//
//                    mdayItems.initData();
//                    showData();
//                    state = "insert";
                    Toast.makeText(ReportModifyActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    public void refreshUI() {

        WorkReportList.get(this).cleanList();
        Map map = new HashMap();
        map.put("id", User.get().getId());
        Log.v("dayday",  User.get().getTempDate());
        map.put("date", User.get().getTempDate());
        map.put("no", "2");


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("reportselect", map, mCallback3);
    }
//    private void updateData(String no){
//
//
//        WorkReportList wr = WorkReportList.get(ReportModifyActivity.this);
//
//
//
//            ReportItems r = new ReportItems(no,"업무보고",
//                    salesAccount.getText()+"",
//                    tempContent,
//                    "0",
//                   "0",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "");
//            wr.addReport(r);
//        onBackPressed();
//
//    }

    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                   // updateData(jsonbody.getString("data"));
                    refreshUI();
                  //  Toast.makeText(ReportModifyActivity.this,jsonbody.getString("data"), Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(ReportModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    private void setUpEditor() {
        findViewById(R.id.action_h1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportmemo.UpdateTextStyle(EditorTextStyle.H1);
            }
        });

        findViewById(R.id.action_h2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportmemo.UpdateTextStyle(EditorTextStyle.H2);
            }
        });

        findViewById(R.id.action_h3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportmemo.UpdateTextStyle(EditorTextStyle.H3);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportmemo.UpdateTextStyle(EditorTextStyle.BOLD);
            }
        });

        findViewById(R.id.action_Italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportmemo.UpdateTextStyle(EditorTextStyle.ITALIC);
            }
        });


        findViewById(R.id.action_erase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportmemo.clearAllContents();
            }
        });
        //editor.dividerBackground=R.drawable.divider_background_dark;

    }

    public static Intent newIntent(Context packageContext, UUID reportId) {

        Intent intent = new Intent(packageContext, ReportModifyActivity.class);
        intent.putExtra(REPORT_ID, reportId);
        return intent;


    }

    private void setTextValue(){

        //reportmemo.setText(reportItems.getContent());
        startdis.setText(reportItems.getStartDistance());
        enddis.setText(reportItems.getEndDistance());
        salesAccount.setText(reportItems.getSalesAccount());
    }

    public void itemUpdate(){
        reportItems.setSalesAccount(salesAccount.getText()+"");
       // reportItems.setContent(reportmemo.getText()+"");
        reportItems.setStartDistance(startdis.getText()+"");
        reportItems.setEndDistance(enddis.getText()+"");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        getMenuInflater().inflate(R.menu.menu_report_modify, menu);
//        return true;

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        if(state.equals("insert")){

            inflater.inflate(R.menu.menu_report_modify, menu);
        }
        else{

            inflater.inflate(R.menu.menu_report_modify_update, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        HttpConnector httpcon = new HttpConnector();


        if (item.getItemId() == R.id.reportfinish || item.getItemId() == R.id.reportsend) {
            Map map = new HashMap();
            if(item.getItemId() == R.id.reportfinish){
                map.put("approval", "0");
            }
            else
                {
                    map.put("approval", "1");
            }

            if(state.equals("insert")) {
                Editor editor = new Editor(this, null);
                String b = reportmemo.getContentAsSerialized();
                Log.v("jsonjson", b);
                b = editor.getContentAsHTML(b);
                b = b.replaceAll("<u>", "");
                b = b.replaceAll("</u>", "");
                tempContent = b;
                Log.v("underunderlin", b);



                map.put("id", User.get().getId());
                map.put("title", "업무보고");
                map.put("date", User.get().getTempDate());
                map.put("content", tempContent);

                map.put("report_sale", salesAccount.getText() + "");
                httpcon.accessServerMap("reportinsert", map, mCallback2);
                //itemUpdate();
                //onBackPressed();
                return true;
            }

            else if(state.equals("update")) {
                Editor editor = new Editor(this, null);
                String b = reportmemo.getContentAsSerialized();
                Log.v("jsonjson", b);
                b = editor.getContentAsHTML(b);
                b = b.replaceAll("<u>", "");
                b = b.replaceAll("</u>", "");
                tempContent = b;
                Log.v("underunderlin", b);


                map.put("report_no",reportItems.getReportNo());
                map.put("id", User.get().getId());
                map.put("title",reportItems.getTitle());
                map.put("date",User.get().getTempDate());
                map.put("content", tempContent);

                map.put("report_sale", salesAccount.getText() + "");
                httpcon.accessServerMap("reportupdate", map, mCallback2);
                //itemUpdate();
                //onBackPressed();
                return true;
            }
        }


        else if (item.getItemId() == R.id.reportdelete){

            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date", User.get().getTempDate());
            map.put("report_no",reportItems.getReportNo());
            httpcon.accessServerMap("reportdelete", map, mCallback4);
        }

        else if (item.getItemId() == R.id.reportback){
            onBackPressed();
        }
//            itemUpdate();
//            //    NavUtils.navigateUpFromSameTask(this);
//            onBackPressed();
//
//        }
//
//        if(item.getItemId()==R.id.dayback){
//            onBackPressed();
//        }
            return super.onOptionsItemSelected(item);
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_modify);

        reportmemo = (Editor) findViewById(R.id.reportmemo);
//        startdis = (EditText) findViewById(R.id.startdis);
//        enddis =(EditText)  findViewById(R.id.enddis);
//        salesAccount = (EditText)findViewById(R.id.salerep);
//       // reportItems = ReportItems.get();


        salesAccount = (EditText) findViewById(R.id.salerep);

        getSupportActionBar().setTitle("수정");
        setUpEditor();
        reportId = (UUID)getIntent().getSerializableExtra(ReportModifyActivity.REPORT_ID);
        if(!(reportId==null)) {
            state = "update";
            Log.v("크라임", reportId + "");
            reportItems = WorkReportList.get(this).getReports(reportId);
            reportmemo.Render(reportItems.getContent());
            salesAccount.setText(reportItems.getSalesAccount());
        }
        else{
            state = "insert";
            reportmemo.Render();
        }



//        setTextValue();

    }



}
