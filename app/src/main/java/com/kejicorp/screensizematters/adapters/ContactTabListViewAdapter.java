package com.kejicorp.screensizematters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.models.ContactModelList;

import java.util.ArrayList;

/**
 * Created by Keji's Lab on 27/08/2017.
 */

public class ContactTabListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<ContactModelList> ArraylistContactlistModel;
    public ContactTabListViewAdapter(Context context, ArrayList<ContactModelList> contactModelLists){
    this.context = context;
    this.ArraylistContactlistModel = contactModelLists;


    }

    @Override
    public int getCount() {
        return ArraylistContactlistModel.size();
    }

    @Override
    public Object getItem(int position) {
        return ArraylistContactlistModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactModelList contactModelList = ArraylistContactlistModel.get(position);
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.people_item_single, null);
        }
        TextView userName = (TextView) convertView.findViewById(R.id.username);
        TextView total_balance = (TextView) convertView.findViewById(R.id.total_balance);
        TextView contact_number = (TextView) convertView.findViewById(R.id.contact_number);
        TextView icon_text = (TextView) convertView.findViewById(R.id.icon_text);
        icon_text.setText(contactModelList.getUserName().substring(0,1));
        userName.setText(contactModelList.getUserName());
        total_balance.setText("₱ "+contactModelList.getTotal_balance()+".00");
        contact_number.setText(contactModelList.getContact_number());


        return convertView;
    }

}
