package com.example.tan.mtapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tan.mtapp.API.ActivityCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.LoginCallbackListener;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    ConnectionManager connect = new ConnectionManager();
    LoginCallbackListener loginCallbackListener;
    ActivityCallbackListener activityCallbackListener = new ActivityCallbackListener() {
        @Override
        public void onResponse(ActivityModel activityModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_MODEL = activityModel;
            StaticClass.toast(getApplicationContext(),"200 : OK");
            Log.d(TAG, "onResponse: ");
            Log.d(TAG, "onResponse: "+activityModel.getDetail().size());
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: ");
        }
    };
    Button mLogin, mRegis;
    EditText mUsername, mPassword;
    String TAG = "Debug => ";
    String status;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLogin = (Button) findViewById(R.id.btnlogin);
        mRegis = (Button) findViewById(R.id.btnregis);
        mUsername = (EditText) findViewById(R.id.txtuser);
        mPassword = (EditText) findViewById(R.id.txtpass);

        mPrefs = getSharedPreferences("prefs_user", Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();

        String user = mPrefs.getString("user", null);
        String pass = mPrefs.getString("password", null);
        loginCallbackListener = new LoginCallbackListener() {

            @Override
            public void onResponse(UserModel userModel, Retrofit retrofit) {
                status = userModel.getStatus();
                StaticClass.USER_MODEL = userModel;
                Log.d(TAG, "onResponse: " + status);

                progressDialog.dismiss();

                if (status.equals("200")) {
                    onLoginSucced();
                } else {
                    onLoginFailed();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                progressDialog.dismiss();
                StaticClass.toast(getApplicationContext(), "ข้อมูลผิดพลาด");

                Log.d(TAG, "onFailure: " + t.toString());
            }

            @Override
            public void onBodyError(ResponseBody responseBody) {
                progressDialog.dismiss();
                StaticClass.toast(getApplicationContext(), "ข้อมูลผิดพลาด");
                Log.d(TAG, "onBodyError: " + responseBody.toString());
            }

            @Override
            public void onBodyErrorIsNull() {
                progressDialog.dismiss();
                StaticClass.toast(getApplicationContext(), "ข้อมูลผิดพลาด");
                Log.d(TAG, "onBodyErrorIsNull: ");
            }
        };

        if (user != null && pass != null) {
            progressDialog = ProgressDialog.show(this, "Please wait", "Loading...", true, false);
            connect.login(loginCallbackListener, user, pass);
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        mRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }
        progressDialog = ProgressDialog.show(this, "Please wait", "Loading...", true, false);

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        connect.login(loginCallbackListener, username, password);

    }

    private void onLoginSucced() {
        connect.getAc(activityCallbackListener);
//        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
//        startActivity(intent);
//        finish();
    }

    private void onLoginFailed() {
        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {
        boolean valid = true;

        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        mEditor.putString("user", username);
        mEditor.putString("password", password);
        mEditor.commit();
        if (username.isEmpty()) {
            mUsername.setError("please fill username");
            valid = false;
        } else {
            mUsername.setError(null);
        }

        if (password.isEmpty()) {
            mPassword.setError("please fill password");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }
}
