package com.fastweather.android.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.pojo.User;
import com.fastweather.android.util.HttpUtil;

public class UserActivity extends AppCompatActivity {

    private SharedPreferences pref, sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.my_nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        TextView nickName = headerLayout.findViewById(R.id.my_user_name);
        TextView description = headerLayout.findViewById(R.id.my_user_description);
        pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);
        nickName.setText(pref.getString("username",""));
        description.setText(pref.getString("description",""));
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.my_information:
                        Intent intent = new Intent(UserActivity.this, UserInformationActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.my_cityList:
                        Intent intent1 = new Intent(UserActivity.this, UserCityListActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.my_login_out:
                        sharedPreferences.edit().clear().commit();
                        ((MyApplication)getApplication()).setLoginFlag(0);
                        Intent intent2 = new Intent(UserActivity.this, LoginActivity.class);
                        startActivity(intent2);
                        finish();
                        break;

                    default:break;
                }
                return true;
            }
        });
    }
}
