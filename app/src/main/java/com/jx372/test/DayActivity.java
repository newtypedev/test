package com.jx372.test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.github.irshulx.Editor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DayActivity extends AppCompatActivity {

    Editor editor;
    DayItems mdayItems;
    Calendar cal;
    int mYear, mMonth, mDay;
    TextView daytext;
    LinearLayout dayLayout;
    TextView dayContent;
    Editor dayMemo;
    TextView dayGoalsale;
    TextView dayChallenge;
    String state="";
    TextView visitPoint;
    TextView distance;
    String userId="";
    ViewFlipper mFlip;
    private Toolbar toolbar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    public void showData() {
        dayMemo.clearAllContents();
        dayMemo.Render(mdayItems.getContent());
        //dayContent.setText(mdayItems.getContent());
        dayGoalsale.setText(createComma(mdayItems.getGoalsale()+""));
        distance.setText(mdayItems.getShortDistance()+"km");
        visitPoint.setText(mdayItems.getVisitPoint());
        dayChallenge.setText(mdayItems.getChallenge());


    }
    private void refreshUI(){
        HttpConnector httpcon = new HttpConnector();
        Map map = new HashMap();
        map.put("id",User.get().getId());
        map.put("date",getDay());
        httpcon.accessServerMap("dayselect",map,mCallback);

        mFlip.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.viewdown));
        mFlip.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.viewup));
    }

    public void createDayItem(JSONObject datajson) throws JSONException{

        mdayItems.setContent(datajson.getString("content"));
        mdayItems.setGoalsale(datajson.getString("goal_sale"));
        mdayItems.setChallenge(datajson.getString("challenge_content"));
        mdayItems.setTitle(datajson.getString("title"));
        mdayItems.setOpinion(datajson.getString("opinion"));
        mdayItems.setShortDistance(datajson.getString("estimate_distance"));
        mdayItems.setVisitPoint(datajson.getString("estimate_course"));


    }




    public String createComma(String num) {
        int value = Integer.parseInt(num);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result = (String)Commas.format(value);
        return result;
    }


    public void startDayActivity(String msg){
        Intent i = new Intent(DayActivity.this,DayModifyActivity.class);
      //  i.putExtra("day",msg);
        startActivity(i);

    }




    DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    //사용자가 입력한 값을 가져온뒤

                    String year2 = year+"";
                    String month =(monthOfYear+1)+"";
                    String day =dayOfMonth+"";

                    if(month.length()==1){
                        month ="0"+month;
                    }

                    if(day.length()==1){
                        day ="0"+day;
                    }

                    String firstDay = year2+"-"+month+"-"+day;
                    daytext.setText(firstDay);

                    cal.set(Integer.parseInt(year2),Integer.parseInt(month)-1,Integer.parseInt(day));
                    Map map = new HashMap();
                    map.put("date",getDay());
                    map.put("id",userId);
                    HttpConnector httpcon = new HttpConnector();

                    httpcon.accessServerMap("dayselect",map,mCallback);

                    //텍스트뷰의 값을 업데이트함


                }

            };

//일일계획 삭제 콜백
    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(DayActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(DayActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    refreshUI();
                    Toast.makeText(DayActivity.this,"Success", Toast.LENGTH_SHORT).show();


                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(DayActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    Callback2 mCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(DayActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(DayActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson",msg);
                if(jsonbody.getString("result").equals("success")){


                    JSONObject datajson = jsonbody.getJSONObject("data");
                   // Toast.makeText(DayActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
                    createDayItem(datajson);
                    showData();
                    state = "update";

                }
                else if(jsonbody.getString("result").equals("fail")){

                    mdayItems.initData();
                    showData();
                    state = "insert";
                    Toast.makeText(DayActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    Callback2 mCallback3 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(DayActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(DayActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson3",msg);
                if(jsonbody.getString("result").equals("success")){

                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    ArrayList<String> spinList = new ArrayList<>();
                    Log.v("listsize",size+"");
                  //  Map map = new HashMap();
                  //  Map map2 = new HashMap();

                    for(int i=0;i<size;i++){

                        spinList.add(datas.getJSONObject(i).getString("content"));

                    }
                 //   mdayItems.setSpinnerMap2(map2);
                    mdayItems.setSpinnerItem(spinList);
                   // mdayItems.setSpinnerMap(map);

                }
                else if(jsonbody.getString("result").equals("fail")){

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dayplan);
       getSupportActionBar().setTitle("일일계획");
        mFlip = (ViewFlipper)findViewById(R.id.dayflip);
//        toolbar = (Toolbar) findViewById(R.id.toolbar2);
//        toolbar.setTitle("Plan");
//        setSupportActionBar(toolbar);
        dayGoalsale = (TextView) findViewById(R.id.dayGoalsale);
      //  dayContent = (TextView) findViewById(R.id.daycontent);
        dayMemo = (Editor)findViewById(R.id.daycontent2);
        dayChallenge = (TextView) findViewById(R.id.daychallenge);
        visitPoint = (TextView)findViewById(R.id.visitPoint);
        distance =(TextView) findViewById(R.id.distance);
        mdayItems = DayItems.get();



        dayLayout = (LinearLayout)findViewById(R.id.daylinear);
         cal = new GregorianCalendar();
        daytext = (TextView)findViewById(R.id.daytext);
        daytext.setText(getDay());
        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);
        User user = User.get();

        userId = user.getId();
        Map map = new HashMap();
        map.put("id",user.getId());
        Log.v("dayday",getDay());
        map.put("date",getDay());


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("dayselect",map,mCallback);
        Map map2 = new HashMap();
        map2.put("dept",user.getDept());
        httpcon.accessServerMap("challenge",map2,mCallback3);
        dayLayout.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startDayActivity("mon");
                return true;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_day, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Map map = new HashMap();
        map.put("id",userId);
        HttpConnector httpcon = new HttpConnector();



            if(id == R.id.send){


                map.put("date",getDay());
                map.put("title",mdayItems.getTitle());
                map.put("content",mdayItems.getContent());
                map.put("goal_sale",mdayItems.getGoalsale());
                map.put("challenge_content",mdayItems.getChallenge());
                map.put("estimate_distance",mdayItems.getShortDistance());
                map.put("estimate_course",mdayItems.getVisitPoint());
                map.put("dept",User.get().getDept());
            //    map.put("challenge_no","3");


                if(state.equals("insert")){
                    Log.v("insert",state);


                    httpcon.accessServerMap("dayinsert",map,mCallback2);
                }
                else if(state.equals("update")){
                    Log.v("update",state);
                    httpcon.accessServerMap("dayupdate",map,mCallback2);
                }

            return true;


        }
        else if (id == R.id.previous) {
            cal.add(cal.DAY_OF_MONTH,-1);
            daytext.setText(getDay());
            Log.v("preprepre",getDay());
            map.put("date",getDay());
            httpcon.accessServerMap("dayselect",map,mCallback);
                mFlip.showPrevious();


                  mFlip.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.viewdown));
                  mFlip.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.viewup));

              //  mFlip.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.viewin));
              //  mFlip.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.viewout));
            return true;
        }

        else if (id == R.id.next) {
            cal.add(cal.DAY_OF_MONTH,+1);
            daytext.setText(getDay());
            map.put("date",getDay());
            httpcon.accessServerMap("dayselect",map,mCallback);
                mFlip.showNext();
             //   mFlip.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.viewout));
               // mFlip.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.viewin));
                mFlip.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.viewdown));
                mFlip.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.viewup));

            return true;
        }
        else if(id == R.id.daysearch){

            new DatePickerDialog(DayActivity.this, mDateSetListener, mYear,

                    mMonth, mDay).show();


            return true;
        }
        else if(id == R.id.daydel){

                final LinearLayout linear = (LinearLayout)
                        View.inflate(DayActivity.this, R.layout.dialog_daydel, null);

                new AlertDialog.Builder(this)
                        .setTitle("정말 삭제하시겠습니까?")
                        .setView(linear)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Map map = new HashMap();
                                map.put("id",userId);
                                map.put("date",getDay());
                                HttpConnector httpcon = new HttpConnector();
                                httpcon.accessServerMap("daydelete",map,mCallback2);


                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        })
                        .show();

                return true;
            }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {

        showData();
        super.onResume();
    }
}
