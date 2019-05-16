package com.fastweather.android.util;

import com.fastweather.android.pojo.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 13055 on 2019/5/16.
 */

public class GetUserInfoUtil {
    public static void getUserInfo(String address){
        HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String userInfoString = response.body().string();
            }
        });
    }
}
