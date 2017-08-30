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
    DayItems mDayItems;
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

            mDayItems.setMonItems(Items);
               mDayItems.setMonSales(mDaysale);
        }

        else if(nowday.equals("tue")){
            mDayItems.setTueItems(Items);
              mDayItems.setTueSales(mDaysale);
        }

        else if(nowday.equals("wed")){
            mDayItems.setWedItems(Items);
             mDayItems.setWedSales(mDaysale);
        }

        else if(nowday.equals("thu")){
            mDayItems.setThuItems(Items);
             mDayItems.setThuSales(mDaysale);
        }

        else if(nowday.equals("fri")){
            mDayItems.setFriItems(Items);
             mDayItems.setFriSales(mDaysale);
        }

    }
    public ArrayList<String> getItem(){


        if(nowday.equals("mon")){
              salehint = mDayItems.getMonSales();
            return mDayItems.getMonItems();
        }

        else if(nowday.equals("tue")){
             salehint = mDayItems.getTueSales();
            return mDayItems.getTueItems();
        }

        else if(nowday.equals("wed")){
               salehint = mDayItems.getWedSales();
            return mDayItems.getWedItems();
        }

        else if(nowday.equals("thu")){
              salehint = mDayItems.getThuSales();
            return mDayItems.getThuItems();
        }

        else if(nowday.equals("fri")){
             salehint = mDayItems.getFriSales();
            return mDayItems.getFriItems();
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_modify);

        nowday = getIntent().getStringExtra("day");
        mDayItems = DayItems.get();
        Items = new ArrayList<>();

        for(String s: getItem()){
            Items.add(new String(s));
        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
