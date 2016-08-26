package com.example.fuyifang.androidtv.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.bean.TodayRecommende;

import java.util.List;

/**
 * Created by fuyifang on 2016/8/11.
 */
public class LiveDetailAdapter extends BaseQuickAdapter<TodayRecommende>{
    public LiveDetailAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, TodayRecommende recommende) {
        baseViewHolder.setText(R.id.recommon_name,recommende.getRecommendName());
        baseViewHolder.setImageResource(R.id.recommon_img,recommende.getRecommendImg());
    }
}
