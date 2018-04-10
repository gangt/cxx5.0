package com.xi6666.evaluate.bean;

import java.util.List;

/**
 * 作者： qsdsn on 2016/12/3
 * 描述：${DESC}
 */

public class UploadBean {

    private boolean success;
    private String       info;
    private String       version;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
