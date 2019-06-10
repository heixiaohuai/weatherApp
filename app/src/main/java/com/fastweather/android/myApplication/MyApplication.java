package com.fastweather.android.myApplication;

import android.app.Application;
import android.content.Context;
import android.support.design.widget.Snackbar;

import com.fastweather.android.pojo.User;

import org.litepal.LitePal;

/**
 * Created by 13055 on 2019/4/29.
 */

public class MyApplication extends Application {
    private static Context context;
    private String okHttpURL;
    private int loginFlag;//登录标志，0表示未登录，1表示登录

    public int getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(int loginFlag) {
        this.loginFlag = loginFlag;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        MyApplication.context = context;
    }

    public String getOkHttpURL() {
        return okHttpURL;
    }

    public void setOkHttpURL(String okHttpURL) {
        this.okHttpURL = okHttpURL;
    }

    //小米手机联通卡开热点ip（192.168.43.226）
    //连接同一电信网ip（192.168.1.157）
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setOkHttpURL("192.168.43.226:8080");
        LitePal.initialize(context);
    }
}
