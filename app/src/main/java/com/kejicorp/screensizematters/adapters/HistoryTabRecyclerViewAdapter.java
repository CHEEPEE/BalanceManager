package com.kejicorp.screensizematters.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kejicorp.screensizematters.R;

/**
 * Created by Keji's Lab on 11/09/2017.
 */

public class HistoryTabRecyclerViewAdapter extends RecyclerView.Adapter<HistoryTabRecyclerViewAdapter.ViewHolder> {
    private String[] mDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;

        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.username);
        }
    }
    public HistoryTabRecyclerViewAdapter(Context c,String[] myDatabaseSet){
        mDataset = myDatabaseSet;
        context = c;
    }

    @Override
    public HistoryTabRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item_single,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
