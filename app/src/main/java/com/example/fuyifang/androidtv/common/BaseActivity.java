package com.example.fuyifang.androidtv.common;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.app.AppContext;
import com.example.fuyifang.androidtv.utils.HttpUtils;
import com.example.fuyifang.androidtv.utils.LogUtil;
import com.example.fuyifang.androidtv.utils.Utils;

/**
 * Created by fuyifang on 2016/7/24.
 */
public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected AppContext  mAppContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mAppContext = AppContext.getAppContext();
        LogUtil.isDebug = true;
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }
    public void showToast(String text){
        Utils.showToast(mContext,text);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
