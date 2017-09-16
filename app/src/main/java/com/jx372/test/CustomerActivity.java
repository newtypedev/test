package com.jx372.test;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.tmap.BaseActivity;
import com.skp.Tmap.TMapGpsManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pys on 2017. 9. 15..
 */

public class CustomerActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    EditText et;


    Callback2 mCallback2 = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(CustomerActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(CustomerActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
           //     String jsonbody2 = jsonbody.getString("pois");
              // JSONArray datas = jsonbody.getJSONArray("pois");
                JSONArray json = jsonbody.getJSONObject("searchPoiInfo").getJSONObject("pois").getJSONArray("poi");

                String s = "name : "+json.getJSONObject(0).getString("name")+
                        "\n"+"telNo : "+json.getJSONObject(0).getString("telNo")+
                        "\n"+"address : "+json.getJSONObject(0).getString("upperAddrName")+"  "+ json.getJSONObject(0).getString("middleAddrName")+"  "
                        + json.getJSONObject(0).getString("lowerAddrName")+
                        "\n"+"classification : "+json.getJSONObject(0).getString("lowerBizName")+
                        "\n"+"explain : "+json.getJSONObject(0).getString("desc")
                        ;
                Log.v("결과", json.get(0)+"");
                tv.setText(s+"");





            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        tv = (TextView)findViewById(R.id.textView18);
        btn = (Button)findViewById(R.id.button2);
        et = (EditText) findViewById(R.id.et);

    }

    public void mOnclick(View view) {



        String temp = et.getText()+"";


        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerGet("WGS84GEO","0964bcd8-f1f6-325c-9903-0210ac72ef61",temp,1,mCallback2);

    }


}
