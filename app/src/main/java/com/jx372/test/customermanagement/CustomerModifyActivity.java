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
import com.jx372.test.tmap.TmapSearchActivity;

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
    private EditText managername;
    private EditText managercontact;
    private EditText manageremail;
    private UUID customerId;
    private TextView posx;
    private TextView posy;
    private String state="";
    private Customer customer;
    private CustomerList customerList;

    public void refreshUI() {

        HttpConnector httpcon = new HttpConnector();
        Map map = new HashMap();
        map.put("id", User.get().getId());
        httpcon.accessServerMap("customerselect", map, selectCustomer);

    }




    public static Intent newIntent(Context packageContext, UUID customerId) {

        Intent intent = new Intent(packageContext, CustomerModifyActivity.class);
        intent.putExtra(CUSTOMER_ID, customerId);
        return intent;


    }

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
                    customerList = CustomerList.get(CustomerModifyActivity.this);
                    customerList.cleanList();

                    for (int i = 0; i < size; i++) {

                        Customer c = new Customer(datas.getJSONObject(i).getString("customer_code"),
                                datas.getJSONObject(i).getString("name"),
                                datas.getJSONObject(i).getString("manager_name"),
                                datas.getJSONObject(i).getString("address"),
                                datas.getJSONObject(i).getString("contact"),
                                datas.getJSONObject(i).getString("manager_contact"),
                                datas.getJSONObject(i).getString("manager_email"),
                                datas.getJSONObject(i).getString("time"),
                                datas.getJSONObject(i).getString("positionX"),
                                datas.getJSONObject(i).getString("positionY")
                        );

                        customerList.addCustomer(c);

                        // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));
                    }
               onBackPressed();

                } else if (jsonbody.getString("result").equals("fail")) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };


    //고객검색 콜백
    Callback2 custoemrSearchCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                Toast.makeText(CustomerModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                Toast.makeText(CustomerModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {

                JSONObject jsonbody = new JSONObject(msg);
                //     String jsonbody2 = jsonbody.getString("pois");
                // JSONArray datas = jsonbody.getJSONArray("pois");
                JSONArray json = jsonbody.getJSONObject("searchPoiInfo").getJSONObject("pois").getJSONArray("poi");
                    contact.setText(json.getJSONObject(0).getString("telNo"));
                address.setText( json.getJSONObject(0).getString("upperAddrName") + "  " + json.getJSONObject(0).getString("middleAddrName") + "  "
                        + json.getJSONObject(0).getString("lowerAddrName") );
                latitude.setText(Double.parseDouble(json.getJSONObject(0).getString("frontLat"))+"");
                longitude.setText(Double.parseDouble(json.getJSONObject(0).getString("frontLon"))+"");
//                String s = "name : " + json.getJSONObject(0).getString("name") +
//                        "\n" + "telNo : " + json.getJSONObject(0).getString("telNo") +
//                        "\n" + "address : " + json.getJSONObject(0).getString("upperAddrName") + "  " + json.getJSONObject(0).getString("middleAddrName") + "  "
//                        + json.getJSONObject(0).getString("lowerAddrName") +
//                        "\n" + "classification : " + json.getJSONObject(0).getString("lowerBizName") +
//                        "\n" + "explain : " + json.getJSONObject(0).getString("desc");
//                Log.v("결과", json.get(0) + "");
//                nowLatitude = Double.parseDouble(json.getJSONObject(0).getString("frontLat"));
//                nowLongitude = Double.parseDouble(json.getJSONObject(0).getString("frontLon"));
//                if (!(nowLongitude == 0.0)) {
//                    mMapView.setLocationPoint(nowLongitude, nowLatitude);
//                    mMapView.setCenterPoint(nowLongitude, nowLatitude, true);
//                    Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.end);
//                    mMapView.setIcon(bitmap);
//                    mMapView.setIconVisibility(true);
//                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(CustomerModifyActivity.this,"검색 실패", Toast.LENGTH_SHORT).show();
            }

        }
    };


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
                    refreshUI();


                    Toast.makeText(CustomerModifyActivity.this,"Success", Toast.LENGTH_SHORT).show();
                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(CustomerModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
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
                    refreshUI();


                    Toast.makeText(CustomerModifyActivity.this,"Success", Toast.LENGTH_SHORT).show();

                }
                else if(jsonbody.getString("result").equals("fail")){




                    Toast.makeText(CustomerModifyActivity.this,"Fail", Toast.LENGTH_SHORT).show();
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
                String s = json.getJSONObject(0).getString("lat")+"";
                latitude.setText(s);
                s =  json.getJSONObject(0).getString("lon")+"";
                longitude.setText(s);

                if(s.equals("")){
                    Toast.makeText(CustomerModifyActivity.this,"좌표 검색 실패", Toast.LENGTH_SHORT).show();
                    latitude.setText("0");
                    longitude.setText("0");
                }



            } catch (JSONException e) {
                Toast.makeText(CustomerModifyActivity.this,"좌표 검색 실패", Toast.LENGTH_SHORT).show();
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

                map.put("manager_name", managername.getText().toString());
                map.put("manager_contact", managercontact.getText().toString());
                map.put("manager_email", manageremail.getText().toString());
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
                map.put("manager_name", managername.getText().toString());
                map.put("manager_contact", managercontact.getText().toString());
                map.put("manager_email", manageremail.getText().toString());
                httpcon.accessServerMap("customerupdate", map, insertCallback);
            }

        }

        else if(item.getItemId()==R.id.back){
            onBackPressed();

        }
        else if(item.getItemId()==R.id.delete){

            Map map = new HashMap();
                map.put("customer_code",customer.getCode());
                httpcon.accessServerMap("customerdelete", map, deleteCallback);



//            if(state.equals("update")){
//                Map map = new HashMap();
//                map.put("customer_code",customer.getCode());
//                httpcon.accessServerMap("customerinsert", map, deleteCallback);
//            }



        }


        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_modify);
        getSupportActionBar().setTitle("업체관리");


        managername = (EditText) findViewById(R.id.managername);
        managercontact = (EditText) findViewById(R.id.managercontact);
        manageremail = (EditText) findViewById(R.id.manageremail);


        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        contact = (EditText) findViewById(R.id.contact);
        latitude = (TextView) findViewById(R.id.latitude);
        longitude = (TextView) findViewById(R.id.longitude);
        latitude.setText("0");
        longitude.setText("0");
        customerId = (UUID)getIntent().getSerializableExtra(CustomerModifyActivity.CUSTOMER_ID);

        if(!(customerId==null)) {
            state = "update";
            customer = CustomerList.get(this).getCustomers(customerId);
            name.setText(customer.getName().toString());
            address.setText(customer.getAddress().toString());
            latitude.setText(customer.getPosX().toString());
            longitude.setText(customer.getPosY().toString());
            managername.setText(customer.getManager().toString());
            managercontact.setText(customer.getManagercontact().toString());
            manageremail.setText(customer.getManageremail().toString());

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

            temp = address.getText().toString();
            //temp="서울 동작구 보라매로가길 9";
            HttpConnector httpcon = new HttpConnector();
            httpcon.accessTmap(temp,searchCallback);


        }

    public void btnCustomer(View view) {
        String temp = "";

        temp = name.getText().toString();
        HttpConnector httpcon = new HttpConnector();
        httpcon.accessServerGet("WGS84GEO", "0964bcd8-f1f6-325c-9903-0210ac72ef61", temp, 1, custoemrSearchCallback);
    }

    public void btnPosCheck(View view) {

        Intent intent = new Intent(this, TmapSearchActivity.class);

        intent.putExtra("x", Double.parseDouble(latitude.getText()+""));
        intent.putExtra("y", Double.parseDouble(longitude.getText()+""));
        startActivity(intent);


    }
}
