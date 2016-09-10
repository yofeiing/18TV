package com.shafa.tv.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shafa.tv.R;
import com.shafa.tv.app.AppContext;
import com.shafa.tv.bean.InfoBean;


import java.util.List;

/**
 * Created by fuyifang on 2016/7/26.
 */
public class InfoAdapter extends BaseQuickAdapter {
    private Context mContext;
    private String imgUri;
    public InfoAdapter(int layoutResId, List data) {
        super(layoutResId, data);
        this.mContext = AppContext.getAppContext();
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object object) {
        if (object instanceof InfoBean.RecommendBean) {
            baseViewHolder.setText(R.id.recommon_name, ((InfoBean.RecommendBean) object).getName());
            imgUri = (String) ((InfoBean.RecommendBean) object).getIco();
            Glide.with(mContext).load(imgUri).placeholder(R.mipmap.ic_launcher).into((ImageView) baseViewHolder.getView(R.id.recommon_img));
        }else if (object instanceof InfoBean.MovieBean){
            baseViewHolder.setText(R.id.recommon_name,((InfoBean.MovieBean)object).getClassName());
            imgUri = ((InfoBean.MovieBean) object).getIco();
            Glide.with(mContext).load(imgUri).placeholder(R.mipmap.ic_launcher).into((ImageView) baseViewHolder.getView(R.id.recommon_img));
        }else if (object instanceof InfoBean.TvLiveBean){
            baseViewHolder.setText(R.id.recommon_name,((InfoBean.TvLiveBean)object).getClassName());
            imgUri = ((InfoBean.TvLiveBean) object).getIco();
            Glide.with(mContext).load(imgUri).placeholder(R.mipmap.ic_launcher).into((ImageView) baseViewHolder.getView(R.id.recommon_img));
        }

    }


}
