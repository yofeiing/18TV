package com.example.fuyifang.androidtv.ui;

import android.content.Intent;
import android.os.Bundle;

import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.app.MainActivity;
import com.example.fuyifang.androidtv.common.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by loe on 12/6/16.
 */
public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext,InfoActivity.class);
                mContext.startActivity(intent);
                finish();
            }
        },1000);
    }
}
