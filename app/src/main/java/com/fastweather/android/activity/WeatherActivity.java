package com.fastweather.android.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.fastweather.android.R;
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

/**
 * Created by 13055 on 2019/4/30.
 */

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;//地域
    private TextView titleUpdateTime;//更新时间
    private TextView degreeText;//温度
    private TextView weatherInfoText;//天气信息的描述
    private LinearLayout forecastLayout;//未来七天天气的展示
    private TextView aqiText;//空气指数
    private TextView aqiLevelText;//空气指数的等级
    private TextView pm25Text;//PM2.5指数
    private ImageView suggestionComfortImage;//舒适度的图片
    private TextView suggestionComfortTitleText;//舒适度的title
    private ImageView suggestionDYImage;//垂钓的图片
    private TextView suggestionDYTitleText;//垂钓的title
    private ImageView suggestionXCImage;//洗车的图片
    private TextView suggestionXCTitleText;//洗车的title
    private ImageView suggestionGMImage;//感冒的图片
    private TextView suggestionGMTitleText;//感冒的title
    private ImageView suggestionZWXImage;//紫外线的图片
    private TextView suggestionZWXTitleText;//紫外线的title
    private ImageView suggestionCYImage;//穿衣的图片
    private TextView suggestionCYTitleText;//穿衣的title
    private ImageView suggestionGJImage;//逛街的图片
    private TextView suggestionGJTitleText;//逛街的title
    private ImageView suggestionPJImage;//啤酒的图片
    private TextView suggestionPJTitleText;//啤酒的title

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        //初始化各个控件
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        aqiLevelText = (TextView) findViewById(R.id.aqi_level_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        suggestionComfortImage = (ImageView) findViewById(R.id.suggestion1_img);
        suggestionComfortTitleText = (TextView) findViewById(R.id.suggestion1_title);
        suggestionDYImage = (ImageView) findViewById(R.id.suggestion2_img);
        suggestionDYTitleText = (TextView) findViewById(R.id.suggestion2_title);
        suggestionXCImage = (ImageView) findViewById(R.id.suggestion3_img);
        suggestionXCTitleText = (TextView) findViewById(R.id.suggestion3_title);
        suggestionGMImage = (ImageView) findViewById(R.id.suggestion4_img);
        suggestionGMTitleText = (TextView) findViewById(R.id.suggestion4_title);
        suggestionZWXImage = (ImageView) findViewById(R.id.suggestion5_img);
        suggestionZWXTitleText = (TextView) findViewById(R.id.suggestion5_title);
        suggestionCYImage = (ImageView) findViewById(R.id.suggestion6_img);
        suggestionCYTitleText = (TextView) findViewById(R.id.suggestion6_title);
        suggestionGJImage = (ImageView) findViewById(R.id.suggestion7_img);
        suggestionGJTitleText = (TextView) findViewById(R.id.suggestion7_title);
        suggestionPJImage = (ImageView) findViewById(R.id.suggestion8_img);
        suggestionPJTitleText = (TextView) findViewById(R.id.suggestion8_title);

        String cityName = getIntent().getStringExtra("cityName");
        weatherLayout.setVisibility(View.INVISIBLE);
        requestWeather(cityName);
    }

    /*
    * 根据城市名来查询天气信息
    * */
    private void requestWeather(String cityName) {
        String weatherURL = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getWeather/" + cityName;
        HttpUtil.sendOkHttpRequestByGet(weatherURL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApplication.getContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final NowWeather nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(responseText);
                final List<WeatherInfo> futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (nowWeather != null && futureWeatherInfoList != null){
                            showWeatherInfo(nowWeather, futureWeatherInfoList);
                        }else {
                            Toast.makeText(MyApplication.getContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    /*
    * 将JSON数据中的天气信息进行展示
    * */
    private void showWeatherInfo(NowWeather nowWeather, List<WeatherInfo> futureWeatherInfoList) {
        String cityName = nowWeather.getAqiDetail().getArea();
        String updateTime = nowWeather.getTemperature_time();
        String degree = nowWeather.getTemperature() + "°C";
        String weatherInfo = nowWeather.getWeather();
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (int i=0; i<futureWeatherInfoList.size(); i++){
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView weekText = (TextView) view.findViewById(R.id.week);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxTemp = (TextView) view.findViewById(R.id.max_text);
            TextView minTemp = (TextView) view.findViewById(R.id.min_text);
        }
    }


}
