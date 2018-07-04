package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.ActivityModel;

import java.util.List;

import retrofit2.Retrofit;

public interface ActivityCallbackListener {
    public void onResponse(ActivityModel activityModel, Retrofit retrofit);
    public void onFailure(Throwable t);
}
