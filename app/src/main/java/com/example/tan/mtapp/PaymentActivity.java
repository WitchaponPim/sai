package com.example.tan.mtapp;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.tan.mtapp.API.ConnectUpload;
import com.example.tan.mtapp.API.UploadService;
import com.example.tan.mtapp.Model.UpPicModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    Gallery gallery;
    ImageView slip;
    Button picker, upload;
    String mIDMember, mIDActivity, mIDReserve;
    String TAG = "Payment";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Bundle bundle = getIntent().getExtras();
        mIDMember = bundle.getString("mIDMember");
        mIDActivity = bundle.getString("mIDActivity");
        mIDReserve = bundle.getString("mIDReserve");

        slip = (ImageView) findViewById(R.id.slip);
        picker = (Button) findViewById(R.id.picker);
        upload = (Button) findViewById(R.id.upload);

        if (Build.VERSION.SDK_INT > 22) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    public void pick(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        gallery = new Gallery.Builder()
                .setpickPhotoRequestCode(123)
                .resetToCorrectOrientation(true)
                .setDirectory("DCIM/Camera/")
                .setName("IMG_")
                .setImageFormat(Gallery.IMAGE_JPG)
                .setCompression(75)
                .setImageHeight(1000)
                .build(this);

        try {
            gallery.pickPicture();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery.REQUEST_PICK_PHOTO && resultCode == RESULT_OK && data != null) {
//            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();
            String respath = gallery.getCameraBitmapPath();
            Uri pickedImage = data.getData();

            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            Bitmap bitmap = StaticClass.decodeFile(new File(imagePath), 1000);
            bitmap = StaticClass.rotateBitmap(bitmap, StaticClass.getImageRotation(imagePath));
            StaticClass.saveBitmap(bitmap, respath, "jpg", 75);

            slip.setImageBitmap(bitmap);

            file = new File(respath);
            cursor.close();
        }
    }

    // Uploading Image/Video
    private void uploadFile() {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

//        String mIdRentel;
//        String mFname,mSname;
        RequestBody id_activity = RequestBody.create(MediaType.parse("text/plain"), mIDActivity);
        RequestBody id_member = RequestBody.create(MediaType.parse("text/plain"), mIDMember);
        RequestBody id_reserve = RequestBody.create(MediaType.parse("text/plain"), mIDReserve);

        UploadService getResponse = ConnectUpload.getClient().create(UploadService.class);
        Call<UpPicModel> call = getResponse.uploadFile(id_member, id_activity, id_reserve, fileToUpload, filename);
        call.enqueue(new Callback<UpPicModel>() {
            @Override
            public void onResponse(Call<UpPicModel> call, Response<UpPicModel> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                UpPicModel serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.isSuccess()) {
                        Log.d(TAG, "onResponse : " + serverResponse.getMessage());
                    } else {

                    }
                } else {
                    assert serverResponse != null;
                }
            }

            @Override
            public void onFailure(Call<UpPicModel> call, Throwable t) {

            }
        });
    }
}
