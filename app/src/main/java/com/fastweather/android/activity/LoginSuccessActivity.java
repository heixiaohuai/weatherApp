package com.fastweather.android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.pojo.User;

public class LoginSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        TextView u = (TextView) findViewById(R.id.userInfo);
        User user = (User) getIntent().getSerializableExtra("loginUser_data");
        StringBuilder userInfo = new StringBuilder();
        userInfo.append("id：").append(user.getId()).append("\n");
        userInfo.append("phone：").append(user.getPhone()).append("\n");
        userInfo.append("username：").append(user.getUsername()).append("\n");
        userInfo.append("password：").append(user.getPassword()).append("\n");
        userInfo.append("sex：").append(user.getSex()).append("\n");
        userInfo.append("birthday：").append(user.getBirthdate()).append("\n");
        userInfo.append("description：").append(user.getDescription()).append("\n");
        userInfo.append("headportrait：").append(user.getHeadportrait()).append("\n");
        u.setText(userInfo);
    }
}