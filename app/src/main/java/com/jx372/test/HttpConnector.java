package com.jx372.test;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    public String accessPhoto(String url, MultipartBody.Part body,RequestBody name, final Callback2 callback) {


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);

        Map map = new HashMap();
        map.put("date","20130514");
        retrofit2.Call<okhttp3.ResponseBody> req = apiService.postImage(body, name,90302,"test01");
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.v("접속 성공","굳굳");


                String json = response.body()+"";
                Log.v("jsonjson",json);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("받음","끝");

                t.printStackTrace();
            }
        });


        return "111";
    }






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
        else if(url.equals("reportselect")){
            comment2 = apiService.getPostReportSelect(map);
        }
        else if(url.equals("reportinsert")){
            comment2 = apiService.getPostReportInsert(map);
        }
        else if(url.equals("reportupdate")){
            comment2 = apiService.getPostReportUpdate(map);
        }
        else if(url.equals("consultselect")){
            comment2 = apiService.getPostAdviceSelect(map);
        }
        else if(url.equals("consultinsert")){
            comment2 = apiService.getPostAdviceInsert(map);
        }
        else if(url.equals("consultupdate")){
            comment2 = apiService.getPostAdviceUpdate(map);
        }
        else if(url.equals("consultdelete")){
            comment2 = apiService.getPostAdviceDelete(map);
        }

        else if(url.equals("checkemail")){
            comment2 = apiService.getPostEmailCheck(map);
        }
        else if(url.equals("getPo")){
            comment2 = apiService.getPo(map,1,"0964bcd8-f1f6-325c-9903-0210ac72ef61");
        }
        else if(url.equals("customerselect")){
            comment2 = apiService.getPostCustomerSelect(map);
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
