package com.fastweather.android.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fastweather.android.R;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.util.HttpUtil;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences pref, userPref;

    private TextView userInfoUserNameTextView;
    private TextView userInfoUserSexTextView;
    private TextView userInfoUserDescriptionTextView;
    private TextView userInfoUserBirthdayTextView;

    private CardView userInfoUserNameCardView;
    private CardView userInfoUserSexCardView;
    private CardView userInfoUserDescriptionCardView;
    private CardView userInfoUserBirthdayCardView;

    private Button userInfoSaveUserInfoButton;
    private Button userInfoBackButton;
    private Button userInfoSaveUserInfoTitleButton;

    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        pref = getSharedPreferences("UserInfo", MODE_PRIVATE);
        userPref = getSharedPreferences("Users", MODE_PRIVATE);

        userInfoUserNameTextView = (TextView) findViewById(R.id.user_info_user_name);
        userInfoUserSexTextView = (TextView) findViewById(R.id.user_info_user_sex);
        userInfoUserDescriptionTextView = (TextView) findViewById(R.id.user_info_user_description);
        userInfoUserBirthdayTextView = (TextView) findViewById(R.id.user_info_user_birthday);
        userInfoUserNameCardView = (CardView) findViewById(R.id.user_info_user_name_cardView);
        userInfoUserSexCardView = (CardView) findViewById(R.id.user_info_user_sex_cardView);
        userInfoUserDescriptionCardView = (CardView) findViewById(R.id.user_info_user_description_cardView);
        userInfoUserBirthdayCardView = (CardView) findViewById(R.id.user_info_user_birthday_cardView);
        userInfoSaveUserInfoButton = (Button) findViewById(R.id.user_info_save_info);
        userInfoBackButton = (Button) findViewById(R.id.user_info_back_button);
        userInfoSaveUserInfoTitleButton = (Button) findViewById(R.id.user_info_save);

        userInfoUserNameTextView.setText(pref.getString("username",""));
        userInfoUserSexTextView.setText(pref.getString("sex",""));
        userInfoUserDescriptionTextView.setText(pref.getString("description",""));
        userInfoUserBirthdayTextView.setText(pref.getString("birthday",""));

        userInfoUserNameCardView.setOnClickListener(this);
        userInfoUserSexCardView.setOnClickListener(this);
        userInfoUserDescriptionCardView.setOnClickListener(this);
        userInfoUserBirthdayCardView.setOnClickListener(this);
        userInfoSaveUserInfoButton.setOnClickListener(this);
        userInfoBackButton.setOnClickListener(this);
        userInfoSaveUserInfoTitleButton.setOnClickListener(this);

        getDate();//获取时间
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_info_user_name_cardView:
                final EditText edit = new EditText(this);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请输入你的昵称");
                builder.setView(edit);
                builder.setCancelable(true);
                builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userInfoUserNameTextView.setText(edit.getText().toString());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                break;

            case R.id.user_info_user_sex_cardView:
                View chooseSexLayout;
                LayoutInflater inflater;
                inflater = LayoutInflater.from(UserInformationActivity.this);
                chooseSexLayout = (LinearLayout) inflater.inflate(R.layout.choose_sex_dialog, null);
                RadioGroup radioGroup = (RadioGroup) chooseSexLayout.findViewById(R.id.choose_sex_radio_group);
                final RadioButton manRadioButton = (RadioButton) chooseSexLayout.findViewById(R.id.choose_sex_man);
                final RadioButton womanRadioButton = (RadioButton) chooseSexLayout.findViewById(R.id.choose_sex_woman);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i){
                            case R.id.choose_sex_man: userInfoUserSexTextView.setText("男");break;
                            case R.id.choose_sex_woman: userInfoUserSexTextView.setText("女");break;
                        }
                    }
                });
                builder = new AlertDialog.Builder(this);
                builder.setTitle("选择你的性别：");
                builder.setView(chooseSexLayout);
                builder.setCancelable(true);
                builder.create().show();
                break;

            case R.id.user_info_user_description_cardView:
                Intent intent = new Intent(UserInformationActivity.this, EditUserDescriptionActivity.class);
                startActivityForResult(intent, 1);
                break;

            case R.id.user_info_user_birthday_cardView:
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        userInfoUserBirthdayTextView.setText(i+"年"+(i1+1)+"月"+i2+"日");//将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(UserInformationActivity.this, 0, listener, year, month, day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
                break;

            case R.id.user_info_back_button:
                finish();
                break;

            case R.id.user_info_save_info:
            case R.id.user_info_save:
                final String username = userInfoUserNameTextView.getText().toString();
                final String sex = userInfoUserSexTextView.getText().toString();
                String des = userInfoUserDescriptionTextView.getText().toString();
                final String description;
                if (des == null || des.equals("")){
                    description = "这个人很懒，什么都没留下";
                }else {
                    description = des;
                }
                final String birthday = userInfoUserBirthdayTextView.getText().toString();
                String phone = userPref.getString("phone","");
                String address = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/updateUserInfo/" + phone + "/" + username + "/" + sex + "/" + description + "/" + birthday;
                Log.d("address", address);
                HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(UserInformationActivity.this,"服务器错误",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseInfo = response.body().string();
                        if ("update success".equals(responseInfo)){
                            SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
                            editor.putString("description", description);
                            editor.putString("username", username);
                            editor.putString("sex", sex);
                            editor.putString("birthday", birthday);
                            editor.apply();
                            Intent intent1 = new Intent(UserInformationActivity.this, UserActivity.class);
                            startActivity(intent1);
                            finish();
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(UserInformationActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    userInfoUserDescriptionTextView.setText(data.getStringExtra("user_description"));
                }
                break;
            default:break;
        }
    }

    private void getDate(){
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
}
