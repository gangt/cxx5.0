package com.xi6666.order.bean;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public class PayBean {

    /**
     * attach : goods_pay_merge
     * body : 车小喜商城
     * out_trade_no : 2016070922190904511
     * pay_id : 4
     * pay_name : 支付宝
     * total_fee : 204.00
     */

    private DataBean data;
    /**
     * data : {"attach":"goods_pay_merge","body":"车小喜商城","out_trade_no":"2016070922190904511","pay_id":"4","pay_name":"支付宝","total_fee":"204.00"}
     * info : 请发送data里面的数据至interface_url调用支付
     * interface_url : http://www.xi6666.com/xiaoxiv4/paylib/demo/apppay.php
     * success : true
     * zhifubao_url : http://www.xi6666.com/xiaoxiv4/paylib/mob_pay/notify_url.php
     */

    private String   info;
    private String interface_url;
    private String success;
    private String zhifubao_url;

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

    public String getInterface_url() {
        return interface_url;
    }

    public void setInterface_url(String interface_url) {
        this.interface_url = interface_url;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getZhifubao_url() {
        return zhifubao_url;
    }

    public void setZhifubao_url(String zhifubao_url) {
        this.zhifubao_url = zhifubao_url;
    }

    public static class DataBean {
        private String attach;
        private String body;
        private String out_trade_no;
        private String pay_id;
        private String pay_name;
        private String total_fee;

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getPay_name() {
            return pay_name;
        }

        public void setPay_name(String pay_name) {
            this.pay_name = pay_name;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }
    }
}
