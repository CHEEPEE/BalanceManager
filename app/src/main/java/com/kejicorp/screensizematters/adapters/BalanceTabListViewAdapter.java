package com.kejicorp.screensizematters.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.models.BalanceModelList;
import com.kejicorp.screensizematters.models.ContactModelList;

import java.util.ArrayList;

/**
 * Created by Keji's Lab on 27/08/2017.
 */

public class BalanceTabListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<BalanceModelList> ArraylistBalanceTab;
    public BalanceTabListViewAdapter(Context context, ArrayList<BalanceModelList> balanceModelLists){
    this.context = context;
    this.ArraylistBalanceTab = balanceModelLists;


    }

    @Override
    public int getCount() {
        return ArraylistBalanceTab.size();
    }

    @Override
    public Object getItem(int position) {
        return ArraylistBalanceTab.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BalanceModelList balanceModelList = ArraylistBalanceTab.get(position);
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_balance_single, null);
        }
        TextView user = (TextView) convertView.findViewById(R.id.name);
        TextView description = (TextView) convertView.findViewById(R.id.des);
        TextView balance = (TextView) convertView.findViewById(R.id.balance);
        TextView date = (TextView)convertView.findViewById(R.id.date);


        user.setText(balanceModelList.getUserName());
        description.setText(balanceModelList.getDescription());
        balance.setText("₱ "+balanceModelList.getBalance()+".00");
        date.setText(balanceModelList.getDate());


        return convertView;
    }
}
