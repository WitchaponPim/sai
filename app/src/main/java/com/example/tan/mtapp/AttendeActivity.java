package com.example.tan.mtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.LoginCallbackListener;
import com.example.tan.mtapp.Model.UserModel;
import com.example.tan.mtapp.staticPack.StaticClass;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class AttendeActivity extends AppCompatActivity {

    String TAG = "AttendeActivity";
    EditText mIdCard;
    Button mLogin,mRegis;
    ConnectionManager connect = new ConnectionManager();
    LoginCallbackListener loginCallbackListener = new LoginCallbackListener() {
        @Override
        public void onResponse(UserModel userModel, Retrofit retrofit) {
            Log.d(TAG, "onResponse: ");
            StaticClass.USER_MODEL_AT = userModel;
            startActivity(new Intent(AttendeActivity.this,MenuActivity.class));
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: ");
        }

        @Override
        public void onBodyError(ResponseBody responseBody) {
            Log.d(TAG, "onBodyError: ");
        }

        @Override
        public void onBodyErrorIsNull() {
            Log.d(TAG, "onBodyErrorIsNull: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attende);

        mIdCard = (EditText) findViewById(R.id.editText);
        mLogin = (Button) findViewById(R.id.btnloginAT);
        mRegis = (Button) findViewById(R.id.btnregisAT);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + String.valueOf(mIdCard.getText()));
                String idCard = mIdCard.getText().toString();
                connect.loginAT(loginCallbackListener, idCard);
            }
        });
    }
}
