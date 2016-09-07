package com.shafa.tv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


import com.shafa.tv.R;

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
        //TODO  做播放地址更换
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
