package com.jx372.test.util;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.Callback2;
import com.jx372.test.HttpConnector;
import com.jx372.test.LoginActivity;
import com.jx372.test.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pys on 2017. 10. 13..
 */

public class DialogLoginUtils {

    private LoginActivity mDialogMediaActivity;
    private Dialog mDialog;
    private String comment = "";
    private EditText mDialogComment;
    private TextView mOKButton;
    private TextView mCancelButton;
    private ImageView mDialogImage;
    private TextView mName;
    private EditText mComment;
    private RatingBar mRatingBar;
    private Context context;
    private RadioGroup group;
    private EditText idEdit;
    private EditText nameEdit;
    private EditText emailEdit;
    private String state;


    Callback2 searchCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if(msg.equals("JsonException")){
                Toast.makeText(mDialogMediaActivity,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if(msg.equals("ConnectFail")){
                Toast.makeText(mDialogMediaActivity,msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson3",msg);
                if(jsonbody.getString("result").equals("success")){

                    if(state.equals("id")){
                    Toast.makeText(mDialogMediaActivity,jsonbody.getString("data")+"", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(mDialogMediaActivity,"메일 발송 완료", Toast.LENGTH_SHORT).show();
                    }

                    mDialog.dismiss();

                       // spinList.add(datas.getJSONObject(i).getString("content"));

                }
                else if(jsonbody.getString("result").equals("fail")){
                    Toast.makeText(mDialogMediaActivity,jsonbody.getString("message")+"", Toast.LENGTH_SHORT).show();
                }
                else if(jsonbody.getString("result").equals("error")){
                    Toast.makeText(mDialogMediaActivity,jsonbody.getString("message")+"", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            jsonTest = msg;
//            if(!msg.equals(""))
//                Toast.makeText(JoinActivity.this,"사용 가능한 ID 입니다", Toast.LENGTH_SHORT).show();

        }
    };



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DialogLoginUtils(LoginActivity mDialogMediaActivity) {
        this.mDialogMediaActivity = mDialogMediaActivity;
    }

    private void setUpEditor() {

        //editor.dividerBackground=R.drawable.divider_background_dark;

    }

    public boolean checkEmail(String email) {

        String regex = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        boolean isNormal = m.matches();
        return isNormal;

    }

    public void showDialog(Context context) {
        this.context = context;
        if (mDialog == null) {
            mDialog = new Dialog(mDialogMediaActivity,
                    R.style.CustomDialogTheme);
        }


        mDialog.setContentView(R.layout.dialog_login);
        mDialog.getWindow().setGravity(Gravity.BOTTOM);
        mDialog.show();

        mDialogComment = (EditText) mDialog.findViewById(R.id.dialog_media_comment);
        state = "null";

        idEdit = (EditText) mDialog.findViewById(R.id.idEdit);
        nameEdit = (EditText) mDialog.findViewById(R.id.nameEdit);
        emailEdit = (EditText) mDialog.findViewById(R.id.emailEdit);
        group = (RadioGroup) mDialog.findViewById(R.id.radioGroup);
        mOKButton = (TextView) mDialog.findViewById(R.id.dialog_media_ok);
        mCancelButton = (TextView) mDialog.findViewById(R.id.dialog_media_cancel);
        mName = (TextView) mDialog.findViewById(R.id.dialog_media_ok);
        mComment = (EditText) mDialog.findViewById(R.id.dialog_media_comment);
        //	mDialogImage = (ImageView) mDialog.findViewById(R.id.dialog_media_image);

//		ImageUtil.displayRoundImage(mDialogImage, "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg", null);


        initDialogButtons();
    }

    private void initDialogButtons() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioid:
                        state = "id";
                        nameEdit.setText("");
                        emailEdit.setText("");
                        idEdit.setVisibility(View.INVISIBLE);
                        emailEdit.setVisibility(View.VISIBLE);
                        nameEdit.setVisibility(View.VISIBLE);

                        break;
                    case R.id.radiopw:
                        state = "pw";
                        idEdit.setText("");
                        emailEdit.setText("");
                        idEdit.setVisibility(View.VISIBLE);
                        emailEdit.setVisibility(View.VISIBLE);
                        nameEdit.setVisibility(View.INVISIBLE);
                        break;

                }
            }
        });


        mOKButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String id;
                String email;
                String name;

                if (state.equals("null")) {
                    Toast.makeText(mDialogMediaActivity, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show();
                } else if (state.equals("id")) {
                    email = emailEdit.getText() + "";
                    name = nameEdit.getText() + "";
                    if (!checkEmail(email)) {
                        Toast.makeText(mDialogMediaActivity, "잘못된 이메일 형식입니다", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if ((!email.equals("")) && (!name.equals(""))) {

                        Map map = new HashMap();
                        map.put("name", name );
                        map.put("email",email);
                        HttpConnector httpcon = new HttpConnector();
                        httpcon.accessServerMap("idsearch",map,searchCallback);

                    } else {
                        Toast.makeText(mDialogMediaActivity, "이메일,아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                    }

                } else if (state.equals("pw")) {

                    email = emailEdit.getText() + "";
                    id = idEdit.getText() + "";
                    if (!checkEmail(email)) {
                        Toast.makeText(mDialogMediaActivity, "잘못된 이메일 형식입니다", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    if ((!email.equals("")) && (!id.equals(""))) {
                        Map map = new HashMap();
                        map.put("id", id );
                        map.put("email",email);
                        HttpConnector httpcon = new HttpConnector();
                        httpcon.accessServerMap("pwsearch",map,searchCallback);
                    } else {
                        Toast.makeText(mDialogMediaActivity, "이름,이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    }


                }


                //  Toast.makeText(mDialogMediaActivity, mDialogComment.getText()+"",Toast.LENGTH_SHORT).show();
                //setComment(mDialogComment.getText()+"");
                // ((ApprovalActivity)context).callbackComment(mDialogComment.getText()+"");

            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // ((ApprovalActivity)context).callbackComment("취소");
                mDialog.dismiss();
            }
        });
    }

    public void dismissDialog() {
        mDialog.dismiss();
    }
}
