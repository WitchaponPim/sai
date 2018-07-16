package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.JobModel;

import java.util.List;

import retrofit2.Retrofit;

public interface JobCallbackListener {
    public void onResponse(List<JobModel> historyMedel, Retrofit retrofit);
    public void onFailure(Throwable t);
}
