package com.xi6666.databean;

/**
 * Created by Mr_yang on 2017/2/9.
 */

public class IllegaPayBean {

    /**
     * data : {"attach":"illegal_card_pay","body":"洗车卡充值","out_trade_no":"2017020914260891443","pay_id":5,"pay_name":"微信支付","total_fee":"66600"}
     * info : 请发送data里面的数据至interface_url调用支付
     * interface_url : http://dev-app.xiaoxi6.com/Pay/apppay
     * success : true
     * zhifubao_url : http://dev-app.xiaoxi6.com/Pay/alipay
     */

    private DataBean data;
    private String info;
    private String interface_url;
    private boolean success;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getZhifubao_url() {
        return zhifubao_url;
    }

    public void setZhifubao_url(String zhifubao_url) {
        this.zhifubao_url = zhifubao_url;
    }

    public static class DataBean {
        /**
         * attach : illegal_card_pay
         * body : 洗车卡充值
         * out_trade_no : 2017020914260891443
         * pay_id : 5
         * pay_name : 微信支付
         * total_fee : 66600
         */

        private String attach;
        private String body;
        private String out_trade_no;
        private int pay_id;
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

        public int getPay_id() {
            return pay_id;
        }

        public void setPay_id(int pay_id) {
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
