package com.jx372.test;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dell on 2017-08-09.
 */

public interface ApiService {
   // https://api.androidhive.info/contacts/
    public static final String API_URL = "http://192.168.1.121:8080/";
    public static final String API_URL2 = "https://apis.skplanetx.com/tmap/";
    //http://192.168.1.11:8181/

    @GET("pois")
    Call<ResponseBody>getPoi(@Query("resCoordType") String req,@Query("appKey") String key,@Query("searchKeyword") String search,@Query("version") int version);

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

    @FormUrlEncoded
    @POST("sfa/m/position/")
    Call<ResponseBody> getPostPosition(@FieldMap Map<String, String> pos);
    @FormUrlEncoded
    @POST("sfa/m/report/insert")
    Call<ResponseBody> getPostReportInsert(@FieldMap Map<String, String> report);

    @FormUrlEncoded
    @POST("sfa/m/report/update")
    Call<ResponseBody> getPostReportUpdate(@FieldMap Map<String, String> report);

    @FormUrlEncoded
    @POST("sfa/m/report/")
    Call<ResponseBody> getPostReportSelect(@FieldMap Map<String, String> report);

    @FormUrlEncoded
    @POST("sfa/m/report/delete")
    Call<ResponseBody> getPostReportDelete(@FieldMap Map<String, String> report);

    @FormUrlEncoded
    @POST("sfa/m/challenge/")
    Call<ResponseBody> getPostChallenge(@FieldMap Map<String, String> report);
}
