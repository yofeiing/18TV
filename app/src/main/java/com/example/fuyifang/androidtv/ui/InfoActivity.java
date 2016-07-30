package com.example.fuyifang.androidtv.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.InfoAdapter;
import com.example.fuyifang.androidtv.adapter.SetupAdapter;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.bean.TodayRecommende;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.example.fuyifang.androidtv.common.BaseActivity;
import com.example.fuyifang.androidtv.utils.HttpUtils;
import com.example.fuyifang.androidtv.utils.LogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends BaseActivity {
    private RecyclerView reTodayRecommond;
    private RecyclerView re_Live;
    private RecyclerView re_Movie;
    private RecyclerView re_setup;
    private List<InfoBean> mDatas;
    private InfoAdapter mAdapter;
    private SetupAdapter setupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initview();
        initdata();
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        reTodayRecommond.setLayoutManager(manager);
        mAdapter = new InfoAdapter(R.layout.today_recommonde, mDatas);
        setupAdapter = new SetupAdapter(mContext);
        reTodayRecommond.setAdapter(mAdapter);
        LinearLayoutManager manager_movie = new LinearLayoutManager(mContext);
        manager_movie.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Movie.setLayoutManager(manager_movie);
        re_Movie.setAdapter(mAdapter);
        LinearLayoutManager manager_live = new LinearLayoutManager(mContext);
        manager_live.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Live.setLayoutManager(manager_live);
        re_Live.setAdapter(mAdapter);
        LinearLayoutManager manager_setup = new LinearLayoutManager(mContext);
        manager_setup.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_setup.setLayoutManager(manager_setup);
        re_setup.setAdapter(setupAdapter);
    }

    private void initview() {
        reTodayRecommond = (RecyclerView) findViewById(R.id.today_recommon);
        re_Live = (RecyclerView) findViewById(R.id.live_content);
        re_Movie = (RecyclerView) findViewById(R.id.movies_content);
        re_setup = (RecyclerView) findViewById(R.id.setup_content);

    }

    private void initdata() {
        mDatas = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            TodayRecommende raTodayRecommende = new TodayRecommende();
//            raTodayRecommende.setRecommendImg(R.drawable.jueditaowang);
//            raTodayRecommende.setRecommendName("绝命逃亡");
//            mDatas.add(raTodayRecommende);
//        }

        HttpUtils.get(AppConfig.TV_URL, null, null, new ApiStringCallback(InfoActivity.this) {
            @Override
            public void onSuccessEvent(String response) {
                showToast(response);
                Gson gson = new Gson();
                List<InfoBean> bean= (List<InfoBean>) gson.fromJson(response, InfoBean.class);
//                mDatas = bean.getRecommend();
//                final List<InfoBean.RecommendBean> recommend = bean.getRecommend();
//                LogUtil.i(bean.getRecommend()+"bean"+recommend);
                LogUtil.i(bean+"");

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        HttpUtils.cancel();
    }
}
