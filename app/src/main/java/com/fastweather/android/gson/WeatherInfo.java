package com.fastweather.android.gson;

/**
 * 展示某一天的天气详情信息，比如紫外线强度，以及一些建议，比如洗车建议，出行建议等
 * Created by 13055 on 2019/4/30.
 */

public class WeatherInfo {

    /**
     * air_press : 955 hPa
     * day : 20190430
     * day_air_temperature : 27
     * day_weather : 晴
     * day_weather_code : 00
     * day_weather_pic : http://app1.showapi.com/weather/icon/day/00.png
     * day_wind_direction : 无持续风向
     * day_wind_power : 0-3级 <5.4m/s
     * index : {"ac":{"desc":"您将感到很舒适，一般不需要开启空调。","title":"较少开启"},"ag":{"desc":"天气条件极易诱发过敏，易过敏人群尽量减少外出，外出宜穿长衣长裤并佩戴好眼镜和口罩，外出归来时及时清洁手和口鼻。","title":"极易发"},"aqi":{"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"},"beauty":{"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"},"cl":{"desc":"请减少运动时间并降低运动强度。","title":"较适宜"},"clothes":{"desc":"建议穿长袖衬衫单裤等服装","title":"舒适"},"cold":{"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"},"comfort":{"desc":"偏热或较热，部分人感觉不舒适","title":"较差"},"dy":{"desc":"天气稍热会对垂钓产生一定影响。","title":"较适宜"},"gj":{"desc":"这种好天气去逛街可使身心畅快放松。","title":"适宜"},"glass":{"desc":"必要佩戴","title":"必要"},"hc":{"desc":"天气较好，适宜划船及嬉玩水上运动。","title":"适宜"},"ls":{"desc":"天气不错，抓紧时机让衣物晒太阳吧。","title":"较适宜"},"mf":{"desc":"温湿适宜，风力较小，这为您的头发创造一个健康、洁净的生长环境，加上您细心的呵护打理，您的秀发定能飘逸动人。","title":"极适宜"},"nl":{"desc":"天气较好，虽然有点风，但仍比较适宜夜生活，您可以放心外出。","title":"较适宜"},"pj":{"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"},"pk":{"desc":"气温略高，放风筝时戴上太阳帽。","title":"较适宜"},"sports":{"desc":"请减少运动时间并降低运动强度。","title":"较适宜"},"travel":{"desc":"天热注意防晒，可选择水上娱乐项目。","title":"较适宜"},"uv":{"desc":"涂擦SPF大于15、PA+防晒护肤品","title":"强"},"wash_car":{"desc":"无雨且风力较小，易保持清洁度","title":"较适宜"},"xq":{"desc":"温度适宜，心情会不错。","title":"好"},"yh":{"desc":"天气较好，适宜约会","title":"较适宜"},"zs":{"desc":"气温不高，中暑几率极低。","title":"不容易中暑"}}
     * jiangshui : 0%
     * night_air_temperature : 17
     * night_weather : 晴
     * night_weather_code : 00
     * night_weather_pic : http://app1.showapi.com/weather/icon/night/00.png
     * night_wind_direction : 无持续风向
     * night_wind_power : 0-3级 <5.4m/s
     * sun_begin_end : 06:21|19:41
     * weekday : 2
     * ziwaixian : 强
     */

    private String air_press;
    private String day;
    private String day_air_temperature;
    private String day_weather;
    private String day_weather_code;
    private String day_weather_pic;
    private String day_wind_direction;
    private String day_wind_power;
    private IndexBean index;
    private String jiangshui;
    private String night_air_temperature;
    private String night_weather;
    private String night_weather_code;
    private String night_weather_pic;
    private String night_wind_direction;
    private String night_wind_power;
    private String sun_begin_end;
    private int weekday;
    private String ziwaixian;

    public String getAir_press() {
        return air_press;
    }

    public void setAir_press(String air_press) {
        this.air_press = air_press;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay_air_temperature() {
        return day_air_temperature;
    }

    public void setDay_air_temperature(String day_air_temperature) {
        this.day_air_temperature = day_air_temperature;
    }

    public String getDay_weather() {
        return day_weather;
    }

    public void setDay_weather(String day_weather) {
        this.day_weather = day_weather;
    }

    public String getDay_weather_code() {
        return day_weather_code;
    }

    public void setDay_weather_code(String day_weather_code) {
        this.day_weather_code = day_weather_code;
    }

    public String getDay_weather_pic() {
        return day_weather_pic;
    }

    public void setDay_weather_pic(String day_weather_pic) {
        this.day_weather_pic = day_weather_pic;
    }

    public String getDay_wind_direction() {
        return day_wind_direction;
    }

    public void setDay_wind_direction(String day_wind_direction) {
        this.day_wind_direction = day_wind_direction;
    }

    public String getDay_wind_power() {
        return day_wind_power;
    }

    public void setDay_wind_power(String day_wind_power) {
        this.day_wind_power = day_wind_power;
    }

    public IndexBean getIndex() {
        return index;
    }

    public void setIndex(IndexBean index) {
        this.index = index;
    }

    public String getJiangshui() {
        return jiangshui;
    }

    public void setJiangshui(String jiangshui) {
        this.jiangshui = jiangshui;
    }

    public String getNight_air_temperature() {
        return night_air_temperature;
    }

    public void setNight_air_temperature(String night_air_temperature) {
        this.night_air_temperature = night_air_temperature;
    }

    public String getNight_weather() {
        return night_weather;
    }

    public void setNight_weather(String night_weather) {
        this.night_weather = night_weather;
    }

    public String getNight_weather_code() {
        return night_weather_code;
    }

    public void setNight_weather_code(String night_weather_code) {
        this.night_weather_code = night_weather_code;
    }

    public String getNight_weather_pic() {
        return night_weather_pic;
    }

    public void setNight_weather_pic(String night_weather_pic) {
        this.night_weather_pic = night_weather_pic;
    }

    public String getNight_wind_direction() {
        return night_wind_direction;
    }

    public void setNight_wind_direction(String night_wind_direction) {
        this.night_wind_direction = night_wind_direction;
    }

    public String getNight_wind_power() {
        return night_wind_power;
    }

    public void setNight_wind_power(String night_wind_power) {
        this.night_wind_power = night_wind_power;
    }

    public String getSun_begin_end() {
        return sun_begin_end;
    }

    public void setSun_begin_end(String sun_begin_end) {
        this.sun_begin_end = sun_begin_end;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getZiwaixian() {
        return ziwaixian;
    }

    public void setZiwaixian(String ziwaixian) {
        this.ziwaixian = ziwaixian;
    }

    public static class IndexBean {
        /**
         * ac : {"desc":"您将感到很舒适，一般不需要开启空调。","title":"较少开启"}
         * ag : {"desc":"天气条件极易诱发过敏，易过敏人群尽量减少外出，外出宜穿长衣长裤并佩戴好眼镜和口罩，外出归来时及时清洁手和口鼻。","title":"极易发"}
         * aqi : {"desc":"空气很好，可以外出活动，呼吸新鲜空气","title":"优质"}
         * beauty : {"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"}
         * cl : {"desc":"请减少运动时间并降低运动强度。","title":"较适宜"}
         * clothes : {"desc":"建议穿长袖衬衫单裤等服装","title":"舒适"}
         * cold : {"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}
         * comfort : {"desc":"偏热或较热，部分人感觉不舒适","title":"较差"}
         * dy : {"desc":"天气稍热会对垂钓产生一定影响。","title":"较适宜"}
         * gj : {"desc":"这种好天气去逛街可使身心畅快放松。","title":"适宜"}
         * glass : {"desc":"必要佩戴","title":"必要"}
         * hc : {"desc":"天气较好，适宜划船及嬉玩水上运动。","title":"适宜"}
         * ls : {"desc":"天气不错，抓紧时机让衣物晒太阳吧。","title":"较适宜"}
         * mf : {"desc":"温湿适宜，风力较小，这为您的头发创造一个健康、洁净的生长环境，加上您细心的呵护打理，您的秀发定能飘逸动人。","title":"极适宜"}
         * nl : {"desc":"天气较好，虽然有点风，但仍比较适宜夜生活，您可以放心外出。","title":"较适宜"}
         * pj : {"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"}
         * pk : {"desc":"气温略高，放风筝时戴上太阳帽。","title":"较适宜"}
         * sports : {"desc":"请减少运动时间并降低运动强度。","title":"较适宜"}
         * travel : {"desc":"天热注意防晒，可选择水上娱乐项目。","title":"较适宜"}
         * uv : {"desc":"涂擦SPF大于15、PA+防晒护肤品","title":"强"}
         * wash_car : {"desc":"无雨且风力较小，易保持清洁度","title":"较适宜"}
         * xq : {"desc":"温度适宜，心情会不错。","title":"好"}
         * yh : {"desc":"天气较好，适宜约会","title":"较适宜"}
         * zs : {"desc":"气温不高，中暑几率极低。","title":"不容易中暑"}
         */

        private AcBean ac;
        private AgBean ag;
        private AqiBean aqi;
        private BeautyBean beauty;
        private ClBean cl;
        private ClothesBean clothes;
        private ColdBean cold;
        private ComfortBean comfort;
        private DyBean dy;
        private GjBean gj;
        private GlassBean glass;
        private HcBean hc;
        private LsBean ls;
        private MfBean mf;
        private NlBean nl;
        private PjBean pj;
        private PkBean pk;
        private SportsBean sports;
        private TravelBean travel;
        private UvBean uv;
        private WashCarBean wash_car;
        private XqBean xq;
        private YhBean yh;
        private ZsBean zs;

        public AcBean getAc() {
            return ac;
        }

        public void setAc(AcBean ac) {
            this.ac = ac;
        }

        public AgBean getAg() {
            return ag;
        }

        public void setAg(AgBean ag) {
            this.ag = ag;
        }

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BeautyBean getBeauty() {
            return beauty;
        }

        public void setBeauty(BeautyBean beauty) {
            this.beauty = beauty;
        }

        public ClBean getCl() {
            return cl;
        }

        public void setCl(ClBean cl) {
            this.cl = cl;
        }

        public ClothesBean getClothes() {
            return clothes;
        }

        public void setClothes(ClothesBean clothes) {
            this.clothes = clothes;
        }

        public ColdBean getCold() {
            return cold;
        }

        public void setCold(ColdBean cold) {
            this.cold = cold;
        }

        public ComfortBean getComfort() {
            return comfort;
        }

        public void setComfort(ComfortBean comfort) {
            this.comfort = comfort;
        }

        public DyBean getDy() {
            return dy;
        }

        public void setDy(DyBean dy) {
            this.dy = dy;
        }

        public GjBean getGj() {
            return gj;
        }

        public void setGj(GjBean gj) {
            this.gj = gj;
        }

        public GlassBean getGlass() {
            return glass;
        }

        public void setGlass(GlassBean glass) {
            this.glass = glass;
        }

        public HcBean getHc() {
            return hc;
        }

        public void setHc(HcBean hc) {
            this.hc = hc;
        }

        public LsBean getLs() {
            return ls;
        }

        public void setLs(LsBean ls) {
            this.ls = ls;
        }

        public MfBean getMf() {
            return mf;
        }

        public void setMf(MfBean mf) {
            this.mf = mf;
        }

        public NlBean getNl() {
            return nl;
        }

        public void setNl(NlBean nl) {
            this.nl = nl;
        }

        public PjBean getPj() {
            return pj;
        }

        public void setPj(PjBean pj) {
            this.pj = pj;
        }

        public PkBean getPk() {
            return pk;
        }

        public void setPk(PkBean pk) {
            this.pk = pk;
        }

        public SportsBean getSports() {
            return sports;
        }

        public void setSports(SportsBean sports) {
            this.sports = sports;
        }

        public TravelBean getTravel() {
            return travel;
        }

        public void setTravel(TravelBean travel) {
            this.travel = travel;
        }

        public UvBean getUv() {
            return uv;
        }

        public void setUv(UvBean uv) {
            this.uv = uv;
        }

        public WashCarBean getWash_car() {
            return wash_car;
        }

        public void setWash_car(WashCarBean wash_car) {
            this.wash_car = wash_car;
        }

        public XqBean getXq() {
            return xq;
        }

        public void setXq(XqBean xq) {
            this.xq = xq;
        }

        public YhBean getYh() {
            return yh;
        }

        public void setYh(YhBean yh) {
            this.yh = yh;
        }

        public ZsBean getZs() {
            return zs;
        }

        public void setZs(ZsBean zs) {
            this.zs = zs;
        }

        public static class AcBean {
            /**
             * desc : 您将感到很舒适，一般不需要开启空调。
             * title : 较少开启
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class AgBean {
            /**
             * desc : 天气条件极易诱发过敏，易过敏人群尽量减少外出，外出宜穿长衣长裤并佩戴好眼镜和口罩，外出归来时及时清洁手和口鼻。
             * title : 极易发
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class AqiBean {
            /**
             * desc : 空气很好，可以外出活动，呼吸新鲜空气
             * title : 优质
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class BeautyBean {
            /**
             * desc : 请选用露质面霜打底，水质无油粉底霜。
             * title : 去油
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ClBean {
            /**
             * desc : 请减少运动时间并降低运动强度。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ClothesBean {
            /**
             * desc : 建议穿长袖衬衫单裤等服装
             * title : 舒适
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ColdBean {
            /**
             * desc : 感冒机率较低，避免长期处于空调屋中。
             * title : 少发
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ComfortBean {
            /**
             * desc : 偏热或较热，部分人感觉不舒适
             * title : 较差
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class DyBean {
            /**
             * desc : 天气稍热会对垂钓产生一定影响。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class GjBean {
            /**
             * desc : 这种好天气去逛街可使身心畅快放松。
             * title : 适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class GlassBean {
            /**
             * desc : 必要佩戴
             * title : 必要
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class HcBean {
            /**
             * desc : 天气较好，适宜划船及嬉玩水上运动。
             * title : 适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class LsBean {
            /**
             * desc : 天气不错，抓紧时机让衣物晒太阳吧。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class MfBean {
            /**
             * desc : 温湿适宜，风力较小，这为您的头发创造一个健康、洁净的生长环境，加上您细心的呵护打理，您的秀发定能飘逸动人。
             * title : 极适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class NlBean {
            /**
             * desc : 天气较好，虽然有点风，但仍比较适宜夜生活，您可以放心外出。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class PjBean {
            /**
             * desc : 天气炎热，可适量饮用啤酒，不要过量。
             * title : 适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class PkBean {
            /**
             * desc : 气温略高，放风筝时戴上太阳帽。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class SportsBean {
            /**
             * desc : 请减少运动时间并降低运动强度。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class TravelBean {
            /**
             * desc : 天热注意防晒，可选择水上娱乐项目。
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class UvBean {
            /**
             * desc : 涂擦SPF大于15、PA+防晒护肤品
             * title : 强
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class WashCarBean {
            /**
             * desc : 无雨且风力较小，易保持清洁度
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class XqBean {
            /**
             * desc : 温度适宜，心情会不错。
             * title : 好
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class YhBean {
            /**
             * desc : 天气较好，适宜约会
             * title : 较适宜
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class ZsBean {
            /**
             * desc : 气温不高，中暑几率极低。
             * title : 不容易中暑
             */

            private String desc;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
