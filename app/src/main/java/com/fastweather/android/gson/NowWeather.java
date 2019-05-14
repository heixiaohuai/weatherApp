package com.fastweather.android.gson;

import java.io.Serializable;

/**
 * 用于展示当前日的天气信息
 * Created by 13055 on 2019/4/29.
 */
public class NowWeather implements Serializable {
    /**
     * aqi : 43
     * aqiDetail : {"aqi":"43","area":"成都","co":"0.811","no2":"40","num":"149","o3":"51","o3_8h":"53","pm10":"45","pm2_5":"27","primary_pollutant":"","quality":"优质","so2":"5"}
     * sd : 90%
     * temperature : 20
     * temperature_time : 12:01
     * weather : 多云
     * weather_code : 01
     * weather_pic : http://app1.showapi.com/weather/icon/day/01.png
     * wind_direction : 静风
     * wind_power : 0级
     */

    private String aqi;
    private AqiDetailBean aqiDetail;
    private String sd;
    private String temperature;
    private String temperature_time;
    private String weather;
    private String weather_code;
    private String weather_pic;
    private String wind_direction;
    private String wind_power;

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public AqiDetailBean getAqiDetail() {
        return aqiDetail;
    }

    public void setAqiDetail(AqiDetailBean aqiDetail) {
        this.aqiDetail = aqiDetail;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature_time() {
        return temperature_time;
    }

    public void setTemperature_time(String temperature_time) {
        this.temperature_time = temperature_time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(String weather_code) {
        this.weather_code = weather_code;
    }

    public String getWeather_pic() {
        return weather_pic;
    }

    public void setWeather_pic(String weather_pic) {
        this.weather_pic = weather_pic;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_power() {
        return wind_power;
    }

    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }

    public static class AqiDetailBean implements Serializable {
        /**
         * aqi : 43
         * area : 成都
         * co : 0.811
         * no2 : 40
         * num : 149
         * o3 : 51
         * o3_8h : 53
         * pm10 : 45
         * pm2_5 : 27
         * primary_pollutant :
         * quality : 优质
         * so2 : 5
         */

        private String aqi;
        private String area;
        private String co;
        private String no2;
        private String num;
        private String o3;
        private String o3_8h;
        private String pm10;
        private String pm2_5;
        private String primary_pollutant;
        private String quality;
        private String so2;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO3_8h() {
            return o3_8h;
        }

        public void setO3_8h(String o3_8h) {
            this.o3_8h = o3_8h;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm2_5() {
            return pm2_5;
        }

        public void setPm2_5(String pm2_5) {
            this.pm2_5 = pm2_5;
        }

        public String getPrimary_pollutant() {
            return primary_pollutant;
        }

        public void setPrimary_pollutant(String primary_pollutant) {
            this.primary_pollutant = primary_pollutant;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }
    }


}
