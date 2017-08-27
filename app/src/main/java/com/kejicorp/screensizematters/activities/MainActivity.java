package com.kejicorp.screensizematters.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;

import com.kejicorp.screensizematters.fragments.BalaceTab;
import com.kejicorp.screensizematters.fragments.ContactsTab;
import com.kejicorp.screensizematters.fragments.HistoryTab;
import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.helper.DatabaseHelper;


public class MainActivity extends AppCompatActivity {
   public Toolbar toolbar;
   public TabLayout tabLayout;
   public AppBarLayout appBarLayout;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
         toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        DatabaseHelper.getInstance(MainActivity.this,"balanacem.db");
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

         tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_account_circle_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_history_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_monetization_on_black_24dp);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        appBarLayout.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBlue)));
                        updateStatusBarColor("#03A9F4");
                        break;
                    case 1:

                        appBarLayout.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));
                        updateStatusBarColor("#E91E63");
                        break;
                    case 2:
                        appBarLayout.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGreen)));
                        updateStatusBarColor("#009688");
                        break;

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    ContactsTab contactsTab = new ContactsTab();
                    return contactsTab;
                case 1:
                    BalaceTab balaceTab = new BalaceTab();
                    return balaceTab;
                case 2:
                    HistoryTab historyTab = new HistoryTab();
                    return historyTab;
                 default:
                     return null;
            }


        }

        @Override
        public int getCount() {

            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Accounts";
                case 1:
                    return "Balances";
                case 2:
                    return "History";
            }
            return null;
        }



    }

    public void updateStatusBarColor(String color){// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}
