package com.fastweather.android.pojo;

import java.io.Serializable;

/**
 * Created by 13055 on 2019/5/6.
 */

public class User implements Serializable {
    private int id;
    private String phone;//用户手机号码（唯一）
    private String username;//用户的昵称（唯一）
    private String headportrait;//用户头像
    private String sex;
    private String birthdate;//出生年月日
    private String description;//个性签名
    private String password;//密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
