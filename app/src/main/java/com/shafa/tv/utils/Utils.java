package com.shafa.tv.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

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
                    "com.shafa.tv", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }



}
