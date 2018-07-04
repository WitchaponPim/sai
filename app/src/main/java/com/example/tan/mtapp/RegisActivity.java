package com.example.tan.mtapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tan.mtapp.API.AcDetailCallbackListener;
import com.example.tan.mtapp.API.ActivityCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ActivityModel;

import org.w3c.dom.Text;

import retrofit2.Retrofit;

public class RegisActivity extends AppCompatActivity {
    TextView sdate, edate, dis, act;
    Button mRegis;
    String TAG = "DebugRe =>";
    ConnectionManager connect = new ConnectionManager();
    ActivityCallbackListener activityCallbackListener = new ActivityCallbackListener() {

        @Override
        public void onResponse(final ActivityModel activityModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_REGIS = activityModel;
            Log.d(TAG, "onResponse: " + activityModel.getQR());
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisActivity.this);
            builder.setMessage("Register Success");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(RegisActivity.this, QRActivity.class);
                    intent.putExtra("QRCode", activityModel.getQR());
                    startActivity(intent);
                    finish();
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
            Log.d(TAG, "onResponse: "+acDetailModel.getStatus());
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        sdate = (TextView) findViewById(R.id.txtsdate);
        edate = (TextView) findViewById(R.id.txtedate);
//        dis = (TextView) findViewById(R.id.txtdescrip);
        act = (TextView) findViewById(R.id.txtactivity);
        mRegis = (Button) findViewById(R.id.btnregister);

        act.setText(StaticClass.ACTIVITY_PICKER.getAc_name());
        sdate.setText("Start : " + StaticClass.ACTIVITY_PICKER.getStart_date() + " " + StaticClass.ACTIVITY_PICKER.getStart_time());
        edate.setText("End   : " + StaticClass.ACTIVITY_PICKER.getEnd_date() + " " + StaticClass.ACTIVITY_PICKER.getEnd_time());
//        dis.setText(StaticClass.ACTIVITY_PICKER.getDetail());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect.postActivity(activityCallbackListener,
                        StaticClass.ACTIVITY_PICKER.getId_activity()
                        ,StaticClass.USER_MODEL.getProfile().getId_member()
                        ,StaticClass.USER_MODEL.getProfile().getUsername()+"_"+StaticClass.ACTIVITY_PICKER.getAc_name());
//                finish();
                connect.getAcDetail(acDetailCallbackListener,StaticClass.USER_MODEL.getProfile().getUsername());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
