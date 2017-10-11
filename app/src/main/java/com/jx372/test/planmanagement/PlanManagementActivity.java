//package com.jx372.test.planmanagement;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v7.app.AppCompatActivity;
//
//import com.jx372.test.R;
//import com.jx372.test.fragment.ConsultFragment;
//import com.jx372.test.fragment.WorkReportListFragment;
//
//import java.util.ArrayList;
//
///**
// * Created by pys on 2017. 10. 2..
// */
//
//public class PlanManagementActivity extends AppCompatActivity {
//
//    private DayPlanFragment nowDay;
//    private WeekPlanFragment nowWeek;
//    private WorkApprovalFragment nowApproval;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_planmanagement);
//    }
//
//
//    public class MyPagerAdapter extends FragmentPagerAdapter {
//
//        private final ArrayList<String> tabNames = new ArrayList<String>() {{
//            add("업무보고");
//            add("상담일지");
//            add("첨부파일");
//
//        }};
//
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabNames.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return tabNames.size();
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            if (position == 0) {
//                nowDay = DayPlanFragment.newInstance(position);
//                return nowDay;
//            }
//            else if(position ==1){
//                nowConsulFragment = ConsultFragment.newInstance(position);
//                return nowConsulFragment;
//            }
//            else if(position ==2){
//                nowConsulFragment = ConsultFragment.newInstance(position);
//                return nowConsulFragment;
//            }
//            else {
//
//                return ConsultFragment.newInstance(position);
//            }
//        }
//    }
//}
