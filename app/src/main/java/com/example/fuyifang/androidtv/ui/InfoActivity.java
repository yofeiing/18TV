package com.example.fuyifang.androidtv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.InfoAdapter;
import com.example.fuyifang.androidtv.adapter.SetupAdapter;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.example.fuyifang.androidtv.common.BaseActivity;
import com.example.fuyifang.androidtv.utils.HttpUtils;
import com.example.fuyifang.androidtv.utils.LogUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends BaseActivity{

    private RecyclerView reTodayRecommond;
    private RecyclerView re_Live;
    private RecyclerView re_Movie;
    private RecyclerView re_setup;
    private List<InfoBean.RecommendBean> data_recommendBean;
    private List<InfoBean.MovieBean> data_movie;
    private List<InfoBean.TvLiveBean> data_tvlivebean;
    private RelativeLayout re_person;
    private InfoAdapter recommendAdapter;
    private InfoAdapter reMovieAdapter;
    private InfoAdapter reLiveAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initdata();
        initview();
    }

    private void initview() {
        reTodayRecommond = (RecyclerView) findViewById(R.id.today_recommon);
        re_Live = (RecyclerView) findViewById(R.id.live_content);
        re_Movie = (RecyclerView) findViewById(R.id.movies_content);
        re_setup = (RecyclerView) findViewById(R.id.setup_content);
        re_person = (RelativeLayout) findViewById(R.id.re_person);
        re_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginActivity.class));
            }
        });

        // 设置  RecyclerView
        LinearLayoutManager manager_setup = new LinearLayoutManager(mContext);
        manager_setup.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_setup.setLayoutManager(manager_setup);
        SetupAdapter setupAdapter = new SetupAdapter(mContext);
        re_setup.setAdapter(setupAdapter);

    }
    private void initdata() {
        data_tvlivebean = new ArrayList<>();
        data_movie = new ArrayList<>();
        data_recommendBean = new ArrayList<>();
        HttpUtils.get(AppConfig.TV_INFO, null, null, new ApiStringCallback(InfoActivity.this) {
            @Override
            public void onSuccessEvent(String response) {
                Gson gson = new Gson();
                InfoBean bean = gson.fromJson(response, InfoBean.class);
                data_recommendBean = bean.getRecommend() ;
                data_movie = bean.getMovie();
                data_tvlivebean= bean.getTvLive();
                initRecyclerView();
            }
        });


    }

    private void initRecyclerView(){
        //推荐视频
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        reTodayRecommond.setLayoutManager(manager);
        recommendAdapter = new InfoAdapter(R.layout.today_recommonde, data_recommendBean);
        reTodayRecommond.setAdapter(recommendAdapter);
        recommendAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("url", data_recommendBean.get(i).getUrl());
                intent.putExtra("title", data_recommendBean.get(i).getName());
                mContext.startActivity(intent);
            }
        });

        //直播视频
        LinearLayoutManager manager_live = new LinearLayoutManager(mContext);
        manager_live.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Live.setLayoutManager(manager_live);
        reLiveAdapter = new InfoAdapter(R.layout.today_recommonde, data_recommendBean);
        re_Live.setAdapter(reLiveAdapter);
//        reLiveAdapter.setOnRecyclerViewItemClickListener(this);

        //点播电影
        LinearLayoutManager manager_movie = new LinearLayoutManager(mContext);
        manager_movie.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Movie.setLayoutManager(manager_movie);
        reMovieAdapter = new InfoAdapter(R.layout.today_recommonde, data_recommendBean);
        re_Movie.setAdapter(reMovieAdapter);
//        reMovieAdapter.setOnRecyclerViewItemClickListener(this);

    }


    @Override
    protected void onPause() {
        super.onPause();
        HttpUtils.cancel();
    }

}
