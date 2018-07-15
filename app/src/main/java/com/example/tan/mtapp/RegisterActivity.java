package com.example.tan.mtapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.RegisterCallbackListener;
import com.example.tan.mtapp.Model.JobModel;
import com.example.tan.mtapp.Model.UserModel;
import com.example.tan.mtapp.staticPack.StaticClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button mSubmit, mCancel;
    EditText mName, mSurname, mUsername, mPassword, mRePassword, mEmail, mAddress, mTel, mAge;
    RadioButton mRd1, mRd2;
    List<JobModel> LmJob;
    Spinner mJob;
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
                if (status.equals("200")) {
                    StaticClass.toast(getApplicationContext(), "สมัครสำเร็จ");
                    finish();
                } else {
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
        mJob = (Spinner) findViewById(R.id.txtjob);
        mAge = (EditText) findViewById(R.id.txtage);

        mRd1 = (RadioButton) findViewById(R.id.r1);
        mRd2 = (RadioButton) findViewById(R.id.r2);

        mCancel = (Button) findViewById(R.id.btncancel);
        mSubmit = (Button) findViewById(R.id.btnsave);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

//        final List<String> plantsList = new ArrayList<>(Arrays.asList(plants));

        mJob.setPrompt("Select an item");
//        mJob.setAdapter(StaticClass.JOB_MODEL);


        mSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                regis();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void regis() {
        progressDialog = ProgressDialog.show(this, "Please wait", "Loading...", true, false);

        String Username = mUsername.getText().toString();
        String Password = mPassword.getText().toString();
        String RePassword = mRePassword.getText().toString();
        String Name = mName.getText().toString();
        String Surname = mSurname.getText().toString();
        String Email = mEmail.getText().toString();
        String Address = mAddress.getText().toString();
        String Tel = mTel.getText().toString();
        String Sex;
//        String Job = mJob.getTransitionName().toString();
        String Age = mAge.getText().toString();

        if (mRd1.isChecked()) {
            Sex = "M";
        } else {
            Sex = "F";
        }

        if (Password.equals(RePassword)) {
            connect.regis(registerCallbackListener, Username, Password, Name, Surname, Email, Address, Age, "พนักงาน", Sex, Tel);
        } else {
            progressDialog.dismiss();
            mPassword.setError("please fill password");
        }
    }
}
