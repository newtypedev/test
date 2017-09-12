package com.jx372.test;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jx372.test.fragment.JoinFragment;
import com.jx372.test.fragment.TabMediaFragment;
import com.jx372.test.fragment.TabShopFragment;
import com.jx372.test.fragment.TabSocialFragment;
import com.jx372.test.fragment.TabTravelFragment;
import com.jx372.test.fragment.TabUniversalFragment;
import com.jx372.test.util.ImageUtil;
import com.jx372.test.view.PagerSlidingTabStrip;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import static com.jx372.test.R.id.activity_tab_universal_pager;

public class MainActivity extends AppCompatActivity {


    public static final String LEFT_MENU_OPTION = "com.csform.android.uiapptemplate.LeftMenusActivity";

    public static final String LEFT_MENU_OPTION_1 = "Universal Left Menu";
    public static final String LEFT_MENU_OPTION_2 = "Universal 2 Left Menu";

    private ListView mDrawerList;
    private List<DrawerItem> mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private MyPagerAdapter adapter;
    private Toolbar toolbar;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tabs universal");
        setSupportActionBar(toolbar);
        tabs = (PagerSlidingTabStrip) findViewById(R.id.activity_tab_universal_tabs);
        pager = (ViewPager) findViewById(activity_tab_universal_pager);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        pager.setCurrentItem(0);

        tabs.setOnTabReselectedListener(new PagerSlidingTabStrip.OnTabReselectedListener() {
            @Override
            public void onTabReselected(int position) {
                findViewById(activity_tab_universal_pager).setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Tab reselected: " + position, Toast.LENGTH_SHORT).show();
            }
        });






        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mTitle = mDrawerTitle = getTitle();
        mDrawerList = (ListView) findViewById(R.id.list_view);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.END);
        prepareNavigationDrawerItems();
        setAdapter();
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

        if (savedInstanceState == null) {
            mDrawerLayout.openDrawer(mDrawerList);
        }
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
        tv.setText(email);

        return headerView;
    }

    private void prepareNavigationDrawerItems() {
        mDrawerItems = new ArrayList<>();
        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_linked_in,
                R.string.drawer_title_linked_in,
                DrawerItem.DRAWER_ITEM_TAG_LINKED_IN));
        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_blog,
                R.string.drawer_title_blog, DrawerItem.DRAWER_ITEM_TAG_BLOG));
        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_git_hub,
                R.string.drawer_title_git_hub,
                DrawerItem.DRAWER_ITEM_TAG_GIT_HUB));
        mDrawerItems.add(new DrawerItem(R.string.drawer_icon_instagram,
                R.string.drawer_title_instagram,
                DrawerItem.DRAWER_ITEM_TAG_INSTAGRAM));

    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int itemId = item.getItemId();

        System.out.println("=======>menu"+itemId);

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
        Toast.makeText(this,
                "You selected " + drawerTitle + " at position: " + position,
                Toast.LENGTH_SHORT).show();

if(position==1) {
    Intent i = new Intent(this, JoinActivity.class);
    startActivity(i);
//        getSupportFragmentManager().beginTransaction().add( R.id.content_frame, new JoinFragment()).commit();
//        findViewById(R.id.activity_tab_universal_tabs).setVisibility(View.GONE);
    getSupportActionBar().setTitle("팀원등록");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
else if(position==2){
    Intent i = new Intent(this, WeekPlanActivity.class);
    startActivity(i);
    getSupportActionBar().setTitle("주간계획작성");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
else if(position==3){
    Intent i = new Intent(this, DayActivity.class);
    startActivity(i);
    getSupportActionBar().setTitle("일일계획작성");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            add("주간계획");
            add("업무보고");
            add("업체관리");
            add("제품검색");
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
            if(position == 0) {

                return TabMediaFragment.newInstance(position);
            }
            else if(position == 1) {
                return TabShopFragment.newInstance(position);
            }
            else if(position == 2) {
                return TabSocialFragment.newInstance(position);
            }
            else if(position == 3) {
                return TabTravelFragment.newInstance(position);
            }
            else{
                return TabUniversalFragment.newInstance(position);
            }
        }
    }
}
