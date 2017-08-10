package com.jx372.test;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dell on 2017-08-09.
 */

public interface ApiService {

    public static final String API_URL = "http://192.168.1.11:8080/";


//    @GET("sfa")
//    Call<ResponseBody>getComment(@Query("id") int postId);
//
//    @POST("sfa/m/user/auth")
//    Call<ResponseBody> getPostComment(@Query("id") int postId);
//
//
//    @POST("sfa")
//    Call<ResponseBody>getPostCommenStr(@Field("id") String password);
    @FormUrlEncoded
    @POST("sfa/m/user/auth")
    Call<ResponseBody> getPost(@FieldMap Map<String, String> id);
}
