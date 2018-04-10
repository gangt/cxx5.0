package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/25.
 */

public class ProductSkuBean {

    /**
     * success : true
     * info : 获取成功
     * data : {"goods_info":{"goods_number":"45","goods_name":"马勒KL765汽油滤清器适用于别克君越2.4/3.0 GL8汽油格滤芯","shop_price":"44.00","market_price":"49.00","is_on_sale":1,"goods_thumb_img":"http://www.xi6666.com/images/201611/thumb_img/43171_thumb_G_1478891734365.jpg"},"sku_info":{"sku1_list":{"sku_name":"颜色","list":[{"is_selected":0,"sku1_name":"红色","sku_value_id":"1"},{"is_selected":1,"sku1_name":"蓝色","sku_value_id":"5"},{"is_selected":0,"sku1_name":"黑色","sku_value_id":"3"}]},"sku2_list":{"sku_name":"尺寸","list":[{"is_selected":1,"sku1_name":"L码","sku_value_id":"2"},{"is_selected":0,"sku1_name":"K码","sku_value_id":"5"}]}}}
     * version : 5
     */

    private boolean success;
    private String info;
    /**
     * goods_info : {"goods_number":"45","goods_name":"马勒KL765汽油滤清器适用于别克君越2.4/3.0 GL8汽油格滤芯","shop_price":"44.00","market_price":"49.00","is_on_sale":1,"goods_thumb_img":"http://www.xi6666.com/images/201611/thumb_img/43171_thumb_G_1478891734365.jpg"}
     * sku_info : {"sku1_list":{"sku_name":"颜色","list":[{"is_selected":0,"sku1_name":"红色","sku_value_id":"1"},{"is_selected":1,"sku1_name":"蓝色","sku_value_id":"5"},{"is_selected":0,"sku1_name":"黑色","sku_value_id":"3"}]},"sku2_list":{"sku_name":"尺寸","list":[{"is_selected":1,"sku1_name":"L码","sku_value_id":"2"},{"is_selected":0,"sku1_name":"K码","sku_value_id":"5"}]}}
     */

    private DataBean data;
    private String version;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        /**
         * goods_number : 45
         * goods_name : 马勒KL765汽油滤清器适用于别克君越2.4/3.0 GL8汽油格滤芯
         * shop_price : 44.00
         * market_price : 49.00
         * is_on_sale : 1
         * goods_thumb_img : http://www.xi6666.com/images/201611/thumb_img/43171_thumb_G_1478891734365.jpg
         */

        private GoodsInfoBean goods_info;
        /**
         * sku1_list : {"sku_name":"颜色","list":[{"is_selected":0,"sku1_name":"红色","sku_value_id":"1"},{"is_selected":1,"sku1_name":"蓝色","sku_value_id":"5"},{"is_selected":0,"sku1_name":"黑色","sku_value_id":"3"}]}
         * sku2_list : {"sku_name":"尺寸","list":[{"is_selected":1,"sku1_name":"L码","sku_value_id":"2"},{"is_selected":0,"sku1_name":"K码","sku_value_id":"5"}]}
         */

        private SkuInfoBean sku_info;

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public SkuInfoBean getSku_info() {
            return sku_info;
        }

        public void setSku_info(SkuInfoBean sku_info) {
            this.sku_info = sku_info;
        }

        public static class GoodsInfoBean {
            private String goods_number;
            private String goods_name;
            private String shop_price;
            private String market_price;
            private int is_on_sale;
            private String goods_thumb_img;

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
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

            public String getGoods_thumb_img() {
                return goods_thumb_img;
            }

            public void setGoods_thumb_img(String goods_thumb_img) {
                this.goods_thumb_img = goods_thumb_img;
            }
        }

        public static class SkuInfoBean {
            /**
             * sku_name : 颜色
             * list : [{"is_selected":0,"sku1_name":"红色","sku_value_id":"1"},{"is_selected":1,"sku1_name":"蓝色","sku_value_id":"5"},{"is_selected":0,"sku1_name":"黑色","sku_value_id":"3"}]
             */

            private Sku1ListBean sku1_list;
            /**
             * sku_name : 尺寸
             * list : [{"is_selected":1,"sku1_name":"L码","sku_value_id":"2"},{"is_selected":0,"sku1_name":"K码","sku_value_id":"5"}]
             */

            private Sku2ListBean sku2_list;

            public Sku1ListBean getSku1_list() {
                return sku1_list;
            }

            public void setSku1_list(Sku1ListBean sku1_list) {
                this.sku1_list = sku1_list;
            }

            public Sku2ListBean getSku2_list() {
                return sku2_list;
            }

            public void setSku2_list(Sku2ListBean sku2_list) {
                this.sku2_list = sku2_list;
            }

            public static class Sku1ListBean {
                private String sku_name;
                /**
                 * is_selected : 0
                 * sku1_name : 红色
                 * sku_value_id : 1
                 */

                private List<ListBean> list;

                public String getSku_name() {
                    return sku_name;
                }

                public void setSku_name(String sku_name) {
                    this.sku_name = sku_name;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    private int is_selected;
                    private String sku1_name;
                    private String sku_value_id;

                    public int getIs_selected() {
                        return is_selected;
                    }

                    public void setIs_selected(int is_selected) {
                        this.is_selected = is_selected;
                    }

                    public String getSku1_name() {
                        return sku1_name;
                    }

                    public void setSku1_name(String sku1_name) {
                        this.sku1_name = sku1_name;
                    }

                    public String getSku_value_id() {
                        return sku_value_id;
                    }

                    public void setSku_value_id(String sku_value_id) {
                        this.sku_value_id = sku_value_id;
                    }
                }
            }

            public static class Sku2ListBean {
                private String sku_name;
                /**
                 * is_selected : 1
                 * sku1_name : L码
                 * sku_value_id : 2
                 */

                private List<ListBean> list;

                public String getSku_name() {
                    return sku_name;
                }

                public void setSku_name(String sku_name) {
                    this.sku_name = sku_name;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    private int is_selected;
                    private String sku1_name;
                    private String sku_value_id;

                    public int getIs_selected() {
                        return is_selected;
                    }

                    public void setIs_selected(int is_selected) {
                        this.is_selected = is_selected;
                    }

                    public String getSku1_name() {
                        return sku1_name;
                    }

                    public void setSku1_name(String sku1_name) {
                        this.sku1_name = sku1_name;
                    }

                    public String getSku_value_id() {
                        return sku_value_id;
                    }

                    public void setSku_value_id(String sku_value_id) {
                        this.sku_value_id = sku_value_id;
                    }
                }
            }
        }
    }
}
