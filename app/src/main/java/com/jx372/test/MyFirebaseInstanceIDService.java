package com.jx372.test;

import android.content.Context;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private String token="";
    public String getToken(){
        onTokenRefresh();
        return token;
    }
    private static final String TAG = "MyFirebaseIIDService";
private Context context;
    public MyFirebaseInstanceIDService(){}
    public MyFirebaseInstanceIDService(Context context){
        onTokenRefresh();
        this.context = context;}
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);
        token = refreshedToken;
       // GcmTest g = (GcmTest) context;
       // g.sendText(refreshedToken);


    }

    private void sendRegistrationToServer(String token) {
        Log.d(TAG, "send Server");
    }
}
