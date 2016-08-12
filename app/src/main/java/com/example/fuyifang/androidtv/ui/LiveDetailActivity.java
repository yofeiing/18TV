package com.example.fuyifang.androidtv.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.LiveDetailAdapter;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.bean.TodayRecommende;
import com.example.fuyifang.androidtv.common.BaseActivity;

import java.util.ArrayList;
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
        re_livedetaill.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new LiveDetailAdapter(R.layout.today_recommonde,mDatas);
        re_livedetaill.setAdapter(mAdapter);


    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i =0;i<20;i++){
            TodayRecommende bean = new TodayRecommende();
            bean.setRecommendName("中央卫视");
            bean.setRecommendImg(R.drawable.jueditaowang);
            mDatas.add(bean);
        }
    }
}
