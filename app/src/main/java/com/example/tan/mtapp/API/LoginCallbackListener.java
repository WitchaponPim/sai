package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public interface LoginCallbackListener {
    public void onResponse(UserModel userModel, Retrofit retrofit);
    public void onFailure(Throwable t);
    public void onBodyError(ResponseBody responseBody);
    public void onBodyErrorIsNull();
}
