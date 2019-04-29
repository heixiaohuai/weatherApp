package com.fastweather.android.pojo;

import org.litepal.crud.DataSupport;

/**
 * Created by 13055 on 2019/4/29.
 */

public class County extends DataSupport {
    private int id;
    private String countyName;
    private int cityId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
