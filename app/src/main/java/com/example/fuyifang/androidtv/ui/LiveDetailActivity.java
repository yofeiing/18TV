package com.example.fuyifang.androidtv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.LiveDetailAdapter;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.bean.VideoInfoBeaan;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.example.fuyifang.androidtv.common.BaseActivity;
import com.example.fuyifang.androidtv.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LiveDetailActivity extends BaseActivity {
    private RecyclerView re_livedetaill;
    private List<VideoInfoBeaan> mDatas;
    private LiveDetailAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);
        initData();
        re_livedetaill = (RecyclerView) findViewById(R.id.live_detail);
        re_livedetaill.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
    }

    private void initData() {
         String tvType = LiveDetailActivity.this.getIntent().getStringExtra("tv_type");
         String typeId = LiveDetailActivity.this.getIntent().getStringExtra("typeId");
         String url = String.format(AppConfig.TV_URL,tvType,typeId,new Date().getTime()+"");
         HttpUtils.get(url, null, null, new ApiStringCallback(LiveDetailActivity.this) {
            @Override
            public void onSuccessEvent(String response) {
                mDatas = new Gson().fromJson(response,new TypeToken<List<VideoInfoBeaan>>(){}.getType());
                Collections.reverse(mDatas);
                mAdapter = new LiveDetailAdapter(R.layout.today_recommonde,mDatas);
                re_livedetaill.setAdapter(mAdapter);
                mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {

                    @Override
                    public void onItemClick(View view, int i) {
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.putExtra("url", mDatas.get(i).getUrl());
                        intent.putExtra("title", mDatas.get(i).getName());
                        mContext.startActivity(intent);
                    }
                });
            }
        });
    }
}
