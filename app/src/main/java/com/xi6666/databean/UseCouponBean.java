package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2017/2/24.
 */

public class UseCouponBean {


    /**
     * success : true
     * info : 获取用户加油优惠券成功
     * data : [{"id":"36398","user_id":"11058","coupon_id":"11","coupon_sn":"20170224161863732","coupon_type":"3","price":"50.00","sdate":"2017-02-24","edate":"2017-12-24","is_used":"0","add_time":"2017-02-24","used_time":"0000-00-00","current":"1","is_receive":"1","coupon_money":"50.00","coupon_name":"满400可用","coupon_platform":0,"coupon_use_type":"1"}]
     * version : 5
     */

    private boolean success;
    private String info;
    private String version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 36398
         * user_id : 11058
         * coupon_id : 11
         * coupon_sn : 20170224161863732
         * coupon_type : 3
         * price : 50.00
         * sdate : 2017-02-24
         * edate : 2017-12-24
         * is_used : 0
         * add_time : 2017-02-24
         * used_time : 0000-00-00
         * current : 1
         * is_receive : 1
         * coupon_money : 50.00
         * coupon_name : 满400可用
         * coupon_platform : 0
         * coupon_use_type : 1
         */

        private String id;
        private String user_id;
        private String coupon_id;
        private String coupon_sn;
        private String coupon_type;
        private String price;
        private String sdate;
        private String edate;
        private String is_used;
        private String add_time;
        private String used_time;
        private String current;
        private String is_receive;
        private String coupon_money;
        private String coupon_name;
        private int coupon_platform;
        private String coupon_use_type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCoupon_sn() {
            return coupon_sn;
        }

        public void setCoupon_sn(String coupon_sn) {
            this.coupon_sn = coupon_sn;
        }

        public String getCoupon_type() {
            return coupon_type;
        }

        public void setCoupon_type(String coupon_type) {
            this.coupon_type = coupon_type;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSdate() {
            return sdate;
        }

        public void setSdate(String sdate) {
            this.sdate = sdate;
        }

        public String getEdate() {
            return edate;
        }

        public void setEdate(String edate) {
            this.edate = edate;
        }

        public String getIs_used() {
            return is_used;
        }

        public void setIs_used(String is_used) {
            this.is_used = is_used;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUsed_time() {
            return used_time;
        }

        public void setUsed_time(String used_time) {
            this.used_time = used_time;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(String is_receive) {
            this.is_receive = is_receive;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public int getCoupon_platform() {
            return coupon_platform;
        }

        public void setCoupon_platform(int coupon_platform) {
            this.coupon_platform = coupon_platform;
        }

        public String getCoupon_use_type() {
            return coupon_use_type;
        }

        public void setCoupon_use_type(String coupon_use_type) {
            this.coupon_use_type = coupon_use_type;
        }
    }
}
