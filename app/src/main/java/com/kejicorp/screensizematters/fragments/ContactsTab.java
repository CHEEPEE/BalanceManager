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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.numberlis, container, false);
        Context context = getActivity();
        ListView lv = (ListView) rootView.findViewById(R.id.lv_number_list);
        lv.setEmptyView(rootView.findViewById(R.id.empty));
        ArrayList<ContactModelList> contactModelLists = new ArrayList<ContactModelList>();
        ContactTabListViewAdapter contactTabListViewAdapter;

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



        return rootView;
    }
}
