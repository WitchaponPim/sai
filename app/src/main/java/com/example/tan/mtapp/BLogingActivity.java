package com.example.tan.mtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tan.mtapp.staticPack.StaticClass;

public class BLogingActivity extends AppCompatActivity {
    Button mToLogin, mToID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloging);

        mToID = (Button) findViewById(R.id.btnLogin2);
        mToLogin = (Button) findViewById(R.id.btnLogin1);

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
