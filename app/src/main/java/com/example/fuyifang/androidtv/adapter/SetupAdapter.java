package com.example.fuyifang.androidtv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fuyifang.androidtv.R;

/**
 * Created by fuyifang on 2016/7/30.
 */
public class SetupAdapter extends RecyclerView.Adapter<SetupAdapter.Setupviewhoder> {
    private Context mContext;
    private String[] setup_text= {"软件更新","问题反馈","播放设置","关于"};
    private int[] setup_img = {R.drawable.ic_update,R.drawable.ic_feedback,R.drawable.ic_setup_play,R.drawable.ic_about};
    public SetupAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public Setupviewhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Setupviewhoder(LayoutInflater.from(mContext).inflate(R.layout.setup_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Setupviewhoder holder, int position) {
        holder.setup_img.setImageResource(setup_img[position]);
        holder.setup_text.setText(setup_text[position]);

    }



    @Override
    public int getItemCount() {
        return setup_text.length;
    }
    class Setupviewhoder extends RecyclerView.ViewHolder {
        ImageView setup_img;
        TextView setup_text;

        public Setupviewhoder(View itemView) {
            super(itemView);
            setup_img = (ImageView) itemView.findViewById(R.id.setup_img);
            setup_text = (TextView) itemView.findViewById(R.id.setup_text);
        }
    }
}
