package com.fastweather.android.activity;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fastweather.android.R;
import com.fastweather.android.adapter.CityAdapter;
import com.fastweather.android.gson.UserCity;
import com.fastweather.android.myApplication.MyApplication;
import com.fastweather.android.util.HttpUtil;
import com.fastweather.android.util.ParseGsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserCityListActivity extends AppCompatActivity {

    private List<UserCity> userCityList = new ArrayList<>();
    private SharedPreferences pref;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Bundle data = msg.getData();
                    String cityListInfo = data.getString("responseInfo");
                    Log.d("cityInfo", cityListInfo);
                    userCityList = ParseGsonUtil.handleUserCityResponse(cityListInfo);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
                    recyclerView.setLayoutManager(layoutManager);
                    CityAdapter cityAdapter = new CityAdapter(userCityList, pref.getString("phone",""),((MyApplication)getApplication()).getOkHttpURL());
                    recyclerView.setAdapter(cityAdapter);
                    break;
                default:break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_city_list);
        pref = getSharedPreferences("Users", MODE_PRIVATE);
        Button back = (Button) findViewById(R.id.user_city_list_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String address = "http://" + ((MyApplication) getApplication()).getOkHttpURL() + "/Android/getUserCityList/" + pref.getString("phone","");
        HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseInfo = response.body().string();
                Log.d("response info", responseInfo);
                Bundle bundle = new Bundle();
                bundle.putString("responseInfo",responseInfo);
                Message message = new Message();
                message.what = 1;
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }
}
