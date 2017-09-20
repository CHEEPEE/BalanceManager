package com.kejicorp.screensizematters.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.adapters.BalanceTabListViewAdapter;
import com.kejicorp.screensizematters.helper.DatabaseHelper;
import com.kejicorp.screensizematters.models.BalanceModelList;
import com.kejicorp.screensizematters.utils.UtilDatabaseStrings;

import java.util.ArrayList;

/**
 * Created by Keji's Lab on 21/08/2017.
 */

public class BalaceTab extends Fragment {
    private static BalanceTabListViewAdapter balanceTabListViewAdapter;
    private static ListView balanceListView;
    private static Context context;
    LinearLayout balanceTabContent;
    private static ArrayList<BalanceModelList> balanceModelLists = new ArrayList<BalanceModelList>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.balance, container, false);
        context = getActivity();
        balanceListView = (ListView) rootView.findViewById(R.id.lv_balance_tab);
        balanceTabContent = (LinearLayout) rootView.findViewById(R.id.main_content);
        datachange();

        return rootView;
    }
    public static void datachange(){
        balanceModelLists.clear();
        UtilDatabaseStrings getStrings = new UtilDatabaseStrings();
        String query = "Select * from "+getStrings.tb_balance_manager+" where status = 'unpaid';";
        Cursor c1=DatabaseHelper.rawQuery(query);
        c1.moveToFirst();
        if (c1.getCount() != 0 && c1!=null) {
            if (c1.moveToFirst()) {
                do {
                    BalanceModelList balancemode = new BalanceModelList();
                    balancemode.setItemId(c1.getString(c1.getColumnIndex("id")));
                    System.out.println(c1.getString(c1.getColumnIndex(getStrings.tb_b_id)));
                    balancemode.setUsername(c1.getString(c1.getColumnIndex(getStrings.tb_b_users)));
                    balancemode.setBalance(c1.getString(c1.getColumnIndex(getStrings.tb_b_balance)));
                    balancemode.setDescription(c1.getString(c1.getColumnIndex(getStrings.tb_b_description)));
                    balancemode.setDate(c1.getString(c1.getColumnIndex(getStrings.tb_b_preDate)));
                    balanceModelLists.add(balancemode);

                } while(c1.moveToNext());
            }
        }
        c1.close();


        balanceTabListViewAdapter = new BalanceTabListViewAdapter(context,balanceModelLists);
        balanceListView.setAdapter(balanceTabListViewAdapter);
        balanceTabListViewAdapter.notifyDataSetChanged();
        //snakbar to confirm paid payment
        balanceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Snackbar.make(balanceListView,
                        "Confirm Paid?", Snackbar.LENGTH_LONG).setAction("Yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      String itemId =  balanceModelLists.get(position).getId();
                        System.out.println(balanceModelLists.get(position).getUserName()+" "+balanceModelLists.get(position).getBalance()+" "+itemId);
                      String update = "Update "+UtilDatabaseStrings.tb_balance_manager+" set "+UtilDatabaseStrings.tb_b_status+" ='paid'"
                              +" where id="+itemId+";";
                        DatabaseHelper.execute(update);
                        datachange();


                    }
                }).show();
            }
        });
    }
}
