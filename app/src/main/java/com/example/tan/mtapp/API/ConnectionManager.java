package com.example.tan.mtapp.API;

import android.telecom.Call;

import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.HistoryMedel;
import com.example.tan.mtapp.Model.SeatModel;
import com.example.tan.mtapp.Model.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionManager {
//    String API = "http://192.168.82.2/Project/api/";

    String URL = "http://meeting.wat-huathanon.com/api/";
//    String URL = "http://192.168.43.124/api/";
//    String URL = "http://192.168.43.133/api/";
//    String URL = "http://10.10.95.18/api/";
    public ConnectionManager() {

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService con = retrofit.create(APIService.class);

    public void login(final LoginCallbackListener listener, String user, String pass) {
        retrofit2.Call<UserModel> call = con.postLogin(user, pass);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                UserModel user = response.body();
                if (user == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse(user, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void regis(final RegisterCallbackListener listener, String user, String pass, String name, String surname, String email, String address, String tel) {
        retrofit2.Call<UserModel> call = con.postRegis(user, pass, name, surname, email, address, tel);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                UserModel user = response.body();
                if (user == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse(user, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void getAc(final ActivityCallbackListener listener) {
        retrofit2.Call<ActivityModel> call = con.getActivity();
        call.enqueue(new Callback<ActivityModel>() {
            @Override
            public void onResponse(retrofit2.Call<ActivityModel> call, Response<ActivityModel> response) {
                ActivityModel user = response.body();
                if (user == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                } else {
                    //200
                    listener.onResponse(user, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ActivityModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void postActivity(final ActivityCallbackListener listener, String id_activity, String id_member, String QRText) {
        retrofit2.Call<ActivityModel> call = con.postActivity(id_activity,id_member,QRText);
        call.enqueue(new Callback<ActivityModel>() {
            @Override
            public void onResponse(retrofit2.Call<ActivityModel> call, Response<ActivityModel> response) {
                ActivityModel user = response.body();
                if (user == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                } else {
                    //200
                    listener.onResponse(user, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ActivityModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void getSeat(final SeatCallbackListener listener, String seat) {
        retrofit2.Call<SeatModel> call = con.getSeat(seat);
        call.enqueue(new Callback<SeatModel>() {

            @Override
            public void onResponse(retrofit2.Call<SeatModel> call, Response<SeatModel> response) {
                SeatModel seat = response.body();
                if (seat == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse(seat, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<SeatModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void getAcDetail(final AcDetailCallbackListener listener, String username) {
        retrofit2.Call<AcDetailModel> call = con.postAcDetail(username);
        call.enqueue(new Callback<AcDetailModel>() {
            @Override
            public void onResponse(retrofit2.Call<AcDetailModel> call, Response<AcDetailModel> response) {
                AcDetailModel username = response.body();
                if (username == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                } else {
                    //200
                    listener.onResponse(username, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<AcDetailModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void postSeat(final ActivityCallbackListener listener, String activity_id, String member_id, String idSit) {
        retrofit2.Call<ActivityModel> call = con.postSeat(activity_id,member_id,idSit);
        call.enqueue(new Callback<ActivityModel>() {
            @Override
            public void onResponse(retrofit2.Call<ActivityModel> call, Response<ActivityModel> response) {
                ActivityModel activityModel = response.body();
                if (activityModel == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                } else {
                    //200
                    listener.onResponse(activityModel, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ActivityModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }

    public void getHistory(final HistoryCallbackListener listener, String member_id) {
        retrofit2.Call<HistoryMedel> call = con.getHistory(member_id);
        call.enqueue(new Callback<HistoryMedel>() {
            @Override
            public void onResponse(retrofit2.Call<HistoryMedel> call, Response<HistoryMedel> response) {
                HistoryMedel historyMedel = response.body();
                if (historyMedel == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                } else {
                    //200
                    listener.onResponse(historyMedel, retrofit);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<HistoryMedel> call, Throwable t) {
                listener.onFailure(t);
            }
        });

    }
}
