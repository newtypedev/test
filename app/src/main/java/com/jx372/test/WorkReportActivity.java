package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.fragment.ConsultFragment;
import com.jx372.test.fragment.WorkReportListFragment;
import com.jx372.test.view.PagerSlidingTabStrip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static com.jx372.test.R.id.activity_tab_universal_pager2;

/**
 * Created by pys on 2017. 9. 5..
 */

public class WorkReportActivity extends AppCompatActivity {


    private WorkReportListFragment now;
    private ConsultFragment nowConsulFragment;
    private WorkReportActivity.MyPagerAdapter adapter;
    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private boolean menuState=true;
    private TextView reportDate;
    Calendar cal;
    int mYear, mMonth, mDay;
    private ReportItems reportItems;
    private ConsultList cl;
    private WorkReportList wr;
    private TextView countReport;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        wr.cleanList();
        cl.cleanList();
    }

    public void replaceFragment()
    {
        WorkReportListFragment fragment = new WorkReportListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.reportListLinear, fragment);
        fragmentTransaction.commit();


    }


    //상담일지 조회 콜
    Callback2 mCallback4 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
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



    //업무보고 콜백
    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
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
                    ReportItems r = new ReportItems(datas.getJSONObject(i).getString("report_no"),datas.getJSONObject(i).getString("title"),
                            datas.getJSONObject(i).getString("report_sale"),
                            datas.getJSONObject(i).getString("content"),
                            datas.getJSONObject(i).getString("achive_rank"),
                            datas.getJSONObject(i).getString("approval"),
                            datas.getJSONObject(i).getString("opinion"),
                            datas.getJSONObject(i).getString("goal_sale"),
                            datas.getJSONObject(i).getString("start_gauge"),
                            datas.getJSONObject(i).getString("end_gauge"),
                            datas.getJSONObject(i).getString("mile"));
                        wr.addReport(r);
                    }
                   now.updateUI();

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



    // 상담일지 콜백
    Callback2 mCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(WorkReportActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson",msg);
                if(jsonbody.getString("result").equals("success")){


                    JSONArray datas = jsonbody.getJSONArray("data");
                   //createConsultItem(datas);

//
//                    JSONObject datajson = jsonbody.getJSONObject("data");
//                    Toast.makeText(TmapActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
//                    createDayItem(datajson);
//                    showData();
//                    state = "update";

                }
                else if(jsonbody.getString("result").equals("fail")){
//
//                    mdayItems.initData();
//                    showData();
//                    state = "insert";
                    Toast.makeText(WorkReportActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };





    public void createReportItem(JSONObject datajson) throws JSONException {

        reportItems.setSalesAccount(datajson.getString("report_sale"));
        reportItems.setTargetFigure(datajson.getString("goal_sale"));
        reportItems.setOpinion(datajson.getString("opinion"));
        reportItems.setContent(datajson.getString("content"));
        reportItems.setTitle(datajson.getString("title"));
        reportItems.setApproval(datajson.getString("approval"));
        reportItems.setAchiveRate(datajson.getString("achive_rank"));

    }
//
//    public void createConsultItem(JSONArray datas) throws JSONException {
//
//        int size = datas.length();
//        String no;
//        String title;
//        String content;
//        String human_name;
//        String name;
//        Consult consult;
//        for(int i=0;i<size;i++){
//            no = datas.getJSONObject(i).getString("advice_no");
//            title = datas.getJSONObject(i).getString("title");
//            content = datas.getJSONObject(i).getString("content");
//            human_name = datas.getJSONObject(i).getString("human_name");
//            name = datas.getJSONObject(i).getString("name");
//
//            consult = new Consult(no,title,content,name,human_name);
//            cl.addConsult(consult);
//
//        }
//
//
//    }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        if(menuState){

            inflater.inflate(R.menu.menu_report, menu);
        }
        else{

            inflater.inflate(R.menu.menu_consult, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.previous) {
            cal.add(cal.DAY_OF_MONTH,-1);
            reportDate.setText(getDay());
            User.get().setTempDate(getDay());
            // wr = WorkReportList.get(WorkReportActivity.this);
            wr.cleanList();
            Map map = new HashMap();
            map.put("id",User.get().getId());
            Log.v("dayday",getDay());
            map.put("date",User.get().getTempDate());
            map.put("no","2");


            HttpConnector httpcon = new HttpConnector();
            httpcon.accessServerMap("reportselect",map,mCallback2);

            return true;
        }

        else if (id == R.id.next) {
            cal.add(cal.DAY_OF_MONTH,+1);
            reportDate.setText(getDay());
            User.get().setTempDate(getDay());
             //wr = WorkReportList.get(WorkReportActivity.this);
            wr.cleanList();
            Map map = new HashMap();
            map.put("id",User.get().getId());
            Log.v("dayday",getDay());
            map.put("date",User.get().getTempDate());
            map.put("no","2");


            HttpConnector httpcon = new HttpConnector();
            httpcon.accessServerMap("reportselect",map,mCallback2);

            //pager.removeAllViews();



            return true;
        }
        else if(id == R.id.writeconsult){
            Intent i = new Intent(this,ConsultModifyActivity.class);
            startActivity(i);
        }

        else if(id == R.id.writereport){
            Intent i = new Intent(this,ReportModifyActivity.class);
            startActivity(i);
        }


        // itemUpdate();
        //onBackPressed();
//        if(item.getItemId() == R.id.writeconsult){
//            //itemUpdate();
//            //    NavUtils.navigateUpFromSameTask(this);
//          //  onBackPressed();
//
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
        setContentView(R.layout.activity_report);

        reportDate = (TextView)findViewById(R.id.reportDate);
        countReport = (TextView)findViewById(R.id.reportcount);
        //reportItems = ReportItems.get();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("업무보고");
        wr = WorkReportList.get(this);
        cl = ConsultList.get(this);

        nowConsulFragment = new ConsultFragment();
        cal = new GregorianCalendar();
        reportDate.setText(getDay());
//        mYear = cal.get(Calendar.YEAR);
//
//        mMonth = cal.get(Calendar.MONTH);
//
//        mDay = cal.get(Calendar.DAY_OF_MONTH);

        Map map = new HashMap();
        map.put("id",User.get().getId());
        Log.v("dayday",getDay());
        User.get().setTempDate(getDay());
        map.put("date",getDay());
        map.put("no","2");


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("reportselect",map,mCallback2);






        getSupportActionBar().setTitle("업무보고");
//        toolbar = (Toolbar) findViewById(R.id.toolbar2);
//        toolbar.setTitle("업무보고");
//        setSupportActionBar(toolbar);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_tab_universal_tabs2);
        //tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        pager = (ViewPager) findViewById(activity_tab_universal_pager2);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
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
                    wr.cleanList();
                    menuState = true;
                    Map map = new HashMap();
                    map.put("id","test01");
                    Log.v("dayday",getDay());
                    User.get().setTempDate(getDay());
                    map.put("date",getDay());
                    map.put("no","2");


                    HttpConnector httpcon = new HttpConnector();
                    httpcon.accessServerMap("reportselect",map,mCallback2);

                    invalidateOptionsMenu();
                }
                else if(position == 1) {
                    menuState = false;
                    cl.cleanList();
                    Log.v("상담일지 실행","통신");
                    Map map = new HashMap();
                    map.put("id",User.get().getId());
                    User.get().setTempDate(getDay());
                    map.put("date",getDay());
                    HttpConnector httpcon = new HttpConnector();
                    httpcon.accessServerMap("consultselect",map,mCallback4);

                    invalidateOptionsMenu();

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
                    now = WorkReportListFragment.newInstance(position);
                return now;
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
