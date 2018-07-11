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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tan.mtapp.API.ActivityCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.ConnectionManagers;
import com.example.tan.mtapp.API.LoginCallbackListener;
import com.example.tan.mtapp.JClass.BaseApplication;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.UserModel;
import com.example.tan.mtapp.staticPack.PreferenceUtils;
import com.example.tan.mtapp.staticPack.PushUtils;
import com.example.tan.mtapp.staticPack.StaticClass;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

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
            StaticClass.toast(getApplicationContext(), "200 : OK");
            Log.d(TAG, "onResponse: ");
            Log.d(TAG, "onResponse: " + activityModel.getDetail().size());
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
    String TAG = "LoginActivity";
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
                    PreferenceUtils.setConnected(true);

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

        //comment close auto login
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

    @Override
    protected void onStart() {
        super.onStart();
        if (PreferenceUtils.getConnected()) {
            connectToSendBird(PreferenceUtils.getUserId(), PreferenceUtils.getNickname());
        }
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

        connectToSendBird(username, "User_" + username);

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

    private void connectToSendBird(final String userId, final String userNickname) {
        // Show the loading indicator
        ConnectionManagers.login(userId, new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error!
                    Log.d(TAG, "onConnected: " + e.getMessage());
                    Toast.makeText(
                            LoginActivity.this, "" + e.getCode() + ": " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    // Show login failure snackbar
                    PreferenceUtils.setConnected(false);
                    return;
                }

                PreferenceUtils.setNickname(user.getNickname());
                PreferenceUtils.setProfileUrl(user.getProfileUrl());
                PreferenceUtils.setConnected(true);

                // Update the user's nickname
                updateCurrentUserInfo(userNickname);
                updateCurrentUserPushToken();

                // Proceed to MainActivity
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });
    }

    private void updateCurrentUserInfo(final String userNickname) {
        SendBird.updateCurrentUserInfo(userNickname, null, new SendBird.UserInfoUpdateHandler() {
            @Override
            public void onUpdated(SendBirdException e) {
                if (e != null) {
                    // Error!
                    Log.d(TAG, "onUpdated: " + e.getMessage());
                    Toast.makeText(
                            LoginActivity.this, "" + e.getCode() + ":" + e.getMessage(),
                            Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

                PreferenceUtils.setNickname(userNickname);
            }
        });
    }

    private void updateCurrentUserPushToken() {
        PushUtils.registerPushTokenForCurrentUser(LoginActivity.this, null);
    }
}
