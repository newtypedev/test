package com.jx372.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Dell on 2017-08-24.
 */

public class WeekPlanActivity extends AppCompatActivity {

    WeekItems mWeekItems;
    ArrayList<String> Items;
    ArrayAdapter<String> Adapter;
    ArrayAdapter<String> Adapter2;
    ArrayAdapter<String> Adapter3;
    ArrayAdapter<String> Adapter4;
    ArrayAdapter<String> Adapter5;
    ListView list;
    ListView list2;
    ListView list3;
    ListView list4;
    ListView list5;
    ScrollView mScrollView ;
    TextView monsale;
    TextView tuesale;
    TextView wedsale;
    TextView thusale;
    TextView frisale;
    TextView montext;
    TextView tuetext;
    TextView wedtext;
    TextView thutext;
    TextView fritext;
    TextView weektext;
    TextView targettext;
    TextView salestext;
    TextView achivetext;
    Calendar firstdayofweek;
    Calendar daytemp;
    String state="";
    String userId="";
    int nowDayofweek=0;
    int nowWeek=0;
    Retrofit retrofit;
    ApiService apiService;


    public void preventModify(){


        if(nowWeek == getFirstDay2()){



            if(nowDayofweek == 1){
                montext.setEnabled(true);
                tuetext.setEnabled(true);
                wedtext.setEnabled(true);
                thutext.setEnabled(true);
                fritext.setEnabled(true);
            }

            else if(nowDayofweek== 2){
                montext.setEnabled(true);
                tuetext.setEnabled(true);
                wedtext.setEnabled(true);
                thutext.setEnabled(true);
                fritext.setEnabled(true);
                //Monday
            }
            else if(nowDayofweek== 3){
                montext.setEnabled(false);
                tuetext.setEnabled(true);
                wedtext.setEnabled(true);
                thutext.setEnabled(true);
                fritext.setEnabled(true);

            }
            else if(nowDayofweek== 4){
                montext.setEnabled(false);
                tuetext.setEnabled(false);
                wedtext.setEnabled(true);
                thutext.setEnabled(true);
                fritext.setEnabled(true);

            }
            else if(nowDayofweek== 5){
                montext.setEnabled(false);
                tuetext.setEnabled(false);
                wedtext.setEnabled(false);
                thutext.setEnabled(true);
                fritext.setEnabled(true);

            }
            else if(nowDayofweek== 6){
                montext.setEnabled(false);
                tuetext.setEnabled(false);
                wedtext.setEnabled(false);
                thutext.setEnabled(false);
                fritext.setEnabled(true);

            }
            else if(nowDayofweek== 7){
                montext.setEnabled(false);
                tuetext.setEnabled(false);
                wedtext.setEnabled(false);
                thutext.setEnabled(false);
                fritext.setEnabled(false);

            }


        }



        else if(nowWeek > getFirstDay2()){

            //sendItem.setEnabled(false);
            montext.setEnabled(false);
            tuetext.setEnabled(false);
            wedtext.setEnabled(false);
            thutext.setEnabled(false);
            fritext.setEnabled(false);
        }



        else if(nowWeek < getFirstDay2()){

            montext.setEnabled(true);
            tuetext.setEnabled(true);
            wedtext.setEnabled(true);
            thutext.setEnabled(true);
            fritext.setEnabled(true);
        }



    }

    public String createComma(String num) {
        int value = Integer.parseInt(num);
        DecimalFormat Commas = new DecimalFormat("#,###");
        String result = (String)Commas.format(value);
        return result;
    }

    // 삭제 시 받는 콜백
    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(WeekPlanActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(WeekPlanActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    Toast.makeText(WeekPlanActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(WeekPlanActivity.this,"Fail", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(WeekPlanActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(WeekPlanActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("weekjson",msg);
                if(jsonbody.getString("result").equals("success")){



                    JSONObject datajson = jsonbody.getJSONObject("data");

//                    mWeekItems.setFirstDate(datajson.getString("first_date"));
//                    weektext.setText(mWeekItems.getFirstDate());
//                    String tempDay = mWeekItems.getFirstDate().substring(8,10);
//                    int day = Integer.parseInt(tempDay);
                    weektext.setText(getFirstDay());
                    createDay(firstdayofweek.get(firstdayofweek.DAY_OF_MONTH));
                    createDayItem(datajson);
                    showFigure();
                    showDayItem();
                    state = "update";

                    String date = datajson.getString("week_no");
                    String date2 = date.charAt(0)+"월 "+date.charAt(2)+"주차";
                    String weekdata ="";
                    if(date.length() ==7){
                        Log.v("leng7777","ok");
                        date2 = date.charAt(4)+"월 "+date.charAt(6)+"주차";
                        weekdata = date2+" ("+firstdayofweek.get(Calendar.YEAR)+")";
                    }
                    else if(date.length()==8){
                        Log.v("leng8888","ok");

                        String temp = date.charAt(4)+"";

                        if(temp.equals("0")){

                            date2 = date.charAt(5) + "월 " + date.charAt(7) + "주차";
                        }
                        else {
                            date2 = date.charAt(4) + "" + date.charAt(5) + "월 " + date.charAt(7) + "주차";
                        }
                            weekdata = date2+" ("+firstdayofweek.get(Calendar.YEAR)+")";
                    }

                    weektext.setText(weekdata);

                    Toast.makeText(WeekPlanActivity.this,date2, Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){



                    JSONObject datajson = jsonbody.getJSONObject("data");
                    mWeekItems.setFirstDate(datajson.getString("first_date"));
                    weektext.setText(getFirstDay());
                    createDay(firstdayofweek.get(firstdayofweek.DAY_OF_MONTH));
                    mWeekItems.initData();
                    showFigure();
                    showDayItem();
                    state = "insert";
                    Toast.makeText(WeekPlanActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    public void addTargetSale(){

        int temp =Integer.parseInt(mWeekItems.getMonSales())+Integer.parseInt(mWeekItems.getTueSales())+
                Integer.parseInt(mWeekItems.getWedSales())+Integer.parseInt(mWeekItems.getThuSales())+
                Integer.parseInt(mWeekItems.getFriSales());
        mWeekItems.setTargetFigure(temp+"");
    }

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

    public int getFirstDay2(){
        String year = firstdayofweek.get(firstdayofweek.YEAR)+"";
        String month = (firstdayofweek.get(firstdayofweek.MONTH)+1)+"";
        String day = firstdayofweek.get(firstdayofweek.DAY_OF_MONTH)+"";

        if(month.length()==1){
            month ="0"+month;
        }
        if(day.length()==1){
            day ="0"+day;
        }

        String firstDay = year+month+day;
        return Integer.parseInt(firstDay);
    }


    public void createFirstDay(int dayofweek){

        if(dayofweek== 1){
            firstdayofweek.add ( firstdayofweek.DAY_OF_MONTH,+1);
        }
        else if(dayofweek== 2){
            //Monday
        }
        else if(dayofweek== 3){
            firstdayofweek.add ( firstdayofweek.DAY_OF_MONTH,-1);
        }
        else if(dayofweek== 4){
            firstdayofweek.add ( firstdayofweek.DAY_OF_MONTH,-2);
        }
        else if(dayofweek== 5){
            firstdayofweek.add ( firstdayofweek.DAY_OF_MONTH,-3);
        }
        else if(dayofweek== 6){
            firstdayofweek.add ( firstdayofweek.DAY_OF_MONTH,-4);
        }
        else if(dayofweek== 7){
            firstdayofweek.add ( firstdayofweek.DAY_OF_MONTH,-5);
        }

    }

    public void createDayItem(JSONObject datajson) throws JSONException{

        mWeekItems.setMonItems( splitString(datajson.getString("monday")));
        mWeekItems.setTueItems( splitString(datajson.getString("tuesday")));
        mWeekItems.setWedItems( splitString(datajson.getString("wednesday")));
        mWeekItems.setThuItems( splitString(datajson.getString("thursday")));
        mWeekItems.setFriItems( splitString(datajson.getString("friday")));
        mWeekItems.setMonSales(datajson.getString("monday_money"));
        mWeekItems.setTueSales(datajson.getString("tuesday_money"));
        mWeekItems.setWedSales(datajson.getString("wednesday_money"));
        mWeekItems.setThuSales(datajson.getString("thursday_money"));
        mWeekItems.setFriSales(datajson.getString("friday_money"));

        mWeekItems.setWeekSale(datajson.getString("week_sale"));
        mWeekItems.setTargetFigure(datajson.getString("target_figure"));
        mWeekItems.setAchiveRank(datajson.getString("achive_rank"));

    }
    public void showFigure() {
        monsale.setText(createComma(mWeekItems.getMonSales()));
        tuesale.setText(createComma(mWeekItems.getTueSales()));
        wedsale.setText(createComma(mWeekItems.getWedSales()));
        thusale.setText(createComma(mWeekItems.getThuSales()));
        frisale.setText(createComma(mWeekItems.getFriSales()));

        targettext.setText(createComma(mWeekItems.getTargetFigure()));
        salestext.setText(createComma(mWeekItems.getWeekSale()));
        achivetext.setText(mWeekItems.getAchiveRank());
    }
    public void createDay(int day){

        daytemp = (Calendar) firstdayofweek.clone();
        String dayset =daytemp.get(daytemp.DAY_OF_MONTH)+"\n"+"MON";
        montext.setText(dayset);

        daytemp.add(daytemp.DATE,+1);
        dayset =    daytemp.get(daytemp.DAY_OF_MONTH)+"\n"+"TUE";
        tuetext.setText(dayset);

        daytemp.add(daytemp.DATE,+1);
        dayset =    daytemp.get(daytemp.DAY_OF_MONTH)+"\n"+"WED";
        wedtext.setText(dayset);

        daytemp.add(daytemp.DATE,+1);
        dayset =    daytemp.get(daytemp.DAY_OF_MONTH)+"\n"+"THU";
        thutext.setText(dayset);

        daytemp.add(daytemp.DATE,+1);
        dayset =    daytemp.get(daytemp.DAY_OF_MONTH)+"\n"+"FRI";
        fritext.setText(dayset);


    }

    public void showDayItem(){
        Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mWeekItems.getMonItems());
        Adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mWeekItems.getTueItems());
        Adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mWeekItems.getWedItems());
        Adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mWeekItems.getThuItems());
        Adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mWeekItems.getFriItems());

        list.setAdapter(Adapter);
        list2.setAdapter(Adapter2);
        list3.setAdapter(Adapter3);
        list4.setAdapter(Adapter4);
        list5.setAdapter(Adapter5);
    }

    public ArrayList splitString(String data){

        ArrayList<String> ar = new ArrayList<>();

        if(data.equals("")){

        }

        else {


           String arr[];
           arr = data.split("\n");

           for (int i = 0; i < arr.length; i++) {
               ar.add(arr[i]);
           }
       }
        return ar;

    }


    public String combineItem(ArrayList item){


        String merge = "";
        if(item.size()==0){
            merge = "";
        }
        else {
            for (int i = 0; i < item.size(); i++) {
                merge = merge + item.get(i) + "\n";
            }
        }
        return merge;

    }

    public void startWeekActivity(String msg){
        Intent i = new Intent(WeekPlanActivity.this,WeekModifyActivity.class);
        i.putExtra("day",msg);
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_week, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Map map = new HashMap();
        map.put("id",userId);
        HttpConnector httpcon = new HttpConnector();
        if(id == R.id.send){
            addTargetSale();

//            map.put("first_date",getFirstDay());
//            map.put("title",getFirstDay());
//            map.put("target_figure","10000000");
//            map.put("monday","월요일 내용");
//            map.put("monday_money","100");
//            map.put("tuesday","화요일 내용");
//            map.put("tuesday_money","1200");
//            map.put("wednesday","수요일 내용");
//            map.put("wednesday_money","1300");
//            map.put("thursday","목요일 내용");
//            map.put("thursday_money","1400");
//            map.put("friday","금요일 내용");
//            map.put("friday_money","5100");




            Log.v("statetasteaetat",state);
            map.put("first_date",getFirstDay());
            map.put("title",getFirstDay());
            map.put("target_figure",mWeekItems.getTargetFigure());
            map.put("monday",combineItem(mWeekItems.getMonItems()));
            map.put("tuesday",combineItem(mWeekItems.getTueItems()));
            map.put("wednesday",combineItem(mWeekItems.getWedItems()));
            map.put("thursday",combineItem(mWeekItems.getThuItems()));
            map.put("friday",combineItem(mWeekItems.getFriItems()));

            map.put("monday_money",mWeekItems.getMonSales());
            map.put("tuesday_money",mWeekItems.getTueSales());
            map.put("wednesday_money",mWeekItems.getWedSales());
            map.put("thursday_money",mWeekItems.getThuSales());
            map.put("friday_money",mWeekItems.getFriSales());


            if(state.equals("insert")){
                Log.v("insert",state);


            httpcon.accessServerMap("weekinsert",map,mCallback2);
            }
            else if(state.equals("update")){
                Log.v("update",state);
                httpcon.accessServerMap("weekupdate",map,mCallback2);
            }
            return true;

        }
        else if (id == R.id.previous) {
            firstdayofweek.add(firstdayofweek.DAY_OF_MONTH,-7);
            map.put("first_date",getFirstDay());

            preventModify();

            httpcon.accessServerMap("weekselect",map,mCallback);
            return true;
        }
        else if (id == R.id.next) {


            //   httpcon.accessServerMap4(map,mCallback2);
            firstdayofweek.add(firstdayofweek.DAY_OF_MONTH,+7);
            map.put("first_date",getFirstDay());
            preventModify();


            httpcon.accessServerMap("weekselect",map,mCallback);
            return true;
        }
        else if(id == R.id.del){


            final LinearLayout linear = (LinearLayout)
                    View.inflate(WeekPlanActivity.this, R.layout.dialog_weekdel, null);

            new AlertDialog.Builder(WeekPlanActivity.this)
                    .setTitle("정말 삭제하시겠습니까?")
                    .setView(linear)
                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Map map = new HashMap();
                            map.put("id",userId);
                            map.put("first_date",getFirstDay());
                            HttpConnector httpcon = new HttpConnector();
                            httpcon.accessServerMap("weekdelete",map,mCallback2);


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekplan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("주간계획");
        User user = User.get();
        userId = user.getId();
//        Log.v("useruser",userId);
        mWeekItems = WeekItems.get();
        firstdayofweek = Calendar.getInstance();
        nowDayofweek = firstdayofweek.get(firstdayofweek.DAY_OF_WEEK);
        createFirstDay(firstdayofweek.get(firstdayofweek.DAY_OF_WEEK));
        nowWeek = getFirstDay2();

//        Items = new ArrayList<String>();
//        Items.add("본사출근");
//        Items.add("동작슈퍼");
//        Items.add("신갈대리점");
//        Items.add("신규거래처상담");
//        Items.add("거래선방문");

        list = (ListView)findViewById(R.id.list);
        list2 = (ListView)findViewById(R.id.list2);
        list3 = (ListView)findViewById(R.id.list3);
        list4 = (ListView)findViewById(R.id.list4);
        list5 = (ListView)findViewById(R.id.list5);
        list.setDivider(new ColorDrawable(Color.BLACK));
        list.setDividerHeight(1);

        montext = (TextView) findViewById(R.id.montext);
        tuetext = (TextView) findViewById(R.id.tuetext);
        wedtext = (TextView) findViewById(R.id.wedtext);
        thutext = (TextView) findViewById(R.id.thutext);
        fritext = (TextView) findViewById(R.id.fritext);
        weektext = (TextView) findViewById(R.id.weektext);
        targettext = (TextView)findViewById(R.id.targettext);
        salestext = (TextView) findViewById(R.id.salestext);
        achivetext = (TextView)findViewById(R.id.achievetext);

        monsale = (TextView) findViewById(R.id.monsale);
        tuesale = (TextView) findViewById(R.id.tuesale);
        wedsale = (TextView) findViewById(R.id.wedsale);
        thusale = (TextView) findViewById(R.id.thusale);
        frisale = (TextView) findViewById(R.id.frisale);
       // String firstDay = firstdayofweek.get(firstdayofweek.YEAR)+"-"+(firstdayofweek.get(firstdayofweek.MONTH)+1)+"-"+firstdayofweek.get(firstdayofweek.DAY_OF_MONTH);
        Map map = new HashMap();
        map.put("id",userId);
        map.put("first_date",getFirstDay());

        preventModify();
        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("weekselect",map,mCallback);

       // montext.setEnabled(false);


      /*  retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);


        Map map = new HashMap();
        map.put("id","test01");

        Call<ResponseBody> comment2 = apiService.getPost(map);
        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.v("succes","던짐");

                   String json =  response.body().string();
                    JSONObject jsonbody = new JSONObject(json);
                    JSONObject datajson = jsonbody.getJSONObject("data");
                    Log.v("getsitrng",jsonbody.getString("result"));


                    mDayItems.setFirstDate(datajson.getString("first_date"));
                    weektext.setText(mDayItems.getFirstDate());
                    String tempDay = mDayItems.getFirstDate().substring(8,10);
                    int day = Integer.parseInt(tempDay);
                    createDay(day);
                    createDayItem(datajson);
                    showFigure();
                    showDayItem();

                    Log.v("datedatedate",tempDay);
                    Log.v("loginjson",json);

//                    if(!response.body().string().equals("")){
//                        String json =  response.body().string();
//                        Log.v("succes",json);
//
//                    }
                    return;


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"IOException!!",Toast.LENGTH_SHORT).show();
                }catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"JSONException!!",Toast.LENGTH_SHORT).show();
                    }
                    catch(NullPointerException e){
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"JSONException!!",Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this,"서버와 연결이 되지 않습니다",Toast.LENGTH_SHORT).show();

            }
        });


*/



        //   if(mDayItems.getThuItems() != null){  Log.v("sibalAdapter", mDayItems.getTueItems().size()+"");}
//
//        Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDayItems.getMonItems());
//        Adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDayItems.getTueItems());
//        Adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDayItems.getWedItems());
//        Adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDayItems.getThuItems());
//        Adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mDayItems.getFriItems());
//
//        list.setAdapter(Adapter);
//        list2.setAdapter(Adapter2);
//        list3.setAdapter(Adapter3);
//        list4.setAdapter(Adapter4);
//        list5.setAdapter(Adapter5);

        mScrollView = (ScrollView) findViewById(R.id.ScrollView1);

        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });
        list2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });
        list3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });

        list4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });

        list5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }

        });



        montext.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startWeekActivity("mon");
                return true;
            }
        });


        tuetext.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startWeekActivity("tue");
                return true;
            }
        });

        wedtext.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startWeekActivity("wed");
                return true;
            }
        });

        thutext.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startWeekActivity("thu");
                return true;
            }
        });

        fritext.setOnLongClickListener(new TextView.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                startWeekActivity("fri");
                return true;
            }
        });

//        salestext.setOnLongClickListener(new TextView.OnLongClickListener(){
//            @Override
//            public boolean onLongClick(View v) {
//                final LinearLayout linear = (LinearLayout)
//                        View.inflate(WeekPlanActivity.this, R.layout.dialog_weeksale, null);
//
//                new AlertDialog.Builder(WeekPlanActivity.this)
//                        .setTitle("목표액 수정")
//                        .setView(linear)
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//                                EditText product = (EditText)linear.findViewById(R.id.saletext);
//                                targettext.setText(product.getText());
//                                mDayItems.setTargetFigure(product.getText()+"");
//
//                            }
//                        })
//                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                            }
//                        })
//                        .show();
//                return true;
//            }
//        });
//



//        list2.setOnLongClickListener(new );
//        list2.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,WeekActivity.class);
//                startActivity(i);
//            }
//        });



    }



    @Override
    protected void onResume() {
        // Log.v("sisisisi",mDayItems.getMonSales()+"");
//        monsale.setText(mDayItems.getMonSales());
//        tuesale.setText(mDayItems.getTueSales());
//        wedsale.setText(mDayItems.getWedSales());
//        thusale.setText(mDayItems.getThuSales());
//        frisale.setText(mDayItems.getFriSales());

        addTargetSale();
        showFigure();
       // targettext.setText(createComma(mDayItems.getTargetFigure()));
        showDayItem();
        Log.v("rerereresume","start");
        super.onResume();


    }


}
