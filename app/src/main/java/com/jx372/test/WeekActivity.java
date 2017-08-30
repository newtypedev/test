package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

/**
 * Created by Dell on 2017-08-16.
 */

public class WeekActivity extends AppCompatActivity {

    Retrofit retrofit;
    ApiService apiService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("주간계획등록");




        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);

        findViewById(R.id.weeksub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map map = new HashMap();
                map.put("first_date","2017-08-17");
                map.put("title","타이틀제목변경");
                map.put("target_figure","10000000");
                map.put("id","test01");
                map.put("monday","월요일 내용");
                map.put("monday_money","100");
                map.put("tuesday","화요일 내용");
                map.put("tuesday_money","1200");
                map.put("wednesday","수요일 내용");
                map.put("wednesday_money","1300");
                map.put("thursday","목요일 내용");
                map.put("thursday_money","1400");
                map.put("friday","금요일 내용");
                map.put("friday_money","5100");

                Call<ResponseBody> comment2 = apiService.getPostWeekSubmit(map);
                comment2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.v("succes","던짐");

                            if(!response.body().string().equals("")){
                           String json =  response.body().string();}
                            return;
                         //   Log.v("weekjson",json);
                           // JSONObject jsonbody = new JSONObject(json);


//                            if(jsonbody.getString("result").equals("success")){
//                                Intent i = new Intent(WeekActivity.this,MainActivity.class);
//                                startActivity(i);
//
//                            }
//                            else{
//                                messageResId2 = R.string.message_re;
//                                Toast.makeText(WeekActivity.this,messageResId2,Toast.LENGTH_SHORT).show();
//                            }


//                            String json =  response.body().string();
//                            Log.v("Test", json);
//                            JSONObject jsonbody = new JSONObject(json);
//                            JSONObject userjson = jsonbody.getJSONObject("data");
//
//                            Log.v("jsontest---", userjson.getString("dept"));


                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(WeekActivity.this,"IOException!!",Toast.LENGTH_SHORT).show();
                        }
                        catch(NullPointerException e){
                            e.printStackTrace();
                            Toast.makeText(WeekActivity.this,"JsonException!!",Toast.LENGTH_SHORT).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(WeekActivity.this,"서버와 연결이 되지 않습니다",Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });



    }
}