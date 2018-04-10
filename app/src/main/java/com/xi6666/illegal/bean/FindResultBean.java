package com.xi6666.illegal.bean;

import java.util.List;

/**
 * Created by Huang Yingde on 2016/6/19 0019.
 */
public class FindResultBean {


    /**
     * hphm : 粤SN5M58
     * illegal_fen : 8
     * illegal_money : 520
     * illegal_num : 1
     * lists : [{"act":"驾驶中型以上载客载货汽车、危险物品运输车辆以外的机动车超过规定时速10%以下的","area":"沪昆高速806公里1000米","carno":"粤SN5M58","code":"1352A","date":"2016-06-24 17:42:46","fen":"8","handled":"0","id":"46","money":"520","user_id":"11058"}]
     */

    private DataBean data;
    /**
     * data : {"hphm":"粤SN5M58","illegal_fen":8,"illegal_money":520,"illegal_num":1,"lists":[{"act":"驾驶中型以上载客载货汽车、危险物品运输车辆以外的机动车超过规定时速10%以下的","area":"沪昆高速806公里1000米","carno":"粤SN5M58","code":"1352A","date":"2016-06-24 17:42:46","fen":"8","handled":"0","id":"46","money":"520","user_id":"11058"}]}
     * info : 查询成功
     * jumpurl :
     * success : true
     */

    private String   info;
    private String   jumpurl;
    private String   success;

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
        private String          hphm;
        private int             illegal_fen;
        private int             illegal_money;
        private int             illegal_num;
        /**
         * act : 驾驶中型以上载客载货汽车、危险物品运输车辆以外的机动车超过规定时速10%以下的
         * area : 沪昆高速806公里1000米
         * carno : 粤SN5M58
         * code : 1352A
         * date : 2016-06-24 17:42:46
         * fen : 8
         * handled : 0
         * id : 46
         * money : 520
         * user_id : 11058
         */

        private List<ListsBean> lists;

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }

        public int getIllegal_fen() {
            return illegal_fen;
        }

        public void setIllegal_fen(int illegal_fen) {
            this.illegal_fen = illegal_fen;
        }

        public int getIllegal_money() {
            return illegal_money;
        }

        public void setIllegal_money(int illegal_money) {
            this.illegal_money = illegal_money;
        }

        public int getIllegal_num() {
            return illegal_num;
        }

        public void setIllegal_num(int illegal_num) {
            this.illegal_num = illegal_num;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            private String act;
            private String area;
            private String carno;
            private String code;
            private String date;
            private String fen;
            private String handled;
            private String id;
            private String money;
            private String user_id;

            public String getAct() {
                return act;
            }

            public void setAct(String act) {
                this.act = act;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCarno() {
                return carno;
            }

            public void setCarno(String carno) {
                this.carno = carno;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getFen() {
                return fen;
            }

            public void setFen(String fen) {
                this.fen = fen;
            }

            public String getHandled() {
                return handled;
            }

            public void setHandled(String handled) {
                this.handled = handled;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
