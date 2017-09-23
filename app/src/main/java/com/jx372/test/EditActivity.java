package com.jx372.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.irshulx.Editor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pys on 2017. 9. 19..
 */

public class EditActivity extends Activity{

    Callback2 mCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(EditActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(EditActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson",msg);
                if(jsonbody.getString("result").equals("success")){


                    JSONObject datajson = jsonbody.getJSONObject("data");

                    Toast.makeText(EditActivity.this,"데이터 있음", Toast.LENGTH_SHORT).show();


                }
                else if(jsonbody.getString("result").equals("fail")){


                    Toast.makeText(EditActivity.this,"데이터가 없습니다", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(EditActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(EditActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    Toast.makeText(EditActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(EditActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    Editor a;
    Editor b;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edi);
        a = (Editor) findViewById(R.id.editor);
        b = (Editor) findViewById(R.id.renderer);
        tv = (TextView) findViewById(R.id.tv);
    }



    public void send(View view) {

        HttpConnector httpcon = new HttpConnector();
        Map map = new HashMap();
        Editor editor=new Editor(this ,null);
        String st= editor.getContentAsHTML(a.getContent());

        map.put("goal_sale","1000");
        map.put("date","2017-09-19");
        map.put("title","abcde");
        map.put("opinion","1");
        map.put("content",st);
        httpcon.accessServerMap("dayupdate",map,mCallback2);

    }

    public void take(View view) {
        Map map = new HashMap();
        map.put("id","test01");
        map.put("date","2017-09-19");


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerMap("dayselect",map,mCallback);
    }
}
