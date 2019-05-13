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
    private String loginPhone;

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

    public String getLoginPhone() {
        return loginPhone;
    }

    public void setLoginPhone(String loginPhone) {
        this.loginPhone = loginPhone;
    }

    //服务器ip（132.232.43.80）
    //小米手机开热点ip（）
    //连接同一电信网ip（192.168.1.157）
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        setOkHttpURL("192.168.1.157:8080");
        LitePal.initialize(context);
        loginPhone = null;
    }
}
