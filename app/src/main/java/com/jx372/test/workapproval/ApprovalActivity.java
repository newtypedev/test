package com.jx372.test.workapproval;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.Callback2;
import com.jx372.test.Consult;
import com.jx372.test.ConsultList;
import com.jx372.test.HttpConnector;
import com.jx372.test.R;
import com.jx372.test.ReportItems;
import com.jx372.test.User;
import com.jx372.test.WorkReportList;
import com.jx372.test.fragment.ConsultFragment;
import com.jx372.test.fragment.WorkReportListFragment;
import com.jx372.test.util.DialogMediaUtils;
import com.jx372.test.view.PagerSlidingTabStrip;
import com.kakao.sdk.newtoneapi.TextToSpeechClient;
import com.kakao.sdk.newtoneapi.TextToSpeechListener;
import com.kakao.sdk.newtoneapi.TextToSpeechManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.jx372.test.R.id.activity_tab_universal_pager2;

/**
 * Created by pys on 2017. 10. 12..
 */

public class ApprovalActivity extends AppCompatActivity implements TextToSpeechListener {
    private static final String TAG = "TextToSpeechActivity";

    private TextToSpeechClient ttsClient;

    private TextView mStatus;
    private Spinner mSpinnerMode;
    public static final String REPORT_ID="";
    private UUID reportId;
    private ReportItems report;
    private WorkReportListFragment now;
    private ApprovalReportFragment nowReport;
    private ConsultFragment nowConsulFragment;
    private ApprovalActivity.MyPagerAdapter adapter;
    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private boolean menuState=true;
    private TextView reportDate;
    private WorkReportList workReportList;
    Calendar cal;
    int mYear, mMonth, mDay;

    private ConsultList cl;
    private WorkReportList wr;
    private TextView countReport;
    private UUID approvalId;
    private boolean approvalToggle;

    public static Intent newIntent(Context packageContext, UUID reportId) {

        Intent intent = new Intent(packageContext, ApprovalActivity.class);
        intent.putExtra(REPORT_ID, reportId);
        return intent;


    }

    private void handleError(int errorCode) {
        String errorText;
        switch (errorCode) {
            case TextToSpeechClient.ERROR_NETWORK:
                errorText = "네트워크 오류";
                break;
            case TextToSpeechClient.ERROR_NETWORK_TIMEOUT:
                errorText = "네트워크 지연";
                break;
            case TextToSpeechClient.ERROR_CLIENT_INETRNAL:
                errorText = "음성합성 클라이언트 내부 오류";
                break;
            case TextToSpeechClient.ERROR_SERVER_INTERNAL:
                errorText = "음성합성 서버 내부 오류";
                break;
            case TextToSpeechClient.ERROR_SERVER_TIMEOUT:
                errorText = "음성합성 서버 최대 접속시간 초과";
                break;
            case TextToSpeechClient.ERROR_SERVER_AUTHENTICATION:
                errorText = "음성합성 인증 실패";
                break;
            case TextToSpeechClient.ERROR_SERVER_SPEECH_TEXT_BAD:
                errorText = "음성합성 텍스트 오류";
                break;
            case TextToSpeechClient.ERROR_SERVER_SPEECH_TEXT_EXCESS:
                errorText = "음성합성 텍스트 허용 길이 초과";
                break;
            case TextToSpeechClient.ERROR_SERVER_UNSUPPORTED_SERVICE:
                errorText = "음성합성 서비스 모드 오류";
                break;
            case TextToSpeechClient.ERROR_SERVER_ALLOWED_REQUESTS_EXCESS:
                errorText = "허용 횟수 초과";
                break;
            default:
                errorText = "정의하지 않은 오류";
                break;
        }

        final String statusMessage = errorText + " (" + errorCode + ")";

        Log.i(TAG, statusMessage);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
              //  mStatus.setText(statusMessage);
                Toast.makeText(ApprovalActivity.this,statusMessage+"",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onError(int code, String message) {
        handleError(code);

        ttsClient = null;
    }

    @Override
    public void onFinished() {
        int intSentSize = ttsClient.getSentDataSize();
        int intRecvSize = ttsClient.getReceivedDataSize();

        final String strInacctiveText = "onFinished() SentSize : " + intSentSize + " RecvSize : " + intRecvSize;

        Log.i(TAG, strInacctiveText);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//               mStatus.setText(strInacctiveText);
//            }
//        });

        ttsClient = null;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        wr.cleanList();
        cl.cleanList();
    }

    public void refreshUI() {

        WorkReportList.get(this).cleanList();
        Map map = new HashMap();
        map.put("id", User.get().getId());
        map.put("date",getDay());


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("approvalselect", map, approvalselect);
    }


    //업무승인조회 콜백
    Callback2 approvalselect = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ApprovalActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ApprovalActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson",msg);
                if(jsonbody.getString("result").equals("success")){


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    workReportList = WorkReportList.get(ApprovalActivity.this);
                    workReportList.cleanList();

                    // WorkReportList wr = WorkReportList.get(WorkReportActivity.this);
                    //JSONObject datajson = jsonbody.getJSONObject("data");
                    Toast.makeText(ApprovalActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
                    //createReportItem(datajson);

                    for(int i=0;i<size;i++){
                        ReportItems r = new ReportItems(datas.getJSONObject(i).getString("report_no"),
                                datas.getJSONObject(i).getString("title"),
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
                        Log.v("randtest",datas.getJSONObject(i).getString("achive_rank")+"");
                        Log.v("randtest",r.getAchiveRate()+"");
                        WorkReportList.get(ApprovalActivity.this).addReport(r);
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
                    Toast.makeText(ApprovalActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };



    // 업무승인 승인,반려 콜백
    Callback2 approvalCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ApprovalActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ApprovalActivity.this,msg, Toast.LENGTH_SHORT).show();
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




                    Toast.makeText(ApprovalActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    //상담일지 조회 콜
    Callback2 consultselect = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
              //  Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                //Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
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
                    // Toast.makeText(WorkReportActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
                    //createReportItem(datajson);

                    for(int i=0;i<size;i++){
                        Consult c = new Consult(
                                datas.getJSONObject(i).getString("advice_no"),
                                datas.getJSONObject(i).getString("title"),
                                datas.getJSONObject(i).getString("content"),
                                datas.getJSONObject(i).getString("manager_name"),
                                datas.getJSONObject(i).getString("customer_code"),
                                datas.getJSONObject(i).getString("name")
                        );
                        cl.addConsult(c);
                    }
                    nowConsulFragment.updateUI();

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
                    // Toast.makeText(WorkReportActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };








    public String getDay(){
        String year =  cal.get(Calendar.YEAR)+"";
        String month = (cal.get(Calendar.MONTH)+1)+"";
        String day = cal.get(Calendar.DAY_OF_MONTH)+"";

        if(month.length()==1){
            month ="0"+month;
        }

        if(day.length()==1){
            day ="0"+day;
        }

        String firstDay = year+"-"+month+"-"+day;
        return firstDay;
    }
    public void callbackComment(String msg){

        if(msg.equals("취소")){
            return;
        }
        else{
            String temp = "";
            if(approvalToggle){
                temp = "2";
            }
                else{
                temp = "3";
            }
            HttpConnector httpcon = new HttpConnector();
            Map map = new HashMap();
            map.put("report_no",report.getReportNo());
            map.put("id", report.getUserid());
            map.put("title",report.getTitle());
            map.put("date",report.getDate());
            map.put("content", report.getContent());
            map.put("opinion",msg);
            map.put("approval",temp);
            map.put("report_sale", report.getSalesAccount()+ "");
            httpcon.accessServerMap("reportupdate", map, approvalCallback);
        }
    }

    private void speechStart(String str,double speed){


        if (ttsClient != null && ttsClient.isPlaying()) {
            ttsClient.stop();
            return;
        }

        String strText =str;

        String speechMode =TextToSpeechClient.NEWTONE_TALK_1;


        String voiceType = TextToSpeechClient.VOICE_WOMAN_READ_CALM;

       // speechSpeed = 1.0D;
        double speechSpeed = speed;


        ttsClient = new TextToSpeechClient.Builder()
                .setSpeechMode(speechMode)
                .setSpeechSpeed(speechSpeed)
                .setSpeechVoice(voiceType)
                .setListener(ApprovalActivity.this)
                .build();

        ttsClient.play(strText);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_approval_report, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        HttpConnector httpcon = new HttpConnector();

        if (id == R.id.back) {

            onBackPressed();
            return true;
        }

        else if (id == R.id.next) {

            DialogMediaUtils dialog = new DialogMediaUtils(this);
            dialog.showDialog(this);



            return true;
        }
        else if(id == R.id.approval){

            if(report.getApproval().equals("1")) {
                approvalToggle = true;

                DialogMediaUtils dialog = new DialogMediaUtils(this);
                dialog.showDialog(this);
            }
            else{
                Toast.makeText(this,"수정 불가",Toast.LENGTH_LONG).show();
            }
        }

        else if(id == R.id.deny){

            if(report.getApproval().equals("1")) {
            approvalToggle = false;
            DialogMediaUtils dialog = new DialogMediaUtils(this);
            dialog.showDialog(this);}
            else{
                Toast.makeText(this,"수정 불가",Toast.LENGTH_LONG).show();
            }


//            Map map = new HashMap();
//            map.put("report_no",report.getReportNo());
//            map.put("id", report.getUserid());
//            map.put("title",report.getTitle());
//            map.put("date",report.getDate());
//            map.put("content", report.getContent());
//            map.put("approval","3");
//            map.put("report_sale", report.getSalesAccount()+ "");
//            httpcon.accessServerMap("reportupdate", map, approvalCallback);
        }
        else if(id == R.id.reportplay){


            String b= report.getContent();
            Log.v("jsonjson",b);
            b =b.replaceAll("<u>","");
            b = b.replaceAll("</u>","");
            Log.v("underunderlin",b);

        String test = b;

            test =test.replaceAll("&nbsp;"," ");


        test =test.replaceAll("<h1 data-tag=\"input\" >"," ");
        test = test.replaceAll("</h1>"," ");

        test =test.replaceAll("<h2 data-tag=\"input\" >"," ");
        test = test.replaceAll("</h2>"," ");

        test =test.replaceAll("<h3 data-tag=\"input\" >"," ");
        test = test.replaceAll("</h3>"," ");


        test =test.replaceAll("<p data-tag=\"input\" >","");
        test = test.replaceAll("</p>"," ");

        test = test.replaceAll("<b>","");
        test = test.replaceAll("</b>"," ");
        test = test.replaceAll("<i>","");
        test = test.replaceAll("</i>"," ");
        test = test.replaceAll("<p data-tag=\"input\">"," ");
        Log.v("내용이다",test+"");
         speechStart(test,1.0D);
        }
        else if(id == R.id.reportfast){
            String b= report.getContent();
            Log.v("jsonjson",b);
            b =b.replaceAll("<u>","");
            b = b.replaceAll("</u>","");
            Log.v("underunderlin",b);

            String test = b;

            test =test.replaceAll("&nbsp;"," ");


            test =test.replaceAll("<h1 data-tag=\"input\" >"," ");
            test = test.replaceAll("</h1>"," ");

            test =test.replaceAll("<h2 data-tag=\"input\" >"," ");
            test = test.replaceAll("</h2>"," ");

            test =test.replaceAll("<h3 data-tag=\"input\" >"," ");
            test = test.replaceAll("</h3>"," ");


            test =test.replaceAll("<p data-tag=\"input\" >","");
            test = test.replaceAll("</p>"," ");

            test = test.replaceAll("<b>","");
            test = test.replaceAll("</b>"," ");
            test = test.replaceAll("<i>","");
            test = test.replaceAll("</i>"," ");
            test = test.replaceAll("<p data-tag=\"input\">"," ");
            Log.v("내용이다",test+"");
            speechStart(test,2.0D);
        }
        else if(id == R.id.reportstop){
            speechStart(" ",0D);
        }


        return super.onOptionsItemSelected(item);
    }


    public UUID getReportId(){
        return reportId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        reportId = (UUID)getIntent().getSerializableExtra(ApprovalActivity.REPORT_ID);
        report = WorkReportList.get(this).getReports(reportId);

       TextView tv = (TextView)findViewById(R.id.reportDate);
        tv.setVisibility(View.INVISIBLE);
       tv= (TextView)findViewById(R.id.reportcount);
        tv.setVisibility(View.INVISIBLE);
        reportDate = (TextView)findViewById(R.id.reportDate);
        countReport = (TextView)findViewById(R.id.reportcount);

        TextToSpeechManager.getInstance().initializeLibrary(getApplicationContext());
        cl = ConsultList.get(this);
        cl.cleanList();
        Map map = new HashMap();
        map.put("id", report.getUserid());
//        User.get().setTempDate(getDay());
        map.put("date",report.getDate());
        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("consultselect",map,consultselect);
        nowConsulFragment = new ConsultFragment();
        cal = new GregorianCalendar();
        reportDate.setText(getDay());
//        mYear = cal.get(Calendar.YEAR);
//
//        mMonth = cal.get(Calendar.MONTH);
//
//        mDay = cal.get(Calendar.DAY_OF_MONTH);




        getSupportActionBar().setTitle("");
//        toolbar = (Toolbar) findViewById(R.id.toolbar2);
//        toolbar.setTitle("업무보고");
//        setSupportActionBar(toolbar);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_tab_universal_tabs2);
        //tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        pager = (ViewPager) findViewById(activity_tab_universal_pager2);

        adapter = new ApprovalActivity.MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
//        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources()
//                .getDisplayMetrics());
//        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);

//        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
//            @Override
//            public void onTabReselected(int position) {
//                findViewById(activity_tab_universal_pager2).setVisibility(View.VISIBLE);
//                Toast.makeText(WorkReportActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {

                }
                else if(position == 1) {


                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<String> tabNames = new ArrayList<String>() {{
            add("업무보고");
            add("상담일지");
            //add("첨부파일");

        }};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

        @Override
        public int getCount() {
            return tabNames.size();
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                nowReport = ApprovalReportFragment.newInstance(position);
                return nowReport;
            }
            else if(position ==1){
                nowConsulFragment = ConsultFragment.newInstance(position);
                return nowConsulFragment;
            }
            else {

                return ConsultFragment.newInstance(position);
            }
        }
    }

}
