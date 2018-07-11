package com.example.tan.mtapp.JClass;


import android.app.Application;

import com.example.tan.mtapp.staticPack.PreferenceUtils;
import com.sendbird.android.SendBird;

public class BaseApplication extends Application {

    private static final String APP_ID = "227F09E0-F4F1-4DBE-AE53-FF4F191B0EBC"; // US-1 Demo
    public static final String VERSION = "3.0.40";

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.init(getApplicationContext());

        SendBird.init(APP_ID, getApplicationContext());
    }
}
