package com.example.tan.mtapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tan.mtapp.API.ActivityCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.SeatCallbackListener;
import com.example.tan.mtapp.Adapter.ActivityAdapter;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.SeatModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;


public class ActivityFragment extends Fragment {

    Intent intent;
    RecyclerView mRecyclerView;
    ActivityAdapter mActivityAdapter;
    GridLayoutManager mGridLayoutManager;
    String ID;
    String TAG = "Activity";

    ConnectionManager connect = new ConnectionManager();
    ActivityCallbackListener activityCallbackListener = new ActivityCallbackListener() {
        @Override
        public void onResponse(ActivityModel activityModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_MODEL = activityModel;
//            StaticClass.toast(getContext(), "200 : OK");
            Log.d(TAG, "onResponse: ");
            Log.d(TAG, "onResponse: " + activityModel.getDetail().size());

        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: ");
        }
    };
    SeatCallbackListener seatCallbackListener = new SeatCallbackListener() {
        @Override
        public void onResponse(SeatModel seatModel, Retrofit retrofit) {
            StaticClass.seatRoom = seatModel.getDetail();
            Intent intent = new Intent(getActivity(), ReservActivity.class);
            intent.putExtra("ID",ID);
            startActivity(intent);
        }

        @Override
        public void onFailure(Throwable t) {

        }

        @Override
        public void onBodyError(ResponseBody responseBody) {

        }

        @Override
        public void onBodyErrorIsNull() {

        }
    };

    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        final SwipeRefreshLayout pullToRefresh = view.findViewById(R.id.swiperefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                connect.getAc(activityCallbackListener);
                pullToRefresh.setRefreshing(false);
            }
        });

        mRecyclerView = (RecyclerView) view.findViewById(R.id.ReAc);

        mActivityAdapter = new ActivityAdapter(getContext(), StaticClass.ACTIVITY_MODEL.getDetail(), new ActivityAdapter.OnItemClickListener() {
            @Override
            public void moreClick(List<ActivityModel.DetailBean> activityAdapters, int position) {
//                StaticClass.ACTIVITY_PICKER = activityAdapters.get(position);
//                StaticClass.toast(getContext(),"more click :" + activityAdapters.get(position).getDetail());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(activityAdapters.get(position).getDetail());
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            @Override
            public void regisClick(List<ActivityModel.DetailBean> activityAdapters, int position) {
                StaticClass.ACTIVITY_PICKER = activityAdapters.get(position);
                StaticClass.toast(getContext(), "regis click :" + activityAdapters.get(position).getId_activity());
                if (activityAdapters.get(position).getActivity_pay().equals("0")) {
                    Intent intent = new Intent(getActivity(), RegisActivity.class);
                    startActivity(intent);
                } else {
                    ID = activityAdapters.get(position).getId_room();
                    connect.getSeat(seatCallbackListener,activityAdapters.get(position).getId_activity());
                }
            }
        });
        mRecyclerView.setAdapter(mActivityAdapter);
        mGridLayoutManager = new GridLayoutManager(getContext(), 1);
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
        return view;
    }

}
