package com.example.tan.mtapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tan.mtapp.API.ActivityCallbackListener;
import com.example.tan.mtapp.API.ConnectionManager;
import com.example.tan.mtapp.API.HistoryCallbackListener;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.HistoryMedel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class MenuActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    String TAG = "Menu";
    private ViewPager mViewPager;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ConnectionManager connect = new ConnectionManager();
    ActivityCallbackListener activityCallbackListener = new ActivityCallbackListener() {
        @Override
        public void onResponse(ActivityModel activityModel, Retrofit retrofit) {
            StaticClass.ACTIVITY_MODEL = activityModel;
//            StaticClass.toast(getApplicationContext(), "200 : OK");
            Log.d(TAG, "onResponse: ");
            Log.d(TAG, "onResponse: " + activityModel.getDetail().size());

        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: ");
        }
    };
    HistoryCallbackListener historyCallbackListener = new HistoryCallbackListener() {
//        @Override
//        public void onResponse(HistoryMedel historyMedel, Retrofit retrofit) {
//            Log.d(TAG, "onResponse: history");
//            StaticClass.HISTORY_MODEL = historyMedel;
//        }

        @Override
        public void onResponse(List<HistoryMedel> historyMedel, Retrofit retrofit) {
            StaticClass.HISTORY_MODEL = historyMedel;
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d(TAG, "onFailure: history" + t);
        }

        @Override
        public void onBodyError(ResponseBody responseBody) {
            Log.d(TAG, "onBodyError: history" + responseBody);
        }

        @Override
        public void onBodyErrorIsNull() {
            Log.d(TAG, "onBodyErrorIsNull: history");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        connect.getAc(activityCallbackListener);
        connect.getHistory(historyCallbackListener,StaticClass.USER_MODEL.getProfile().getId_member());
//        connect.getHistory(historyCallbackListener,StaticClass.USER_MODEL.getProfile().getId_member());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ActivityFragment();
                default:
                    return new PaymentFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Activity";
                case 2:
                    return "Payment";
            }
            return null;
        }
    }

    public void onLogoutPressed() {
        sp = getSharedPreferences("prefs_user", Context.MODE_PRIVATE);
        editor = sp.edit();
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("ออกจากระบบ");
        dialog.setCancelable(true);
        dialog.setMessage("คุณต้องการออกจากระบบใช่หรือไม่");
        dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                editor.clear();
                editor.commit();
                finish();
            }
        });

        dialog.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                onLogoutPressed();
                return true;

            case R.id.Scanqr:
                Intent intent = new Intent(MenuActivity.this, ScanActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}