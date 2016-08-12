package com.example.fuyifang.androidtv.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.app.AppContext;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by fuyifang on 2016/7/26.
 */
public class Utils {
    private static Toast toast;
    private static String downloadUri;
    public  static void showToast(Context context,String content){
        if (toast == null){
            toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(content);
        }
        toast.show();

    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static  String getVersionCode(Context context) {
        String versionName = "" ;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionName = context.getPackageManager().getPackageInfo(
                    "com.example.fuyifang.androidtv", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

}
