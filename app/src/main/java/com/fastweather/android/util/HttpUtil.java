package com.fastweather.android.util;

import android.util.Log;

import com.fastweather.android.pojo.User;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 13055 on 2019/4/29.
 */

public class HttpUtil {
    public static void sendOkHttpRequestByGet(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
