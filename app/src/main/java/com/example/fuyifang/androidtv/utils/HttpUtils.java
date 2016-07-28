package com.example.fuyifang.androidtv.utils;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.fuyifang.androidtv.common.ApiStringCallback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**网络请求工具类
 * Created by User on 2016/3/31.
 */
public class HttpUtils {

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @throws IOException
     */
    public static void get(String url, Map<String, String> params) throws IOException {
        OkHttpUtils
                .get()
                .url(url)
                .params(params)
                .build().execute();
    }


    /**
     * Get请求  异步 主要使用
     *
     * @param url
     * @param params
     * @param callback 代表是异步，null代表不进行回调处理
     * @throws IOException
     */
    public static void get(String url, Map<String, String> params, Map<String, String> headers , ApiStringCallback callback) {
        OkHttpUtils
                .get()
                .url(url)
                .headers(headers)
                .params(params)
                .build().execute(callback);
    }

    /**
     * Post 请求
     *
     * @param url  URL地址
     * @param json 将string作为请求体传入到服务端，例如json字符串。
     * @throws IOException
     */
    public static void postString(String url, String json) throws IOException {
        OkHttpUtils
                .postString()
                .url(url)
                .content(json)
                .build()
                .execute();
    }


    /**
     * Post 请求
     *
     * @param url      URL地址
     * @param json     将string作为请求体传入到服务端，例如json字符串。
     * @param callback 代表是异步,null代表不进行回调处理
     */
    public static void postString(String url, String json, StringCallback callback) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(json)
                .build()
                .execute(callback);
    }

    /**
     * Post 请求 异步 主要使用方法
     *
     * @param url      URL地址
     * @param json     将string作为请求体传入到服务端，例如json字符串。
     * @param mediaType  。
     * @param callback 代表是异步,null代表不进行回调处理
     */
    public static void postString(String url,String json, Map<String, String> headers,MediaType mediaType, ApiStringCallback callback) {

        if(mediaType==null){
            mediaType = MediaType.parse("application/json; charset=utf-8");
        }
        OkHttpUtils
                .postString()
                .url(url)
                .headers(headers)
                .mediaType(mediaType)
                .content(json)
                .build()
                .execute(callback);
    }


    /**
     * file作为请求体传入到服务端
     *
     * @param url  URL地址
     * @param file File文件
     * @throws IOException
     */
    public static void postFile(String url, File file) throws IOException {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute();
    }


    /**
     * file作为请求体传入到服务端
     *
     * @param url      URL地址
     * @param file     File文件
     * @param callback 代表是异步,null代表不进行回调处理
     */
    public static void postFile(String url, File file, StringCallback callback) {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .build()
                .execute(callback);
    }


    /**
     * 解析实体类  解析成List<>
     *
     * @param url
     * @param callback
     */
    public static void getLists(String url, Map<String, String> params, Callback callback) {
        OkHttpUtils//
                .post()//
                .url(url)//
                .params(params)//
                .build()//
                .execute(callback);
    }


    /**
     * 解析实体类  解析成Bean包
     *
     * @param url
     * @param callback
     */
    public static void getBean(String url, Map<String, String> params, Callback callback) {
        OkHttpUtils
                .post()//
                .url(url)//
                .params(params)//
                .build()//
                .execute(callback);
    }

    /**
     * 下载文件
     *
     * @param url          文件地址
     * @param destFileDir  目标文件存储的文件夹路径
     * @param destFileName 目标文件存储的文件名
     * @param callBack     文件下载回调
     */
    public static void downloadFile(String url, String destFileDir, String destFileName, FileCallBack callBack) {
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(callBack);
    }

    /**
     * 下载图片
     *
     * @param url
     */
    public static void getBitmap(String url, BitmapCallback callback) {
        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(callback);
    }


}
