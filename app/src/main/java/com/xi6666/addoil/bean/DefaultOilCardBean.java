package com.xi6666.addoil.bean;

/**
 * Created by Mr_yang on 2016/8/25.
 */
public class DefaultOilCardBean {

    /**
     * card_number : 1000114500001331210
     * card_type : 1
     * id : 765
     * is_default : 1
     * is_del : 1
     * is_verify : 0
     * mobile_phone : 15277235155
     * truename : 熊庆东
     * user_id : 11058
     */

    private DataBean data;
    /**
     * data : {"card_number":"1000114500001331210","card_type":"1","id":"765","is_default":"1","is_del":"1","is_verify":"0","mobile_phone":"15277235155","truename":"熊庆东","user_id":"11058"}
     * info : true
     * jumpurl :
     * success : true
     */

    private String info;
    private String jumpurl;
    private String success;

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

    public String getJumpurl() {
        return jumpurl;
    }

    public void setJumpurl(String jumpurl) {
        this.jumpurl = jumpurl;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public static class DataBean {
        private String card_number;
        private String card_type;
        private String id;
        private String is_default;
        private String is_del;
        private String is_verify;
        private String mobile_phone;
        private String truename;
        private String user_id;

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(String is_verify) {
            this.is_verify = is_verify;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }
}
