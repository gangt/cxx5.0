package com.xi6666.order.bean;

/**
 * 作者： qsdsn on 2016/11/30
 * 描述：${DESC}
 */

public class PaySuccessBean {

    private boolean success;
    private String   info;
    private DataBean data;
    private String   version;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {

        private String order_id;
        private String order_sn;
        private String shipping_status;
        private String fendan;
        private String order_type;
        private String can_get_xidou;

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

        public String getShipping_status() {
            return shipping_status;
        }

        public void setShipping_status(String shipping_status) {
            this.shipping_status = shipping_status;
        }

        public String getFendan() {
            return fendan;
        }

        public void setFendan(String fendan) {
            this.fendan = fendan;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getCan_get_xidou() {
            return can_get_xidou;
        }

        public void setCan_get_xidou(String can_get_xidou) {
            this.can_get_xidou = can_get_xidou;
        }
    }
}
