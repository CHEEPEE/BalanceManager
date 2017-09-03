package com.kejicorp.screensizematters.fragments;

/**
 * Created by Keji's Lab on 21/08/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.kejicorp.screensizematters.adapters.ContactTabListViewAdapter;
import com.kejicorp.screensizematters.helper.DatabaseHelper;
import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.models.ContactModelList;
import com.kejicorp.screensizematters.utils.UtilDatabaseStrings;

import java.util.ArrayList;

public class ContactsTab extends Fragment {
    private static ListView lv;
    private static ArrayList<ContactModelList> contactModelLists = new ArrayList<ContactModelList>();
    private static Context context;
    private static  ContactTabListViewAdapter contactTabListViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.numberlis, container, false);
        context = getActivity();
        lv = (ListView) rootView.findViewById(R.id.lv_number_list);
        lv.setEmptyView(rootView.findViewById(R.id.empty));
        callData();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                return false;
            }
        });

        return rootView;
    }

    public static void callData(){
        contactModelLists.clear();
        String query = "Select * from "+UtilDatabaseStrings.tb_users_manager+";";

        Cursor cursor = DatabaseHelper.rawQuery(query);
        cursor.moveToFirst();
        if (cursor != null && cursor.getCount() != 0){
            if (cursor.moveToFirst()){
                do {
                    ContactModelList contactModelList = new ContactModelList();

                    contactModelList.setUsername(cursor.getString(cursor.getColumnIndex(UtilDatabaseStrings.tb_u_users)));
                    contactModelList.setTotal_balance(cursor.getString(cursor.getColumnIndex(UtilDatabaseStrings.tb_u_totalBalance.toString())));
                    contactModelList.setContact_number(cursor.getString(cursor.getColumnIndex(UtilDatabaseStrings.tb_u_user_contact)));

                    contactModelLists.add(contactModelList);

                }while (cursor.moveToNext());
            }
        }
        cursor.close();

        contactTabListViewAdapter = new ContactTabListViewAdapter(context,contactModelLists);
        lv.setAdapter(contactTabListViewAdapter);
        contactTabListViewAdapter.notifyDataSetChanged();

    }
}
