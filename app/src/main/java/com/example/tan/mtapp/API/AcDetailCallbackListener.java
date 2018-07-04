package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.AcDetailModel;

import retrofit2.Retrofit;

public interface AcDetailCallbackListener {
    public void onResponse(AcDetailModel acDetailModel, Retrofit retrofit);
    public void onFailure(Throwable t);
}
