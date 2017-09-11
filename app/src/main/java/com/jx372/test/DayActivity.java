package com.jx372.test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DayActivity extends AppCompatActivity {

    DayItems mdayItems;
    Calendar cal;
    int mYear, mMonth, mDay;
    TextView daytext;
    LinearLayout dayLayout;
    TextView dayContent;
    TextView dayGoalsale;
    TextView dayChallenge;
    String state="";
    private Toolbar toolbar;

    public void showData() {
        dayContent.setText(mdayItems.getContent());
        dayGoalsale.setText(mdayItems.getGoalsale()+"");
        dayChallenge.setText(mdayItems.getChallenge());
    }

    public void createDayItem(JSONObject datajson) throws JSONException{

        mdayItems.setContent(datajson.getString("content"));
        mdayItems.setGoalsale(datajson.getString("goal_sale"));
        mdayItems.setGoalsale(datajson.getString("opinion"));

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
                    map.put("id","test01");
                    HttpConnector httpcon = new HttpConnector();

                    httpcon.accessServerMap("dayselect",map,mCallback);

                    //텍스트뷰의 값을 업데이트함


                }

            };


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
                    Toast.makeText(DayActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();
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

//        toolbar = (Toolbar) findViewById(R.id.toolbar2);
//        toolbar.setTitle("Plan");
//        setSupportActionBar(toolbar);
        dayGoalsale = (TextView) findViewById(R.id.dayGoalsale);
        dayContent = (TextView) findViewById(R.id.daycontent);
        dayChallenge = (TextView) findViewById(R.id.daychallenge);
        mdayItems = DayItems.get();
        dayLayout = (LinearLayout)findViewById(R.id.daylinear);
         cal = new GregorianCalendar();
        daytext = (TextView)findViewById(R.id.daytext);
        daytext.setText(getDay());
        mYear = cal.get(Calendar.YEAR);

        mMonth = cal.get(Calendar.MONTH);

        mDay = cal.get(Calendar.DAY_OF_MONTH);

        Map map = new HashMap();
        map.put("id","test01");
        Log.v("dayday",getDay());
        map.put("date",getDay());


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("dayselect",map,mCallback);

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
        map.put("id","test01");
        HttpConnector httpcon = new HttpConnector();



            if(id == R.id.send){

                Log.v("statetasteaetat",state);
                map.put("date",getDay());
                map.put("title","1234");
                map.put("opinion",mdayItems.getChallenge());
                map.put("content",mdayItems.getContent());
                map.put("goal_sale",mdayItems.getGoalsale());


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
            return true;
        }

        else if (id == R.id.next) {
            cal.add(cal.DAY_OF_MONTH,+1);
            daytext.setText(getDay());
            map.put("date",getDay());
            httpcon.accessServerMap("dayselect",map,mCallback);
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
                                map.put("id","test01");
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
