package com.xi6666.eventbus;

/**
 * Created by Mr_yang on 2017/2/9.
 */

public class IllegaCardChangeEvent {
    private String mMsg;

    public IllegaCardChangeEvent(String msg) {
        this.mMsg = msg;
    }

    public String getItem() {
        return mMsg;
    }
}
