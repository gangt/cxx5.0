package com.xi6666.databean;

import java.util.List;

/**
 * Created by Mr_yang on 2016/11/25.
 */

public class HomeSpecialBean {

    /**
     * data : [{"goods_list":[{"goods_id":"43159","goods_name":"曼牌机油滤清器W712/83陆巡普拉多4.0 4.7 GS430 LX470 RX300机滤","goods_thumb_img":"images/201611/thumb_img/43159_thumb_G_1478831775023.jpg","shop_price":"43.00"}],"zt_id":"1","zt_img":"http://www.xi6666.com/xiaoxiv4/img/index/huodong2.jpg","zt_name":"壳牌加油","zt_url":""},{"goods_list":[{"goods_id":"43159","goods_name":"曼牌机油滤清器W712/83陆巡普拉多4.0 4.7 GS430 LX470 RX300机滤","goods_thumb_img":"images/201611/thumb_img/43159_thumb_G_1478831775023.jpg","shop_price":"43.00"}],"zt_id":"2","zt_img":"http://www.xi6666.com/xiaoxiv4/img/index/huodong3.jpg","zt_name":"美途宝","zt_url":""},{"goods_list":[],"zt_id":"3","zt_img":"http://www.xi6666.com/xiaoxiv4/img/index/huodong1.jpg","zt_name":"亿高","zt_url":""}]
     * info : 获取获取热销商品列表成功
     * success : true
     */

    private String info;
    private boolean success;
    /**
     * goods_list : [{"goods_id":"43159","goods_name":"曼牌机油滤清器W712/83陆巡普拉多4.0 4.7 GS430 LX470 RX300机滤","goods_thumb_img":"images/201611/thumb_img/43159_thumb_G_1478831775023.jpg","shop_price":"43.00"}]
     * zt_id : 1
     * zt_img : http://www.xi6666.com/xiaoxiv4/img/index/huodong2.jpg
     * zt_name : 壳牌加油
     * zt_url :
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String zt_id;
        private String zt_img;
        private String zt_name;
        private String zt_url;
        /**
         * goods_id : 43159
         * goods_name : 曼牌机油滤清器W712/83陆巡普拉多4.0 4.7 GS430 LX470 RX300机滤
         * goods_thumb_img : images/201611/thumb_img/43159_thumb_G_1478831775023.jpg
         * shop_price : 43.00
         */

        private List<GoodsListBean> goods_list;

        public String getZt_id() {
            return zt_id;
        }

        public void setZt_id(String zt_id) {
            this.zt_id = zt_id;
        }

        public String getZt_img() {
            return zt_img;
        }

        public void setZt_img(String zt_img) {
            this.zt_img = zt_img;
        }

        public String getZt_name() {
            return zt_name;
        }

        public void setZt_name(String zt_name) {
            this.zt_name = zt_name;
        }

        public String getZt_url() {
            return zt_url;
        }

        public void setZt_url(String zt_url) {
            this.zt_url = zt_url;
        }

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            private String goods_id;
            private String goods_name;
            private String goods_thumb_img;
            private String shop_price;

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

            public String getGoods_thumb_img() {
                return goods_thumb_img;
            }

            public void setGoods_thumb_img(String goods_thumb_img) {
                this.goods_thumb_img = goods_thumb_img;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }
        }
    }
}
