package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.HistoryMedel;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public interface HistoryCallbackListener {
    public void onResponse(HistoryMedel historyMedel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
