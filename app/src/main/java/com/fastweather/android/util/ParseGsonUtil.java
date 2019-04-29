package com.fastweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastweather.android.pojo.City;
import com.fastweather.android.pojo.County;
import com.fastweather.android.pojo.Province;

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
}
