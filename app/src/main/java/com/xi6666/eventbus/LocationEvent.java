package com.xi6666.eventbus;

/**
 * Created by Mr_yang on 2016/12/7.
 */

public class LocationEvent {
    private String msg;

    public LocationEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
