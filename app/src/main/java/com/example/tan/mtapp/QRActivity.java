package com.example.tan.mtapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tan.mtapp.staticPack.StaticClass;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRActivity extends AppCompatActivity {

    ImageView mQR;
    TextView mName,mDT,mSTD,mEND,mRM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        mQR = (ImageView) findViewById(R.id.qrView);
//        mName = (TextView) findViewById(R.id.ac_name);
        mDT = (TextView) findViewById(R.id.date_time);
        mSTD = (TextView) findViewById(R.id.start_date);
        mEND = (TextView) findViewById(R.id.end_date);
        mRM = (TextView) findViewById(R.id.room);

        String text = StaticClass.USER_MODEL.getProfile().getUsername()+"_"+StaticClass.ACTIVITY_PICKER.getAc_name();// Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            mQR.setImageBitmap(bitmap);
            mDT.setText(StaticClass.ACTIVITY_DETAIL.getDetail().get(0).getDate() + " " + StaticClass.ACTIVITY_DETAIL.getDetail().get(0).getTime() );
            mSTD.setText(StaticClass.ACTIVITY_DETAIL.getDetail().get(0).getStart_date());
            mEND.setText(StaticClass.ACTIVITY_DETAIL.getDetail().get(0).getEnd_date());
            mRM.setText(StaticClass.ACTIVITY_DETAIL.getDetail().get(0).getActivity_name());
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
