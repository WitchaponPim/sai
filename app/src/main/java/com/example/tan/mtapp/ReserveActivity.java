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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tan.mtapp.API.AcDetailCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.ReserveCallbackListener;
import com.example.tan.mtapp.Adapter.SeatAdapter;
import com.example.tan.mtapp.Adapter.SeatReAdapter;
import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ReserveModel;
import com.example.tan.mtapp.Model.SeatModel;

import java.util.List;

import retrofit2.Retrofit;

public class ReserveActivity extends AppCompatActivity {

    List<String> test;
    String TAG = "faker";
    RecyclerView mRecyclerView;
    SeatReAdapter adapter;
    GridLayoutManager mGridLayoutManager;
    Button mRegis;
    TextView sdate, edate, dis, act;
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
//            Log.d(TAG, "onResponse: " + activityModel.getQR());
            AlertDialog.Builder builder = new AlertDialog.Builder(ReserveActivity.this);
            builder.setMessage("Reserve Success");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(ReserveActivity.this, PaymentActivity.class);
                    startActivity(intent);
                    finish();
                    Log.d(TAG, "seatClick: getid_reserve"+ StaticClass.ACTIVITY_QR.getId_reserve());

//                    startActivity(intent);
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
//        dis.setText(StaticClass.ACTIVITY_PICKER.getDetail());

        mRecyclerView = (RecyclerView) findViewById(R.id.Seat);
//        Log.d(TAG, "onCreate: " + StaticClass.seatRoom.size());

        if (ID.equals("1") || ID.equals("8")) {
            mRegis.setVisibility(View.INVISIBLE);
            adapter = new SeatReAdapter(getApplicationContext(), StaticClass.seatRoom, new SeatReAdapter.OnItemClickListener() {
                @Override
                public void seatClick(List<SeatModel.DetailBean> activityAdapters, int position) {
                    if (activityAdapters.get(position).getStatus().equals("0")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReserveActivity.this);
                        builder.setMessage("คุณ" + StaticClass.USER_MODEL.getProfile().getUsername() + "ได้จองเก้าอี้เบอรฺ " + activityAdapters.get(position).getId_sit());
                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        StaticClass.toast(getApplicationContext(), activityAdapters.get(position).getId_sit());
                        connect.postSeat(reserveCallbackListener,
                                StaticClass.ACTIVITY_PICKER.getId_activity()
                                , StaticClass.USER_MODEL.getProfile().getId_member()
                                , activityAdapters.get(position).getId_sit());
                        connect.getAcDetail(acDetailCallbackListener, StaticClass.USER_MODEL.getProfile().getUsername());

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
        } else {
            connect.getAcDetail(acDetailCallbackListener, StaticClass.USER_MODEL.getProfile().getUsername());
            mRegis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connect.postSeat(reserveCallbackListener,
                            StaticClass.ACTIVITY_PICKER.getId_activity()
                            , StaticClass.USER_MODEL.getProfile().getId_member()
                            , "-1");
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
