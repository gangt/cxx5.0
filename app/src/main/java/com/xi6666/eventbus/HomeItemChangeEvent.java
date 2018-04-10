package com.xi6666.eventbus;

/**
 * Created by Mr_yang on 2016/12/10.
 */

public class HomeItemChangeEvent {
    private int item;

    public HomeItemChangeEvent(int item) {
        this.item = item;
    }

    public int getItem() {
        return item;
    }
}
