package com.fastweather.android.adapter;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fastweather.android.R;
import com.fastweather.android.activity.UserCityListActivity;
import com.fastweather.android.gson.UserCity;
import com.fastweather.android.util.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 13055 on 2019/5/16.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{
    private List<UserCity> mUserCity;
    private String mPhone;
    private String mIp;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View cityView;
        TextView cityName;
        public ViewHolder(View view){
            super(view);
            cityView = view;
            cityName = (TextView) view.findViewById(R.id.user_city_name);
        }
    }

    public CityAdapter(List<UserCity> cityList, String phone, String ip){
        mUserCity = cityList;
        mPhone = phone;
        mIp = ip;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                UserCity city = mUserCity.get(position);
                Intent broadcastIntent = new Intent("com.fastweather.android.MY_LOCAL_BROADCAST");
                broadcastIntent.setComponent(new ComponentName("com.fastweather.android", "com.fastweather.android.broadcast.MyBroadcastReceiver"));
                broadcastIntent.putExtra("cityName", city.getCityName());
                view.getContext().sendBroadcast(broadcastIntent);
            }
        });
        holder.cityView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                int position = holder.getAdapterPosition();
                final UserCity city = mUserCity.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("删除");
                builder.setMessage("删除"+city.getCityName()+"？");
                builder.setCancelable(false);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, final int i) {
                        String address = "http://"+mIp+"/Android/deleteUserCity/" + mPhone + "/" + city.getCityName();
                        HttpUtil.sendOkHttpRequestByGet(address, new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Toast.makeText(view.getContext(),"服务器错误", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String responseInfo = response.body().string();
                                if (responseInfo.equals("Delete city success")){
                                    Intent intent = new Intent(view.getContext(), UserCityListActivity.class);
                                    view.getContext().startActivity(intent);
                                }else {
                                    Toast.makeText(view.getContext(),"删除失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
                return true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserCity city = mUserCity.get(position);
        holder.cityName.setText(city.getCityName());
    }

    @Override
    public int getItemCount() {
        return mUserCity.size();
    }
}
