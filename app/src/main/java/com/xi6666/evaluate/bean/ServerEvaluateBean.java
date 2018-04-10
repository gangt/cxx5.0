package com.xi6666.evaluate.bean;

import java.util.List;

/**
 * 作者： qsdsn on 2016/11/9
 * 描述：${DESC}
 */

public class ServerEvaluateBean {
    private boolean success;
    private String         info;
    private String         count;
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

        private String id;
        private String  shop_user_id;
        private String  service_cate_id;
        private String  service_order_sn;
        private String  comment_content;
        private String  comment_level;
        private String  add_datetime;
        private String  service_name;
        private String  shop_banner;
        private String  shop_name;
        private List<String> pl_pics;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShop_user_id() {
            return shop_user_id;
        }

        public void setShop_user_id(String shop_user_id) {
            this.shop_user_id = shop_user_id;
        }

        public String getService_cate_id() {
            return service_cate_id;
        }

        public void setService_cate_id(String service_cate_id) {
            this.service_cate_id = service_cate_id;
        }

        public String getService_order_sn() {
            return service_order_sn;
        }

        public void setService_order_sn(String service_order_sn) {
            this.service_order_sn = service_order_sn;
        }

        public String getComment_content() {
            return comment_content;
        }

        public void setComment_content(String comment_content) {
            this.comment_content = comment_content;
        }

        public String getComment_level() {
            return comment_level;
        }

        public void setComment_level(String comment_level) {
            this.comment_level = comment_level;
        }

        public String getAdd_datetime() {
            return add_datetime;
        }

        public void setAdd_datetime(String add_datetime) {
            this.add_datetime = add_datetime;
        }

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        public String getShop_banner() {
            return shop_banner;
        }

        public void setShop_banner(String shop_banner) {
            this.shop_banner = shop_banner;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public List<String> getPl_pics() {
            return pl_pics;
        }

        public void setPl_pics(List<String> pl_pics) {
            this.pl_pics = pl_pics;
        }
    }
}
