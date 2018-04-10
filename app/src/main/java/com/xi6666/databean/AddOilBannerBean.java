package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/23.
 */

public class AddOilBannerBean {

    /**
     * banner : [{"img":"http://www.xi6666.com/xiaoxiv4/wechat_static/img/1080x431.png","link":"","title":"8.9折加油"}]
     * data : [{"package_discount":0.99,"package_id":"1","package_name":"即时充值","package_return_number":"1"},{"package_discount":0.85,"package_id":"2","package_name":"15天充值套餐","package_return_number":"2"},{"package_discount":0.85,"package_id":"3","package_name":"2个月加油套餐","package_return_number":"2"},{"package_discount":0.96,"package_id":"4","package_name":"3个月充值套餐","package_return_number":"3"},{"package_discount":0.9,"package_id":"5","package_name":"6个月充值套餐","package_return_number":"6"},{"package_discount":0.86,"package_id":"6","package_name":"12个月充值套餐","package_return_number":"12"},{"package_discount":0.79,"package_id":"7","package_name":"18个月充值套餐","package_return_number":"18"},{"package_discount":0.79,"package_id":"8","package_name":"20个月充值套餐","package_return_number":"20"}]
     * info : 返回成功
     * max_money : 5000
     * success : true
     */

    private String info;
    private int max_money;
    private boolean success;
    /**
     * img : http://www.xi6666.com/xiaoxiv4/wechat_static/img/1080x431.png
     * link :
     * title : 8.9折加油
     */

    private List<BannerBean> banner;
    /**
     * package_discount : 0.99
     * package_id : 1
     * package_name : 即时充值
     * package_return_number : 1
     */

    private List<DataBean> data;

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
        private String img;
        private String link;
        private String title;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class DataBean {
        private double package_discount;
        private String package_id;
        private String package_name;
        private String package_return_number;

        public double getPackage_discount() {
            return package_discount;
        }

        public void setPackage_discount(double package_discount) {
            this.package_discount = package_discount;
        }

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
    }
}
