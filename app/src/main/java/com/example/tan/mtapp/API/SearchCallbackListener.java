package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.SearchModel;

import java.util.List;

import retrofit2.Retrofit;

public interface SearchCallbackListener {
    public void onResponse(List<SearchModel> searchModel, Retrofit retrofit);
    public void onFailure(Throwable t);
}
