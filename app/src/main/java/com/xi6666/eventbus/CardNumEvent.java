package com.xi6666.eventbus;

/**
 * Created by Mr_yang on 2016/12/5.
 */

public class CardNumEvent {
    private String msg;

    public CardNumEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
