package com.example.tan.mtapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tan.mtapp.API.AcDetailCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.ReserveCallbackListener;
import com.example.tan.mtapp.Adapter.SeatReAdapter;
import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ReserveModel;
import com.example.tan.mtapp.Model.SeatModel;
import com.example.tan.mtapp.staticPack.StaticClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class ReserveActivity extends AppCompatActivity {

    List<String> test;
    String TAG = "ReserveActivity";
    RecyclerView mRecyclerView;
    SeatReAdapter adapter;
    GridLayoutManager mGridLayoutManager;
    Button mRegis;
    TextView sdate, edate, dis, act;
    ArrayList<String> sit_set = new ArrayList<>();
    String Sit_ID;
    String ID;
    ConnectionManager connect = new ConnectionManager();
    AcDetailCallbackListener acDetailCallbackListener = new AcDetailCallbackListener() {
        @Override
        public void onResponse(AcDetailModel acDetailModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_DETAIL = acDetailModel;
            Log.d(TAG, "onResponse: " + acDetailModel.getStatus());
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };
    ReserveCallbackListener reserveCallbackListener = new ReserveCallbackListener() {
        @Override
        public void onResponse(ReserveModel reserveModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_QR = reserveModel;
            AlertDialog.Builder builder = new AlertDialog.Builder(ReserveActivity.this);
            builder.setMessage("Reserve Success");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ReserveActivity.this, PaymentActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d(TAG, "seatClick: getid_reserve"+ StaticClass.ACTIVITY_QR.getId_reserve());

                    startActivity(intent);
                }
            });
            builder.show();
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        ID = bundle.getString("ID");
        sdate = (TextView) findViewById(R.id.txtsdate);
        edate = (TextView) findViewById(R.id.txtedate);
//        dis = (TextView) findViewById(R.id.txtdescrip);
        act = (TextView) findViewById(R.id.txtactivity);
        mRegis = (Button) findViewById(R.id.btnresver);


        act.setText(StaticClass.ACTIVITY_PICKER.getAc_name());
        sdate.setText("Start : " + StaticClass.ACTIVITY_PICKER.getStart_date() + " " + StaticClass.ACTIVITY_PICKER.getStart_time());
        edate.setText("End   : " + StaticClass.ACTIVITY_PICKER.getEnd_date() + " " + StaticClass.ACTIVITY_PICKER.getEnd_time());

        mRecyclerView = (RecyclerView) findViewById(R.id.Seat);

        if (ID.equals("1") || ID.equals("8")) {
            mRegis.setVisibility(View.VISIBLE);
            adapter = new SeatReAdapter(getApplicationContext(), StaticClass.seatRoom, new SeatReAdapter.OnItemClickListener() {
                @Override
                public void seatClick(List<SeatModel.DetailBean> activityAdapters, int position) {
                    if (activityAdapters.get(position).getStatus().equals("0")) {
                        if (activityAdapters.get(position).getCheck()!=null){
                            if (activityAdapters.get(position).getCheck().equals("1")){
                                //remove
                                activityAdapters.get(position).setCheck("0");
                            }else {
                                //swap
                                activityAdapters.get(position).setCheck("1");
                            }
                        }else {
                            //add
                            activityAdapters.get(position).setCheck("1");
                        }


                        adapter.notifyDataSetChanged();
                        //23,23,23,23,23,23

                        StaticClass.toast(getApplicationContext(), String.valueOf(position));

//                        connect.getAcDetail(acDetailCallbackListener, StaticClass.USER_MODEL.getProfile().getUsername());

                    } else {
                        StaticClass.toast(getApplicationContext(), "จองแล้วนะจ๊ะตะเอง");
                    }
                }
            });

            mRecyclerView.setAdapter(adapter);
            mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 15);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mGridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
            mRegis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sit_set = new ArrayList<>();
                    for (int i =0;i<StaticClass.seatRoom.size();i++){
                        if (StaticClass.seatRoom.get(i).getCheck()!=null){
                            if (StaticClass.seatRoom.get(i).getCheck().equals("1")){
                                sit_set.add(StaticClass.seatRoom.get(i).getId_sit());
                            }
                        }
                    }

                        StringBuffer sb = new StringBuffer(sit_set.get(0));
                        for (int i = 0; i < sit_set.size(); i++) {
                            if (i == 0) {
                            } else {
                                sb.append("," + sit_set.get(i));
                            }
                        }



                    Log.d("Ammy", "onClick: " + sb.toString());
                    connect.postSeat(reserveCallbackListener,
                            StaticClass.ACTIVITY_PICKER.getId_activity()
                            , StaticClass.USER_MODEL.getProfile().getId_member()
                            , sb.toString());
                }
            });
        } else {
//            connect.getAcDetail(acDetailCallbackListener, StaticClass.USER_MODEL.getProfile().getUsername());
            mRegis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    connect.postSeat(reserveCallbackListener,
//                            StaticClass.ACTIVITY_PICKER.getId_activity()
//                            , StaticClass.USER_MODEL.getProfile().getId_member()
//                            , "-1");
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
