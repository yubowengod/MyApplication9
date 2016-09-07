package com.example.god.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by GOD on 2016/9/6.
 */
public class listview_pic {
    public Bitmap onDecodeClicked(String string) {
        byte[] decode = Base64.decode(string,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
//        //save to image on sdcard
//        saveBitmap(bitmap);
        return bitmap;
    }
}
