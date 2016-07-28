package com.example.fuyifang.androidtv.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.bean.TodayRecommende;

import java.util.List;

/**
 * Created by fuyifang on 2016/7/26.
 */
public class InfoAdapter extends BaseQuickAdapter<TodayRecommende> {
    public InfoAdapter(int layoutResId, List<TodayRecommende> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TodayRecommende todayRecommende) {
        baseViewHolder.setImageResource(R.id.recommon_img,todayRecommende.getRecommendImg());
        baseViewHolder.setText(R.id.recommon_name,todayRecommende.getRecommendName());


    }
}
