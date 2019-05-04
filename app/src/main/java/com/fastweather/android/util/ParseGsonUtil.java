package com.fastweather.android.util;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastweather.android.gson.CityInfo;
import com.fastweather.android.gson.NowWeather;
import com.fastweather.android.gson.WeatherInfo;
import com.fastweather.android.pojo.City;
import com.fastweather.android.pojo.County;
import com.fastweather.android.pojo.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13055 on 2019/4/29.
 */

public class ParseGsonUtil {

    /*
    * 解析处理服务器返回的省级数据
    * */
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            JSONArray allProvince = JSONArray.parseArray(response);
            for (int i=0; i<allProvince.size(); i++){
                JSONObject provinceObject = allProvince.getJSONObject(i);
                Province province = new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInteger("id"));
                province.save();
            }
            return true;
        }
        return false;
    }


    /*
    * 解析处理服务器返回的市级数据
    * */
    public static boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            JSONArray allCity = JSONArray.parseArray(response);
            for (int i=0; i<allCity.size(); i++){
                JSONObject cityObject = allCity.getJSONObject(i);
                City city = new City();
                city.setCityName(cityObject.getString("name"));
                city.setCityCode(cityObject.getInteger("id"));
                city.setProvinceId(provinceId);
                city.save();
            }
            return true;
        }
        return false;
    }


    /*
    * 解析处理服务器返回的县级数据
    * */
    public static boolean handleCountyResponse(String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            JSONArray allCounty = JSONArray.parseArray(response);
            for (int i=0; i<allCounty.size(); i++){
                JSONObject countyObject = allCounty.getJSONObject(i);
                County county = new County();
                county.setCountyName(countyObject.getString("name"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        }
        return false;
    }

    /*
    * 将返回的天气相关的json数据解析成NowWeather实体类
    * 运用的是阿里巴巴的FastJson
    * */
    public static NowWeather handleNowWeatherInfoResponse(String response){
        try {
            JSONArray allWeatherInfoArray = JSONArray.parseArray(response);
            JSONObject nowWeatherInfoObject = allWeatherInfoArray.getJSONObject(0);
            NowWeather nowWeather = JSON.parseObject(String.valueOf(nowWeatherInfoObject), NowWeather.class);
            return nowWeather;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 将返回的天气相关的Json数据解析为WeatherInfo实体类类型的集合
    * 使用的是阿里巴巴的FastJson
    * */
    public static List<WeatherInfo> handleFutureWeatherInfoResponse(String response){
        List<WeatherInfo> futureWeatherInfoList = new ArrayList<>();
        JSONArray allWeatherInfoArray = JSONArray.parseArray(response);
        for (int i=2; i<allWeatherInfoArray.size(); i++){
            futureWeatherInfoList.add(JSON.parseObject(String.valueOf(allWeatherInfoArray.getJSONObject(i)), WeatherInfo.class));
        }
        return futureWeatherInfoList;
    }

    /*
    * 将返回的天气信息json串中的cityInfo信息解析为CityInfo实体类类型
    * 使用的是阿里巴巴的FastJson
    * */
    public static CityInfo handleCityInfoResponse(String response){
        JSONArray allWeatherInfoArray = JSONArray.parseArray(response);
        JSONObject cityInfoObject = allWeatherInfoArray.getJSONObject(1);
        CityInfo cityInfo = JSON.parseObject(String.valueOf(cityInfoObject), CityInfo.class);
        return cityInfo;
    }
}
