package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/8/25.
 */
public class AddOilBean {

    /**
     * success : true
     * info : 返回成功
     * max_money : 5000
     * time : 1479894628
     * banner : [{"title":"8.9折加油","link":"","img":"http://www.xi6666.com/xiaoxiv4/wechat_static/img/1080x431.png"}]
     * data : [{"package_id":"2","package_name":"15天充值套餐","package_return_number":"2","package_discount":0.85,"package_title":"8.5折"},{"package_id":"4","package_name":"3个月加油套餐","package_return_number":"3","package_discount":0.96,"package_title":"9.6折"},{"package_id":"5","package_name":"6个月加油套餐","package_return_number":"6","package_discount":0.9,"package_title":"9折"},{"package_id":"6","package_name":"12个月加油套餐","package_return_number":"12","package_discount":0.86,"package_title":"8.6折"}]
     */

    private boolean success;
    private String info;
    private int max_money;
    private int time;
    /**
     * title : 8.9折加油
     * link :
     * img : http://www.xi6666.com/xiaoxiv4/wechat_static/img/1080x431.png
     */

    private List<BannerBean> banner;
    /**
     * package_id : 2
     * package_name : 15天充值套餐
     * package_return_number : 2
     * package_discount : 0.85
     * package_title : 8.5折
     */

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getMax_money() {
        return max_money;
    }

    public void setMax_money(int max_money) {
        this.max_money = max_money;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class BannerBean {
        private String title;
        private String link;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class DataBean {
        private String package_id;
        private String package_name;
        private String package_return_number;
        private double package_discount;
        private String package_title;

        public String getPackage_id() {
            return package_id;
        }

        public void setPackage_id(String package_id) {
            this.package_id = package_id;
        }

        public String getPackage_name() {
            return package_name;
        }

        public void setPackage_name(String package_name) {
            this.package_name = package_name;
        }

        public String getPackage_return_number() {
            return package_return_number;
        }

        public void setPackage_return_number(String package_return_number) {
            this.package_return_number = package_return_number;
        }

        public double getPackage_discount() {
            return package_discount;
        }

        public void setPackage_discount(double package_discount) {
            this.package_discount = package_discount;
        }

        public String getPackage_title() {
            return package_title;
        }

        public void setPackage_title(String package_title) {
            this.package_title = package_title;
        }
    }
}
