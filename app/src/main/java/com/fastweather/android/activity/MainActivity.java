package com.fastweather.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences userPref;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        userPref = getSharedPreferences("Users", MODE_PRIVATE);
        MyApplication myApplication = (MyApplication) getApplication();
        if (userPref.getString("phone", null) != null){
            myApplication.setLoginFlag(1);
        }else {
            myApplication.setLoginFlag(0);
        }
        if (prefs.getString("weather", null) != null){
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
