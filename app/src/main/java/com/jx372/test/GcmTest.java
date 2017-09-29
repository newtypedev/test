package com.jx372.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by pys on 2017. 9. 27..
 */

public class GcmTest extends AppCompatActivity {

    private static final String TAG = "GcmTest";
    TextView tv ;
    public void sendText(String msg){
        tv.setText(msg);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //타이틀바 숨기기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //풀 스크린
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcm);





        tv = (TextView)findViewById(R.id.tokenid);
        Button getTokenButton = (Button) findViewById(R.id.checkTokenButton);
        getTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFirebaseInstanceIDService service = new MyFirebaseInstanceIDService(GcmTest.this);
                service.onTokenRefresh();
            }
        });
    }


}
