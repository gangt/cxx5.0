package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/25.
 */

public class HomeBannerBean {

    /**
     * success : true
     * info : 获取首页参数成功
     * index_block : [{"id":"1","icon_name":"7.9折加油","icon_position":"0101","icon_img":"","icon_url":null},{"id":"2","icon_name":"特惠洗车","icon_position":"0102","icon_img":"","icon_url":null},{"id":"3","icon_name":"车品商城","icon_position":"0103","icon_img":"","icon_url":null}]
     * index_icon : [{"id":"4","icon_name":"保养","icon_position":"0201","icon_img":"","icon_url":null},{"id":"5","icon_name":"美容","icon_position":"0202","icon_img":"","icon_url":null},{"id":"6","icon_name":"违章处理","icon_position":"0203","icon_img":"","icon_url":null},{"id":"7","icon_name":"代驾","icon_position":"0204","icon_img":"","icon_url":null},{"id":"8","icon_name":"换机油","icon_position":"0301","icon_img":"","icon_url":null},{"id":"9","icon_name":"买轮胎","icon_position":"0302","icon_img":"","icon_url":null},{"id":"10","icon_name":"换刹车片","icon_position":"0303","icon_img":"","icon_url":null},{"id":"11","icon_name":"雨刮器","icon_position":"0304","icon_img":"","icon_url":null},{"id":"13","icon_name":"小喜夺宝","icon_position":"0401","icon_img":"","icon_url":null},{"id":"14","icon_name":"喜豆专区","icon_position":"0402","icon_img":"","icon_url":null},{"id":"15","icon_name":"0元秒杀","icon_position":"0403","icon_img":"","icon_url":null},{"id":"16","icon_name":"冲200送100","icon_position":"0404","icon_img":"","icon_url":null}]
     */

    private boolean success;
    private String info;
    /**
     * id : 1
     * icon_name : 7.9折加油
     * icon_position : 0101
     * icon_img :
     * icon_url : null
     */

    private List<IndexBlockBean> index_block;
    /**
     * id : 4
     * icon_name : 保养
     * icon_position : 0201
     * icon_img :
     * icon_url : null
     */

    private List<IndexIconBean> index_icon;

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

    public List<IndexBlockBean> getIndex_block() {
        return index_block;
    }

    public void setIndex_block(List<IndexBlockBean> index_block) {
        this.index_block = index_block;
    }

    public List<IndexIconBean> getIndex_icon() {
        return index_icon;
    }

    public void setIndex_icon(List<IndexIconBean> index_icon) {
        this.index_icon = index_icon;
    }

    public static class IndexBlockBean {
        private String id;
        private String icon_name;
        private String icon_position;
        private String icon_img;
        private Object icon_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon_name() {
            return icon_name;
        }

        public void setIcon_name(String icon_name) {
            this.icon_name = icon_name;
        }

        public String getIcon_position() {
            return icon_position;
        }

        public void setIcon_position(String icon_position) {
            this.icon_position = icon_position;
        }

        public String getIcon_img() {
            return icon_img;
        }

        public void setIcon_img(String icon_img) {
            this.icon_img = icon_img;
        }

        public Object getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(Object icon_url) {
            this.icon_url = icon_url;
        }
    }

    public static class IndexIconBean {
        private String id;
        private String icon_name;
        private String icon_position;
        private String icon_img;
        private Object icon_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon_name() {
            return icon_name;
        }

        public void setIcon_name(String icon_name) {
            this.icon_name = icon_name;
        }

        public String getIcon_position() {
            return icon_position;
        }

        public void setIcon_position(String icon_position) {
            this.icon_position = icon_position;
        }

        public String getIcon_img() {
            return icon_img;
        }

        public void setIcon_img(String icon_img) {
            this.icon_img = icon_img;
        }

        public Object getIcon_url() {
            return icon_url;
        }

        public void setIcon_url(Object icon_url) {
            this.icon_url = icon_url;
        }
    }
}
