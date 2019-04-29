package com.fastweather.android.util;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 13055 on 2019/4/29.
 */

public class HttpUtil {
    public static void sendOkHttpRequestByGet(String address, okhttp3.Callback callback){
        Log.d("this is address:", address);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
