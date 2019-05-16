package com.fastweather.android.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.pojo.User;
import com.fastweather.android.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userPhoneTextView;
    private TextView passwordTextView;
    private Button loginButton;
    private Button registerButton;
    private ProgressDialog dialog;
    private CheckBox rememberPhone;

    private SharedPreferences pref;
    private SharedPreferences userInfoPref;
    private SharedPreferences.Editor userEditor;
    private SharedPreferences.Editor userInfoEditor;

    private String loginInfo;//服务器返回的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("Users", MODE_PRIVATE);
        userInfoPref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        userPhoneTextView = (TextView) findViewById(R.id.userPhone);
        passwordTextView = (TextView) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        rememberPhone = (CheckBox) findViewById(R.id.remember_phone);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember){
            String phone = pref.getString("phone", "");
            String password = pref.getString("password", "");
            userPhoneTextView.setText(phone);
            passwordTextView.setText(password);
            rememberPhone.setChecked(true);
        }
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                String phone = userPhoneTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                final String address = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/login/" + phone + "/" + password;
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("正在登录");
                dialog.setMessage("清稍后");
                dialog.setCancelable(false);
                dialog.show();
                HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this,"登陆失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        loginInfo = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if ("login success".equals(loginInfo)){
                                    dialog.dismiss();
                                    userEditor = pref.edit();
                                    if (rememberPhone.isChecked()){
                                        userEditor.putString("phone", userPhoneTextView.getText().toString());
                                        userEditor.putString("password", passwordTextView.getText().toString());
                                        userEditor.putBoolean("remember_password", true);
                                    }else {
                                        userEditor.clear();
                                    }
                                    userEditor.commit();
                                    Toast.makeText(LoginActivity.this,"登陆成功", Toast.LENGTH_LONG).show();
                                    ((MyApplication) getApplication()).setLoginFlag(1);//登录成功后将登陆标志设置为1
                                    //登陆成功后根据手机号码查询到用户信息，并且将用户信息传递到下一个Activity
                                    String address1 = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getUserInfo/" + userPhoneTextView.getText().toString();
                                    HttpUtil.sendOkHttpRequestByGet(address1, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Toast.makeText(LoginActivity.this, "服务器内部错误", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String responseUserInfo = response.body().string();
                                            User loginUser = JSON.parseObject(responseUserInfo, User.class);
                                            userInfoEditor = userInfoPref.edit();
                                            userInfoEditor.putString("phone", loginUser.getPhone());
                                            userInfoEditor.putString("password", loginUser.getPassword());
                                            userInfoEditor.putString("username", loginUser.getUsername());
                                            if (loginUser.getDescription()==null || loginUser.getDescription().equals("")){
                                                userInfoEditor.putString("description", "这个人很懒，什么都没留下");
                                            }else {
                                                userInfoEditor.putString("description", loginUser.getDescription());
                                            }

                                            if (loginUser.getSex()==null || loginUser.getSex().equals("")){
                                                userInfoEditor.putString("sex", "未设置");
                                            }else {
                                                userInfoEditor.putString("sex", loginUser.getSex());
                                            }

                                            if (loginUser.getBirthdate()==null || loginUser.getBirthdate().equals("")){
                                                userInfoEditor.putString("birthday", "未设置");
                                            }else {
                                                userInfoEditor.putString("birthday", loginUser.getBirthdate());
                                            }
                                            userInfoEditor.apply();
                                            Intent intent = new Intent(LoginActivity.this, WeatherActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }else {
                                    dialog.dismiss();
                                    Toast.makeText(LoginActivity.this,"登陆失败", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.registerButton:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
