package com.xi6666.databean;

/**
 * Created by Mr_yang on 2016/11/23.
 */

public class DefaultOilCardBean {
    /**
     * success : true
     * info : 返回成功
     * data : {"card_id":"4051","user_id":"11058","card_name":"郭迪","card_number":"1000115200002882375","card_type":"1","card_status":"1","user_mobile":"android.support","is_default":"0","add_datetime":"2016-11-23 15:01:57"}
     * version : 5
     */

    private boolean success;
    private String info;
    /**
     * card_id : 4051
     * user_id : 11058
     * card_name : 郭迪
     * card_number : 1000115200002882375
     * card_type : 1
     * card_status : 1
     * user_mobile : android.support
     * is_default : 0
     * add_datetime : 2016-11-23 15:01:57
     */

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
        private String card_id;
        private String user_id;
        private String card_name;
        private String card_number;
        private String card_type;
        private String card_status;
        private String user_mobile;
        private String is_default;
        private String add_datetime;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

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

        public String getCard_status() {
            return card_status;
        }

        public void setCard_status(String card_status) {
            this.card_status = card_status;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getAdd_datetime() {
            return add_datetime;
        }

        public void setAdd_datetime(String add_datetime) {
            this.add_datetime = add_datetime;
        }
    }
}
