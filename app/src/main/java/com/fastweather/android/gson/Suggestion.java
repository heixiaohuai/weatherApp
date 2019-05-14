package com.fastweather.android.gson;

/**
 * Created by 13055 on 2019/5/13.
 */

public class Suggestion {

    /**
     * ac : {"desc":"您将感到很舒适，一般不需要开启空调。","title":"较少开启"}
     * ag : {"desc":"天气条件不易诱发过敏，有降水，特殊体质人群应预防感冒可能引发的过敏。","title":"不易发"}
     * aqi : {"desc":"可以正常在户外活动，易敏感人群应减少外出","title":"良好"}
     * beauty : {"desc":"请选用露质面霜打底，水质无油粉底霜。","title":"去油"}
     * cl : {"desc":"推荐您进行室内运动。","title":"较适宜"}
     * clothes : {"desc":"建议穿薄外套或牛仔裤等服装","title":"较舒适"}
     * cold : {"desc":"感冒机率较低，避免长期处于空调屋中。","title":"少发"}
     * comfort : {"desc":"偏凉或凉，部分人感觉不舒适","title":"较好"}
     * dy : {"desc":"天气稍热会对垂钓产生一定影响。","title":"较适宜"}
     * gj : {"desc":"这种好天气去逛街可使身心畅快放松。","title":"适宜"}
     * glass : {"desc":"不需要佩戴","title":"不需要"}
     * hc : {"desc":"天气较好，适宜划船及嬉玩水上运动。","title":"适宜"}
     * ls : {"desc":"天气阴沉，不太适宜晾晒。","title":"不适宜"}
     * mf : {"desc":"温湿适宜，风力较小，这为您的头发创造一个健康、洁净的生长环境，加上您细心的呵护打理，您的秀发定能飘逸动人。","title":"极适宜"}
     * nl : {"desc":"有风，且有降水，会给您的出行带来很大的不便，建议就近或最好在室内进行夜生活。","title":"较不适宜"}
     * pj : {"desc":"天气炎热，可适量饮用啤酒，不要过量。","title":"适宜"}
     * pk : {"desc":"气温略高，放风筝时戴上太阳帽。","title":"较适宜"}
     * sports : {"desc":"推荐您进行室内运动。","title":"较适宜"}
     * travel : {"desc":"天热注意防晒，可选择水上娱乐项目。","title":"较适宜"}
     * uv : {"desc":"辐射弱，涂擦SPF8-12防晒护肤品","title":"最弱"}
     * wash_car : {"desc":"有雨，雨水和泥水会弄脏爱车","title":"不宜"}
     * xq : {"desc":"温度适宜，心情会不错。","title":"好"}
     * yh : {"desc":"不用担心天气来调皮捣乱而影响了兴致。 ","title":"较适宜"}
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
         * desc : 天气条件不易诱发过敏，有降水，特殊体质人群应预防感冒可能引发的过敏。
         * title : 不易发
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
         * desc : 可以正常在户外活动，易敏感人群应减少外出
         * title : 良好
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
         * desc : 推荐您进行室内运动。
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
         * desc : 建议穿薄外套或牛仔裤等服装
         * title : 较舒适
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
         * desc : 偏凉或凉，部分人感觉不舒适
         * title : 较好
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
         * desc : 不需要佩戴
         * title : 不需要
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
         * desc : 天气阴沉，不太适宜晾晒。
         * title : 不适宜
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
         * desc : 有风，且有降水，会给您的出行带来很大的不便，建议就近或最好在室内进行夜生活。
         * title : 较不适宜
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
         * desc : 推荐您进行室内运动。
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
         * desc : 辐射弱，涂擦SPF8-12防晒护肤品
         * title : 最弱
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
         * desc : 有雨，雨水和泥水会弄脏爱车
         * title : 不宜
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
         * desc : 不用担心天气来调皮捣乱而影响了兴致。
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
