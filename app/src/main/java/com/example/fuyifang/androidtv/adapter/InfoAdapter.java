package com.example.fuyifang.androidtv.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.app.AppContext;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.utils.UrlBitMap;

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
//        LogUtil.i(data.get(0).getRecommend().get(0).getName());
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Object object) {
        if (object instanceof InfoBean.RecommendBean) {
            baseViewHolder.setText(R.id.recommon_name, ((InfoBean.RecommendBean) object).getName());
            imgUri = (String) ((InfoBean.RecommendBean) object).getIco();
            if (((InfoBean.RecommendBean) object).getIco() == null||((InfoBean.RecommendBean) object).getIco().equals("")){
                baseViewHolder.setImageResource(R.id.recommon_img,R.mipmap.ic_launcher);
            }else {
                Glide.with(mContext).load(imgUri).into((ImageView) baseViewHolder.getView(R.id.recommon_img));
//                baseViewHolder.setImageBitmap(R.id.recommon_img, UrlBitMap.createVideoThumbnail(((InfoBean.RecommendBean) object).getUrl(),150,150));
            }
        }else if (object instanceof InfoBean.MovieBean){
            baseViewHolder.setText(R.id.recommon_name,((InfoBean.MovieBean)object).getClassName());
        }else if (object instanceof InfoBean.TvLiveBean){
            baseViewHolder.setText(R.id.recommon_name,((InfoBean.TvLiveBean)object).getClassName());
        }

    }


}
