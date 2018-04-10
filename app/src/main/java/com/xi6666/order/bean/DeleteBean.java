package com.xi6666.order.bean;

/**
 * 作者： qsdsn on 2016/11/25
 * 描述：${DESC}
 */

public class DeleteBean {
    private String goods_id;
    private String sku_value_id;


    public String getSku_value_id() {
        return sku_value_id;
    }

    public void setSku_value_id(String sku_value_id) {
        this.sku_value_id = sku_value_id;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public DeleteBean(String goods_id, String sku_value_id) {
        this.goods_id = goods_id;
        this.sku_value_id = sku_value_id;
    }
}
