package com.example.tan.mtapp.API;

import android.app.Activity;

import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.HistoryMedel;
import com.example.tan.mtapp.Model.SeatModel;
import com.example.tan.mtapp.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("login.php")
    Call<UserModel> postLogin(@Field("username") String user,
                              @Field("password") String pass);

    @FormUrlEncoded
    @POST("register.php")
    Call<UserModel> postRegis(@Field("username") String user,
                              @Field("password") String pass,
                              @Field("name") String name,
                              @Field("surname") String surname,
                              @Field("email") String email,
                              @Field("address") String address,
                              @Field("tel") String tel);

    @GET("getActivity.php")
    Call<ActivityModel> getActivity();

    @FormUrlEncoded
    @POST("postActivity.php")
    Call<ActivityModel> postActivity(@Field("activity_id") String activity_id,
                                     @Field("member_id") String member_id,
                                     @Field("qrCode") String QrText);

    @FormUrlEncoded
    @POST("getSeat.php")
    Call<SeatModel> getSeat(@Field("ACID") String room);

    @FormUrlEncoded
    @POST("getAcDetail.php")
    Call<AcDetailModel> postAcDetail(@Field("user") String user);

    @FormUrlEncoded
    @POST("postSeat.php")
    Call<ActivityModel> postSeat(@Field("activity_id") String activity_id,
                                 @Field("member_id") String member_id,
                                 @Field("idSit") String idSit);

    @FormUrlEncoded
    @POST("getHistory.php")
    Call<HistoryMedel> getHistory(@Field("idMem") String idMem);

}
