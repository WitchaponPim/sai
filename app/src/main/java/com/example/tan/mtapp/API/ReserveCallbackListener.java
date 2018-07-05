package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.ReserveModel;

import retrofit2.Retrofit;

public interface ReserveCallbackListener {
    public void onResponse(ReserveModel reserveModel, Retrofit retrofit);
    public void onFailure(Throwable t);
}
