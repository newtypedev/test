package com.jx372.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Dell on 2017-08-24.
 */

public class WeekModifyActivity extends AppCompatActivity {

    String salehint="";
    String nowday="";
    ArrayList<String> Items;
    ArrayAdapter<String> Adapter6;
    ListView list;
    WeekItems mWeekItems;
    String mDaysale="";
    EditText saleEdit;
    @Override
    public void onBackPressed() {
        Log.v("backback","keyeky");
        //  itemUpdate();

        super.onBackPressed();

    }

    public void itemUpdate(){
        mDaysale = saleEdit.getText()+"";

        if(nowday.equals("mon")){

            mWeekItems.setMonItems(Items);
            mWeekItems.setMonSales(mDaysale);
        }

        else if(nowday.equals("tue")){
            mWeekItems.setTueItems(Items);
            mWeekItems.setTueSales(mDaysale);
        }

        else if(nowday.equals("wed")){
            mWeekItems.setWedItems(Items);
            mWeekItems.setWedSales(mDaysale);
        }

        else if(nowday.equals("thu")){
            mWeekItems.setThuItems(Items);
            mWeekItems.setThuSales(mDaysale);
        }

        else if(nowday.equals("fri")){
            mWeekItems.setFriItems(Items);
            mWeekItems.setFriSales(mDaysale);
        }

    }
    public ArrayList<String> getItem(){


        if(nowday.equals("mon")){
              salehint = mWeekItems.getMonSales();
            return mWeekItems.getMonItems();
        }

        else if(nowday.equals("tue")){
             salehint = mWeekItems.getTueSales();
            return mWeekItems.getTueItems();
        }

        else if(nowday.equals("wed")){
               salehint = mWeekItems.getWedSales();
            return mWeekItems.getWedItems();
        }

        else if(nowday.equals("thu")){
              salehint = mWeekItems.getThuSales();
            return mWeekItems.getThuItems();
        }

        else if(nowday.equals("fri")){
             salehint = mWeekItems.getFriSales();
            return mWeekItems.getFriItems();
        }

        return Items;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weekmodify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        // itemUpdate();
        //onBackPressed();
        if(item.getItemId() == R.id.modify){
            itemUpdate();
            //    NavUtils.navigateUpFromSameTask(this);
            onBackPressed();

        }

        if(item.getItemId()==R.id.weekback){
            onBackPressed();
        }


//        if(item.getItemId()==android.R.id.home){
//            Log.v("sdfsdf","sdfsf");
//            onBackPressed();
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_modify);

        nowday = getIntent().getStringExtra("day");
        mWeekItems = WeekItems.get();
        Items = new ArrayList<>();

        for(String s: getItem()){
            Items.add(new String(s));
        }



        getSupportActionBar().setTitle(nowday.toUpperCase());
//        Items = new ArrayList<String>();
//        Items.add("본사출근");
//        Items.add("동작슈퍼");
//        Items.add("신갈대리점");
//        Items.add("신규거래처상담");
//        Items.add("거래선방문");

        saleEdit = (EditText) findViewById(R.id.saleedit);
        saleEdit.setText(salehint);
        Adapter6 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,Items);
        list = (ListView)findViewById(R.id.weeklist);
        list.setAdapter(Adapter6);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void mOnclick(View v){
        EditText ed = (EditText)findViewById(R.id.newitem);
        switch(v.getId()){

            case R.id.add:
                String text = ed.getText().toString();
                if(text.length()!=0){
                    Items.add(text);
                    ed.setText("");
                    Adapter6.notifyDataSetChanged();


                }
                break;
            case R.id.delete:
                int pos;
                pos = list.getCheckedItemPosition();
                if(pos != ListView.INVALID_POSITION){
                    Items.remove(pos);
                    list.clearChoices();
                    Adapter6.notifyDataSetChanged();
                }
                break;
        }
    }
}
