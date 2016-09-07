package com.shafa.tv.app;

import android.app.Activity;
import android.app.Application;
import android.widget.TextView;

import com.shafa.tv.R;
import com.shafa.tv.common.LoadingDialog;


/**
 * Created by loe on 12/6/16.
 */
public class AppContext extends Application {

    //全局ApplicationContext
    private static AppContext appContext = null;
    //用于存储临时变量
    private Object tmpObject;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        //各种初始化操作,包括极光推送 百度地图 友盟等第三方sdk 初始化 Log 信息抓取
    }

    public static AppContext getAppContext(){
        return appContext;
    }

    //取出临时变量
    public Object getTmpObject(){
        return tmpObject;
    }
    //存放临时变量
    public void setTmpObject(Object obj){
        tmpObject = obj;
    }


    /**
     * 全局显示LoagingDialog
     * @param loadingtips
     * @param isCanCancel
     * @return
     */
    public LoadingDialog displayLoadingDialog(Activity mActiviy, String loadingtips, boolean isCanCancel) {

        LoadingDialog dialog = new LoadingDialog(mActiviy, R.layout.dlg_loading,
                R.style.Theme_dialog);
        TextView message = (TextView) dialog.findViewById(R.id.message);
        if (loadingtips == null) {
            message.setText(R.string.loading_tips);
        } else {
            message.setText(loadingtips);
        }
        dialog.show();
        dialog.setCancelable(isCanCancel);
        return dialog;
    }


}
