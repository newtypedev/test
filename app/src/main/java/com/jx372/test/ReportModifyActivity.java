package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by pys on 2017. 9. 13..
 */

public class ReportModifyActivity extends AppCompatActivity {

    EditText reportmemo;
    EditText startdis;
    EditText enddis;
    EditText salesAccount;
    ReportItems reportItems;

    private void setTextValue(){

        reportmemo.setText(reportItems.getContent());
        startdis.setText(reportItems.getStartDistance());
        enddis.setText(reportItems.getEndDistance());
        salesAccount.setText(reportItems.getSalesAccount());
    }

    public void itemUpdate(){
        reportItems.setSalesAccount(salesAccount.getText()+"");
        reportItems.setContent(reportmemo.getText()+"");
        reportItems.setStartDistance(startdis.getText()+"");
        reportItems.setEndDistance(enddis.getText()+"");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report_modify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.reportfinish) {
            itemUpdate();
            onBackPressed();
        }
//            itemUpdate();
//            //    NavUtils.navigateUpFromSameTask(this);
//            onBackPressed();
//
//        }
//
//        if(item.getItemId()==R.id.dayback){
//            onBackPressed();
//        }
            return super.onOptionsItemSelected(item);
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_modify);

        reportmemo = (EditText) findViewById(R.id.reportmemo);
        startdis = (EditText) findViewById(R.id.startdis);
        enddis =(EditText)  findViewById(R.id.enddis);
        salesAccount = (EditText)findViewById(R.id.salerep);
        reportItems = ReportItems.get();

        getSupportActionBar().setTitle("수정");
        setTextValue();

    }



}
