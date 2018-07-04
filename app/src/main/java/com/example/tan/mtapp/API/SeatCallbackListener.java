package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.SeatModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public interface SeatCallbackListener {
    public void onResponse(SeatModel seatModel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
