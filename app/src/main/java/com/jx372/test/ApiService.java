package com.jx372.test;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dell on 2017-08-09.
 */

public interface ApiService {
   // https://api.androidhive.info/contacts/
    public static final String API_URL = "http://192.168.1.11:8181/";
    //http://192.168.1.11:8181/

//    @GET("sfa")
//    Call<ResponseBody>getComment(@Query("id") int postId);
//
    @POST("sfa/m/check2")
    Call<ResponseBody> getPostComment(@Query("id") int postId);

    @FormUrlEncoded
    @POST("contacts/")
    Call<ResponseBody>getPostIdStr(@Field("id") String id);

    @FormUrlEncoded
    @POST("sfa/m/auth")
    Call<ResponseBody> getPostAuth(@FieldMap Map<String, String> id);

    @FormUrlEncoded
    @POST("sfa/m/join")
    Call<ResponseBody> getPostJoin(@FieldMap Map<String, String> join);

    @FormUrlEncoded
    @POST("sfa/m/check")
    Call<ResponseBody> getPostJoinCheck(@FieldMap Map<String, String> join);

    @FormUrlEncoded
    @POST("sfa/m/week/insert")
    Call<ResponseBody> getPostWeekSubmit(@FieldMap Map<String, String> week);

    @FormUrlEncoded
    @POST("sfa/m/week/update")
    Call<ResponseBody> getPostWeekUpdate(@FieldMap Map<String, String> week);

    @FormUrlEncoded
    @POST("sfa/m/week/select")
    Call<ResponseBody> getPostWeek(@FieldMap Map<String, String> week);

    @FormUrlEncoded
    @POST("sfa/m/week/delete")
    Call<ResponseBody> getPostWeekDelete(@FieldMap Map<String, String> week);

    @FormUrlEncoded
    @POST("sfa/m/date/select")
    Call<ResponseBody> getPostDaySelect(@FieldMap Map<String, String> day);

    @FormUrlEncoded
    @POST("sfa/m/date/insert")
    Call<ResponseBody> getPostDayInsert(@FieldMap Map<String, String> day);

    @FormUrlEncoded
    @POST("sfa/m/date/update")
    Call<ResponseBody> getPostDayUpdate(@FieldMap Map<String, String> day);

    @FormUrlEncoded
    @POST("sfa/m/date/delete")
    Call<ResponseBody> getPostDayDelete(@FieldMap Map<String, String> day);

}
