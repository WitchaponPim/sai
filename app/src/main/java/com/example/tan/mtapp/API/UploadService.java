package com.example.tan.mtapp.API;

import com.example.tan.mtapp.Model.PostPaymentModel;
import com.example.tan.mtapp.Model.UpPicModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadService {

    @Multipart
    @POST("postPayment.php")
    Call<UpPicModel> uploadFile(@Part("id_member") RequestBody id_member
            , @Part("id_activity") RequestBody id_activity
            , @Part("id_reserve") RequestBody id_reserve
            , @Part MultipartBody.Part file
            , @Part("image") RequestBody name);
}
