package com.jx372.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.Callback2;
import com.jx372.test.HttpConnector;
import com.jx372.test.R;
import com.jx372.test.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pys on 2017. 10. 12..
 */

public class MypageFragment extends Fragment {

    private static final String ARG_POSITION = "position";
    private TextView myid;
    private TextView myname;
    private TextView mydept;
    private TextView mygrade;
    private EditText pw1;
    private EditText pw2;
    private TextView changePw;
    private int position;



    Callback2 mypageCallback = new Callback2() {
        @Override
        public void callback(String msg) {

            if(msg.equals("JsonException")){
                Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                if(jsonbody.getString("result").equals("success")){

                    Toast.makeText(getActivity(),"등록 성공", Toast.LENGTH_SHORT).show();
                    pw1.setText("");
                    pw2.setText("");
                }
                else if(jsonbody.getString("result").equals("fail")){
                    Toast.makeText(getActivity(),"등록 실패", Toast.LENGTH_SHORT).show();
                    pw1.setText("");
                    pw2.setText("");
                }
                else if(jsonbody.getString("result").equals("error")){
                    Toast.makeText(getActivity(),jsonbody.getString("message"), Toast.LENGTH_SHORT).show();
                    pw1.setText("");
                    pw2.setText("");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };


    public static MypageFragment newInstance(int position) {
        MypageFragment f = new MypageFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mypage,
                container, false);
        myid = (TextView) rootView.findViewById(R.id.myid);
        myname = (TextView) rootView.findViewById(R.id.myname);
        mydept = (TextView) rootView.findViewById(R.id.mydept);
        mygrade = (TextView) rootView.findViewById(R.id.mygrade);
        changePw = (TextView) rootView.findViewById(R.id.changePw);
        pw2 = (EditText) rootView.findViewById(R.id.pw2);
        pw1 = (EditText) rootView.findViewById(R.id.pw1);
        myid.setText(User.get().getId());
        myname.setText(User.get().getName());
        mydept.setText(User.get().getDept());
        mygrade.setText(User.get().getGrade());
        changePw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp1 = pw1.getText().toString();
                String temp2 = pw2.getText().toString();
                if(temp1.equals(temp2)) {


                    Map map = new HashMap();
                    map.put("id", User.get().getId()+"");
                    map.put("passwd", pw2.getText()+"");
                    HttpConnector httpcon = new HttpConnector();
                    httpcon.accessServerMap("mypage", map, mypageCallback);
                }
                else{
                    Toast.makeText(getActivity(),"비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return rootView;
    }


}
