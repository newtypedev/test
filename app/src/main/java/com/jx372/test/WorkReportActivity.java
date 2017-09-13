package com.jx372.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.fragment.ConsultFragment;
import com.jx372.test.fragment.TabMediaFragment;
import com.jx372.test.fragment.TabShopFragment;
import com.jx372.test.fragment.TabSocialFragment;
import com.jx372.test.fragment.TabTravelFragment;
import com.jx372.test.fragment.TabUniversalFragment;
import com.jx372.test.fragment.WorkReportFragment;
import com.jx372.test.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.jx372.test.R.id.activity_tab_universal_pager;
import static com.jx372.test.R.id.activity_tab_universal_pager2;

/**
 * Created by pys on 2017. 9. 5..
 */

public class WorkReportActivity extends AppCompatActivity {


    private WorkReportActivity.MyPagerAdapter adapter;
    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private boolean menuState=true;
    private TextView reportDate;
    Calendar cal;
    int mYear, mMonth, mDay;
    private ReportItems reportItems;


    public String getDay(){
        String year =  cal.get(Calendar.YEAR)+"";
        String month = (cal.get(Calendar.MONTH)+1)+"";
        String day = cal.get(Calendar.DAY_OF_MONTH)+"";

        if(month.length()==1){
            month ="0"+month;
        }

        if(day.length()==1){
            day ="0"+day;
        }

        String firstDay = year+"-"+month+"-"+day;
        return firstDay;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        if(menuState){

            inflater.inflate(R.menu.menu_report, menu);
        }
        else{

            inflater.inflate(R.menu.menu_consult, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.previous) {
            cal.add(cal.DAY_OF_MONTH,-1);
            reportDate.setText(getDay());

            return true;
        }

        else if (id == R.id.next) {
            cal.add(cal.DAY_OF_MONTH,+1);
            reportDate.setText(getDay());
            return true;
        }
        else if(id == R.id.writeconsult){
            Intent i = new Intent(this,ConsultActivity.class);
            startActivity(i);
        }


        // itemUpdate();
        //onBackPressed();
//        if(item.getItemId() == R.id.writeconsult){
//            //itemUpdate();
//            //    NavUtils.navigateUpFromSameTask(this);
//          //  onBackPressed();
//
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
        setContentView(R.layout.activity_report);
        reportDate = (TextView)findViewById(R.id.reportDate);
        reportItems = ReportItems.get();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("업무보고");

        cal = new GregorianCalendar();
        reportDate.setText(getDay());
//        mYear = cal.get(Calendar.YEAR);
//
//        mMonth = cal.get(Calendar.MONTH);
//
//        mDay = cal.get(Calendar.DAY_OF_MONTH);







        getSupportActionBar().setTitle("업무보고");
//        toolbar = (Toolbar) findViewById(R.id.toolbar2);
//        toolbar.setTitle("업무보고");
//        setSupportActionBar(toolbar);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_tab_universal_tabs2);
        //tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        pager = (ViewPager) findViewById(activity_tab_universal_pager2);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
//        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources()
//                .getDisplayMetrics());
//        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);

//        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
//            @Override
//            public void onTabReselected(int position) {
//                findViewById(activity_tab_universal_pager2).setVisibility(View.VISIBLE);
//                Toast.makeText(WorkReportActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    menuState = true;
                    invalidateOptionsMenu();
                }
                else {
                    menuState = false;
                    invalidateOptionsMenu();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<String> tabNames = new ArrayList<String>() {{
            add("업무보고");
            add("상담일지");

        }};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

        @Override
        public int getCount() {
            return tabNames.size();
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return WorkReportFragment.newInstance(position);
            }
            else {

                return ConsultFragment.newInstance(position);
            }
        }
    }
}
