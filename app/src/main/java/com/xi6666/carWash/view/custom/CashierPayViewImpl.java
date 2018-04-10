package com.xi6666.carWash.view.custom;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/3 上午11:53.
 * 个人公众号 ardays
 */

public interface CashierPayViewImpl {

    /**
     *  写入数据
     */
     void setItemDatas(List<CashierBean> datas);


    /**
     * 是否可以取消选择
     */
    void setUnSelect(boolean bol);
}
