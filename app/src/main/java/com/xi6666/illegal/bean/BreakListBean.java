package com.xi6666.illegal.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public class BreakListBean {

    /**
     * success : true
     * info : 查询成功
     * data : [{"user_id":"11058","query_engineno":"118086","query_classno":"466740","province_code":"GD","city_code":"GD_SZ","query_carno":"粤L7Z413","count":"5","fen":"6160","money":"70","city":"深圳"},{"user_id":"11058","query_engineno":"111435","query_classno":"433933","province_code":"BJ","city_code":"BJ","query_carno":"粤SN5M57","count":"1","fen":"3","money":"6","city":"深圳"},{"user_id":"11058","query_engineno":"111435","query_classno":"433933","province_code":"GD","city_code":"GD_DG","query_carno":"粤SN5M57","count":"2","fen":"9","money":"350"}]
     * version : 5
     */

    private boolean success;
    private String         info;
    private String         version;
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
         * user_id : 11058
         * query_engineno : 118086
         * query_classno : 466740
         * province_code : GD
         * city_code : GD_SZ
         * query_carno : 粤L7Z413
         * count : 5
         * fen : 6160
         * money : 70
         * city : 深圳
         */

        private String user_id;
        private String engineno;
        private String classno;
        private String province_code;
        private String city_code;
        private String car_no;
        private String count;
        private String fen;
        private String money;
        private String city;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getEngineno() {
            return engineno;
        }

        public void setEngineno(String engineno) {
            this.engineno = engineno;
        }

        public String getClassno() {
            return classno;
        }

        public void setClassno(String classno) {
            this.classno = classno;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFen() {
            return fen;
        }

        public void setFen(String fen) {
            this.fen = fen;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
