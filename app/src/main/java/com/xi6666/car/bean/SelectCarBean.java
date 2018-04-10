package com.xi6666.car.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:24.
 * 个人公众号 ardays
 */

public class SelectCarBean {

    public boolean success;
    public String info;
    public DataBean data;

    public class DataBean{

        public List<CharBean> A;
        public List<CharBean> B;
        public List<CharBean> C;
        public List<CharBean> D;
        public List<CharBean> E;
        public List<CharBean> F;
        public List<CharBean> G;
        public List<CharBean> H;
        public List<CharBean> I;
        public List<CharBean> J;
        public List<CharBean> K;
        public List<CharBean> L;
        public List<CharBean> M;
        public List<CharBean> N;
        public List<CharBean> O;
        public List<CharBean> P;
        public List<CharBean> Q;
        public List<CharBean> R;
        public List<CharBean> S;
        public List<CharBean> T;
        public List<CharBean> U;
        public List<CharBean> V;
        public List<CharBean> W;
        public List<CharBean> X;
        public List<CharBean> Y;
        public List<CharBean> Z;
        public class CharBean{

            public String brand_number;
            public String brand_name;
            public String brand_logo;
            public String brand_char;

            private String cx_id;
            private String cx_name;
            private String cx_desc;
            private String cx_images;
            private String parent_id;
            private String sort_order;
            private String is_show;
            private String firstchar;
            private String add_time;
            private String valueid;
            private String propertyId;
            private String level;
            private String cx_img;

            @Override
            public String toString() {
                return "CharBean{" +
                        "cx_id='" + cx_id + '\'' +
                        ", cx_name='" + cx_name + '\'' +
                        ", cx_desc='" + cx_desc + '\'' +
                        ", cx_images='" + cx_images + '\'' +
                        ", parent_id='" + parent_id + '\'' +
                        ", sort_order='" + sort_order + '\'' +
                        ", is_show='" + is_show + '\'' +
                        ", firstchar='" + firstchar + '\'' +
                        ", add_time='" + add_time + '\'' +
                        ", valueid='" + valueid + '\'' +
                        ", propertyId='" + propertyId + '\'' +
                        ", level='" + level + '\'' +
                        ", cx_img='" + cx_img + '\'' +
                        '}';
            }
        }
    }
}
