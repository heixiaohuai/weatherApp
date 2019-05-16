package com.fastweather.android.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bumptech.glide.Glide;
import com.fastweather.android.R;
import com.fastweather.android.gson.CityInfo;
import com.fastweather.android.gson.NowWeather;
import com.fastweather.android.gson.Suggestion;
import com.fastweather.android.gson.WeatherInfo;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.pojo.User;
import com.fastweather.android.service.AutoUpdateService;
import com.fastweather.android.util.HttpUtil;
import com.fastweather.android.util.ParseGsonUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 13055 on 2019/4/30.
 */

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M月d号");

    public ImageView bingPicImage;
    public SwipeRefreshLayout swipeRefresh;
    private String mCityName;
    private Button navButton;
    private Button myButton;
    private Button settingButton;
    public DrawerLayout drawerLayout;

    private FloatingActionButton fab_getLoction;//定位的悬浮按钮

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

    private RelativeLayout suggestion1;
    private RelativeLayout suggestion2;
    private RelativeLayout suggestion3;
    private RelativeLayout suggestion4;
    private RelativeLayout suggestion5;
    private RelativeLayout suggestion6;
    private RelativeLayout suggestion7;
    private RelativeLayout suggestion8;

    private LinearLayout aqiLinearLayout;

    private Suggestion suggestion;
    private NowWeather nowWeather;
    private List<WeatherInfo> futureWeatherInfoList;
    private CityInfo cityInfo;

    private LocationClient mLocationClient;
    private String cityNameByLocation;

    private List<String> permissionList;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
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
        navButton.setOnClickListener(this);
        settingButton = (Button) findViewById(R.id.settings_button);
        settingButton.setOnClickListener(this);
        myButton = (Button) findViewById(R.id.my_button);
        myButton.setOnClickListener(this);
        weatherImage = (ImageView) findViewById(R.id.weatherImage);
        fab_getLoction = (FloatingActionButton) findViewById(R.id.fab_get_location);
        fab_getLoction.setOnClickListener(this);

        permissionList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(WeatherActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(WeatherActivity.this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(WeatherActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null){
            //有缓存时直接加载数据进行展示，不进行网络请求
            nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(weatherString);
            List<WeatherInfo> futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(weatherString);
            CityInfo cityInfo = ParseGsonUtil.handleCityInfoResponse(weatherString);
            suggestion = ParseGsonUtil.handleSuggestionResponse(weatherString);
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

        //AQI空气质量指数模块的点击事件
        aqiLinearLayout = (LinearLayout) findViewById(R.id.aqi_model);
        aqiLinearLayout.setOnClickListener(this);

        //生活建议的点击事件
        suggestion1 = (RelativeLayout) findViewById(R.id.suggestion_rl_1);
        suggestion1.setOnClickListener(this);
        suggestion2 = (RelativeLayout) findViewById(R.id.suggestion_rl_2);
        suggestion2.setOnClickListener(this);
        suggestion3 = (RelativeLayout) findViewById(R.id.suggestion_rl_3);
        suggestion3.setOnClickListener(this);
        suggestion4 = (RelativeLayout) findViewById(R.id.suggestion_rl_4);
        suggestion4.setOnClickListener(this);
        suggestion5 = (RelativeLayout) findViewById(R.id.suggestion_rl_5);
        suggestion5.setOnClickListener(this);
        suggestion6 = (RelativeLayout) findViewById(R.id.suggestion_rl_6);
        suggestion6.setOnClickListener(this);
        suggestion7 = (RelativeLayout) findViewById(R.id.suggestion_rl_7);
        suggestion7.setOnClickListener(this);
        suggestion8 = (RelativeLayout) findViewById(R.id.suggestion_rl_8);
        suggestion8.setOnClickListener(this);
    }

    /*
     * 根据城市名来查询天气信息
     * */
    public void requestWeather(String countyName) {
        String weatherURL = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getWeather/" + countyName;
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
                nowWeather = ParseGsonUtil.handleNowWeatherInfoResponse(responseText);
                futureWeatherInfoList = ParseGsonUtil.handleFutureWeatherInfoResponse(responseText);
                cityInfo = ParseGsonUtil.handleCityInfoResponse(responseText);
                suggestion = ParseGsonUtil.handleSuggestionResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (nowWeather != null && futureWeatherInfoList != null){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            mCityName = cityInfo.getC3();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nav_button:
                drawerLayout.openDrawer(GravityCompat.START);
                break;

            case R.id.my_button:
                MyApplication myApplication = (MyApplication) getApplication();
                int loginFlag = myApplication.getLoginFlag();
                if (loginFlag == 1){
                    Intent intent = new Intent(WeatherActivity.this, UserActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(WeatherActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;

            case R.id.settings_button:
                Toast.makeText(MyApplication.getContext(),"等待开发中......", Toast.LENGTH_SHORT).show();
                break;

            case R.id.fab_get_location:
                getCityByBaiDuLocation();
                Snackbar.make(view, "定位当前位置？", Snackbar.LENGTH_LONG)
                        .setAction("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog alertDialog = new AlertDialog.Builder(WeatherActivity.this)
                                        .setTitle("您所在的城市为：")
                                        .setMessage(cityNameByLocation)
                                        .setCancelable(true)
                                        .setPositiveButton("切换", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                requestWeather(cityNameByLocation);
                                                drawerLayout.closeDrawers();
                                            }
                                        })
                                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(MyApplication.getContext(),"取消切换", Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                            }
                        }).show();
                break;

            case R.id.suggestion_rl_1:
                Intent intent1 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent1.putExtra("suggestion_name", "comfort");
                intent1.putExtra("suggestion_title", suggestion.getComfort().getTitle());
                intent1.putExtra("suggestion_content", suggestion.getComfort().getDesc());
                intent1.putExtra("suggestion_image_id", R.drawable.shushidu_suggestion);
                startActivity(intent1);
                break;

            case R.id.suggestion_rl_2:
                Intent intent2 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent2.putExtra("suggestion_name", "dy");
                intent2.putExtra("suggestion_title", suggestion.getDy().getTitle());
                intent2.putExtra("suggestion_content", suggestion.getDy().getDesc());
                intent2.putExtra("suggestion_image_id", R.drawable.diaoyu_suggestion);
                startActivity(intent2);
                break;

            case R.id.suggestion_rl_3:
                Intent intent3 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent3.putExtra("suggestion_name", "wash_car");
                intent3.putExtra("suggestion_title", suggestion.getWash_car().getTitle());
                intent3.putExtra("suggestion_content", suggestion.getWash_car().getDesc());
                intent3.putExtra("suggestion_image_id", R.drawable.xiche_suggestion);
                startActivity(intent3);
                break;

            case R.id.suggestion_rl_4:
                Intent intent4 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent4.putExtra("suggestion_name", "gj");
                intent4.putExtra("suggestion_title", suggestion.getGj().getTitle());
                intent4.putExtra("suggestion_content", suggestion.getGj().getDesc());
                intent4.putExtra("suggestion_image_id", R.drawable.guangjie_suggestion);
                startActivity(intent4);
                break;

            case R.id.suggestion_rl_5:
                Intent intent5 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent5.putExtra("suggestion_name", "cold");
                intent5.putExtra("suggestion_title", suggestion.getCold().getTitle());
                intent5.putExtra("suggestion_content", suggestion.getCold().getDesc());
                intent5.putExtra("suggestion_image_id", R.drawable.chiyao_suggestion);
                startActivity(intent5);
                break;

            case R.id.suggestion_rl_6:
                Intent intent6 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent6.putExtra("suggestion_name", "clothes");
                intent6.putExtra("suggestion_title", suggestion.getClothes().getTitle());
                intent6.putExtra("suggestion_content", suggestion.getClothes().getDesc());
                intent6.putExtra("suggestion_image_id", R.drawable.chuanyi_suggestion);
                startActivity(intent6);
                break;

            case R.id.suggestion_rl_7:
                Intent intent7 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent7.putExtra("suggestion_name", "uv");
                intent7.putExtra("suggestion_title", suggestion.getUv().getTitle());
                intent7.putExtra("suggestion_content", suggestion.getUv().getDesc());
                intent7.putExtra("suggestion_image_id", R.drawable.ziwaixian_suggestion);
                startActivity(intent7);
                break;

            case R.id.suggestion_rl_8:
                Intent intent8 = new Intent(WeatherActivity.this, SuggestionActivity.class);
                intent8.putExtra("suggestion_name", "pj");
                intent8.putExtra("suggestion_title", suggestion.getPj().getTitle());
                intent8.putExtra("suggestion_content", suggestion.getPj().getDesc());
                intent8.putExtra("suggestion_image_id", R.drawable.pijiu_suggestion);
                startActivity(intent8);
                break;

            case R.id.aqi_model:
                Intent intent9 = new Intent(WeatherActivity.this, AQIActivity.class);
                intent9.putExtra("aqi_data", nowWeather);
                startActivity(intent9);
                break;

            default:
                break;
        }
    }

    /*
    * 通过百度的定位服务获取本机所在地
    * */
    public void getCityByBaiDuLocation(){
        if (!permissionList.isEmpty()){
            String[] permission = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(WeatherActivity.this, permission, 1);
        }else {
            requestLocation();
        }
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            cityNameByLocation = bdLocation.getDistrict();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(getBaseContext(), "必须同意所有权限后才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(getBaseContext(), "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }
}
