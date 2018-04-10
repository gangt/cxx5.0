package com.xi6666.eventbus;


/**
 * @author peng
 * @data 创建时间:2016/11/24
 * @desc 地址事件
 */
public class AddressEvent {
    private String msg;

    public AddressEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
