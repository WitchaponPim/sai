package com.example.tan.mtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.JobCallbackListener;
import com.example.tan.mtapp.Model.JobModel;
import com.example.tan.mtapp.staticPack.StaticClass;

import java.util.List;

import retrofit2.Retrofit;

public class BLogingActivity extends AppCompatActivity {
    Button mToLogin, mToID;
    String TAG = "BLogingActivity";
    ConnectionManager connect = new ConnectionManager();
    JobCallbackListener jobCallbackListener = new JobCallbackListener() {
        @Override
        public void onResponse(List<JobModel> jobModel, Retrofit retrofit) {
            StaticClass.JOB_MODEL = jobModel;
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: " + t.getMessage());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloging);

        mToID = (Button) findViewById(R.id.btnLogin2);
        mToLogin = (Button) findViewById(R.id.btnLogin1);
        connect.getJobDetail(jobCallbackListener);

        mToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BLogingActivity.this, LoginActivity.class));
            }
        });

        mToID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticClass.toast(getApplicationContext(), "Login ID");
                startActivity(new Intent(BLogingActivity.this, AttendeActivity.class));

            }
        });
    }
}
