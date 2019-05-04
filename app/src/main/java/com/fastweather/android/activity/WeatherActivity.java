package com.fastweather.android.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fastweather.android.R;
import com.fastweather.android.gson.CityInfo;
import com.fastweather.android.gson.NowWeather;
import com.fastweather.android.gson.WeatherInfo;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.service.AutoUpdateService;
import com.fastweather.android.util.HttpUtil;
import com.fastweather.android.util.ParseGsonUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 13055 on 2019/4/30.
 */

public class WeatherActivity extends AppCompatActivity {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d号");

    public ImageView bingPicImage;
    public SwipeRefreshLayout swipeRefresh;
    private String mCityName;
    private Button navButton;
    public DrawerLayout drawerLayout;

    private ImageView weatherImage;
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

    @SuppressLint("ResourceAsColor")
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
        suggestionGJImage = (ImageView) findViewById(R.id.suggestion4_img);
        suggestionGJTitleText = (TextView) findViewById(R.id.suggestion4_title);
        suggestionGMImage = (ImageView) findViewById(R.id.suggestion5_img);
        suggestionGMTitleText = (TextView) findViewById(R.id.suggestion5_title);
        suggestionCYImage = (ImageView) findViewById(R.id.suggestion6_img);
        suggestionCYTitleText = (TextView) findViewById(R.id.suggestion6_title);
        suggestionZWXImage = (ImageView) findViewById(R.id.suggestion7_img);
        suggestionZWXTitleText = (TextView) findViewById(R.id.suggestion7_title);
        suggestionPJImage = (ImageView) findViewById(R.id.suggestion8_img);
        suggestionPJTitleText = (TextView) findViewById(R.id.suggestion8_title);
        bingPicImage = (ImageView) findViewById(R.id.bing_pic_image);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeColors(R.color.colorPrimary);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navButton = (Button) findViewById(R.id.nav_button);
        weatherImage = (ImageView) findViewById(R.id.weatherImage);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null){
            //有缓存时直接加载数据进行展示，不进行网络请求
            NowWeather nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(weatherString);
            List<WeatherInfo> futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(weatherString);
            CityInfo cityInfo = ParseGsonUtil.handleCityInfoResponse(weatherString);
            mCityName = cityInfo.getC3();
            showWeatherInfo(nowWeather, futureWeatherInfoList, cityInfo);
        }else {
            //无缓存时进行网络请求展示数据
            mCityName = getIntent().getStringExtra("cityName");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(mCityName);
        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(mCityName);
            }
        });
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null){
            Glide.with(this).load(bingPic).into(bingPicImage);
        }else {
            loadBingPic();
        }
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    /*
    * 根据城市名来查询天气信息
    * */
    public void requestWeather(String countyName) {
        String weatherURL = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getWeather/" + countyName;
        Log.d("weatherURL", weatherURL);
        HttpUtil.sendOkHttpRequestByGet(weatherURL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApplication.getContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d("responseText", responseText);
                final NowWeather nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(responseText);
                Log.d("nowWeather", String.valueOf(nowWeather));
                final List<WeatherInfo> futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(responseText);
                Log.d("futureWeatherInfoList", String.valueOf(futureWeatherInfoList));
                final CityInfo cityInfo = ParseGsonUtil.handleCityInfoResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (nowWeather != null && futureWeatherInfoList != null){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            mCityName = cityInfo.getC3();
                            Log.d("mCityName", mCityName);
                            showWeatherInfo(nowWeather, futureWeatherInfoList, cityInfo);
                        }else {
                            Toast.makeText(MyApplication.getContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }

    /*
    * 加载必应每日一图
    * */
    private void loadBingPic(){
        String requestBingPic = "http://"+ ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getBingPic";
        HttpUtil.sendOkHttpRequestByGet(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImage);
                    }
                });
            }
        });
    }

    /*
    * 将JSON数据中的天气信息进行展示
    * */
    private void showWeatherInfo(final NowWeather nowWeather, List<WeatherInfo> futureWeatherInfoList, CityInfo cityInfo) {
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
        String cityName = cityInfo.getC3();
        Log.d("titleCityName", cityName);
        String updateTime = nowWeather.getTemperature_time();
        String degree = nowWeather.getTemperature() + "°C";
        String weatherInfo = nowWeather.getWeather();
        titleCity.setText(cityName);
        titleUpdateTime.setText("更新时间："+updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
        editor.putString("weatherImage", nowWeather.getWeather_pic());
        editor.apply();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(WeatherActivity.this).load(nowWeather.getWeather_pic()).into(weatherImage);
            }
        });
        /*
        * 展示未来几天的天气信息
        * */
        for (int i=0; i<futureWeatherInfoList.size(); i++){
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView weekText = (TextView) view.findViewById(R.id.week);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxTemp = (TextView) view.findViewById(R.id.max_text);
            TextView minTemp = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(simpleDateFormat.format(getAfterDate(new Date(), i)).toString());
            weekText.setText(parseWeek(futureWeatherInfoList.get(i).getWeekday()));
            infoText.setText(futureWeatherInfoList.get(i).getDay_weather());
            maxTemp.setText(futureWeatherInfoList.get(i).getDay_air_temperature());
            minTemp.setText(futureWeatherInfoList.get(i).getNight_air_temperature());
            forecastLayout.addView(view);
        }
        /*
        * 展示空气污染指数，空气质量等级以及pm2.5含量
        * */
        if (nowWeather.getAqiDetail() != null){
            aqiText.setText(nowWeather.getAqiDetail().getAqi());
            aqiLevelText.setText(nowWeather.getAqiDetail().getQuality());
            pm25Text.setText(nowWeather.getAqiDetail().getPm2_5());
        }
        /*
        * 展示生活小建议
        * */
        suggestionComfortImage.setImageResource(R.drawable.shushi);
        suggestionComfortTitleText.setText(futureWeatherInfoList.get(1).getIndex().getComfort().getTitle());
        suggestionDYImage.setImageResource(R.drawable.diaoyu);
        suggestionDYTitleText.setText(futureWeatherInfoList.get(1).getIndex().getDy().getTitle());
        suggestionXCImage.setImageResource(R.drawable.xiche);
        suggestionXCTitleText.setText(futureWeatherInfoList.get(1).getIndex().getWash_car().getTitle());
        suggestionGJImage.setImageResource(R.drawable.gouwu);
        suggestionGJTitleText.setText(futureWeatherInfoList.get(1).getIndex().getGj().getTitle());
        suggestionGMImage.setImageResource(R.drawable.ganmao);
        suggestionGMTitleText.setText(futureWeatherInfoList.get(1).getIndex().getCold().getTitle());
        suggestionCYImage.setImageResource(R.drawable.chuanyi);
        suggestionCYTitleText.setText(futureWeatherInfoList.get(1).getIndex().getClothes().getTitle());
        suggestionZWXImage.setImageResource(R.drawable.ziwaixian);
        suggestionZWXTitleText.setText(futureWeatherInfoList.get(1).getIndex().getUv().getTitle());
        suggestionPJImage.setImageResource(R.drawable.pijiu);
        suggestionPJTitleText.setText(futureWeatherInfoList.get(1).getIndex().getPj().getTitle());
        weatherLayout.setVisibility(View.VISIBLE);
    }

    /*
    * 获取今天以及后六天的日期，用于展示
    * */
    public Date getAfterDate(Date date, int number){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, number);
        return calendar.getTime();
    }

    /*
    * 根据返回的星期编号返回正确的星期
    * */
    public String parseWeek(int i){
        if (i == 1){
            return "星期一";
        }else if (i == 2){
            return "星期二";
        }else if (i == 3){
            return "星期三";
        }else if (i == 4){
            return "星期四";
        }else if (i == 5){
            return "星期五";
        }else if (i == 6){
            return "星期六";
        }else if (i == 7){
            return "星期天";
        }
        return null;
    }
}
