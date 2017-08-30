package com.jx372.test;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adspin;
    ArrayAdapter<CharSequence> adspin2;
    EditText textId;
    boolean mInitSpinner;
    Retrofit retrofit;
    ApiService apiService;
    String dept="";
    String grade="";
    String jsonTest="";
    public class HttpMessage{
         String temp;
        public void HttpMessage(String msg){
            this.temp = msg;
        }
        public String getTemp(){
            return temp;
        }
    }

Callback2 mCallback = new Callback2() {
    @Override
    public void callback(String msg) {
        if(msg.equals("JsonException")){
            Toast.makeText(JoinActivity.this,msg, Toast.LENGTH_SHORT).show();
            return;
        }

        if(msg.equals("ConnectFail")){
            Toast.makeText(JoinActivity.this,msg, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject jsonbody = new JSONObject(msg);
            if(jsonbody.getString("result").equals("success")){
                textId.setFocusable(false);
                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();
            }
            else if(jsonbody.getString("result").equals("fail")){
                Toast.makeText(JoinActivity.this,"이미 존재하는 ID 입니다", Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
};

    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {

            if(msg.equals("JsonException")){
                Toast.makeText(JoinActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(JoinActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){
                    textId.setFocusable(false);
                    Toast.makeText(JoinActivity.this,"등록 성공", Toast.LENGTH_SHORT).show();
                }
                else if(jsonbody.getString("result").equals("fail")){
                    Toast.makeText(JoinActivity.this,"등록 실패", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        setContentView( R.layout.activity_join );
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("팀원등록");


        textId = (EditText) findViewById(R.id.joinId);
        final TextView checkView = (TextView)findViewById(R.id.joinCheck);
        findViewById(R.id.joinCheck).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

//
//                retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
//                apiService = retrofit.create(ApiService.class);
//
//                Call<ResponseBody> comment2 = apiService.getPostIdStr(textId.getText().toString());
//                comment2.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        try {
//                            String json =  response.body().string();
//                            JSONObject jsonbody = new JSONObject(json);
//
//
//                            if(jsonbody.getString("result").equals("success")){
//                                checkView.setVisibility(View.INVISIBLE);
//                        textId.setFocusable(false);
//
//                        Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        Toast.makeText(JoinActivity.this,"이미 존재하는 ID 입니다", Toast.LENGTH_SHORT).show();
//
//                    }
//
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                            Toast.makeText(JoinActivity.this,"IOException!!",Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Toast.makeText(JoinActivity.this,"JSONException!!",Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(JoinActivity.this,"서버와 연결이 되지 않습니다",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//


                Map map = new HashMap();
                map.put("id",textId.getText().toString());




                HttpConnector httpcon = new HttpConnector();
                httpcon.accessServerMap("check",map,mCallback);




               // editText.setFocusable(false);

               // editText.setClickable(false);




               // textId.setClickable(false);



            }
        });





        Spinner spin = (Spinner)findViewById(R.id.myspinner);

        spin.setPrompt("소속");


        adspin = ArrayAdapter.createFromResource(this, R.array.fruits,
                R.layout.spinner_item);

        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adspin);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                dept = adspin.getItem(position)+"";
				/* 초기화시의 선택 제외시
				if (mInitSpinner == false) {
					mInitSpinner = true;
					return;
				}
				//*/
              //  Toast.makeText(JoinActivity.this,adspin.getItem(position) + "는 맛있다.", Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Spinner spin2 = (Spinner)findViewById(R.id.myspinner2);
        spin2.setPrompt("직급");

        adspin2 = ArrayAdapter.createFromResource(this, R.array.level,
                R.layout.spinner_item);
        adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(adspin2);

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                grade = adspin2.getItem(position)+"";
				/* 초기화시의 선택 제외시
				if (mInitSpinner == false) {
					mInitSpinner = true;
					return;
				}
				//*/
                //  Toast.makeText(JoinActivity.this,adspin.getItem(position) + "는 맛있다.", Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        findViewById(R.id.joinSubmit).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String id =((EditText) findViewById(R.id.joinId)).getText().toString();
               String passwd = ((EditText) findViewById(R.id.joinPasswd)).getText().toString();
                String name =((EditText) findViewById(R.id.joinName)).getText().toString();
                Map map = new HashMap();
                map.put("id",id);
                map.put("passwd",passwd);
                map.put("name",name);
                map.put("dept",dept);
                map.put("grade",grade);

                HttpConnector httpcon = new HttpConnector();
                String result = httpcon.accessServerMap("join",map,mCallback2);

            }
        });



    }
//    @Override
//    public boolean onOptionsItemSelected(android.view.MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }





}
