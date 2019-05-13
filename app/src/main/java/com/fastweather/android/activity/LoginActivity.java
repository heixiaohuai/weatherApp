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
    private SharedPreferences.Editor editor;

    private String loginInfo;//服务器返回的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("Users", MODE_PRIVATE);

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
                                    editor = pref.edit();
                                    if (rememberPhone.isChecked()){
                                        editor.putString("phone", userPhoneTextView.getText().toString());
                                        editor.putString("password", passwordTextView.getText().toString());
                                        editor.putBoolean("remember_password", true);
                                    }else {
                                        editor.clear();
                                    }
                                    editor.commit();
                                    Toast.makeText(LoginActivity.this,"登陆成功", Toast.LENGTH_LONG).show();
                                    //登陆成功后将用户登陆的手机号保存在全局的application中，后续会使用。
                                    MyApplication myApplication = new MyApplication();
                                    myApplication.setLoginPhone(userPhoneTextView.getText().toString());
                                    //登陆成功后根据手机号码查询到用户信息，并且将用户信息传递到下一个Activity
                                    String address1 = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getUserInfo/" + myApplication.getLoginPhone();
                                    HttpUtil.sendOkHttpRequestByGet(address1, new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Toast.makeText(LoginActivity.this, "服务器内部错误", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            String responseUserInfo = response.body().string();
                                            User loginUser = JSON.parseObject(responseUserInfo, User.class);
                                            Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                                            intent.putExtra("loginUser_data", loginUser);
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
