package com.xi6666.technician.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午2:16.
 * 个人公众号 ardays
 */

public class TechnicianDetailsBean extends BaseBean {
    public DataBean data;

    public class DataBean {
        public String js_desc;
        public String user_truename;
        public String user_face;
        public int wenda_count;
        public List<WendaInfoBean> wenda_info;

        public class WendaInfoBean {
            public String user_id;
            public String shop_user_id;
            public String js_user_id;
            public String ques_id;
            public String ques_content;
            public String ques_zan_num;
            public String anwser_content;
            public String add_datetime;
            public String user_mobile;
            public String zandot;
            public List<String> pl_pics;
        }
    }
}
