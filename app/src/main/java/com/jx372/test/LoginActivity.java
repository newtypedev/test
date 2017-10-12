package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity {
    EditText mid;
    EditText passwd;
    Button loginBtn;
    TextView tv;
    Retrofit retrofit;
    ApiService apiService;
    int messageResId = 0;
    int messageResId2 = 0;
    MyFirebaseInstanceIDService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         service = new MyFirebaseInstanceIDService(LoginActivity.this);

        // start splash
        Intent intent = new Intent(this,SplashScreensActivity.class);
        startActivity(intent);



      //  setTheme(R.style.AppTheme); // 투명화 되돌리기

        setContentView( R.layout.activity_login );


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);


        mid = (EditText)findViewById(R.id.editTextId);
        passwd = (EditText)findViewById(R.id.editTextPassword);
        loginBtn = (Button)findViewById(R.id.buttonLogin);
        tv = (TextView)findViewById(R.id.tv);

        loginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(mid.getText().toString().equals("demo")){
                    User user = User.get();
                    user.setId(mid.getText()+"");

                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);

                }
                if(mid.getText().toString().equals("demo2")){
                    User user = User.get();
                    user.setId(mid.getText()+"");

                    Intent i = new Intent(LoginActivity.this,LeaderMainActivity.class);
                    startActivity(i);

                }
                if(mid.getText().toString().equals("") || passwd.getText().toString().equals("")){

                    messageResId = R.string.null_message;
                    Toast.makeText(LoginActivity.this,messageResId,Toast.LENGTH_SHORT).show();
                    return;

                }

                Map map = new HashMap();
                map.put("id",mid.getText().toString());
                map.put("passwd",passwd.getText().toString());
                map.put("Token",service.getToken());

                Call<ResponseBody> comment2 = apiService.getPostAuth(map);
                comment2.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            String json =  response.body().string();
                            Log.v("loginjson",json);
                            JSONObject jsonbody = new JSONObject(json);


                            if(jsonbody.getString("result").equals("success")){
                                User user = User.get();
                                user.setId(mid.getText()+"");

                                JSONObject data = jsonbody.getJSONObject("data");
                                user.setDept(data.getString("dept"));
                                String level = data.getString("level");
                                user.setGrade(data.getString("grade"));
                                user.setEmail(data.getString("email"));
                                user.setName(data.getString("name"));

                                if(level.equals("팀원")){

                                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                }

                                else if(level.equals("팀장")){
                                    Intent i = new Intent(LoginActivity.this,LeaderMainActivity.class);
                                    startActivity(i);
                                }

                            }
                            else{
                                messageResId2 = R.string.message_re;
                                Toast.makeText(LoginActivity.this,messageResId2,Toast.LENGTH_SHORT).show();
                            }


//                            String json =  response.body().string();
//                            Log.v("Test", json);
//                            JSONObject jsonbody = new JSONObject(json);
//                            JSONObject userjson = jsonbody.getJSONObject("data");
//
//                            Log.v("jsontest---", userjson.getString("dept"));


                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"IOException!!",Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"JSONException!!",Toast.LENGTH_SHORT).show();
                        }
                        catch(NullPointerException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,"JSONException!!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"서버와 연결이 되지 않습니다",Toast.LENGTH_SHORT).show();

                    }
                });

//                String temp = "{\"userid\""+":"+"\""+mid.getText().toString()+"\""+ ","
//                        + "\"password\""+":" + "\"" + passwd.getText().toString() + "\"" + "}";
//                tv.setText(temp);
//
//                Intent i = new Intent(LoginActivity.this,MainActivity.class);
//                startActivity(i);


            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
