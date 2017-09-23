package com.jx372.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.jx372.test.fragment.ReportListFragment;

/**
 * Created by pys on 2017. 9. 22..
 */

public class ReportListActivity  extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportlist);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

            fragment = new ReportListFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();


    }


}
