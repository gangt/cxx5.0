package com.xi6666.evaluate.bean;

import java.util.List;

/**
 * 作者： qsdsn on 2016/11/28
 * 描述：${DESC}
 */

public class StoreServiceBean {

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

        private ShopInfoBean shop_info;
        private List<String>               photo_list;
        private List<ServiceListBean> service_list;

        public ShopInfoBean getShop_info() {
            return shop_info;
        }

        public void setShop_info(ShopInfoBean shop_info) {
            this.shop_info = shop_info;
        }

        public List<String> getPhoto_list() {
            return photo_list;
        }

        public void setPhoto_list(List<String> photo_list) {
            this.photo_list = photo_list;
        }

        public List<ServiceListBean> getService_list() {
            return service_list;
        }

        public void setService_list(List<ServiceListBean> service_list) {
            this.service_list = service_list;
        }

        public static class ShopInfoBean {

            private String user_id;
            private String shop_name;
            private String shop_address;
            private String shop_opentime;
            private String shop_closetime;
            private String shop_banner;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_address() {
                return shop_address;
            }

            public void setShop_address(String shop_address) {
                this.shop_address = shop_address;
            }

            public String getShop_opentime() {
                return shop_opentime;
            }

            public void setShop_opentime(String shop_opentime) {
                this.shop_opentime = shop_opentime;
            }

            public String getShop_closetime() {
                return shop_closetime;
            }

            public void setShop_closetime(String shop_closetime) {
                this.shop_closetime = shop_closetime;
            }

            public String getShop_banner() {
                return shop_banner;
            }

            public void setShop_banner(String shop_banner) {
                this.shop_banner = shop_banner;
            }
        }

        public static class ServiceListBean {

            private String service_id;
            private String service_name;
            private String service_price;
            private String cate_name;

            public String getService_id() {
                return service_id;
            }

            public void setService_id(String service_id) {
                this.service_id = service_id;
            }

            public String getService_name() {
                return service_name;
            }

            public void setService_name(String service_name) {
                this.service_name = service_name;
            }

            public String getService_price() {
                return service_price;
            }

            public void setService_price(String service_price) {
                this.service_price = service_price;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }
        }
    }
}
