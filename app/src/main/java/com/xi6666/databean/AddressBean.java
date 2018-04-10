package com.xi6666.databean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mr_yang on 2016/11/11.
 */

public class AddressBean implements Serializable{

    

    private String info;
    private boolean success;
    private String version;
    /**
     * citys : [{"distinct":[{"region_id":"500","region_name":"东城区"},{"region_id":"501","region_name":"西城区"},{"region_id":"502","region_name":"海淀区"},{"region_id":"503","region_name":"朝阳区"},{"region_id":"504","region_name":"崇文区"},{"region_id":"505","region_name":"宣武区"},{"region_id":"506","region_name":"丰台区"},{"region_id":"507","region_name":"石景山区"},{"region_id":"508","region_name":"房山区"},{"region_id":"509","region_name":"门头沟区"},{"region_id":"510","region_name":"通州区"},{"region_id":"511","region_name":"顺义区"},{"region_id":"512","region_name":"昌平区"},{"region_id":"513","region_name":"怀柔区"},{"region_id":"514","region_name":"平谷区"},{"region_id":"515","region_name":"大兴区"},{"region_id":"516","region_name":"密云县"},{"region_id":"517","region_name":"延庆县"}],"region_id":"52","region_name":"北京"}]
     * region_id : 2
     * region_name : 北京
     */

    private List<DataBean> data;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public  class DataBean implements Serializable{
        private String region_id;
        private String region_name;
        /**
         * distinct : [{"region_id":"500","region_name":"东城区"},{"region_id":"501","region_name":"西城区"},{"region_id":"502","region_name":"海淀区"},{"region_id":"503","region_name":"朝阳区"},{"region_id":"504","region_name":"崇文区"},{"region_id":"505","region_name":"宣武区"},{"region_id":"506","region_name":"丰台区"},{"region_id":"507","region_name":"石景山区"},{"region_id":"508","region_name":"房山区"},{"region_id":"509","region_name":"门头沟区"},{"region_id":"510","region_name":"通州区"},{"region_id":"511","region_name":"顺义区"},{"region_id":"512","region_name":"昌平区"},{"region_id":"513","region_name":"怀柔区"},{"region_id":"514","region_name":"平谷区"},{"region_id":"515","region_name":"大兴区"},{"region_id":"516","region_name":"密云县"},{"region_id":"517","region_name":"延庆县"}]
         * region_id : 52
         * region_name : 北京
         */

        private List<CitysBean> citys;

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public List<CitysBean> getCitys() {
            return citys;
        }

        public void setCitys(List<CitysBean> citys) {
            this.citys = citys;
        }

        public  class CitysBean implements Serializable{
            private String region_id;
            private String region_name;
            /**
             * region_id : 500
             * region_name : 东城区
             */

            private List<DistinctBean> distinct;

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public List<DistinctBean> getDistinct() {
                return distinct;
            }

            public void setDistinct(List<DistinctBean> distinct) {
                this.distinct = distinct;
            }

            public  class DistinctBean implements Serializable{
                private String region_id;
                private String region_name;

                public String getRegion_id() {
                    return region_id;
                }

                public void setRegion_id(String region_id) {
                    this.region_id = region_id;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }
            }
        }
    }
}
