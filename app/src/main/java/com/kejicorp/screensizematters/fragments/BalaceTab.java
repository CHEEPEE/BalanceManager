package com.kejicorp.screensizematters.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.balance, container, false);
        Context context = getActivity();
        ListView balanceListView = (ListView) rootView.findViewById(R.id.lv_balance_tab);
        ArrayList<BalanceModelList> balanceModelLists = new ArrayList<BalanceModelList>();
        BalanceTabListViewAdapter balanceTabListViewAdapter;

        String query = "Select * from "+UtilDatabaseStrings.tb_balance_manager+";";
        Cursor c1=DatabaseHelper.rawQuery(query);
        c1.moveToFirst();
        if (c1.getCount() != 0 && c1!=null) {
            if (c1.moveToFirst()) {
                do {
                    BalanceModelList balancemode = new BalanceModelList();
                    balancemode.setUsername(c1.getString(c1.getColumnIndex(UtilDatabaseStrings.tb_b_users)));
                    balancemode.setBalance(c1.getString(c1.getColumnIndex(UtilDatabaseStrings.tb_b_balance)));
                    balancemode.setDescription(c1.getString(c1.getColumnIndex(UtilDatabaseStrings.tb_b_description)));

                    balanceModelLists.add(balancemode);

                } while(c1.moveToNext());
            }
        }
        c1.close();
        balanceTabListViewAdapter = new BalanceTabListViewAdapter(context,balanceModelLists);
        balanceListView.setAdapter(balanceTabListViewAdapter);




        return rootView;
    }
}