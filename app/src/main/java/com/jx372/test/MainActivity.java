package com.jx372.test;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.jx372.test.customermanagement.Customer;
import com.jx372.test.customermanagement.CustomerAdminFragment;
import com.jx372.test.customermanagement.CustomerList;
import com.jx372.test.customermanagement.CustomerModifyActivity;
import com.jx372.test.fragment.MypageFragment;
import com.jx372.test.fragment.TabMediaFragment;
import com.jx372.test.statistic.MemberStatisticFragment;
import com.jx372.test.statistic.MemberStatisticMileFragment;
import com.jx372.test.util.ImageUtil;
import com.jx372.test.view.PagerSlidingTabStrip;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.jx372.test.R.id.activity_tab_universal_pager;

public class MainActivity extends AppCompatActivity {
    private CustomerAdminFragment nowCustomerAdmin;
    private MemberStatisticFragment nowMemberStatistic;
    private CustomerList customerList;
    public static final String LEFT_MENU_OPTION = "com.csform.android.uiapptemplate.LeftMenusActivity";

    public static final String LEFT_MENU_OPTION_1 = "Universal Left Menu";
    public static final String LEFT_MENU_OPTION_2 = "Universal 2 Left Menu";

    private ListView mDrawerList;
    private List<DrawerItem> mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String nowYear="";
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private MyPagerAdapter adapter;
    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private String menuState="";
    private int[] sale;
    private String[] mPlanetTitles;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }

    // 매출액 리스트 콜백
    Callback2 chartSaleCallback = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                 Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);
                if (jsonbody.getString("result").equals("success")) {


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");

                    Statistic.get().initSaleList();
                    sale = Statistic.get().getSales();



                    for (int i = 0; i < size; i++) {


                        sale[datas.getJSONObject(i).getInt("month")] = datas.getJSONObject(i).getInt("total_sale");
                        Log.v("slaesale",sale[i+1]+"");
                    }
                    Statistic.get().setSales(sale);
                    nowMemberStatistic.updateUI();

                } else if (jsonbody.getString("result").equals("fail")) {
                 //   Toast.makeText(MainActivity.this, "데이터가 없습니다", Toast.LENGTH_SHORT).show();
                    Statistic.get().initSaleList();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };



    // 고객 리스트 콜백
    Callback2 selectCustomer = new Callback2() {
        @Override
        public void callback(String msg) {
            if (msg.equals("JsonException")) {
                // Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            if (msg.equals("ConnectFail")) {
                //Toast.makeText(TmapActivity.this, msg, Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                JSONObject jsonbody = new JSONObject(msg);
                Log.v("dayjson", msg);
                if (jsonbody.getString("result").equals("success")) {


                    JSONArray datas = jsonbody.getJSONArray("data");
                    int size = datas.length();
                    Log.v("size", String.valueOf(size));
                    Log.v("sizecontent", datas+"");
                    customerList = CustomerList.get(MainActivity.this);
                    customerList.cleanList();



                    for (int i = 0; i < size; i++) {

                        Customer c = new Customer(datas.getJSONObject(i).getString("customer_code"),
                                datas.getJSONObject(i).getString("name"),
                                datas.getJSONObject(i).getString("manager_name"),
                                datas.getJSONObject(i).getString("address"),
                                datas.getJSONObject(i).getString("contact"),
                                datas.getJSONObject(i).getString("manager_contact"),
                                datas.getJSONObject(i).getString("manager_email"),
                                datas.getJSONObject(i).getString("time"),
                                datas.getJSONObject(i).getString("positionX"),
                                datas.getJSONObject(i).getString("positionY")
                        );

                        customerList.addCustomer(c);

                        // Log.v("customer들어오니",datas.getJSONObject(i).getString("name"));
                    }
                    nowCustomerAdmin.updateUI();

                } else if (jsonbody.getString("result").equals("fail")) {

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nowYear = "2017";
        Statistic.get().initSaleList();
//        HttpConnector httpcon = new HttpConnector();
//        Map map = new HashMap();
//        map.put("id", User.get().getId());
//        map.put("date",nowYear);
//        httpcon.accessServerMap("chartsales", map, chartSaleCallback);


        ActionBar actionBar = getSupportActionBar();
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        }




        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
             //   Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
               // Toast.makeText(MainActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }


        };


        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setRationaleTitle(R.string.rationale_title)
                .setRationaleMessage(R.string.rationale_message)
                .setDeniedTitle("Permission denied")
                .setDeniedMessage(
                        "If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setGotoSettingButtonText("bla bla")
                .setPermissions(Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA, android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("SFA");
        setSupportActionBar(toolbar);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_tab_universal_tabs);
        pager = (ViewPager) findViewById(activity_tab_universal_pager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
//        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
//                .getDisplayMetrics());
     //   pager.setPageMargin(pageMargin);
        pager.setCurrentItem(User.get().getCurrentPager());
if(User.get().getCurrentPager()==1){
        menuState = "customer";
        invalidateOptionsMenu();
}
pager.setOffscreenPageLimit(1);

//        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
//            @Override
//            public void onTabReselected(int position) {
//                findViewById(activity_tab_universal_pager).setVisibility(View.VISIBLE);
//                Toast.makeText(MainActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0){
                    menuState = "commute";
                    invalidateOptionsMenu();


                }
                else if(position == 1){
                    menuState = "customer";
                    invalidateOptionsMenu();
                    HttpConnector httpcon = new HttpConnector();
                    Map map = new HashMap();
                    map.put("id", User.get().getId());
                    httpcon.accessServerMap("customerselect", map, selectCustomer);


                }
                else if(position == 2){
                    menuState = "statistic";
                    invalidateOptionsMenu();
                    HttpConnector httpcon = new HttpConnector();
                    Map map = new HashMap();
                    map.put("id", User.get().getId());
                    map.put("date",nowYear);
                    httpcon.accessServerMap("chartsales", map, chartSaleCallback);

                }

                else if (position == 3) {
                    menuState = "";
                    invalidateOptionsMenu();
                }
                else {
                    menuState = "";
                    invalidateOptionsMenu();



                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mTitle = mDrawerTitle = getTitle();
        mDrawerList = (ListView) findViewById(R.id.list_view);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.END);
        prepareNavigationDrawerItems();

       // setAdapter();

        mPlanetTitles = getResources().getStringArray(R.array.member_array);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));



        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {

                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
               findViewById(activity_tab_universal_pager).setVisibility(View.VISIBLE);
            }

            public void onDrawerOpened(View drawerView) {
                findViewById(activity_tab_universal_pager).setVisibility(View.GONE);
                getSupportActionBar().setTitle(mDrawerTitle);

                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);




//        if (savedInstanceState == null) {
//            mDrawerLayout.openDrawer(mDrawerList);
//        }

        //메인 문제 생기면 이 부분 돌린다 위랑
        findViewById(activity_tab_universal_pager).setVisibility(View.VISIBLE);



    }




    private void setAdapter() {
        String option = LEFT_MENU_OPTION_1;
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(LEFT_MENU_OPTION)) {
            option = extras.getString(LEFT_MENU_OPTION, LEFT_MENU_OPTION_1);
        }

        boolean isFirstType = true;
//
//        View headerView = null;
//        if (option.equals(LEFT_MENU_OPTION_1)) {
//            headerView = prepareHeaderView(R.layout.header_navigation_drawer_1,
//                    "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg",
//                    "dev@csform.com");
//        } else if (option.equals(LEFT_MENU_OPTION_2)) {
//            headerView = prepareHeaderView(R.layout.header_navigation_drawer_2,
//                    "http://pengaja.com/uiapptemplate/newphotos/profileimages/0.jpg",
//                    "dev@csform.com");
//            isFirstType = false;
//        }



        View headerView = null;
        headerView = prepareHeaderView(R.layout.header_navigation_drawer_2,
                    "https://avatars1.githubusercontent.com/u/482271?v=4&s=400",
                    "Dae H. Ahn");
            isFirstType = false;

        BaseAdapter adapter = new DrawerAdapter(this, mDrawerItems, isFirstType);

        mDrawerList.addHeaderView(headerView);// Add header before adapter (for
        // pre-KitKat)
        mDrawerList.setAdapter(adapter);
    }

    private View prepareHeaderView(int layoutRes, String url, String email) {
        View headerView = getLayoutInflater().inflate(layoutRes, mDrawerList,
                false);
        ImageView iv = (ImageView) headerView.findViewById(R.id.image);
        TextView tv = (TextView) headerView.findViewById(R.id.email);

        ImageUtil.displayRoundImage(iv, url, null);
        tv.setText(User.get().getDept()+" / "+User.get().getGrade()+"\n"+User.get().getName());

        return headerView;
    }

    private void prepareNavigationDrawerItems() {
        mDrawerItems = new ArrayList<>();
//        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_linked_in,
//                R.string.drawer_title_linked_in,
//                DrawerItem.DRAWER_ITEM_TAG_LINKED_IN));

        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_blog,
                R.string.drawer_title_blog, DrawerItem.DRAWER_ITEM_TAG_BLOG));
        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_git_hub,
                R.string.drawer_title_git_hub,
                DrawerItem.DRAWER_ITEM_TAG_GIT_HUB));
        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_instagram,
                R.string.drawer_title_instagram,
                DrawerItem.DRAWER_ITEM_TAG_INSTAGRAM));

        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_wizards,
                R.string.drawer_title_wizards,
                DrawerItem.DRAWER_ITEM_TAG_TABS));



    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        if(menuState.equals("statistic")){

            getMenuInflater().inflate(R.menu.menu_member_statistic, menu);
        }
        else if(menuState.equals("commute")){
            getMenuInflater().inflate(R.menu.menu_leader_logout, menu);
        }
        else if(menuState.equals("customer")){
            getMenuInflater().inflate(R.menu.menu_member_main_customer, menu);
        }

        else{

            getMenuInflater().inflate(R.menu.menu_leader_logout, menu);
        }

        return true;



//
//
//        getMenuInflater().inflate(R.menu.main, menu);
//        getMenuInflater().inflate(R.menu.menu_main,menu);
//
//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        System.out.println("=======>menu"+itemId);

        if(item.getItemId()==R.id.addcustomer){


            Intent i = new Intent(this,CustomerModifyActivity.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.logout){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.statisticPre){
            nowYear = (Integer.parseInt(nowYear) -1)+"";
            getSupportActionBar().setTitle(nowYear);
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date",nowYear);
            HttpConnector httpcon = new HttpConnector();
            httpcon.accessServerMap("chartsales", map, chartSaleCallback);

        }

        else if(item.getItemId()==R.id.statisticNext){
            nowYear = (Integer.parseInt(nowYear) +1)+"";
            getSupportActionBar().setTitle(nowYear);
            Map map = new HashMap();
            map.put("id", User.get().getId());
            map.put("date",nowYear);
            HttpConnector httpcon = new HttpConnector();
            httpcon.accessServerMap("chartsales", map, chartSaleCallback);

        }

        if(itemId == R.id.refresh){

            User.get().setCurrentPager(1);
            Intent intent = getIntent();
            finish();
            startActivity(intent);


        }

        if(itemId == R.id.logout){

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        }
        if(itemId == android.R.id.home){
            System.out.println("============>home button clicked");
        }


        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position/* , mDrawerItems.get(position - 1).getTag() */);
        }
    }

    private void selectItem(int position/* , int drawerTag */) {
        // minus 1 because we have header that has 0 position
        if (position < 1) { // because we have header, we skip clicking on it
            return;
        }
        String drawerTitle = getString(mDrawerItems.get(position - 1)
                .getTitle());
       // Toast.makeText(this, "You selected " + drawerTitle + " at position: " + position, Toast.LENGTH_SHORT).show();

//if(position==1) {
//    Intent i = new Intent(this, JoinActivity.class);
//    startActivity(i);
////        getSupportFragmentManager().beginTransaction().add( R.id.content_frame, new JoinFragment()).commit();
////        findViewById(R.id.activity_tab_universal_tabs).setVisibility(View.GONE);
//   // getSupportActionBar().setTitle("팀원등록");
//    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//}
//

if(position==1){
    Intent i = new Intent(this, WeekPlanActivity.class);
    startActivity(i);
   // getSupportActionBar().setTitle("주간계획작성");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
else if(position==2){
    Intent i = new Intent(this, DayActivity.class);
    startActivity(i);
    //getSupportActionBar().setTitle("일일계획작성");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
else if(position==3){
    Intent i = new Intent(this, WorkReportActivity.class);
    startActivity(i);
    //getSupportActionBar().setTitle("업무보고");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
else if(position==4){
    Intent intent = this.getPackageManager().getLaunchIntentForPackage("com.playrtc.sample");

    startActivity(intent);
}



        mDrawerList.setItemChecked(position, true);
        setTitle(mDrawerItems.get(position - 1).getTitle());
        mDrawerLayout.closeDrawer(mDrawerList);


    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }






    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<String> tabNames = new ArrayList<String>() {{
            add("공지사항");
            add("업체관리");
            add("매출통계");
            add("주행거리통계");
            add("마이페이지");
        }};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void clearList(){
            tabNames.clear();
        }

        @Override

        public int getItemPosition(Object object) {

            return POSITION_NONE;

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
            if(position == 0) {

                return TabMediaFragment.newInstance(position);
            }
            else if(position == 1) {

                nowCustomerAdmin =  CustomerAdminFragment.newInstance(position);
                return nowCustomerAdmin;


            }
            else if(position == 2) {
                nowMemberStatistic = MemberStatisticFragment.newInstance(position);
                return nowMemberStatistic;
            }
            else if(position == 3) {
                //return TabShopFragment.newInstance(position);
                return MemberStatisticMileFragment.newInstance(position);
                //return TabTravelFragment.newInstance(position);
            }
            else if(position==4){
                return MypageFragment.newInstance(position);
            }
            else{
                return MypageFragment.newInstance(position);
            }
        }
    }
}
