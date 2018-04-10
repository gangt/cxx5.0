package com.xi6666.cardbag.view.mvp.bean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/22 上午10:50.
 * 个人公众号 ardays
 */

public class OilCardListBean {
    /*
        `card_id` // '编号',

        `user_id` // '用户编号',

        `card_name` // '加油卡姓名',

        `card_number` // '加油卡编号',

        `card_type` //'加油卡类型 1 中石化 2中石油',

        `card_status` // '0 未验证 1 可用 2 不可用',

        `user_mobile` // '通知手机',

        `is_default`// '默认 1',

        `add_datetime` // '添加日期',
     */
    public boolean success;
    public String info;
    public List<DataBean> data;

    public class DataBean{
        public String card_id;
        public String card_name;
        public String card_number;
        public String card_type;
        public String card_status;
        public String user_mobile;
        public String is_default;

        @Override
        public String toString() {
            return "DataBean{" +
                    "card_id='" + card_id + '\'' +
                    ", card_name='" + card_name + '\'' +
                    ", card_number='" + card_number + '\'' +
                    ", card_type='" + card_type + '\'' +
                    ", card_status='" + card_status + '\'' +
                    ", user_mobile='" + user_mobile + '\'' +
                    ", is_default='" + is_default + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AddOilCardBean{" +
                "success=" + success +
                ", info='" + info + '\'' +
                ", data=" + data +
                '}';
    }
}
