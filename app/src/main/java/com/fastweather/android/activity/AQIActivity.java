package com.fastweather.android.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fastweather.android.R;
import com.fastweather.android.gson.NowWeather;

public class AQIActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ActionBar actionBar;
    private ImageView aqiImage;
    private TextView aqiQualityTextView;
    private TextView aqiAqiTextView;
    private TextView aqiNumTextView;
    private TextView aqiCoTextView;
    private TextView aqiN02TextView;
    private TextView aqiO3TextView;
    private TextView aqiPM10TextView;
    private TextView aqiPM25TextView;
    private TextView aqiSo2TextView;

    private NowWeather nowWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aqi);
        Intent intent = getIntent();
        nowWeather = (NowWeather) intent.getSerializableExtra("aqi_data");
        toolbar = (Toolbar) findViewById(R.id.aqi_toolBar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.aqi_collapsing_toolbar);
        aqiImage = (ImageView) findViewById(R.id.aqi_image);
        aqiQualityTextView = (TextView) findViewById(R.id.aqi_quality);
        aqiAqiTextView = (TextView) findViewById(R.id.aqi_aqi);
        aqiNumTextView = (TextView) findViewById(R.id.aqi_num);
        aqiCoTextView = (TextView) findViewById(R.id.aqi_co);
        aqiN02TextView = (TextView) findViewById(R.id.aqi_no2);
        aqiO3TextView = (TextView) findViewById(R.id.aqi_o3);
        aqiPM10TextView = (TextView) findViewById(R.id.aqi_pm10);
        aqiPM25TextView = (TextView) findViewById(R.id.aqi_pm25);
        aqiSo2TextView = (TextView) findViewById(R.id.aqi_so2);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(nowWeather.getAqiDetail().getArea());
        Glide.with(this).load(R.drawable.aqi_image).into(aqiImage);
        aqiQualityTextView.setText(nowWeather.getAqiDetail().getQuality());
        aqiAqiTextView.setText(nowWeather.getAqiDetail().getAqi());
        aqiNumTextView.setText(nowWeather.getAqiDetail().getNum());
        aqiCoTextView.setText(nowWeather.getAqiDetail().getCo());
        aqiN02TextView.setText(nowWeather.getAqiDetail().getNo2());
        aqiO3TextView.setText(nowWeather.getAqiDetail().getO3());
        aqiPM10TextView.setText(nowWeather.getAqiDetail().getPm10());
        aqiPM25TextView.setText(nowWeather.getAqiDetail().getPm2_5());
        aqiSo2TextView.setText(nowWeather.getAqiDetail().getSo2());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
