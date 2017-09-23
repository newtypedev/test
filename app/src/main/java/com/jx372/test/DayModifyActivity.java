package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;
import com.jx372.test.tmap.TmapActivity;


public class DayModifyActivity extends AppCompatActivity{
    ArrayAdapter<CharSequence> adspin;
    TextView dtButton;
    TextView visitPos;
    TextView shortDis;
    DayItems mDayItem;
    EditText daymemo;
    EditText goalsale;
    Spinner spin;
    String nowItem="";
    Editor daycontent;
    int itemPos=0;

    private void setUpEditor() {
        findViewById(R.id.action_h1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daycontent.UpdateTextStyle(EditorTextStyle.H1);
            }
        });

        findViewById(R.id.action_h2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daycontent.UpdateTextStyle(EditorTextStyle.H2);
            }
        });

        findViewById(R.id.action_h3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daycontent.UpdateTextStyle(EditorTextStyle.H3);
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daycontent.UpdateTextStyle(EditorTextStyle.BOLD);
            }
        });

        findViewById(R.id.action_Italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daycontent.UpdateTextStyle(EditorTextStyle.ITALIC);
            }
        });


        findViewById(R.id.action_erase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daycontent.clearAllContents();
            }
        });
        //editor.dividerBackground=R.drawable.divider_background_dark;

    }

    public int getItemNum(String msg){


        for(int i=0;i<mDayItem.getSpinnerItem().size();i++){
            if(msg.equals(mDayItem.getSpinnerItem().get(i))){
                return i;
            }
        }

        return 0;
    }

    public void itemUpdate(){
        Editor editor=new Editor(this ,null);
        String b= daycontent.getContentAsSerialized();
        Log.v("jsonjson",b);
        b = editor.getContentAsHTML(b);
        b =b.replaceAll("<u>","");
        b = b.replaceAll("</u>","");
        Log.v("underunderlin",b);
        mDayItem.setContent(b);
      //mDayItem.setContent(daymemo.getText()+"");
        mDayItem.setGoalsale(goalsale.getText()+"");
        mDayItem.setChallenge(nowItem);


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
        //daymemo = (EditText) findViewById(R.id.daymemo);
        daycontent = (Editor)findViewById(R.id.editorday);
        setUpEditor();
        goalsale = (EditText) findViewById(R.id.goalsale);
        daycontent.Render(mDayItem.getContent());
        //String b = "<p><strong>글자진하게</strong></p><p>평범하게</p><p><em>기울게</em></p><p><br></p><h1>h1이다</h1><p><br></p><p><br></p><h2>h2다</h2><p><br></p><p><br></p><h3>h3다</h3><p><br></p>";
        //daycontent.Render(b);
      //  daymemo.setText(mDayItem.getContent());
        goalsale.setText(mDayItem.getGoalsale());
        nowItem = mDayItem.getChallenge();
        spin= (Spinner)findViewById(R.id.dayspinner);

        //spin.setPrompt("도전과제");

      //  spin.setSelection(mDayItem.getChallenge());


//        String[] entries = {"","List Item A", "List Item B"};
//        ArrayList<String> ent = new ArrayList<>();
//        ent.add("");
//        ent.add("A");
//        ent.add("b");
     //   String[] entries = {"","List Item A", "List Item B"};

        if(mDayItem.getSpinnerItem()==null){
            String[] entries = {"도전 과제 없음"};
            ArrayAdapter<String> arrAdapt =
                    new ArrayAdapter<String>(this, R.layout.spinner_item, entries);
            spin.setAdapter(arrAdapt);
        }
        else {
            ArrayAdapter<String> arrAdapt =
                    new ArrayAdapter<String>(this, R.layout.spinner_item, mDayItem.getSpinnerItem());
            spin.setAdapter(arrAdapt);
            spin.setSelection(getItemNum(nowItem));
        }


//        adspin = ArrayAdapter.createFromResource(this, R.array.country,
//                R.layout.spinner_item);

      //  adspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  spin.setAdapter(adspin);
//        spin.setSelection(Integer.parseInt(mDayItem.getChallenge())-1);

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

				nowItem = spin.getItemAtPosition(position)+"";
				//itemPos = position+1;
                //Toast.makeText(DayModifyActivity.this,spin.getItemAtPosition(position)+"", Toast.LENGTH_SHORT).show();
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
        visitPos.setText(mDayItem.getVisitPoint());
        shortDis.setText(mDayItem.getShortDistance());
        super.onResume();
    }
}
