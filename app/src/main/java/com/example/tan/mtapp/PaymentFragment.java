package com.example.tan.mtapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tan.mtapp.Adapter.ConfirmAdapter;
import com.example.tan.mtapp.Adapter.CustomDialog_detail;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.HistoryMedel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    RecyclerView mRecyclerView;
    ConfirmAdapter adapter;

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.reHis);
        adapter = new ConfirmAdapter(getContext(), StaticClass.HISTORY_MODEL, new ConfirmAdapter.OnItemClickListener() {
            @Override
            public void moreClick(HistoryMedel.DetailBean activityAdapters, int position) {
                CustomDialog_detail cdd = new CustomDialog_detail(getActivity(),activityAdapters);
                cdd.show();
            }
        });
        mRecyclerView.setAdapter(adapter);
        GridLayoutManager  mGridLayoutManager = new GridLayoutManager(getContext(), 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanGroupIndex(int adapterPosition, int spanCount) {
                return super.getSpanGroupIndex(adapterPosition, spanCount);
            }
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
