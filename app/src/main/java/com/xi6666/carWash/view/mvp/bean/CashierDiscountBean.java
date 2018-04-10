package com.xi6666.carWash.view.mvp.bean;

import com.xi6666.carWash.base.network.BaseBean;

/**
 * 创建者 sunsun
 * 时间 2016/12/8 下午4:43.
 * 个人公众号 ardays
 */

public class CashierDiscountBean extends BaseBean {
    public DataBean data;

    public class DataBean {
        public String order_money;  //最终支付的现金金额
        public int is_selected;     //0则当前取消勾选的状态，1则为当前是勾选状态
        public long total_fee;      //totalfee
    }
}
