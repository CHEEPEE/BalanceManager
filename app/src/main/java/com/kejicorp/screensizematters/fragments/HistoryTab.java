package com.kejicorp.screensizematters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kejicorp.screensizematters.R;
import com.kejicorp.screensizematters.activities.MainActivity;
import com.kejicorp.screensizematters.adapters.HistoryTabRecyclerViewAdapter;

/**
 * Created by Keji's Lab on 21/08/2017.
 */

public class HistoryTab extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.history, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle_listview);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        recyclerView.setHasFixedSize(true);
        //use a linear layout manager

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        String[] myDataset = {"test1","test2","Test 3"};


        mAdapter = new HistoryTabRecyclerViewAdapter(getContext(),myDataset);
        recyclerView.setAdapter(mAdapter);
        ;

        //specifiy an adapter


        return rootView;
    }
}
