package com.fastweather.android.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView settingUpdateWeatherTimeCardView;
    private CardView settingVersionUpdateCardView;
    private CardView settingRattingBarCardView;
    private CardView settingAboutTeamCardView;

    private TextView settingWeatherTimeTextView;
    private TextView settingVersionUpdateTextView;
    private TextView settingRattingBarTextView;

    private Button saveButton, backButton;

    private SharedPreferences pref, userPref;
    private SharedPreferences.Editor editor;

    private String startLevel;
    private String weatherTime;
    private String updateModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        userPref = getSharedPreferences("Users", MODE_PRIVATE);
        pref = getSharedPreferences("Setting", MODE_PRIVATE);
        editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
        settingUpdateWeatherTimeCardView = (CardView) findViewById(R.id.setting_update_weather_time_cardView);
        settingVersionUpdateCardView = (CardView) findViewById(R.id.setting_version_updating_cardView);
        settingRattingBarCardView = (CardView) findViewById(R.id.setting_ratting_bar_cardView);
        settingAboutTeamCardView = (CardView) findViewById(R.id.setting_about_team_cardView);

        saveButton = (Button) findViewById(R.id.save_setting);
        backButton = (Button) findViewById(R.id.setting_back_button);

        settingWeatherTimeTextView = (TextView) findViewById(R.id.setting_weather_time);
        settingVersionUpdateTextView = (TextView) findViewById(R.id.setting_version_update);
        settingRattingBarTextView = (TextView) findViewById(R.id.setting_ratting_bar);

        settingWeatherTimeTextView.setText(pref.getString("weatherTime",""));
        settingVersionUpdateTextView.setText(pref.getString("versionUpdate",""));
        settingRattingBarTextView.setText(pref.getString("startLevel",""));

        settingUpdateWeatherTimeCardView.setOnClickListener(this);
        settingVersionUpdateCardView.setOnClickListener(this);
        settingRattingBarCardView.setOnClickListener(this);
        settingAboutTeamCardView.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_update_weather_time_cardView:
                View chooseWeatherTimeLayout;
                LayoutInflater inflater;
                inflater = LayoutInflater.from(SettingActivity.this);
                chooseWeatherTimeLayout = (LinearLayout) inflater.inflate(R.layout.choose_weather_time_dialog, null);
                RadioGroup radioGroup = (RadioGroup) chooseWeatherTimeLayout.findViewById(R.id.choose_weather_time_model);
                final RadioButton radioButton1 = (RadioButton) chooseWeatherTimeLayout.findViewById(R.id.hour1);
                final RadioButton radioButton2 = (RadioButton) chooseWeatherTimeLayout.findViewById(R.id.hour2);
                final RadioButton radioButton3 = (RadioButton) chooseWeatherTimeLayout.findViewById(R.id.hour3);
                final RadioButton radioButton4 = (RadioButton) chooseWeatherTimeLayout.findViewById(R.id.hour4);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.hour1:
                                settingWeatherTimeTextView.setText("1小时");
                                weatherTime = "1小时";
                                break;

                            case R.id.hour2:
                                settingWeatherTimeTextView.setText("2小时");
                                weatherTime = "2小时";
                                break;

                            case R.id.hour3:
                                settingWeatherTimeTextView.setText("3小时");
                                weatherTime = "3小时";
                                break;

                            case R.id.hour4:
                                settingWeatherTimeTextView.setText("8小时");
                                weatherTime = "8小时";
                                break;

                            default:break;
                        }
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请选择：");
                builder.setView(chooseWeatherTimeLayout);
                builder.setCancelable(true);
                builder.create().show();
                break;
            case R.id.setting_version_updating_cardView:
                View chooseUpdateModelLayout;
                LayoutInflater inflater1;
                inflater1 = LayoutInflater.from(SettingActivity.this);
                chooseUpdateModelLayout = (LinearLayout) inflater1.inflate(R.layout.choose_update_version_model, null);
                RadioGroup radioGroup1 = (RadioGroup) chooseUpdateModelLayout.findViewById(R.id.choose_update_model);
                final RadioButton autoModel = (RadioButton) findViewById(R.id.model_auto);
                final RadioButton neverModel = (RadioButton) findViewById(R.id.model_never);
                radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.model_auto:
                                settingVersionUpdateTextView.setText("自动更新");
                                updateModel = "自动更新";
                                break;
                            case R.id.model_never:
                                settingVersionUpdateTextView.setText("从不更新");
                                updateModel = "从不更新";
                                break;
                        }
                    }
                });
                builder = new AlertDialog.Builder(this);
                builder.setTitle("请选择：");
                builder.setView(chooseUpdateModelLayout);
                builder.setCancelable(true);
                builder.create().show();
                break;
            case R.id.setting_ratting_bar_cardView:
                if (((MyApplication)getApplication()).getLoginFlag() == 0){
                    Toast.makeText(SettingActivity.this,"你尚未登录，无法进行评分", Toast.LENGTH_SHORT).show();
                }else {
                    View rattingBarLayout;
                    LayoutInflater inflater2;
                    inflater2 = LayoutInflater.from(SettingActivity.this);
                    rattingBarLayout = (LinearLayout) inflater2.inflate(R.layout.ratting_bar, null);
                    final RatingBar ratingBar = (RatingBar) rattingBarLayout.findViewById(R.id.ratting_bar);
                    ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                            startLevel = String.valueOf(v);
                            settingRattingBarTextView.setText(startLevel);
                            Toast.makeText(SettingActivity.this,"你的评分是"+String.valueOf(v)+"分", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder = new AlertDialog.Builder(this);
                    builder.setTitle("请评分:");
                    builder.setView(rattingBarLayout);
                    builder.setCancelable(true);
                    builder.create().show();
                }
                break;

            case R.id.setting_about_team_cardView:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("关于我们：");
                builder.setMessage("成都信息工程大学2栋3003工作室   邮箱：1305558920@qq.com");
                builder.setCancelable(true);
                builder.create().show();
                break;

            case R.id.save_setting:
                final String address = "http://"+((MyApplication)getApplication()).getOkHttpURL() + "/Android/updateUserInfo/"+userPref.getString("phone","")+"/"+startLevel;
                builder = new AlertDialog.Builder(this);
                builder.setTitle("保存？");
                builder.setMessage("保存修改？");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(SettingActivity.this,"服务器错误", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String responseInfo = response.body().string();
                                if (responseInfo.equals("update success")){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SettingActivity.this,"评分成功", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SettingActivity.this,"评分失败", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                        editor.putString("weatherTime", weatherTime);
                        editor.putString("versionUpdate", updateModel);
                        editor.putString("startLevel", startLevel);
                        editor.commit();
                        Intent intent = new Intent(SettingActivity.this, WeatherActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                break;

            case R.id.setting_back_button:
                finish();
                break;

            default:break;
        }
    }
}
