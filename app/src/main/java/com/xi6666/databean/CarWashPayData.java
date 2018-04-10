package com.xi6666.databean;

/**
 * Created by Mr_yang on 2016/12/1.
 */

public class CarWashPayData {

    /**
     * success : true
     * info : 返回订单信息成功！
     * data : {"package_cash_amount":"200.00","shop_coupon":"70.00","order_total":"200.00","total_amount":"280.00","order_sn":"2017011615135380058","pay_id":"203881"}
     * version : 5
     */

    private boolean success;
    private String info;
    private DataBean data;
    private String version;

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
        /**
         * package_cash_amount : 200.00
         * shop_coupon : 70.00
         * order_total : 200.00
         * total_amount : 280.00
         * order_sn : 2017011615135380058
         * pay_id : 203881
         */

        private String package_cash_amount;
        private String shop_coupon;
        private String order_total;
        private String total_amount;
        private String order_sn;
        private String pay_id;

        public String getPackage_cash_amount() {
            return package_cash_amount;
        }

        public void setPackage_cash_amount(String package_cash_amount) {
            this.package_cash_amount = package_cash_amount;
        }

        public String getShop_coupon() {
            return shop_coupon;
        }

        public void setShop_coupon(String shop_coupon) {
            this.shop_coupon = shop_coupon;
        }

        public String getOrder_total() {
            return order_total;
        }

        public void setOrder_total(String order_total) {
            this.order_total = order_total;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }
    }
}
