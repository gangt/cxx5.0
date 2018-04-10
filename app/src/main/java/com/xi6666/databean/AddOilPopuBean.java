package com.xi6666.databean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr_yang on 2017/2/23.
 */

public class AddOilPopuBean {


    /**
     * data : {"coupon_list":[{"coupon_id":"11","coupon_money":"50.00","coupon_name":"满400可用"}],"is_receive":1}
     * info : 获取信息成功
     * success : true
     * version : 5
     */

    private DataBean data;
    private String info;
    private boolean success;
    private String version;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        /**
         * coupon_list : [{"coupon_id":"11","coupon_money":"50.00","coupon_name":"满400可用"}]
         * is_receive : 1
         */

        private int is_receive;
        private List<CouponListBean> coupon_list;

        public int getIs_receive() {
            return is_receive;
        }

        public void setIs_receive(int is_receive) {
            this.is_receive = is_receive;
        }

        public List<CouponListBean> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBean> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public static class CouponListBean implements Serializable {
            /**
             * coupon_id : 11
             * coupon_money : 50.00
             * coupon_name : 满400可用
             */

            private String coupon_id;
            private String coupon_money;
            private String coupon_name;
            private String coupon_info;

            public void setCoupon_info(String coupon_info) {
                this.coupon_info = coupon_info;
            }

            public String getCoupon_info() {
                return coupon_info;
            }

            public String getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(String coupon_id) {
                this.coupon_id = coupon_id;
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
        }
    }
}
