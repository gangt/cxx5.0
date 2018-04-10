package com.xi6666.classification.view.fragment.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午2:08.
 * 个人公众号 ardays
 */

public class ServiceClassificationBrandBean extends BaseBean {
    public List<DataBean> data;


    public class DataBean {
        public String title;
        public List<ListBean> list;


        public class ListBean {
            public String img;
            public String brand_id;
            public String brand_name;

            @Override
            public String toString() {
                return "ListBean{" +
                        "img='" + img + '\'' +
                        ", brand_id='" + brand_id + '\'' +
                        ", brand_name='" + brand_name + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "title='" + title + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ServiceClassificationBrandBean{" +
                "data=" + data +
                '}';
    }
}
