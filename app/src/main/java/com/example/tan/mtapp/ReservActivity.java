package com.example.tan.mtapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tan.mtapp.API.AcDetailCallbackListener;
import com.example.tan.mtapp.API.ActivityCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.SeatCallbackListener;
import com.example.tan.mtapp.Adapter.ActivityAdapter;
import com.example.tan.mtapp.Adapter.SeatAdapter;
import com.example.tan.mtapp.Adapter.SeatReAdapter;
import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.SeatModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class ReservActivity extends AppCompatActivity {

    List<String> test;
    String TAG = "faker";
    RecyclerView mRecyclerView;
    SeatReAdapter adapter;
    GridLayoutManager mGridLayoutManager;
    Button mRegis;
    TextView sdate, edate, dis, act;
    String ID;
    ConnectionManager connect = new ConnectionManager();
    ActivityCallbackListener activityCallbackListener = new ActivityCallbackListener() {

        @Override
        public void onResponse(final ActivityModel activityModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_QR = activityModel;
//            Log.d(TAG, "onResponse: " + activityModel.getQR());
            AlertDialog.Builder builder = new AlertDialog.Builder(ReservActivity.this);
            builder.setMessage("Reserve Success");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Intent intent = new Intent(ReservActivity.this, QRActivity.class);
//                    intent.putExtra("QRCode", activityModel.getQR());
//                    startActivity(intent);
//                    finish();
//
//                    intent.putExtra("mIDMember",StaticClass.USER_MODEL.getProfile().getId_member());
//                    intent.putExtra("mIDActivity",activityModel.getDetail().get(0).getId_activity());
//                    intent.putExtra("mIDReserve",activityModel.getDetail().get(0).getId_reserve());
                    Log.d(TAG, "seatClick: getId_activity"+activityModel.getDetail().get(0).getId_activity());
                    Log.d(TAG, "seatClick: getid_reserve"+activityModel.getDetail().get(0).getId_reserve());

//                    startActivity(intent);
                }
            });
            builder.show();
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);
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


//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new SeatAdapter(getApplicationContext(),Integer.parseInt(StaticClass.ACTIVITY_PICKER.getSeat())));
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                StaticClass.toast(getApplicationContext(), String.valueOf(position+1));
//            }
//        });
        mRecyclerView = (RecyclerView) findViewById(R.id.Seat);
//        Log.d(TAG, "onCreate: " + StaticClass.seatRoom.size());

        if (ID.equals("1") || ID.equals("8")) {
            mRegis.setVisibility(View.INVISIBLE);
            adapter = new SeatReAdapter(getApplicationContext(), StaticClass.seatRoom, new SeatReAdapter.OnItemClickListener() {
                @Override
                public void seatClick(List<SeatModel.DetailBean> activityAdapters, int position) {
                    if (activityAdapters.get(position).getStatus().equals("0")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReservActivity.this);
                        builder.setMessage("คุณ" + StaticClass.USER_MODEL.getProfile().getUsername() + "ได้จองเก้าอี้เบอรฺ " + activityAdapters.get(position).getId_sit());
                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        StaticClass.toast(getApplicationContext(), activityAdapters.get(position).getId_sit());
                        connect.postSeat(activityCallbackListener,
                                StaticClass.ACTIVITY_PICKER.getId_activity()
                                , StaticClass.USER_MODEL.getProfile().getId_member()
                                , activityAdapters.get(position).getId_sit());
//                finish();
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
        } else {
//            connect.getAcDetail(acDetailCallbackListener, StaticClass.USER_MODEL.getProfile().getUsername());
            mRegis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connect.postSeat(activityCallbackListener,
                            StaticClass.ACTIVITY_PICKER.getId_activity()
                            , StaticClass.USER_MODEL.getProfile().getId_member()
                            , "-1");
                }
            });
        }
    }


}
