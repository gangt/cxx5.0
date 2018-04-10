package com.xi6666.order.bean;

import java.util.List;

/**
 * 作者： qsdsn on 2017/1/16
 * 描述：${DESC}
 */

public class CouponBean {

    private boolean success;
    private String         info;
    private String         count;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String user_id;
        private String coupon_id;
        private String coupon_sn;
        private String coupon_type;
        private String coupon_name;
        private String price;
        private String sdate;
        private String edate;
        private String is_used;
        private String add_time;
        private String used_time;
        private String current;
        private String limit_money;
        private String is_available;
        private int    coupon_platform;

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
        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
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
        public String getLimit_money() {
            return limit_money;
        }

        public void setLimit_money(String limit_money) {
            this.limit_money = limit_money;
        }
        public String getIs_available() {
            return is_available;
        }

        public void setIs_available(String is_available) {
            this.is_available = is_available;
        }
        public int getCoupon_platform() {
            return coupon_platform;
        }

        public void setCoupon_platform(int coupon_platform) {
            this.coupon_platform = coupon_platform;
        }
    }
}
