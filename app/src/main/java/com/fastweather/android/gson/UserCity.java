package com.fastweather.android.gson;

/**
 * Created by 13055 on 2019/5/16.
 */

public class UserCity {

    /**
     * id : 5
     * cityName : 武汉
     * userPhone : 123
     */

    private int id;
    private String cityName;
    private String userPhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
