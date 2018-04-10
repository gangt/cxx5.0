package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/2.
 */

public class MessageBean {
    /**
     * count : 25
     * data : [{"create_time":"2016-11-30 19:55:00","msg_text":"发广告哈哈哈哈","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-30 18:18:39","msg_text":"其实","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:25","msg_text":"ongoing","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:25","msg_text":"ongoing","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:25","msg_text":"ongoing","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:24","msg_text":"ongoing","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:17","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:17","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:17","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:17","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:17","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:17","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:16","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:16","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"},{"create_time":"2016-11-29 17:34:14","msg_text":"听不错的。","msg_title":"用户反馈信息","msg_type":"6"}]
     * info : 获取系统消息成功
     * success : true
     */

    private String count;
    private String info;
    private boolean success;
    /**
     * create_time : 2016-11-30 19:55:00
     * msg_text : 发广告哈哈哈哈
     * msg_title : 用户反馈信息
     * msg_type : 6
     */

    private List<DataBean> data;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String create_time;
        private String msg_text;
        private String msg_title;
        private String msg_type;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMsg_text() {
            return msg_text;
        }

        public void setMsg_text(String msg_text) {
            this.msg_text = msg_text;
        }

        public String getMsg_title() {
            return msg_title;
        }

        public void setMsg_title(String msg_title) {
            this.msg_title = msg_title;
        }

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }
    }
}
