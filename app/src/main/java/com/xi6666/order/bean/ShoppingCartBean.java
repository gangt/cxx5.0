package com.xi6666.order.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class ShoppingCartBean {

    private boolean        success;
    private String         info;
    private String         version;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String              active_type_name;
        private String              active_type;
        private String              active_name;
        private String              active_discount;
        private String              active_desc;
        private String              active_money;
        private String              active_id;
        private List<GoodsListBean> goods_list;

        public String getActive_type_name() {
            return active_type_name;
        }

        public void setActive_type_name(String active_type_name) {
            this.active_type_name = active_type_name;
        }

        public String getActive_type() {
            return active_type;
        }

        public void setActive_type(String active_type) {
            this.active_type = active_type;
        }

        public String getActive_name() {
            return active_name;
        }

        public void setActive_name(String active_name) {
            this.active_name = active_name;
        }

        public String getActive_discount() {
            return active_discount;
        }

        public void setActive_discount(String active_discount) {
            this.active_discount = active_discount;
        }

        public String getActive_desc() {
            return active_desc;
        }

        public void setActive_desc(String active_desc) {
            this.active_desc = active_desc;
        }

        public String getActive_money() {
            return active_money;
        }

        public void setActive_money(String active_money) {
            this.active_money = active_money;
        }

        public String getActive_id() {
            return active_id;
        }

        public void setActive_id(String active_id) {
            this.active_id = active_id;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {

            private String active_type_name;
            private String active_type;
            private String active_name;
            private String active_discount;
            private String active_desc;
            private String active_money;
            private String active_id;
            private String user_id;
            private String goods_number;
            private String goods_id;
            private String goods_name;
            private String sku1_id;
            private String sku1_value;
            private String sku1_name;
            private String sku2_id;
            private String sku2_value;
            private String sku2_name;
            private String sku_value_id;
            private String new_shop_price;
            private String new_sku_shop_price;
            private String goods_thumb_img;
            private String goods_stock_number;
            private String market_price;
            private int    is_on_sale;
            private String goods_soure_img;
            private String sku_market_price;
            private String shop_price;
            private double reduce_price;
            private double add_price;
            private boolean isSelect = true;

            public String getActive_type_name() {
                return active_type_name;
            }

            public void setActive_type_name(String active_type_name) {
                this.active_type_name = active_type_name;
            }

            public String getActive_type() {
                return active_type;
            }

            public void setActive_type(String active_type) {
                this.active_type = active_type;
            }

            public String getActive_name() {
                return active_name;
            }

            public void setActive_name(String active_name) {
                this.active_name = active_name;
            }

            public String getActive_discount() {
                return active_discount;
            }

            public void setActive_discount(String active_discount) {
                this.active_discount = active_discount;
            }

            public String getActive_desc() {
                return active_desc;
            }

            public void setActive_desc(String active_desc) {
                this.active_desc = active_desc;
            }

            public String getActive_money() {
                return active_money;
            }

            public void setActive_money(String active_money) {
                this.active_money = active_money;
            }

            public String getActive_id() {
                return active_id;
            }

            public void setActive_id(String active_id) {
                this.active_id = active_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getSku1_id() {
                return sku1_id;
            }

            public void setSku1_id(String sku1_id) {
                this.sku1_id = sku1_id;
            }

            public String getSku1_value() {
                return sku1_value;
            }

            public void setSku1_value(String sku1_value) {
                this.sku1_value = sku1_value;
            }

            public String getSku1_name() {
                return sku1_name;
            }

            public void setSku1_name(String sku1_name) {
                this.sku1_name = sku1_name;
            }

            public String getSku2_id() {
                return sku2_id;
            }

            public void setSku2_id(String sku2_id) {
                this.sku2_id = sku2_id;
            }

            public String getSku2_value() {
                return sku2_value;
            }

            public void setSku2_value(String sku2_value) {
                this.sku2_value = sku2_value;
            }

            public String getSku2_name() {
                return sku2_name;
            }

            public void setSku2_name(String sku2_name) {
                this.sku2_name = sku2_name;
            }

            public String getSku_value_id() {
                return sku_value_id;
            }

            public void setSku_value_id(String sku_value_id) {
                this.sku_value_id = sku_value_id;
            }

            public String getNew_shop_price() {
                return new_shop_price;
            }

            public void setNew_shop_price(String new_shop_price) {
                this.new_shop_price = new_shop_price;
            }

            public String getNew_sku_shop_price() {
                return new_sku_shop_price;
            }

            public void setNew_sku_shop_price(String new_sku_shop_price) {
                this.new_sku_shop_price = new_sku_shop_price;
            }

            public String getGoods_thumb_img() {
                return goods_thumb_img;
            }

            public void setGoods_thumb_img(String goods_thumb_img) {
                this.goods_thumb_img = goods_thumb_img;
            }

            public String getGoods_stock_number() {
                return goods_stock_number;
            }

            public void setGoods_stock_number(String goods_stock_number) {
                this.goods_stock_number = goods_stock_number;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public String getGoods_soure_img() {
                return goods_soure_img;
            }

            public void setGoods_soure_img(String goods_soure_img) {
                this.goods_soure_img = goods_soure_img;
            }

            public String getSku_market_price() {
                return sku_market_price;
            }

            public void setSku_market_price(String sku_market_price) {
                this.sku_market_price = sku_market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public double getReduce_price() {
                return reduce_price;
            }

            public void setReduce_price(double reduce_price) {
                this.reduce_price = reduce_price;
            }

            public double getAdd_price() {
                return add_price;
            }

            public void setAdd_price(double add_price) {
                this.add_price = add_price;
            }

            public boolean getIsSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }
        }
    }
}
