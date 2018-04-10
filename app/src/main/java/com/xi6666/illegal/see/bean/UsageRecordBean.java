package com.xi6666.illegal.see.bean;

import com.xi6666.carWash.base.network.BaseBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2017/2/8 上午10:06.
 * 个人公众号 ardays
 */

public class UsageRecordBean extends BaseBean {
    public List<DataBean> data;

    /**
      `log_id` ：订单自增id',

     `order_sn` ： '订单编号',

     `card_id` ： '违章卡的id',

     `illegal_log_id` ： '违章日志id',

     `add_datetime` ： 添加时间,

     `do_status` ： '订单的状态 1未审核 2处理中 3已取消 4已完成',

     `finish_datetime` ： '处理完成时间',

     `shop_user_id` ： '处理门店的user_id',

     `do_remark` ： '客服处理记录',

     `admin_truename` ： '处理人姓名',

     `money_paid` ： '实际支付金额',

     `user_id` ： '用户id',

     `date_time` ： '违章时间',

     `area` ： '违章区域',

     `act` ： '违章行为',

     `code` ： '违章代码',

     `fen` ： '扣分',

     `money` ：'罚款金额',

     `handled` ： '违章处理',

     `carno` ： '车牌号',

     `city` ： '城市名',
     */
    public class DataBean {
        public String log_id;
        public String order_sn;
        public String card_id;
        public String illegal_log_id;
        public String add_datetime;
        public String do_status;
        public String finish_datetime;
        public String shop_user_id;
        public String do_remark;
        public String admin_truename;
        public String money_paid;
        public String user_id;
        public String cancel_remark;
        public String id;
        public String date_time;
        public String area;
        public String act;
        public String code;
        public String fen;
        public String money;
        public String handled;
        public String carno;
        public String city;
    }
}
