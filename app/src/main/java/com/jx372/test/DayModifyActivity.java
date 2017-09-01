package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.tmap.TmapActivity;


public class DayModifyActivity extends AppCompatActivity{
    ArrayAdapter<CharSequence> adspin;
    TextView dtButton;
    TextView visitPos;
    TextView shortDis;
    DayItems mDayItem;
    EditText daymemo;
    EditText goalsale;

    public void itemUpdate(){
      mDayItem.setContent(daymemo.getText()+"");
        mDayItem.setGoalsale(goalsale.getText()+"");

    }


    public void startDayModifyActivity(String msg){
        Intent i = new Intent(DayModifyActivity.this,TmapActivity.class);
        //  i.putExtra("day",msg);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_daymodify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        // itemUpdate();
        //onBackPressed();
        if(item.getItemId() == R.id.daymodify){
            itemUpdate();
            //    NavUtils.navigateUpFromSameTask(this);
            onBackPressed();

        }

        if(item.getItemId()==R.id.dayback){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_modify);

        mDayItem = DayItems.get();

        getSupportActionBar().setTitle("수정");

        dtButton = (TextView) findViewById(R.id.distance);
        visitPos = (TextView) findViewById(R.id.visitPos);
        shortDis = (TextView) findViewById(R.id.shortDis);
        daymemo = (EditText) findViewById(R.id.daymemo);
        goalsale = (EditText) findViewById(R.id.goalsale);

        daymemo.setText(mDayItem.getContent());
        goalsale.setText(mDayItem.getGoalsale());

        Spinner spin = (Spinner)findViewById(R.id.dayspinner);

        spin.setPrompt("도전과제");


        adspin = ArrayAdapter.createFromResource(this, R.array.country,
                R.layout.spinner_item);

        adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adspin);
        spin.setSelection(2);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //  dept = adspin.getItem(position)+"";
				/* 초기화시의 선택 제외시
				if (mInitSpinner == false) {
					mInitSpinner = true;
					return;
				}
				//*/
                  Toast.makeText(DayModifyActivity.this,position+"", Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void mOnclick(View view) {

        startDayModifyActivity("tt");

    }

    @Override
    protected void onResume() {
        TmapItem tm = TmapItem.get();
        visitPos.setText(tm.getVisitPoint());
        shortDis.setText(tm.getShortDistance());
        super.onResume();
    }
}
