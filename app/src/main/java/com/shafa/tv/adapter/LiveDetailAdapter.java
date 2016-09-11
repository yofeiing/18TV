package com.shafa.tv.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shafa.tv.R;
import com.shafa.tv.bean.VideoInfoBeaan;


import java.util.List;

/**
 * Created by fuyifang on 2016/8/11.
 */
public class LiveDetailAdapter extends BaseQuickAdapter<VideoInfoBeaan>{
    public LiveDetailAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, VideoInfoBeaan videoInfoBeaan) {
        baseViewHolder.setText(R.id.recommon_name,videoInfoBeaan.getName());
        Glide.with(mContext).load(videoInfoBeaan.getIco()).placeholder(R.drawable.cctv_).into((ImageView) baseViewHolder.getView(R.id.recommon_img));
    }
}
