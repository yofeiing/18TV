package com.example.fuyifang.androidtv.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.fuyifang.androidtv.R;
import com.example.fuyifang.androidtv.adapter.InfoAdapter;
import com.example.fuyifang.androidtv.adapter.SetupAdapter;
import com.example.fuyifang.androidtv.app.AppConfig;
import com.example.fuyifang.androidtv.app.AppContext;
import com.example.fuyifang.androidtv.bean.InfoBean;
import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.example.fuyifang.androidtv.common.BaseActivity;
import com.example.fuyifang.androidtv.utils.HttpUtils;
import com.example.fuyifang.androidtv.utils.LogUtil;
import com.example.fuyifang.androidtv.utils.Utils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.os.Environment.*;

public class InfoActivity extends BaseActivity {

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
    private static String downloadUrl;
    private static String updateName;
    private static final int RC_READ_AND_WRITE = 123;

    /* 更新进度条 */
    private ProgressBar mProgress;
    private Dialog mDownloadDialog;
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
       setupAdapter.setOnItemClickListner(new SetupAdapter.OnItemClickListner() {
           @Override
           public void Itemclick(View view, int position) {
               switch (position){
                   //软件更新
                   case 0:
                       showToast("已是最新版本");
                       break;
                   //问题反馈
                   case 1:
                       break;
                   //播放设置
                   case 2:
                       break;
                   //关于
                   case 3:
                       break;

               }
           }
       });


    }
    private void initdata() {
        data_tvlivebean = new ArrayList<>();
        data_movie = new ArrayList<>();
        data_recommendBean = new ArrayList<>();
        HttpUtils.get(AppConfig.TV_INFO, null, null, new ApiStringCallback(InfoActivity.this) {
            @Override
            public void onSuccessEvent(String response) {
                LogUtil.i(response);
                Gson gson = new Gson();
                InfoBean bean = gson.fromJson(response, InfoBean.class);
                data_recommendBean = bean.getRecommend() ;
                data_movie = bean.getMovie();
                data_tvlivebean= bean.getTvLive();
                initRecyclerView();
                updateApp(); //进行检查更新
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
        reLiveAdapter = new InfoAdapter(R.layout.today_recommonde, data_tvlivebean);
        re_Live.setAdapter(reLiveAdapter);
        reLiveAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                startActivity(new Intent(mContext,LiveDetailActivity.class));
            }
        });

        //点播电影
        LinearLayoutManager manager_movie = new LinearLayoutManager(mContext);
        manager_movie.setOrientation(LinearLayoutManager.HORIZONTAL);
        re_Movie.setLayoutManager(manager_movie);
        reMovieAdapter = new InfoAdapter(R.layout.today_recommonde, data_movie);
        re_Movie.setAdapter(reMovieAdapter);

    }

    /**
     * APP 检查更新
     */
    @AfterPermissionGranted(RC_READ_AND_WRITE)
    private void updateApp(){
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this,perms)) {
            HttpUtils.get(AppConfig.TV_UPDATE, null, null, new ApiStringCallback(null) {
                @Override
                public void onSuccessEvent(String response) {
                    try {
                        LogUtil.d("tag"+response);
                        JSONObject obj = new JSONObject(response);
                        String versionName = Utils.getVersionCode(AppContext.getAppContext());
                        updateName = obj.getString("version");
                        downloadUrl = obj.getString("url");
                        String detail = obj.getString("details");
                        if(!versionName.equals(updateName)){
                            showNoticeDialog(detail);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else {
            EasyPermissions.requestPermissions(this,getString(R.string.request_permissions), RC_READ_AND_WRITE, perms);
        }
    }
    /**
     * 显示软件更新对话框
     */
    private void showNoticeDialog(String msg)
    {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,R.style.update_alert);
        builder.setTitle(R.string.soft_update_title);
        builder.setMessage(msg);
        // 更新
        builder.setPositiveButton(R.string.soft_update_updatebtn, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                // 显示下载对话框
                showDownloadDialog();
            }

        });
        // 稍后更新
        builder.setNegativeButton(R.string.soft_update_later, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 显示软件下载对话框
     */
    private void showDownloadDialog() {
        // 构造软件下载对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,R.style.update_alert);
        builder.setTitle(R.string.soft_update_updatebtn + "···");
        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.softupdate_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        builder.setView(v);
        builder.setCancelable(false);
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        // 文件
        downloadApk();
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        File apkfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"18tv"+updateName+".apk");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }

    /**
     * 下载apk文件
     */
    public  void downloadApk() {
        LogUtil.i(downloadUrl);
        // 启动新线程下载软件
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpUtils.get().url(downloadUrl)
                        .build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),"18tv"+updateName+".apk") {
                    @Override
                    public void inProgress(float progress) {
                        mProgress.setProgress((int) (progress*100));
                    }
                    @Override
                    public void onError(Request request, Exception e) {
                        LogUtil.i(e.toString());
                    }
                    @Override
                    public void onResponse(File response) {
                        LogUtil.i(response.getAbsolutePath()+"文件地址");
                        installApk();
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpUtils.cancel();
    }
}
