package com.xi6666.carWash.view.custom;

import com.xi6666.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 sunsun
 * 时间 16/11/3 上午11:54.
 * 个人公众号 ardays
 */

public class CashierBean {

    //标题
    public int id;              //类型
    public String card_id;      //洗车卡ID
    public String title;        //标题
    public String icon;         //图标地址
    public float moeny;         //价格
    public int icon_resId;      //图标ID
    public int gone_icon_resId; //禁用的图标地址
    public boolean selected;    //是否选中
    public boolean isCanUse;
    public String canUseMoney;
    /**
     * 获取支付数据
     */
    private static List<CashierBean> mPayListData;

    public static final List<CashierBean> getPayData() {
        if (mPayListData == null) {
            mPayListData = new ArrayList<>();
            CashierBean wxCarshier = new CashierBean();
            wxCarshier.icon_resId = R.mipmap.ic_wechat_pay;
            wxCarshier.gone_icon_resId = R.mipmap.ic_wechat_pay_gray;
            wxCarshier.title = "微信支付";
            wxCarshier.id = 0;

            CashierBean aliCarshier = new CashierBean();
            aliCarshier.icon_resId = R.mipmap.ic_ali_pay;
            aliCarshier.gone_icon_resId = R.mipmap.ic_ali_pay_gray;
            aliCarshier.title = "支付宝";
            aliCarshier.id = 1;


            mPayListData.add(wxCarshier);
            mPayListData.add(aliCarshier);
        }

        return mPayListData;
    }
}


