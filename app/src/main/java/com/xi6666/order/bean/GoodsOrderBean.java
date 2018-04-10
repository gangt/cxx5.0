package com.xi6666.order.bean;

import java.util.List;

/**
 * 作者： qsdsn on 2016/11/22
 * 描述：${DESC}
 */

public class GoodsOrderBean {

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

        private String order_id;
        private String              order_sn;
        private String              user_id;
        private String              order_amount;
        private String              order_status;
        private String              shipping_status;
        private String              pay_status;
        private String              order_total;
        private String              money_paid;
        private String              xidou_paid;
        private String              order_state;
        private int                 goods_count;
        private List<GoodsInfoBean> goods_info;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(String shipping_status) {
            this.shipping_status = shipping_status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getOrder_total() {
            return order_total;
        }

        public void setOrder_total(String order_total) {
            this.order_total = order_total;
        }

        public String getMoney_paid() {
            return money_paid;
        }

        public void setMoney_paid(String money_paid) {
            this.money_paid = money_paid;
        }

        public String getXidou_paid() {
            return xidou_paid;
        }

        public void setXidou_paid(String xidou_paid) {
            this.xidou_paid = xidou_paid;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public int getGoods_count() {
            return goods_count;
        }

        public void setGoods_count(int goods_count) {
            this.goods_count = goods_count;
        }

        public List<GoodsInfoBean> getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(List<GoodsInfoBean> goods_info) {
            this.goods_info = goods_info;
        }

        public static class GoodsInfoBean {
            /**
             * goods_name : 香百年 汽车香水座式车载香水座除臭除异味800系列【包邮】
             * goods_shop_price : 67.00
             * buy_number : 1
             * goods_soure_img : images/201512/source_img/21674_G_1449273798821.jpg
             */

            private String goods_name;
            private String goods_shop_price;
            private String buy_number;
            private String goods_soure_img;

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_shop_price() {
                return goods_shop_price;
            }

            public void setGoods_shop_price(String goods_shop_price) {
                this.goods_shop_price = goods_shop_price;
            }

            public String getBuy_number() {
                return buy_number;
            }

            public void setBuy_number(String buy_number) {
                this.buy_number = buy_number;
            }

            public String getGoods_soure_img() {
                return goods_soure_img;
            }

            public void setGoods_soure_img(String goods_soure_img) {
                this.goods_soure_img = goods_soure_img;
            }
        }
    }
}
