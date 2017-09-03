package com.kejicorp.screensizematters.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kejicorp.screensizematters.fragments.BalaceTab;
import com.kejicorp.screensizematters.fragments.ContactsTab;
import com.kejicorp.screensizematters.fragments.HistoryTab;
import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.helper.DatabaseHelper;
import com.kejicorp.screensizematters.utils.UtilDatabaseStrings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
   public Toolbar toolbar;
   public TabLayout tabLayout;
   public AppBarLayout appBarLayout;
    FloatingActionButton fab;
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
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAccounts(MainActivity.this);
            }
        });

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
                        fab.show();

                        break;
                    case 1:

                        appBarLayout.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorRed)));
                        updateStatusBarColor("#E91E63");
                        fab.hide();


                        break;
                    case 2:
                        appBarLayout.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorGreen)));
                        updateStatusBarColor("#009688");
                        fab.hide();
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





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



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

    private ArrayAdapter<String> getName(Context context) {
        ArrayList<String> names = new ArrayList<>();
        Cursor cursor = DatabaseHelper.rawQuery("Select * from tb_user_manager;");
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() != 0){
            if (cursor.moveToFirst()){
                do {
                    names.add(cursor.getString(cursor.getColumnIndex(UtilDatabaseStrings.tb_u_users)));

                }while (cursor.moveToNext());
            }
        }
        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, names);
    }
    private void addAccounts(final Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_add_accounts);
        final AutoCompleteTextView user = (AutoCompleteTextView) dialog.findViewById(R.id.input_username);
        final EditText input_number = (EditText) dialog.findViewById(R.id.input_number);
        final EditText input_des = (EditText) dialog.findViewById(R.id.input_des);
        final EditText input_bal = (EditText) dialog.findViewById(R.id.input_balance);
        dialog.show();
        user.setAdapter(getName(MainActivity.this));
        user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String gotText = user.getText().toString();
                String phoneNumber;
                String query = "Select * from "+UtilDatabaseStrings.tb_users_manager+" where "+UtilDatabaseStrings.tb_u_users+
                        " ='"+gotText+"';";
                Cursor cursor = DatabaseHelper.rawQuery(query);
                cursor.moveToFirst();
                if (cursor != null && cursor.getCount() != 0){
                    if (cursor.moveToFirst()){
                        do {
                            phoneNumber = cursor.getString(cursor.getColumnIndex(UtilDatabaseStrings.tb_u_user_contact));
                            input_number.setText(phoneNumber);
                        }while (cursor.moveToNext());
                    }
                }
            }
        });
        RelativeLayout save = (RelativeLayout) dialog.findViewById(R.id.save_account);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAccount(user.getText().toString(),input_bal.getText().toString(),input_number.getText().toString(),input_des.getText().toString());
                BalaceTab balaceTab = new BalaceTab();
                balaceTab.datachange();
                ContactsTab contactsTab = new ContactsTab();
                contactsTab.callData();
                dialog.hide();
            }
        });




    }

    private void snacks(String text, View view){
        Snackbar.make(view, "text", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void saveAccount(String user,String bal,String pNumber,String des){
        Integer i=0;
        String query = "Select * from "+UtilDatabaseStrings.tb_users_manager+" where "+UtilDatabaseStrings.tb_u_users+"= '"
                +user+"';";
        Cursor cursor = DatabaseHelper.rawQuery(query);
        cursor.moveToFirst();
        ArrayList<Integer> balances = new ArrayList<>();
        if (cursor.getCount() != 0 && cursor !=null){

            String timeStamp = UtilDatabaseStrings.formatTheDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
            Toast.makeText(MainActivity.this,cursor.getCount()+"",Toast.LENGTH_SHORT).show();
            String insert = "Insert Into "+UtilDatabaseStrings.tb_balance_manager+"("+UtilDatabaseStrings.tb_b_users
                    +","+UtilDatabaseStrings.tb_b_balance+","+UtilDatabaseStrings.tb_b_description+
                    ","+UtilDatabaseStrings.tb_b_date+") values('"+user+"','"+bal+"','"+des+"','"+timeStamp+"');";
            DatabaseHelper.execute(insert);


        }
        else {
            String timeStamp = UtilDatabaseStrings.formatTheDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
            String insert_onUsers = "insert into "+UtilDatabaseStrings.tb_users_manager+" ("+UtilDatabaseStrings.tb_u_users+","+
                    UtilDatabaseStrings.tb_u_user_contact+") values('"+user+"','"+pNumber+"');";
            DatabaseHelper.execute(insert_onUsers);
            String insert = "Insert Into "+UtilDatabaseStrings.tb_balance_manager+"("+UtilDatabaseStrings.tb_b_users
                    +","+UtilDatabaseStrings.tb_b_balance+","+UtilDatabaseStrings.tb_b_description+
                    ") values('"+user+"','"+bal+"','"+des+"','"+timeStamp+"');";
            DatabaseHelper.execute(insert);


        }


        String q = "Select * from "+UtilDatabaseStrings.tb_balance_manager+" where "+UtilDatabaseStrings.tb_b_users+
                "= '"+user+"';";
        Cursor c = DatabaseHelper.rawQuery(q);

        c.moveToFirst();
        if (c.getCount() !=0 && c != null){
            if (c.moveToFirst()){
                do {
                    i = i+ Integer.parseInt(c.getString(c.getColumnIndex(UtilDatabaseStrings.tb_b_balance)));
                    System.out.println(i+"");
                    balances.add(Integer.parseInt(c.getString(c.getColumnIndex(UtilDatabaseStrings.tb_b_balance))));
                    System.out.println(c.getString(c.getColumnIndex(UtilDatabaseStrings.tb_b_balance)));

                }while (c.moveToNext());
            }
        }
        String update = "Update "+UtilDatabaseStrings.tb_users_manager+" set "+UtilDatabaseStrings.tb_u_totalBalance+" = '"+i+"' where "+UtilDatabaseStrings.tb_u_users+
                "='"+user+"';";
        DatabaseHelper.execute(update);

    }


}
