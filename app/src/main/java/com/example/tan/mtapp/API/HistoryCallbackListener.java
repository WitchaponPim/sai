package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.HistoryMedel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public interface HistoryCallbackListener {
    public void onResponse(List<HistoryMedel> historyMedel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
