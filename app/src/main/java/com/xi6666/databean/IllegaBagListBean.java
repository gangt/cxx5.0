package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2017/2/8.
 */

public class IllegaBagListBean {

    /**
     * success : true
     * info : 获取违章卡列表成功
     * data : [{"card_id":"4","card_number":"NO.34500987","card_amount":"366.00","binding_carno":"粤E288C3","surplus_number":"3","used_number":"0","add_datetime":"2017-02-07 20:05:46","is_binding":"1","is_give":"0","user_datetime":"2017-06-07 20:05:46","end_datetime":"2017-06-07 20:05:46","expire":0,"card_status":3,"province_code":"","city_code":"","car_no":"粤E288C3","engineno":"231312","classno":"231312","query_id":"680"},{"card_id":"3","card_number":"NO.34500983","card_amount":"366.00","binding_carno":"","surplus_number":"3","used_number":"0","add_datetime":"2017-02-07 20:05:46","is_binding":"0","is_give":"1","user_datetime":"2017-04-07 20:05:46","end_datetime":"2017-04-07 20:05:46","expire":0,"card_status":2,"province_code":"","city_code":"","car_no":"","engineno":null,"classno":null,"query_id":""},{"card_id":"2","card_number":"NO.24500983","card_amount":"666.00","binding_carno":"粤SN5M57","surplus_number":"5","used_number":"0","add_datetime":"2017-02-07 20:04:55","is_binding":"1","is_give":"0","user_datetime":"2017-02-09 17:30:45","end_datetime":"2017-07-09 17:30:45","expire":0,"card_status":3,"province_code":"GD","city_code":"GD_SZ","car_no":"粤SN5M57","engineno":"433933","classno":"111435","query_id":"294"},{"card_id":"1","card_number":"NO.14500983","card_amount":"666.00","binding_carno":"粤sn5m57","surplus_number":"6","used_number":"3","add_datetime":"2017-02-07 16:45:36","is_binding":"1","is_give":"1","user_datetime":"2017-04-07 20:05:46","end_datetime":"2017-04-07 20:05:46","expire":0,"card_status":2,"province_code":"GD","city_code":"GD_SZ","car_no":"粤sn5m57","engineno":"433933","classno":"111435","query_id":"294"}]
     * count : 4
     */

    private boolean success;
    private String info;
    private String count;
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
        /**
         * card_id : 4
         * card_number : NO.34500987
         * card_amount : 366.00
         * binding_carno : 粤E288C3
         * surplus_number : 3
         * used_number : 0
         * add_datetime : 2017-02-07 20:05:46
         * is_binding : 1
         * is_give : 0
         * user_datetime : 2017-06-07 20:05:46
         * end_datetime : 2017-06-07 20:05:46
         * expire : 0
         * card_status : 3
         * province_code :
         * city_code :
         * car_no : 粤E288C3
         * engineno : 231312
         * classno : 231312
         * query_id : 680
         */

        private String card_id;
        private String card_number;
        private String card_amount;
        private String binding_carno;
        private String surplus_number;
        private String used_number;
        private String add_datetime;
        private String is_binding;
        private String is_give;
        private String user_datetime;
        private String end_datetime;
        private int expire;
        private int card_status;
        private String province_code;
        private String city_code;
        private String car_no;
        private String engineno;
        private String classno;
        private String query_id;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCard_amount() {
            return card_amount;
        }

        public void setCard_amount(String card_amount) {
            this.card_amount = card_amount;
        }

        public String getBinding_carno() {
            return binding_carno;
        }

        public void setBinding_carno(String binding_carno) {
            this.binding_carno = binding_carno;
        }

        public String getSurplus_number() {
            return surplus_number;
        }

        public void setSurplus_number(String surplus_number) {
            this.surplus_number = surplus_number;
        }

        public String getUsed_number() {
            return used_number;
        }

        public void setUsed_number(String used_number) {
            this.used_number = used_number;
        }

        public String getAdd_datetime() {
            return add_datetime;
        }

        public void setAdd_datetime(String add_datetime) {
            this.add_datetime = add_datetime;
        }

        public String getIs_binding() {
            return is_binding;
        }

        public void setIs_binding(String is_binding) {
            this.is_binding = is_binding;
        }

        public String getIs_give() {
            return is_give;
        }

        public void setIs_give(String is_give) {
            this.is_give = is_give;
        }

        public String getUser_datetime() {
            return user_datetime;
        }

        public void setUser_datetime(String user_datetime) {
            this.user_datetime = user_datetime;
        }

        public String getEnd_datetime() {
            return end_datetime;
        }

        public void setEnd_datetime(String end_datetime) {
            this.end_datetime = end_datetime;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public int getCard_status() {
            return card_status;
        }

        public void setCard_status(int card_status) {
            this.card_status = card_status;
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

        public String getQuery_id() {
            return query_id;
        }

        public void setQuery_id(String query_id) {
            this.query_id = query_id;
        }
    }
}
