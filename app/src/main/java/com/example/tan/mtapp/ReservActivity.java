package com.example.tan.mtapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tan.mtapp.Adapter.SeatReAdapter;
import com.example.tan.mtapp.Model.SeatModel;
import com.example.tan.mtapp.staticPack.StaticClass;

import java.util.List;

public class ReservActivity extends AppCompatActivity {

    List<String> test;
    String TAG = "ReservActivity";
    RecyclerView mRecyclerView;
    SeatReAdapter adapter;
    GridLayoutManager mGridLayoutManager;
    Button mRegis;
    TextView sdate, edate, dis, act;
    String ID;
//    ConnectionManager connect = new ConnectionManager();
//    ReservActivity reservActivity = new ReservActivity() {
//
//        @Override
//        public void onResponse(final ActivityModel activityModel, Retrofit retrofit) {
//            StaticClass.ACTIVITY_QR = activityModel;
////            Log.d(TAG, "onResponse: " + activityModel.getQR());
//            AlertDialog.Builder builder = new AlertDialog.Builder(ReservActivity.this);
//            builder.setMessage("Reserve Success");
//            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
////                    Intent intent = new Intent(ReservActivity.this, QRActivity.class);
////                    intent.putExtra("QRCode", activityModel.getQR());
////                    startActivity(intent);
////                    finish();
////
////                    intent.putExtra("mIDMember",StaticClass.USER_MODEL.getProfile().getId_member());
////                    intent.putExtra("mIDActivity",activityModel.getDetail().get(0).getId_activity());
////                    intent.putExtra("mIDReserve",activityModel.getDetail().get(0).getId_reserve());
//                    Log.d(TAG, "seatClick: getid_reserve"+activityModel.getDetail().get(0).getId_reserve());
//
////                    startActivity(intent);
//                }
//            });
//            builder.show();
//        }
//
//    };
//    AcDetailCallbackListener acDetailCallbackListener = new AcDetailCallbackListener() {
//        @Override
//        public void onResponse(AcDetailModel acDetailModel, Retrofit retrofit) {
//            StaticClass.ACTIVITY_DETAIL = acDetailModel;
//            Log.d(TAG, "onResponse: " + acDetailModel.getStatus());
//        }
//
//        @Override
//        public void onFailure(Throwable t) {
//
//        }
//    };


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
            mRegis.setVisibility(View.VISIBLE);
            adapter = new SeatReAdapter(getApplicationContext(), StaticClass.seatRoom, new SeatReAdapter.OnItemClickListener() {
                @Override
                public void seatClick(List<SeatModel.DetailBean> activityAdapters, int position) {
                    if (activityAdapters.get(position).getStatus().equals("0")) {

//                        AlertDialog.Builder builder = new AlertDialog.Builder(ReservActivity.this);
//                        builder.setMessage("คุณ" + StaticClass.USER_MODEL.getProfile().getUsername() + "ได้จองเก้าอี้เบอรฺ " + activityAdapters.get(position).getId_sit());
//                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).show();

//                        StaticClass.seatRoompick.add(activityAdapters.get(position));
//                        adapter.notifyDataSetChanged();
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
                        StaticClass.toast(getApplicationContext(), String.valueOf(position));
//                        connect.postSeat(reservActivity,
//                                StaticClass.ACTIVITY_PICKER.getId_activity()
//                                , StaticClass.USER_MODEL.getProfile().getId_member()
//                                , activityAdapters.get(position).getId_sit());
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
//                    connect.postSeat(reservActivity,
//                            StaticClass.ACTIVITY_PICKER.getId_activity()
//                            , StaticClass.USER_MODEL.getProfile().getId_member()
//                            , "-1");
                }
            });
        }
    }


//    public  void onResponse(ActivityModel activityModel, Retrofit retrofit){
//
//    }
//
//    public void onFailure(Throwable t) {
//
//    }
}
