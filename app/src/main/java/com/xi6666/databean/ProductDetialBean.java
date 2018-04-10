package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/25.
 */

public class ProductDetialBean {

    /**
     * cart_num : 0
     * good_para_url : http://app.xiaoxi6.com//index.php/shop/get_goods_para?goods_id=45244
     * goods_id : 45244
     * goods_info : {"goods_name":"马勒KL765汽油滤清器适用于别克君越2.4/3.0 GL8汽油格滤芯","goods_number":"45","goods_thumb_img":"http://www.xi6666.com/234","is_on_sale":1,"market_price":"49.00","shop_price":"12.00"}
     * goods_pics : ["http://www.xi6666.com/images/201611/goods_img/43171_P_1478891734640.jpg","http://www.xi6666.com/images/201611/goods_img/43171_P_1478891734640.jpg","http://www.xi6666.com/images/201611/goods_img/43171_P_1478891734221.jpg"]
     * is_collect : false
     * pic_url : http://app.xiaoxi6.com//index.php/shop/pic_detail?goods_id=45244
     * sku_info : {"sku1_list":{"list":[{"is_selected":1,"sku1_name":"红色","sku_value_id":"1"}],"sku_name":"颜色"},"sku2_list":{"list":[{"is_selected":1,"sku1_name":"M码","sku_value_id":"1"}],"sku_name":"尺寸"}}
     */

    private DataBean data;
    /**
     * data : {"cart_num":0,"good_para_url":"http://app.xiaoxi6.com//index.php/shop/get_goods_para?goods_id=45244","goods_id":"45244","goods_info":{"goods_name":"马勒KL765汽油滤清器适用于别克君越2.4/3.0 GL8汽油格滤芯","goods_number":"45","goods_thumb_img":"http://www.xi6666.com/234","is_on_sale":1,"market_price":"49.00","shop_price":"12.00"},"goods_pics":["http://www.xi6666.com/images/201611/goods_img/43171_P_1478891734640.jpg","http://www.xi6666.com/images/201611/goods_img/43171_P_1478891734640.jpg","http://www.xi6666.com/images/201611/goods_img/43171_P_1478891734221.jpg"],"is_collect":false,"pic_url":"http://app.xiaoxi6.com//index.php/shop/pic_detail?goods_id=45244","sku_info":{"sku1_list":{"list":[{"is_selected":1,"sku1_name":"红色","sku_value_id":"1"}],"sku_name":"颜色"},"sku2_list":{"list":[{"is_selected":1,"sku1_name":"M码","sku_value_id":"1"}],"sku_name":"尺寸"}}}
     * info : 获取成功
     * success : true
     * version : 5
     */

    private String info;
    private boolean success;
    private String version;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static class DataBean {
        private int cart_num;
        private String good_para_url;
        private String goods_id;
        /**
         * goods_name : 马勒KL765汽油滤清器适用于别克君越2.4/3.0 GL8汽油格滤芯
         * goods_number : 45
         * goods_thumb_img : http://www.xi6666.com/234
         * is_on_sale : 1
         * market_price : 49.00
         * shop_price : 12.00
         */

        private GoodsInfoBean goods_info;
        private boolean is_collect;
        private String pic_url;
        /**
         * sku1_list : {"list":[{"is_selected":1,"sku1_name":"红色","sku_value_id":"1"}],"sku_name":"颜色"}
         * sku2_list : {"list":[{"is_selected":1,"sku1_name":"M码","sku_value_id":"1"}],"sku_name":"尺寸"}
         */

        private SkuInfoBean sku_info;
        private List<String> goods_pics;

        public int getCart_num() {
            return cart_num;
        }

        public void setCart_num(int cart_num) {
            this.cart_num = cart_num;
        }

        public String getGood_para_url() {
            return good_para_url;
        }

        public void setGood_para_url(String good_para_url) {
            this.good_para_url = good_para_url;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public GoodsInfoBean getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(GoodsInfoBean goods_info) {
            this.goods_info = goods_info;
        }

        public boolean isIs_collect() {
            return is_collect;
        }

        public void setIs_collect(boolean is_collect) {
            this.is_collect = is_collect;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public SkuInfoBean getSku_info() {
            return sku_info;
        }

        public void setSku_info(SkuInfoBean sku_info) {
            this.sku_info = sku_info;
        }

        public List<String> getGoods_pics() {
            return goods_pics;
        }

        public void setGoods_pics(List<String> goods_pics) {
            this.goods_pics = goods_pics;
        }

        public static class GoodsInfoBean {
            private String goods_name;
            private String goods_number;
            private String goods_thumb_img;
            private int is_on_sale;
            private String market_price;
            private String shop_price;

            private String add_datetime;
            private String goods_id;
            private String goods_img;

            private String goods_price;
            private String goods_seckill_price;
            private String goods_show_number;
            private String goods_sn;
            private String goods_true_number;

            private String seckill_id;
            private String seckill_stage;
            private String seckill_status;
            private String start_datetime;

            public void setAdd_datetime(String add_datetime) {
                this.add_datetime = add_datetime;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_seckill_price(String goods_seckill_price) {
                this.goods_seckill_price = goods_seckill_price;
            }

            public void setGoods_show_number(String goods_show_number) {
                this.goods_show_number = goods_show_number;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public void setGoods_true_number(String goods_true_number) {
                this.goods_true_number = goods_true_number;
            }

            public void setSeckill_id(String seckill_id) {
                this.seckill_id = seckill_id;
            }

            public void setSeckill_stage(String seckill_stage) {
                this.seckill_stage = seckill_stage;
            }

            public void setSeckill_status(String seckill_status) {
                this.seckill_status = seckill_status;
            }

            public void setStart_datetime(String start_datetime) {
                this.start_datetime = start_datetime;
            }


            public String getAdd_datetime() {
                return add_datetime;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public String getGoods_seckill_price() {
                return goods_seckill_price;
            }

            public String getGoods_show_number() {
                return goods_show_number;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public String getGoods_true_number() {
                return goods_true_number;
            }

            public String getSeckill_id() {
                return seckill_id;
            }

            public String getSeckill_stage() {
                return seckill_stage;
            }

            public String getSeckill_status() {
                return seckill_status;
            }

            public String getStart_datetime() {
                return start_datetime;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(String goods_number) {
                this.goods_number = goods_number;
            }

            public String getGoods_thumb_img() {
                return goods_thumb_img;
            }

            public void setGoods_thumb_img(String goods_thumb_img) {
                this.goods_thumb_img = goods_thumb_img;
            }

            public int getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(int is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }
        }

        public static class SkuInfoBean {
            /**
             * list : [{"is_selected":1,"sku1_name":"红色","sku_value_id":"1"}]
             * sku_name : 颜色
             */

            private Sku1ListBean sku1_list;
            /**
             * list : [{"is_selected":1,"sku1_name":"M码","sku_value_id":"1"}]
             * sku_name : 尺寸
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
                 * is_selected : 1
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
                 * sku1_name : M码
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
        }
    }
}
