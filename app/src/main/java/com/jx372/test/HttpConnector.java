package com.jx372.test;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dell on 2017-08-11.
 */

public class HttpConnector {

    Retrofit retrofit;
    ApiService apiService;
     String resultJson="";




    public String accessServerMap(String url, Map map, final Callback2 callback) {

        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> comment2 = apiService.getPostJoin(map);

        if(url.equals("join")){
            comment2 = apiService.getPostJoin(map);
        }

        else if(url.equals("check")){
            comment2 = apiService.getPostJoinCheck(map);
        }

        else if(url.equals("weekselect")){
            comment2 = apiService.getPostWeek(map);
        }
        else if(url.equals("weekinsert")){
            comment2 = apiService.getPostWeekSubmit(map);

        }
        else if(url.equals("weekdelete")){
            comment2 = apiService.getPostWeekDelete(map);
        }
        else if(url.equals("weekupdate")){
            comment2 = apiService.getPostWeekUpdate(map);
        }
        else if(url.equals("dayselect")){
            comment2 = apiService.getPostDaySelect(map);
        }
        else if(url.equals("dayinsert")){
            Log.v("dadadadayyer","09090901");
            comment2 = apiService.getPostDayInsert(map);
        }
        else if(url.equals("dayupdate")){
            comment2 = apiService.getPostDayUpdate(map);
        }

        else if(url.equals("daydelete")){
            comment2 = apiService.getPostDayDelete(map);
        }
        else if(url.equals("selectposition")){
            comment2 = apiService.getPostPosition(map);
        }
        else if(url.equals("challenge")){
            comment2 = apiService.getPostChallenge(map);
        }



        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {


                    Log.v("callcall",response.message());

                    String json = response.body().string();
                    Log.v("jontest",json);
                    resultJson = json;
                    callback.callback(json);






                } catch (IOException e) {

                    e.printStackTrace();


                }
                catch(NullPointerException e){
                    callback.callback("JsonException");
                    e.printStackTrace();
                    Log.v("NNNNNUUUULLLL","nono");

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.callback("ConnectFail");
                Log.v("insfinef","fail");

            }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }







    public String accessServerGet(String req,String key,String search,int version,final Callback2 callback) {

        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL2).build();
        apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> comment2 = apiService.getPoi(req,key,search,version);
        comment2.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {


                        Log.v("callcall",response.message());

                        String json = response.body().string();
                        Log.v("jontest",json);
                        resultJson = json;
                        callback.callback(json);






                    } catch (IOException e) {

                        e.printStackTrace();


                    }
                    catch(NullPointerException e){
                        callback.callback("JsonException");
                        e.printStackTrace();
                        Log.v("NNNNNUUUULLLL","nono");

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.callback("ConnectFail");
                    Log.v("insfinef","fail");

                }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }


}
