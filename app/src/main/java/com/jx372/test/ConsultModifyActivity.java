package com.jx372.test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by pys on 2017. 9. 27..
 */

public class ConsultModifyActivity extends AppCompatActivity {
    String tempContent="";
    TextView customerName;
    TextView customerCode;
    TextView chiefName;
    TextView customerAddress;
    TextView consultTitle;
    Editor consultmemo;
    private CharSequence customerNameList[];
    private ArrayList<Customer> customerList;
    public static final String CONSULT_ID="";
    private  UUID consultId;
    private String state="";
    private Consult consult;



// 고객 리스트 콜백
    Callback2 mCallback5 = new Callback2() {
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
                    customerList = new ArrayList<>();

                    for (int i = 0; i < size; i++) {

                    Customer c = new Customer(datas.getJSONObject(i).getString("code"),datas.getJSONObject(i).getString("name"));
                        customerList.add(c);
                        customerNameList[i] = datas.getJSONObject(i).getString("name");

                    }


                } else if (jsonbody.getString("result").equals("fail")) {
//
//                    mdayItems.initData();
//                    showData();
//                    state = "insert";
                    //Toast.makeText(TmapActivity.this, "데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    // 상담일지 삭제 콜백
    Callback2 mCallback4 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    // updateData(jsonbody.getString("data"));
                    refreshUI();
                    Toast.makeText(ConsultModifyActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(ConsultModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
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
    Callback2 mCallback3 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
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
                                datas.getJSONObject(i).getString("code"),
                                datas.getJSONObject(i).getString("name")
                        );
                        ConsultList.get(ConsultModifyActivity.this).addConsult(c);
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

//상담일지 추가 콜백
    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
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




                    Toast.makeText(ConsultModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
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

        ConsultList.get(this).cleanList();
        Map map = new HashMap();
        map.put("id", User.get().getId());
        Log.v("dayday",  User.get().getTempDate());
        map.put("date", User.get().getTempDate());


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("consultselect", map, mCallback3);
    }

    private void setUpEditor() {
        findViewById(R.id.action_h1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultmemo.UpdateTextStyle(EditorTextStyle.H1);
            }
        });

        findViewById(R.id.action_h2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultmemo.UpdateTextStyle(EditorTextStyle.H2);
            }
        });

        findViewById(R.id.action_h3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultmemo.UpdateTextStyle(EditorTextStyle.H3);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultmemo.UpdateTextStyle(EditorTextStyle.BOLD);
            }
        });

        findViewById(R.id.action_Italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultmemo.UpdateTextStyle(EditorTextStyle.ITALIC);
            }
        });


        findViewById(R.id.action_erase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultmemo.clearAllContents();
            }
        });
        //editor.dividerBackground=R.drawable.divider_background_dark;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consult_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        HttpConnector httpcon = new HttpConnector();


        if (item.getItemId() == R.id.finish) {

            if(state.equals("insert")) {
                Editor editor = new Editor(this, null);
                String b = consultmemo.getContentAsSerialized();
                Log.v("jsonjson", b);
                b = editor.getContentAsHTML(b);
                b = b.replaceAll("<u>", "");
                b = b.replaceAll("</u>", "");
                tempContent = b;
                Log.v("underunderlin", b);


                Map map = new HashMap();
                map.put("id", User.get().getId());
                map.put("title", "상담일지");
                map.put("date", User.get().getTempDate());
                map.put("content", b);
                map.put("manager_name", "최경호");
                map.put("code", "A_001");
                map.put("name", "동작대리점");
                httpcon.accessServerMap("consultinsert", map, mCallback2);
                //itemUpdate();
                //onBackPressed();
                return true;
            }

            else if(state.equals("update")) {
                Editor editor = new Editor(this, null);
                String b = consultmemo.getContentAsSerialized();
                Log.v("jsonjson", b);
                b = editor.getContentAsHTML(b);
                b = b.replaceAll("<u>", "");
                b = b.replaceAll("</u>", "");
                tempContent = b;
                Log.v("underunderlin", b);


                Map map = new HashMap();
                map.put("advice_no",consult.getNo());
                map.put("id", User.get().getId());
                map.put("date", User.get().getTempDate());
                map.put("title", "업무보고");
                map.put("content", b);
                map.put("manager_name", "최경호");
                map.put("code", "A_001");
                map.put("name", "동작대리점");
                httpcon.accessServerMap("consultupdate", map, mCallback2);
                //itemUpdate();
                //onBackPressed();
                return true;
            }




        }

        else if (item.getItemId() == R.id.delete){

            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date", User.get().getTempDate());
            map.put("advice_no",consult.getNo());
            httpcon.accessServerMap("consultdelete", map, mCallback4);
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context packageContext, UUID consultId) {

        Intent intent = new Intent(packageContext, ConsultModifyActivity.class);
        intent.putExtra(CONSULT_ID, consultId);
        return intent;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_modify);
        getSupportActionBar().setTitle("상담일지");
        consultmemo = (Editor)findViewById(R.id.consultmemo);
        customerName= (TextView)findViewById(R.id.customerName);
        customerCode= (TextView)findViewById(R.id.customerCode);
        chiefName= (TextView)findViewById(R.id.chiefName);
        customerAddress= (TextView)findViewById(R.id.customerAddress);
        consultTitle= (TextView)findViewById(R.id.consultTitle);
        HttpConnector httpcon = new HttpConnector();
        Map map = new HashMap();
        map.put("id", User.get().getId());
        httpcon.accessServerMap("customerselect", map, mCallback5);


        findViewById(R.id.namesearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //CharSequence info[] = new CharSequence[] {"내정보", "로그아웃" };


                AlertDialog.Builder builder = new AlertDialog.Builder(ConsultModifyActivity.this);

                builder.setTitle("제목");

                builder.setItems(customerNameList, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        switch(which)

                        {

                            case 0:

                                // 내정보

                                Toast.makeText(ConsultModifyActivity.this, "내정보", Toast.LENGTH_SHORT).show();

                                break;

                            case 1:

                                // 로그아웃

                                Toast.makeText(ConsultModifyActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();

                                break;

                        }

                        dialog.dismiss();

                    }

                });

                builder.show();

            }
        });

        setUpEditor();
        consultId = (UUID)getIntent().getSerializableExtra(ConsultModifyActivity.CONSULT_ID);
        if(!(consultId==null)) {
            state = "update";
            Log.v("크라임", consultId + "");
            consult = ConsultList.get(this).getConsults(consultId);
            consultmemo.Render(consult.getContent());

            Log.v("크라임", consult.getContent() + "");
        }
        else{
            state = "insert";
            consultmemo.Render();
        }

    }

    public void btnSearch(View view) {
    }
}
