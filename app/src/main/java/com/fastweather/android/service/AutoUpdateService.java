package com.fastweather.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.fastweather.android.activity.WeatherActivity;
import com.fastweather.android.gson.NowWeather;
import com.fastweather.android.gson.WeatherInfo;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.util.HttpUtil;
import com.fastweather.android.util.ParseGsonUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour = 1 * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, i, 0);
        manager.cancel(pendingIntent);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    /*
    * 更新天气信息
    * */
    private void updateWeather() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather", null);
        if (weatherString != null){
            //有缓存时直接加载数据进行展示，不进行网络请求
            NowWeather nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(weatherString);
            List<WeatherInfo> futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(weatherString);
            String cityName = nowWeather.getAqiDetail().getArea();
            String weatherURL = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getWeather/" + cityName;
            HttpUtil.sendOkHttpRequestByGet(weatherURL, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    NowWeather nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(responseText);
                    List<WeatherInfo> futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(responseText);
                    if (nowWeather != null && futureWeatherInfoList != null){
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                        editor.putString("weather", responseText);
                        editor.apply();
                    }
                }
            });
        }
    }

    /*
    * 更新必应每日一图
    * */
    private void updateBingPic() {
        String requestBingPic = "http://"+ ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getBingPic";
        HttpUtil.sendOkHttpRequestByGet(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(AutoUpdateService.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
        });
    }
}
