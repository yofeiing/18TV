package com.example.fuyifang.androidtv.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.utils.LogUtil;

import tcking.github.com.giraffeplayer.GiraffePlayer;

public class MainActivity extends AppCompatActivity {
    GiraffePlayer mPlayer;
    private String url = "http://113.247.251.13:5001/nn_live.ts?id=CCTV1HD";
    private String url1 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private Button btn_play;
    private EditText edt_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mPlayer = new GiraffePlayer(this,true);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        String[] split = url.split(",");
        LogUtil.i(split[0]);

        mPlayer.play(split[0]);
        mPlayer.setTitle(title);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPlayer != null)
        {
            mPlayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPlayer !=null)
        {
            mPlayer.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mPlayer != null && mPlayer.onBackPressed())
        {
                return;
        }
    }
}
