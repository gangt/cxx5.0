package com.xi6666.illegal.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Huang Yingde on 2016/6/20 0020.
 */
public class FindResultBean2 implements Serializable {

    private boolean success;
    private String   info;
    private int      code;
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
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public static class DataBean implements Serializable {

        private String         carno;
        private String         count;
        private String         fen;
        private String         money;
        private String         city_name;
        private String         city_code;
        private int            service_cate_id;
        private List<ListBean> list;

        public String getCarno() {
            return carno;
        }

        public void setCarno(String carno) {
            this.carno = carno;
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

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }
        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public int getService_cate_id() {
            return service_cate_id;
        }

        public void setService_cate_id(int service_cate_id) {
            this.service_cate_id = service_cate_id;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {

            private String date_time;
            private String area;
            private String act;
            private String fen;
            private String money;
            private String handled;
            private String log_id;

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
            public String getLog_id() {
                return log_id;
            }

            public void setLog_id(String log_id) {
                this.log_id = log_id;
            }
        }
    }
}
