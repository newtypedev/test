package com.jx372.test.customermanagement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.Callback2;
import com.jx372.test.HttpConnector;
import com.jx372.test.R;
import com.jx372.test.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by pys on 2017. 10. 5..
 */

public class CustomerModifyActivity extends AppCompatActivity {
    public static final String CUSTOMER_ID="";
    private EditText name;
    private EditText address;
    private EditText contact;
    private TextView latitude;
    private TextView longitude;
    private UUID customerId;
    private String state="";
    private Customer customer;


    public static Intent newIntent(Context packageContext, UUID customerId) {

        Intent intent = new Intent(packageContext, CustomerModifyActivity.class);
        intent.putExtra(CUSTOMER_ID, customerId);
        return intent;


    }

    // 고객 삭제 콜백
    Callback2 deleteCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
              //  Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
               // Toast.makeText(ConsultModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    // updateData(jsonbody.getString("data"));
                 //   refreshUI();
                  //  Toast.makeText(ConsultModifyActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    //Toast.makeText(ConsultModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };


    // 고객 등록 콜백
    Callback2 insertCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                //Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
               // Toast.makeText(ReportModifyActivity.this,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    // updateData(jsonbody.getString("data"));
                  //  refreshUI();
                    //  Toast.makeText(ReportModifyActivity.this,jsonbody.getString("data"), Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                   // Toast.makeText(ReportModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };

    Callback2 searchCallback = new Callback2() {
        @Override
        public void callback(String msg) {

//            V/searchJson: { "coordinateInfo" : {  "coordType" : "WGS84GEO",   "addressFlag" : "F00",   "page" : "1",   "count" : "20",   "totalCount" : "2",   "coordinate" : [   {      "matchFlag": "",       "lat": "",       "lon": "",       "latEntr": "",       "lonEntr": "",       "city_do": "서울특별시",       "gu_gun": "동작구",       "eup_myun": "",       "legalDong": "",       "adminDong": "",       "ri": "",       "bunji": "",       "buildingName": "",       "buildingDong": "",       "newMatchFlag": "N55",       "newLat": "37.497256",       "newLon": "126.927899",       "newLatEntr": "",       "newLonEntr": "",       "newRoadName": "보라매로",       "newBuildingIndex": "",       "newBuildingName": "",       "newBuildingCateName": "",       "newBuildingDong": "",       "zipcode": ""    },   {      "matchFlag": "M33",       "lat": "37.498879",       "lon": "126.951636",       "latEntr": "",       "lonEntr": "",       "city_do": "서울특별시",       "gu_gun": "동작구",       "eup_myun": "",       "legalDong": "",       "adminDong": "",       "ri": "",       "bunji": "",       "buildingName": "",       "buildingDong": "",       "newMatchFlag": "",       "newLat": "",       "newLon": "",       "newLatEntr": "",       "newLonEntr": "",       "newRoadName": "",       "newBuildingIndex": "",       "newBuildingName": "",       "newBuildingCateName": "",       "newBuildingDong": "",       "zipcode": ""    }  ] }}
  //          10-06 17:23:03.521 31799-31934/com.jx372.test V/FA: Inactivity, disconnecting from the service
            if (msg.equals("JsonException")) {
                Toast.makeText(CustomerModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                Toast.makeText(CustomerModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }


            try {
                Log.v("searchJson",msg+"");

                JSONObject jsonbody = new JSONObject(msg);
                JSONArray json = jsonbody.getJSONObject("coordinateInfo").getJSONArray("coordinate");
                String s = json.getJSONObject(0).getString("newLat")+"";
                latitude.setText(s);
                s =  json.getJSONObject(0).getString("newLon")+"";
                longitude.setText(s);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        HttpConnector httpcon = new HttpConnector();

        if(item.getItemId() == R.id.finish){

            if(state.equals("insert")) {
                Map map = new HashMap();
                map.put("id", User.get().getId());
                // map.put("customer_code","112");
                // map.put("manager_contact","1212");
                map.put("name", name.getText().toString());
                map.put("contact", contact.getText().toString());
                // map.put("manager_name", "sfdsf");
                map.put("address", address.getText().toString());
                map.put("positionX", latitude.getText().toString());
                map.put("positionY", longitude.getText().toString());
                httpcon.accessServerMap("customerinsert", map, insertCallback);
            }
            else if(state.equals("update")){
                Map map = new HashMap();
                map.put("id", User.get().getId());
                map.put("customer_code",customer.getCode());
                // map.put("customer_code","112");
                // map.put("manager_contact","1212");
                map.put("name", name.getText().toString());
                map.put("contact", contact.getText().toString());
                // map.put("manager_name", "sfdsf");
                map.put("address", address.getText().toString());
                map.put("positionX", latitude.getText().toString());
                map.put("positionY", longitude.getText().toString());
                httpcon.accessServerMap("customerupdate", map, insertCallback);
            }

        }

        else if(item.getItemId()==R.id.back){
            onBackPressed();

        }
        else if(item.getItemId()==R.id.delete){
            if(state.equals("update")){
                Map map = new HashMap();
                map.put("customer_code",customer.getCode());
                httpcon.accessServerMap("customerinsert", map, deleteCallback);
            }
        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_modify);
        getSupportActionBar().setTitle("업체관리");


        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        contact = (EditText) findViewById(R.id.contact);
        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);

        customerId = (UUID)getIntent().getSerializableExtra(CustomerModifyActivity.CUSTOMER_ID);

        if(!(customerId==null)) {
            state = "update";
            customer = CustomerList.get(this).getCustomers(customerId);
            name.setText(customer.getName().toString());
            address.setText(customer.getAddress().toString());

//            reportItems = WorkReportList.get(this).getReports(reportId);
//            reportmemo.Render(reportItems.getContent());
//            salesAccount.setText(reportItems.getSalesAccount());
//



        }
        else{
            state = "insert";

        }

    }

    public void btnSearch(View view) {
        String temp = "";

          //  temp = address.getText().toString();
            temp="서울 동작구 보라매로가길 9";
            HttpConnector httpcon = new HttpConnector();
            httpcon.accessTmap(temp,searchCallback);


        }
}
