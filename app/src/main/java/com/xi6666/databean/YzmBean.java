package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/7.
 */

public class YzmBean {
    /**
     * success : true
     * info : 发送成功
     * jumpurl :
     * data : []
     */

    private String success;
    private String info;
    private String jumpurl;
    private List<?> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
