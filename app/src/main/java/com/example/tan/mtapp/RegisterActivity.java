package com.example.tan.mtapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.RegisterCallbackListener;
import com.example.tan.mtapp.Model.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button mSubmit,mCancel;
    EditText mName,mSurname,mUsername,mPassword,mRePassword,mEmail,mAddress,mTel;
    String TAG = "Debug => ";
    String status;
    ConnectionManager connect = new ConnectionManager();
    RegisterCallbackListener registerCallbackListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerCallbackListener = new RegisterCallbackListener() {
            @Override
            public void onResponse(UserModel userModel, Retrofit retrofit) {
                progressDialog.dismiss();
                status = userModel.getStatus();
                if(status.equals("200")){
                    StaticClass.toast(getApplicationContext(), "สมัครสำเร็จ");
                    finish();
                }else{
                    StaticClass.toast(getApplicationContext(), userModel.getMsg());
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

        mName = (EditText) findViewById(R.id.txtname);
        mSurname = (EditText) findViewById(R.id.txtsurname);
        mUsername = (EditText) findViewById(R.id.txtusername);
        mPassword = (EditText) findViewById(R.id.txtPassRegis);
        mRePassword = (EditText) findViewById(R.id.txtrepass);
        mEmail = (EditText) findViewById(R.id.txtemail);
        mAddress = (EditText) findViewById(R.id.txtaddress);
        mTel = (EditText) findViewById(R.id.txttel);

        mCancel = (Button) findViewById(R.id.btncancel);
        mSubmit = (Button) findViewById(R.id.btnsave);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regis();
            }
        });
    }

    private void regis(){
        progressDialog = ProgressDialog.show(this, "Please wait", "Loading...", true, false);

        String Username = mUsername.getText().toString();
        String Password = mPassword.getText().toString();
        String RePassword = mRePassword.getText().toString();
        String Name = mName.getText().toString();
        String Surname = mSurname.getText().toString();
        String Email = mEmail.getText().toString();
        String Address = mAddress.getText().toString();
        String Tel = mTel.getText().toString();

        if(Password.equals(RePassword)) {
            connect.regis(registerCallbackListener, Username, Password, Name, Surname, Email, Address, Tel);
        }else{
            progressDialog.dismiss();
            mPassword.setError("please fill password");
        }
    }
}
