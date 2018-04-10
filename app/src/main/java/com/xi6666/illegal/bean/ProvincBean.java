package com.xi6666.illegal.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class ProvincBean implements Serializable {

    private boolean        success;
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
         * province : 北京
         * province_code : BJ
         * citys : [{"city_name":"北京","city_code":"BJ","abbr":"京","engine":"1","engineno":"0","classa":"0","class":"0","classno":"0","regist":"0","registno":"0"}]
         */

        private String          province;
        private String          province_code;
        private List<CitysBean> citys;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public List<CitysBean> getCitys() {
            return citys;
        }

        public void setCitys(List<CitysBean> citys) {
            this.citys = citys;
        }

        public static class CitysBean {
            /**
             * city_name : 北京
             * city_code : BJ
             * abbr : 京
             * engine : 1
             * engineno : 0
             * classa : 0
             * class : 0
             * classno : 0
             * regist : 0
             * registno : 0
             */

            private String city_name;
            private String city_code;
            private String abbr;
            private String engine;
            private String engineno;
            private String classa;
            @SerializedName("class")
            private String classX;
            private String classno;
            private String regist;
            private String registno;

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

            public String getAbbr() {
                return abbr;
            }

            public void setAbbr(String abbr) {
                this.abbr = abbr;
            }

            public String getEngine() {
                return engine;
            }

            public void setEngine(String engine) {
                this.engine = engine;
            }

            public String getEngineno() {
                return engineno;
            }

            public void setEngineno(String engineno) {
                this.engineno = engineno;
            }

            public String getClassa() {
                return classa;
            }

            public void setClassa(String classa) {
                this.classa = classa;
            }

            public String getClassX() {
                return classX;
            }

            public void setClassX(String classX) {
                this.classX = classX;
            }

            public String getClassno() {
                return classno;
            }

            public void setClassno(String classno) {
                this.classno = classno;
            }

            public String getRegist() {
                return regist;
            }

            public void setRegist(String regist) {
                this.regist = regist;
            }

            public String getRegistno() {
                return registno;
            }

            public void setRegistno(String registno) {
                this.registno = registno;
            }
        }
    }
}
