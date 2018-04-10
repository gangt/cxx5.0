package com.xi6666.illegal.bean;

import java.util.List;

/**
 * 作者： qsdsn on 2017/2/8
 * 描述：${DESC}
 */

public class IllegalUseBean {

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

        private String count;
        private String         fen;
        private String         money;
        private int            card_type;
        private String            card_id;
        private String         card_number;
        private List<ListBean> list;

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

        public int getCard_type() {
            return card_type;
        }

        public void setCard_type(int card_type) {
            this.card_type = card_type;
        }
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {

            private String id;
            private String user_id;
            private String date_time;
            private String area;
            private String act;
            private String code;
            private String fen;
            private String money;
            private String handled;
            private String carno;
            private String city;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getDate_time() {
                return date_time;
            }

            public void setDate_time(String date_time) {
                this.date_time = date_time;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAct() {
                return act;
            }

            public void setAct(String act) {
                this.act = act;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
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

            public String getHandled() {
                return handled;
            }

            public void setHandled(String handled) {
                this.handled = handled;
            }

            public String getCarno() {
                return carno;
            }

            public void setCarno(String carno) {
                this.carno = carno;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
