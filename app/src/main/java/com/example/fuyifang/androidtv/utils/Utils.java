package com.example.fuyifang.androidtv.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fuyifang on 2016/7/26.
 */
public class Utils {
    private static Toast toast;
    public  static void showToast(Context context,String content){
        if (toast == null){
            toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(content);
        }
        toast.show();
    }
}
