package com.example.tan.mtapp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.HistoryCallbackListener;
import com.example.tan.mtapp.Adapter.ConfirmAdapter;
import com.example.tan.mtapp.Adapter.CustomDialog_detail;
import com.example.tan.mtapp.Model.HistoryMedel;
import com.example.tan.mtapp.R;
import com.example.tan.mtapp.staticPack.StaticClass;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {

    RecyclerView mRecyclerView;
    ConfirmAdapter adapter;
    View view;
    String TAG = "PaymentFragment";
    ConnectionManager connect = new ConnectionManager();
    HistoryCallbackListener historyCallbackListener = new HistoryCallbackListener() {

        @Override
        public void onResponse(List<HistoryMedel> historyMedel, Retrofit retrofit) {
            StaticClass.HISTORY_MODEL = historyMedel;
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: history" + t);
        }

        @Override
        public void onBodyError(ResponseBody responseBody) {
            Log.d(TAG, "onBodyError: history" + responseBody);
        }

        @Override
        public void onBodyErrorIsNull() {
            Log.d(TAG, "onBodyErrorIsNull: history");
        }
    };

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_payment, container, false);
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swiperefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connect.getHistory(historyCallbackListener, StaticClass.USER_MODEL.getProfile().getId_member());
                pullToRefresh.setRefreshing(false);
            }
        });
        setPayment();
        // Inflate the layout for this fragment
        return view;
    }

    public void setPayment(){
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
    }

}
