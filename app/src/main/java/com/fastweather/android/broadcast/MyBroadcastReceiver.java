package com.fastweather.android.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.fastweather.android.activity.WeatherActivity;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
//        Toast.makeText(context, "hahahaha", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, WeatherActivity.class);
        Toast.makeText(context, i.getStringExtra("cityName"), Toast.LENGTH_SHORT).show();
        intent.putExtra("cityNameForBroad",i.getStringExtra("cityName"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
