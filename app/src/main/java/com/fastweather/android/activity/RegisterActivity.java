package com.fastweather.android.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView userPhoneTextView;
    private TextView firstPasswordTextView;
    private TextView secondPasswordTextView;
    private TextView registerInfoTextView;
    private TextView userNameTextView;
    private Button registerButton;
    private ProgressDialog dialog;

    private String phone;
    private String userName;
    private String firstPassword;
    private String secondPassword;
    private String registerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userPhoneTextView = (TextView) findViewById(R.id.userPhone);
        userNameTextView = (TextView) findViewById(R.id.userName);
        firstPasswordTextView = (TextView) findViewById(R.id.firstPassword);
        secondPasswordTextView = (TextView) findViewById(R.id.secondPassword);
        registerInfoTextView = (TextView) findViewById(R.id.registerInfo);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerButton:
                phone = userPhoneTextView.getText().toString();
                if (phone.length() != 11 || phone == null || phone.equals("")){
                    registerInfo = "The phone number format is not correct";
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            registerInfoTextView.setText(registerInfo);
                        }
                    });
                }else {
                    userName = userNameTextView.getText().toString();
                    firstPassword = firstPasswordTextView.getText().toString();
                    secondPassword = secondPasswordTextView.getText().toString();
                    if (firstPassword!=null && !firstPassword.equals("") && secondPassword!=null && !secondPassword.equals("")){
                        if (firstPassword.equals(secondPassword)){
                            if (userName == null || userName.equals("")){
                                registerInfo = "The user name cannot be empty";
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        registerInfoTextView.setText(registerInfo);
                                    }
                                });
                            }else {
                                String address = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/register/" + phone + "/" + userName + "/" + firstPassword;
                                Log.d("address", address);
                                dialog = new ProgressDialog(RegisterActivity.this);
                                dialog.setTitle("注册中");
                                dialog.setMessage("请稍等。。。");
                                dialog.setCancelable(false);
                                dialog.show();
                                HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        dialog.dismiss();
                                        Toast.makeText(RegisterActivity.this,"注册失败", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        registerInfo = response.body().string();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if ("register success".equals(registerInfo)){
                                                    dialog.dismiss();
                                                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                }else {
                                                    dialog.dismiss();
                                                    Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                                                    registerInfoTextView.setText(registerInfo);
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
                            registerInfo = "Entered passwords differ";
                            registerInfoTextView.setText(registerInfo);
                        }
                    }else {
                        registerInfo = "The password cannot be empty";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                registerInfoTextView.setText(registerInfo);
                            }
                        });
                    }
                }
                break;
            default:
                break;
        }
    }
}
