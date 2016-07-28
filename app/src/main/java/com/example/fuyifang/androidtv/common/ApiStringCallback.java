package com.example.fuyifang.androidtv.common;

import android.app.Activity;
import com.example.fuyifang.androidtv.app.AppContext;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by loe on 7/6/16.
 */
public abstract class ApiStringCallback extends StringCallback {

    private LoadingDialog dialog = null;
    public ApiStringCallback(Activity mActiviy){
        if(mActiviy!=null)
             dialog= AppContext.getAppContext().displayLoadingDialog(mActiviy,null,false);
    }

    @Override
    public void onError(Request request, Exception e) {
        if(dialog!=null)
        {
            dialog.dismiss();
        }
    }
    @Override
    public void onResponse(String response) {
        onSuccessEvent(response);
        if(dialog!=null)
        {
            dialog.dismiss();
        }
    }
    public abstract void onSuccessEvent(String response);
}
