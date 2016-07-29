package com.example.fuyifang.androidtv.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.InfoAdapter;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.bean.TodayRecommende;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.example.fuyifang.androidtv.common.BaseActivity;
import com.example.fuyifang.androidtv.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InfoActivity extends BaseActivity {
    private RecyclerView reTodayRecommond;
    private RecyclerView re_Live;
    private RecyclerView re_Movie;
    private List<TodayRecommende> mDatas;
    private InfoAdapter mAdapter;
//    private Timer mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initview();
        initdata();
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        reTodayRecommond.setLayoutManager(manager);
        mAdapter = new InfoAdapter(R.layout.today_recommonde,mDatas);
        reTodayRecommond.setAdapter(mAdapter);
        LinearLayoutManager manager_movie = new LinearLayoutManager(mContext);
        manager_movie.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Movie.setLayoutManager(manager_movie);
        re_Movie.setAdapter(mAdapter);
        LinearLayoutManager manager_live = new LinearLayoutManager(mContext);
        manager_live.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Live.setLayoutManager(manager_live);
        re_Live.setAdapter(mAdapter);
    }

    private void initview() {
        reTodayRecommond = (RecyclerView) findViewById(R.id.today_recommon);
        re_Live = (RecyclerView) findViewById(R.id.live_content);
        re_Movie = (RecyclerView) findViewById(R.id.movies_content);

    }

    private void initdata() {
        mDatas = new ArrayList<>();
        for (int i=0;i<9;i++){
            TodayRecommende raTodayRecommende = new TodayRecommende();
            raTodayRecommende.setRecommendImg(R.drawable.jueditaowang);
            raTodayRecommende.setRecommendName("绝命逃亡");
            mDatas.add(raTodayRecommende);
        }

        HttpUtils.get(AppConfig.TV_URL, null, null, new ApiStringCallback(InfoActivity.this) {
            @Override
            public void onSuccessEvent(String response) {
                showToast(response);
            }
        });

//        mTimer = new Timer();

//        mTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                InfoActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        HttpUtils.get("http://www.google.com", null, null, new ApiStringCallback(InfoActivity.this) {
//                            @Override
//                            public void onSuccessEvent(String response) {
//                                showToast(response);
//                            }
//                        });
//                    }
//                });
//            }
//        },5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtils.get("http://www.baidu.com", null, null, new ApiStringCallback(InfoActivity.this) {
                            @Override
                            public void onSuccessEvent(String response) {
                                showToast(response);
                            }
                        });
                    }
                });
            }
        }, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        showToast("--");
        HttpUtils.cancel();
//        mTimer.cancel();
    }
}
