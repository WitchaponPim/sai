package com.example.tan.mtapp.staticPack;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.widget.Toast;

import com.example.tan.mtapp.Model.AcDetailModel;
import com.example.tan.mtapp.Model.ActivityModel;
import com.example.tan.mtapp.Model.HistoryModel;
import com.example.tan.mtapp.Model.JobModel;
import com.example.tan.mtapp.Model.ReserveModel;
import com.example.tan.mtapp.Model.SearchModel;
import com.example.tan.mtapp.Model.SeatModel;
import com.example.tan.mtapp.Model.UserModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StaticClass {

    public static UserModel USER_MODEL;
    public static UserModel USER_MODEL_AT;
    public static ActivityModel ACTIVITY_MODEL;
    public static ActivityModel ACTIVITY_MODEL2;
    public static List<HistoryModel> HISTORY_MODEL;
    public static ActivityModel.DetailBean ACTIVITY_PICKER;
    public static AcDetailModel ACTIVITY_DETAIL;
    public static ReserveModel ACTIVITY_QR;
    public static ActivityModel ACTIVITY_REGIS;
    public static List<SeatModel.DetailBean> seatRoom;
    public static List<SearchModel> SEARCH_MODEL;
    public static List<JobModel> JOB_MODEL;

    public static void toast(Context context, String string) {
        Toast toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        toast.show();

    }

    /**
     * @param context
     * @param dirName
     * @param fileName
     * @param fileType
     * @return
     */
    public static File createImageFile(
            Context context,
            String dirName,
            String fileName,
            String fileType) {
        try {
            File file = createDir(context, dirName);
            File image = new File(file.getAbsoluteFile() + File.separator + fileName + fileType);
            if (!image.getParentFile().exists()) {
                image.getParentFile().mkdirs();
            }
            image.createNewFile();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param context
     * @param dirName
     * @return
     */
    public static File createDir(Context context, String dirName) {
        File file = new File(context.getFilesDir() + File.separator + dirName);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    /**
     * @param file
     * @param requiredHeight
     * @return
     */
    public static Bitmap decodeFile(File file, int requiredHeight) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, o);

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= requiredHeight &&
                    o.outHeight / scale / 2 >= requiredHeight) {
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(file), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param bitmap
     * @param filePath
     * @param imageType
     * @param compression
     */
    public static void saveBitmap(Bitmap bitmap, String filePath, String imageType, int compression) {

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            // PNG is a loss less format, the compression factor (100) is ignored
            switch (imageType) {
                case "png":
                case "PNG":
                case ".png":
                    bitmap.compress(Bitmap.CompressFormat.PNG, compression, out);
                    break;
                case "jpg":
                case "JPG":
                case ".jpg":
                case "jpeg":
                case "JPEG":
                case ".jpeg":
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compression, out);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param imagePath
     * @return
     */
    public static int getImageRotation(String imagePath) {
        try {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ExifInterface exif = new ExifInterface(imageFile.getPath());
                int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                return exifToDegrees(rotation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param src
     * @param rotation
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap src, int rotation) {
        Matrix matrix = new Matrix();
        if (rotation != 0) {
            matrix.preRotate(rotation);
            return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        }
        return src;
    }

    /**
     * @param exifOrientation
     * @return
     */
    private static int exifToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }

        return 0;
    }
}
