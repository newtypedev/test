package com.jx372.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConsultActivity extends AppCompatActivity {


    public static final String CRIME_ID="";

    private Consult consult;
    private EditText titleField;
    private Button dateButton;
    private CheckBox solvedCheckBox;
    private  UUID consultId;
    private String state="";


    public static Intent newIntent(Context packageContext, UUID crimeid) {

        Intent intent = new Intent(packageContext, ConsultActivity.class);
        intent.putExtra(CRIME_ID, crimeid);
        return intent;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consult_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        ConsultList cl =ConsultList.get(ConsultActivity.this);

        if(id == R.id.finish){

            if(state.equals("insert")){

                cl.addItem(titleField.getText()+"",true);
                onBackPressed();
            }

            else if(state.equals("update")){

            }

        }

        else if(id ==R.id.delete){

          if(!(consultId==null)){
            cl.deleteItem(consultId);
              onBackPressed();
          }

        }

            return true;

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        getSupportActionBar().setTitle("상담일지");
        dateButton = (Button)findViewById(R.id.crime_date);
        titleField = (EditText) findViewById(R.id.crime_title);
        solvedCheckBox = (CheckBox)findViewById(R.id.crime_solved);

        Log.v("프래그먼트","실행");

            consultId = (UUID)getIntent().getSerializableExtra(ConsultActivity.CRIME_ID);
        if(!(consultId==null)) {
            state = "update";
            Log.v("크라임", consultId + "");
            consult = ConsultList.get(this).getConsults(consultId);
            dateButton.setText(consult.getDate().toString());
            //dateButton.setEnabled(false);
            solvedCheckBox.setChecked(consult.isSolved());
            titleField.setText(consult.getTitle());
        }
        else{
            state = "insert";

        }
    }

}