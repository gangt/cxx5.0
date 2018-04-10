package com.xi6666.classification.view.fragment.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午12:30.
 * 个人公众号 ardays
 */

public class ServiceClassificationBean extends BaseBean {

    public List<DataBean> data;

    public class DataBean {
        public String cate_sort;
        public String cate_name;
        public List<ChildBean> child;


        public class ChildBean {
            public String cate_id;
            public String cate_name;
            public String parent_id;
            public String cate_sort;

            @Override
            public String toString() {
                return "ChildBean{" +
                        "cate_id='" + cate_id + '\'' +
                        ", cate_name='" + cate_name + '\'' +
                        ", parent_id='" + parent_id + '\'' +
                        ", cate_sort='" + cate_sort + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "cate_sort='" + cate_sort + '\'' +
                    ", cate_name='" + cate_name + '\'' +
                    ", child=" + child +
                    '}';
        }
    }

    @Override
    public String
    toString() {
        return "ServiceClassificationBean{" +
                "data=" + data +
                '}';
    }
}
