package com.jx372.test;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.customermanagement.Customer;
import com.jx372.test.customermanagement.CustomerAdminFragment;
import com.jx372.test.customermanagement.CustomerList;
import com.jx372.test.customermanagement.CustomerModifyActivity;
import com.jx372.test.membermanagement.Member;
import com.jx372.test.membermanagement.MemberList;
import com.jx372.test.membermanagement.MemberListFragment;
import com.jx372.test.statistic.TeamStatisticFragment;
import com.jx372.test.weekplansearch.ExpandableListAdapter;
import com.jx372.test.weekplansearch.WeekSearchFragment;
import com.jx372.test.workapproval.WorkApprovalFragment;
import com.kakao.sdk.newtoneapi.SpeechRecognizeListener;
import com.kakao.sdk.newtoneapi.SpeechRecognizerClient;
import com.kakao.sdk.newtoneapi.SpeechRecognizerManager;
import com.kakao.sdk.newtoneapi.impl.util.PermissionUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pys on 2017. 10. 6..
 */

public class LeaderMainActivity extends AppCompatActivity implements SpeechRecognizeListener {
    private String menuState="";
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CustomerList customerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private WeekSearchFragment nowWeekSearchFragment;
    private WorkApprovalFragment nowWorkApprovalFragment;
    private CustomerAdminFragment nowCustomerFragment;
    private TeamStatisticFragment nowTeamStatisticFragment;
    private MemberListFragment nowMemberFragment;
    private MemberList memberList;
    private WorkReportList workReportList;
    private boolean micToggle;
    private SpeechRecognizerClient client;
    private MenuItem micItem;
    private Calendar firstdayofweek;
    private Calendar approvalCalendar;

    public String getFirstDay(){
        String year = firstdayofweek.get(firstdayofweek.YEAR)+"";
        String month = (firstdayofweek.get(firstdayofweek.MONTH)+1)+"";
        String day = firstdayofweek.get(firstdayofweek.DAY_OF_MONTH)+"";

        if(month.length()==1){
            month ="0"+month;
        }
        if(day.length()==1){
            day ="0"+day;
        }

        String firstDay = year+"-"+month+"-"+day;
        return firstDay;
    }



    //업무승인조회 콜백
    Callback2 approvalselect = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(LeaderMainActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(LeaderMainActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson",msg);
                if(jsonbody.getString("result").equals("success")){


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    if(size==0){
                        Toast.makeText(LeaderMainActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                    }
                    workReportList = WorkReportList.get(LeaderMainActivity.this);
                    workReportList.cleanList();

                    // WorkReportList wr = WorkReportList.get(WorkReportActivity.this);
                    //JSONObject datajson = jsonbody.getJSONObject("data");
                   // Toast.makeText(LeaderMainActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
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
                        WorkReportList.get(LeaderMainActivity.this).addReport(r);
                    }

                    nowWorkApprovalFragment.updateUI();
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
                    Toast.makeText(LeaderMainActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };


    // 주간계획 조회 콜백
    Callback2 selectWeekPlan = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {

                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);
                if (jsonbody.getString("result").equals("success")) {

                    Map map1 ;
                    Map map2 ;



                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
                    List<ExpandableListAdapter.Item> data = new ArrayList<>();
                    Toast.makeText(LeaderMainActivity.this, datas.getJSONObject(0).getString("week_no"), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < size; i++) {

                        map1 = new HashMap();
                        map1.put("userid",datas.getJSONObject(i).getString("id"));
                        map1.put("weektarget",datas.getJSONObject(i).getString("target_figure"));
                        map1.put("weeksale",datas.getJSONObject(i).getString("week_sale"));
                        map1.put("weekachieve",datas.getJSONObject(i).getString("achive_rank"));
                        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, map1);
                        places.invisibleChildren = new ArrayList<>();

                        map2 = new HashMap();
                        map2.put("daytext","MON");

                        map2.put("daycontent",datas.getJSONObject(i).getString("monday"));
                        map2.put("daytarget",datas.getJSONObject(i).getString("monday_money"));
                        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));

                        map2 = new HashMap();
                        map2.put("daytext","TUE");

                        map2.put("daycontent",datas.getJSONObject(i).getString("tuesday"));
                        map2.put("daytarget",datas.getJSONObject(i).getString("tuesday_money"));
                        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
                        map2 = new HashMap();
                        map2.put("daytext","WED");

                        map2.put("daycontent",datas.getJSONObject(i).getString("wednesday"));
                        map2.put("daytarget",datas.getJSONObject(i).getString("wednesday_money"));
                        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
                        map2 = new HashMap();
                        map2.put("daytext","THU");

                        map2.put("daycontent",datas.getJSONObject(i).getString("thursday"));
                        map2.put("daytarget",datas.getJSONObject(i).getString("thursday_money"));
                        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
                        map2 = new HashMap();
                        map2.put("daytext","FRI");

                        map2.put("daycontent",datas.getJSONObject(i).getString("friday"));
                        map2.put("daytarget",datas.getJSONObject(i).getString("friday_money"));
                        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, map2));
                        data.add(places);
                    }
                    nowWeekSearchFragment.updateUI(data);

                } else if (jsonbody.getString("result").equals("fail")) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };




    @Override
    public void onReady() {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int errorCode, String errorMsg) {
        Log.e("SpeechSampleActivity", "onError");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                micItem.setIcon(R.drawable.ic_action_micon);

            }
        });

        client = null;
    }

    @Override
    public void onPartialResult(String partialResult) {

    }

    @Override
    public void onResults(Bundle results) {
        final StringBuilder builder = new StringBuilder();
        Log.i("SpeechSampleActivity", "onResults");

        ArrayList<String> texts = results.getStringArrayList(SpeechRecognizerClient.KEY_RECOGNITION_RESULTS);
        ArrayList<Integer> confs = results.getIntegerArrayList(SpeechRecognizerClient.KEY_CONFIDENCE_VALUES);

        for (int i = 0; i < texts.size(); i++) {

            builder.append(texts.get(i));
            builder.append(" (");
            builder.append(confs.get(i).intValue());
            builder.append(")\n");
        }
        final String temp = texts.get(0).trim().replace(" ","");
        Log.v("첫번째", temp);

        String arr[] = {"동작대리점","강남대리점","gs25사당점","gs25사당드림점"};

        // Toast.makeText(SpeechSampleActivity.this,temp, Toast.LENGTH_LONG).show();


        if(  temp.substring(temp.length()-2,temp.length()).equals("연결")) {
            for (int i = 0; i < arr.length; i++) {
                if (temp.contains(arr[i]) /*temp.equals("동작대리점연결")*/) {



                    Log.v("연결", "성공성공");
                    Uri number;
                    Intent intent;
                    number = Uri.parse("tel:" + 114);
                    intent = new Intent(Intent.ACTION_CALL, number);
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    startActivity(intent);


//                    nowLatitude = 37.541642248630524;
//                    nowLongitude = 126.99599611759186;
//                    if (!(nowLongitude == 0.0)) {
//                        mMapView.setLocationPoint(nowLongitude, nowLatitude);
//                        mMapView.setCenterPoint(nowLongitude, nowLatitude, true);
//                        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.end);
//                        mMapView.setIcon(bitmap);
//                        mMapView.setIconVisibility(true);
//                        mMapView.setZoom(19);
//                    }


                    if (client != null) {
                        client.cancelRecording();
                    }

                    micItem.setIcon(R.drawable.ic_action_micon);
                }
            }

        }
        if(  temp.substring(temp.length()-2,temp.length()).equals("검색")) {
            for (int i = 0; i < arr.length; i++) {
                if (temp.contains(arr[i]) /*temp.equals("동작대리점연결")*/) {





//                    nowLatitude = 37.541642248630524;
//                    no0wLongitude = 126.99599611759186;
//                    if (!(nowLongitude == 0.0)) {
//                        mMapView.setLocationPoint(nowLongitude, nowLatitude);
//                        mMapView.setCenterPoint(nowLongitude, nowLatitude, true);
//                        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.end);
//                        mMapView.setIcon(bitmap);
//                        mMapView.setIconVisibility(true);
  //                  }


                    if (client != null) {
                        client.cancelRecording();
                    }

                    micItem.setIcon(R.drawable.ic_action_micon);
                }
            }

        }

        // Toast.makeText(this,"인식 실패", Toast.LENGTH_LONG).show();
        if (client != null) {
            client.cancelRecording();
        }

        micItem.setIcon(R.drawable.ic_action_micon);



        client = null;

    }

    @Override
    public void onAudioLevel(float audioLevel) {

    }

    @Override
    public void onFinished() {
        Log.i("SpeechSampleActivity", "onFinished");

    }





    private void cancelSpeech(){


            if (client != null) {
                client.cancelRecording();
            }


    }
    private void startSpeech(){

        String serviceType = SpeechRecognizerClient.SERVICE_TYPE_DICTATION;
        if (PermissionUtils.checkAudioRecordPermission(LeaderMainActivity.this)) {

            SpeechRecognizerClient.Builder builder = new SpeechRecognizerClient.Builder().
                    setServiceType(serviceType);
            client = builder.build();

            client.setSpeechRecognizeListener(LeaderMainActivity.this);
            client.startRecording(true);


        }

    }

//
//
//
//    findViewById(R.id.speechbutton).setOnClickListener(new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {

//        }
//    });
//
//
//    findViewById(R.id.cancelbutton).setOnClickListener(new View.OnClickListener() {
//        @Override

//
//        }
//    });


    // 팀원 리스트 콜백
    Callback2 selectMember = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);
                if (jsonbody.getString("result").equals("success")) {


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
                    memberList = MemberList.get(LeaderMainActivity.this);
                    memberList.cleanList();

                    for (int i = 0; i < size; i++) {

                        Member m = new Member(datas.getJSONObject(i).getString("id"),
                                datas.getJSONObject(i).getString("name"),
                                datas.getJSONObject(i).getString("grade"),
                                datas.getJSONObject(i).getString("email"),
                                datas.getJSONObject(i).getString("dept")

                        );

                        memberList.addMember(m);

                        // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));
                    }
                    nowMemberFragment.updateUI();

                } else if (jsonbody.getString("result").equals("fail")) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };

    // 고객 리스트 콜백
    Callback2 selectCustomer = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);
                if (jsonbody.getString("result").equals("success")) {


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
                    customerList = CustomerList.get(LeaderMainActivity.this);
customerList.cleanList();

                    for (int i = 0; i < size; i++) {

                        Customer c = new Customer(datas.getJSONObject(i).getString("customer_code"),
                                datas.getJSONObject(i).getString("name"),
                                datas.getJSONObject(i).getString("manager_name"),
                                datas.getJSONObject(i).getString("address"));

                        customerList.addCustomer(c);

                       // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));
                    }
                    nowCustomerFragment.updateUI();

                } else if (jsonbody.getString("result").equals("fail")) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };

    public String getDay(){
        String year =  approvalCalendar.get(Calendar.YEAR)+"";
        String month = (approvalCalendar.get(Calendar.MONTH)+1)+"";
        String day = approvalCalendar.get(Calendar.DAY_OF_MONTH)+"";

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        customerList.get(LeaderMainActivity.this);
        micToggle = true;
        firstdayofweek = Calendar.getInstance();
        approvalCalendar = new GregorianCalendar();


        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.leader_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_leader);
        mDrawerList = (ListView) findViewById(R.id.left_drawer_leader);
        SpeechRecognizerManager.getInstance().initializeLibrary(this);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */

                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle); //addDrawerListener

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return super.onCreateOptionsMenu(menu);

        super.onCreateOptionsMenu(menu);

        if(menuState.equals("customer")){

            getMenuInflater().inflate(R.menu.menu_main_customer, menu);
        }
        else if(menuState.equals("member")){
            getMenuInflater().inflate(R.menu.menu_main_member, menu);
        }
        else if(menuState.equals("weekplan")){
            getMenuInflater().inflate(R.menu.menu_main_week, menu);
        }
        else if(menuState.equals("approval")){
            getMenuInflater().inflate(R.menu.menu_main_approval, menu);
        }
        else{

            getMenuInflater().inflate(R.menu.menu_member, menu);
        }

        return true;


    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        HttpConnector httpcon = new HttpConnector();






        if(item.getItemId()==R.id.addcustomer){
            Intent i = new Intent(this,CustomerModifyActivity.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.approvalpre){
            Map map = new HashMap();
            approvalCalendar.add(approvalCalendar.DAY_OF_MONTH,-1);
            getSupportActionBar().setTitle(getDay());
            map.put("id", User.get().getId());
            map.put("date",getDay());
            httpcon.accessServerMap("approvalselect", map, approvalselect);
        }
        else if(item.getItemId()==R.id.approvalnext){
            Map map = new HashMap();
            approvalCalendar.add(approvalCalendar.DAY_OF_MONTH,+1);
            getSupportActionBar().setTitle(getDay());
            map.put("id", User.get().getId());
            map.put("date",getDay());
            httpcon.accessServerMap("approvalselect", map, approvalselect);
        }

        else if (item.getItemId() == R.id.weekprevious) {
            firstdayofweek.add(firstdayofweek.DAY_OF_MONTH,-7);
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date", getFirstDay());
            httpcon.accessServerMap("teamweekselect", map, selectWeekPlan);
            getSupportActionBar().setTitle("이전");
            return true;
        }
        else if (item.getItemId() == R.id.weeknext) {
            getSupportActionBar().setTitle("다음");

            firstdayofweek.add(firstdayofweek.DAY_OF_MONTH,+7);
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date", getFirstDay());
            httpcon.accessServerMap("teamweekselect", map, selectWeekPlan);

            return true;
        }

        else if(item.getItemId() == R.id.logout) {

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.addmember){
            Intent i = new Intent(this,JoinActivity.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.mic){
            micItem = item;
            if(micToggle) {
                startSpeech();
                item.setIcon(R.drawable.ic_action_micon);
                Toast.makeText(LeaderMainActivity.this, "음식 인식 시작", Toast.LENGTH_SHORT).show();

                micToggle = false;
            }
            else{
                cancelSpeech();
                item.setIcon(R.drawable.ic_action_offmic);
                Toast.makeText(LeaderMainActivity.this, "음식 인식 종료", Toast.LENGTH_SHORT).show();

                micToggle = true;
            }
            }

        // item.setIcon(R.drawable.ic_action_offmic);
        // mItem.setIcon(R.drawable.ic_action_offmic);
        // Handle action buttons
        switch(item.getItemId()) {

//            case R.id.action_websearch:
//                // create intent to perform web search for this planet
//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
//                // catch event that there's no activity to handle intent
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
//                }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        if(position==0) {
            menuState = "member";

            invalidateOptionsMenu();
            HttpConnector httpcon = new HttpConnector();
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("grade","전체");
            map.put("dept",User.get().getDept());
            httpcon.accessServerMap("memberselect", map, selectMember);

            Fragment fragment =  MemberListFragment.newInstance(position);
            nowMemberFragment = (MemberListFragment) fragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_leader, fragment).commit();

        }
        else if(position==1){
//            customerList.cleanList();
            menuState = "customer";
            invalidateOptionsMenu();
            HttpConnector httpcon = new HttpConnector();
            Map map = new HashMap();
            map.put("id", User.get().getId());
            httpcon.accessServerMap("customerselect", map, selectCustomer);

            Fragment fragment =  CustomerAdminFragment.newInstance(position);
            nowCustomerFragment = (CustomerAdminFragment) fragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_leader, fragment).commit();
        }
        else if(position==2){
            menuState = "approval";
            invalidateOptionsMenu();
            HttpConnector httpcon = new HttpConnector();
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date",getDay());
            httpcon.accessServerMap("approvalselect", map, approvalselect);
            Fragment fragment =   WorkApprovalFragment.newInstance(position);
            nowWorkApprovalFragment = (WorkApprovalFragment) fragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_leader, fragment).commit();
        }
        else if(position==3){
            menuState = "weekplan";
            invalidateOptionsMenu();
            HttpConnector httpcon = new HttpConnector();
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date", getFirstDay());
            httpcon.accessServerMap("teamweekselect", map, selectWeekPlan);

            Fragment fragment =  WeekSearchFragment.newInstance(position);
            nowWeekSearchFragment = (WeekSearchFragment) fragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_leader, fragment).commit();
        }

        else {
            menuState = "";
            invalidateOptionsMenu();
            // update the main content by replacing fragments
            Fragment fragment = new LeaderFragment();
            Bundle args = new Bundle();
            args.putInt(LeaderFragment.ARG_PLANET_NUMBER, position);
            fragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_leader, fragment).commit();
        }
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class LeaderFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public LeaderFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_leader, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.leader_array)[i];

            ((TextView) rootView.findViewById(R.id.testleader)).setText(planet);

            return rootView;
        }
    }
}
