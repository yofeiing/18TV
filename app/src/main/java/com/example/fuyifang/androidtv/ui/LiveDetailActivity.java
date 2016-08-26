package com.example.fuyifang.androidtv.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.LiveDetailAdapter;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.bean.TodayRecommende;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.example.fuyifang.androidtv.common.BaseActivity;
import com.example.fuyifang.androidtv.utils.HttpUtils;
import com.example.fuyifang.androidtv.utils.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LiveDetailActivity extends BaseActivity {
    private RecyclerView re_livedetaill;
    private List<TodayRecommende> mDatas;
    private LiveDetailAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);
        initData();
        re_livedetaill = (RecyclerView) findViewById(R.id.live_detail);
        re_livedetaill.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new LiveDetailAdapter(R.layout.today_recommonde,mDatas);
        re_livedetaill.setAdapter(mAdapter);
    }

    private void initData() {
         String tvType = LiveDetailActivity.this.getIntent().getStringExtra("tv_type");
         String typeId = LiveDetailActivity.this.getIntent().getStringExtra("typeId");
         String url = String.format(AppConfig.TV_URL,tvType,typeId,new Date().getTime()+"");
         HttpUtils.get(url, null, null, new ApiStringCallback(LiveDetailActivity.this) {
            @Override
            public void onSuccessEvent(String response) {
                LogUtil.d("TAG"+ response);
            }
        });
    }
}
