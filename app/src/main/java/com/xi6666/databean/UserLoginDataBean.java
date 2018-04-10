package com.xi6666.databean;

/**
 * Created by Mr_yang on 2016/11/18.
 */

public class UserLoginDataBean {

    /**
     * success : true
     * info : 登录成功
     * data : {"user_id":"180801","user_no":"18207555397_1","user_name":"18207555397","user_mobile":"18207555397","user_wx_openid":null,"user_nickname":null,"user_truename":null,"user_face":null,"user_idcard":null,"user_sex":"3","user_birthday":null,"user_qrcode":null,"user_xidou_amount":"20","user_xiche_amount":"0.00","user_cash_amount":"0.00","user_withdraw_amount":"0.00","user_lastlogin":"2016-11-19 11:14:37","user_loginnum":"9","parent_id":"0","recomend_id":"0","user_flag":"0","user_rank":"1","user_status":"2","is_pwd":"false","user_token":"830c4e5abc5245b2313dc4f398df686e"}
     * version : 5
     */

    private boolean success;
    private String info;
    /**
     * user_id : 180801
     * user_no : 18207555397_1
     * user_name : 18207555397
     * user_mobile : 18207555397
     * user_wx_openid : null
     * user_nickname : null
     * user_truename : null
     * user_face : null
     * user_idcard : null
     * user_sex : 3
     * user_birthday : null
     * user_qrcode : null
     * user_xidou_amount : 20
     * user_xiche_amount : 0.00
     * user_cash_amount : 0.00
     * user_withdraw_amount : 0.00
     * user_lastlogin : 2016-11-19 11:14:37
     * user_loginnum : 9
     * parent_id : 0
     * recomend_id : 0
     * user_flag : 0
     * user_rank : 1
     * user_status : 2
     * is_pwd : false
     * user_token : 830c4e5abc5245b2313dc4f398df686e
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
        private String user_id;
        private String user_no;
        private String user_name;
        private String user_mobile;
        private Object user_wx_openid;
        private Object user_nickname;
        private Object user_truename;
        private Object user_face;
        private Object user_idcard;
        private String user_sex;
        private Object user_birthday;
        private Object user_qrcode;
        private String user_xidou_amount;
        private String user_xiche_amount;
        private String user_cash_amount;
        private String user_withdraw_amount;
        private String user_lastlogin;
        private String user_loginnum;
        private String parent_id;
        private String recomend_id;
        private String user_flag;
        private String user_rank;
        private String user_status;
        private String is_pwd;
        private String user_token;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_no() {
            return user_no;
        }

        public void setUser_no(String user_no) {
            this.user_no = user_no;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public Object getUser_wx_openid() {
            return user_wx_openid;
        }

        public void setUser_wx_openid(Object user_wx_openid) {
            this.user_wx_openid = user_wx_openid;
        }

        public Object getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(Object user_nickname) {
            this.user_nickname = user_nickname;
        }

        public Object getUser_truename() {
            return user_truename;
        }

        public void setUser_truename(Object user_truename) {
            this.user_truename = user_truename;
        }

        public Object getUser_face() {
            return user_face;
        }

        public void setUser_face(Object user_face) {
            this.user_face = user_face;
        }

        public Object getUser_idcard() {
            return user_idcard;
        }

        public void setUser_idcard(Object user_idcard) {
            this.user_idcard = user_idcard;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public Object getUser_birthday() {
            return user_birthday;
        }

        public void setUser_birthday(Object user_birthday) {
            this.user_birthday = user_birthday;
        }

        public Object getUser_qrcode() {
            return user_qrcode;
        }

        public void setUser_qrcode(Object user_qrcode) {
            this.user_qrcode = user_qrcode;
        }

        public String getUser_xidou_amount() {
            return user_xidou_amount;
        }

        public void setUser_xidou_amount(String user_xidou_amount) {
            this.user_xidou_amount = user_xidou_amount;
        }

        public String getUser_xiche_amount() {
            return user_xiche_amount;
        }

        public void setUser_xiche_amount(String user_xiche_amount) {
            this.user_xiche_amount = user_xiche_amount;
        }

        public String getUser_cash_amount() {
            return user_cash_amount;
        }

        public void setUser_cash_amount(String user_cash_amount) {
            this.user_cash_amount = user_cash_amount;
        }

        public String getUser_withdraw_amount() {
            return user_withdraw_amount;
        }

        public void setUser_withdraw_amount(String user_withdraw_amount) {
            this.user_withdraw_amount = user_withdraw_amount;
        }

        public String getUser_lastlogin() {
            return user_lastlogin;
        }

        public void setUser_lastlogin(String user_lastlogin) {
            this.user_lastlogin = user_lastlogin;
        }

        public String getUser_loginnum() {
            return user_loginnum;
        }

        public void setUser_loginnum(String user_loginnum) {
            this.user_loginnum = user_loginnum;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getRecomend_id() {
            return recomend_id;
        }

        public void setRecomend_id(String recomend_id) {
            this.recomend_id = recomend_id;
        }

        public String getUser_flag() {
            return user_flag;
        }

        public void setUser_flag(String user_flag) {
            this.user_flag = user_flag;
        }

        public String getUser_rank() {
            return user_rank;
        }

        public void setUser_rank(String user_rank) {
            this.user_rank = user_rank;
        }

        public String getUser_status() {
            return user_status;
        }

        public void setUser_status(String user_status) {
            this.user_status = user_status;
        }

        public String getIs_pwd() {
            return is_pwd;
        }

        public void setIs_pwd(String is_pwd) {
            this.is_pwd = is_pwd;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }
    }
}
