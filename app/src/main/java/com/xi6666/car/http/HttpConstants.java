package com.xi6666.car.http;

import com.xi6666.network.ApiRest;

/**
 * 创建人 孙孙啊i
 * 时间 2016/6/12 0012.
 * 功能  这里是网络请求接口参数写入
 */
public class HttpConstants {

    /**
     * 这个是服务器地址
     */
    public static final String WEB_URL = ApiRest.baseUrl + "xiaoxiv4.php";

//    public static final String WEB_URL = "http://beta.xi6666.com/xiaoxiv4.php";
    /**
     * 这个是测试服务器地址
     */
    public static final String CS_WEB_URL = "http://beta.xi6666.com/xiaoxiv4.php";
    /**
     * 这个是图片服务器地址
     */
    public static final String IMAGE_URL = "http://www.xi6666.com/";
    /**
     * 获取喜豆
     */
    public static final String GET_USER_XI = "?m=api&act=wash&detail=get_user_order";
    /**
     * 获取所有洗车的商家
     */
    public static final String CAR_WASH_SHOP = "?m=api&act=wash&detail=get_wash_list";
    /**
     * 设置默认的爱车
     */
    public static final String DEFAULT_CAR = "?m=api&act=cart&detail=cart_set_default";
    /**
     * 洗车卷充值首页
     */
    public static final String CAR_WASH_HOME = "?m=api&act=wash&detail=wash_records_show";
    /**
     * 洗车卷充值
     */
    public static final String CAR_WASH_PAY = "?m=api&act=wash&detail=act_wash_records";
    /**
     * 获取洗车卷充值订单详情
     */
    public static final String CAR_WASH_PAY_DETAILS = "?m=api&act=wash&detail=wash_records_info";
    /**
     * 收货地址列表
     */
    public static final String ADDRESS_LIST = "?m=api&act=user&detail=address_list";
    /**
     * 设置默认地址
     */
    public static final String DEFAULT_ADDRESS = "?m=api&act=user&detail=set_default_address";
    /**
     * 修改地址
     */
    public static final String ADD_UPDATE_ADDRESS = "?m=api&act=user&detail=do_add_address";
    /**
     * 删除地址
     */
    public static final String DELETE_ADDRESS = "?m=api&act=user&detail=del_default_address";
    /**
     * 一级分类
     */
    public static final String ONE_CLASSIFY = "?m=api&act=shop&detail=goods_cate";
    /**
     * 二级分类
     */
    public static final String TWO_CLASSIFY = "?m=api&act=shop&detail=cat_second_data";
    /**
     * 品牌筛选
     */
    public static final String GOODS_BRAND = "?m=api&act=shop&detail=brand_list";
    /**
     * 商城主页
     */
    public static final String GOODS_LIST = "?m=api&act=shop&detail=goods_list";
    /**
     * 热门搜索
     */
    public static final String HOT_SEARCH = "?m=api&act=shop&detail=hot_search";
    /**
     * 服务门店
     */
    public static final String SERVICE_STORE = "?m=api&act=merchant&detail=list";
    /**
     * 首页服务门店
     */
    public static final String HOME_SERVICE_STORE = "?m=api&act=merchant&detail=goods_supers_list";
    /**
     * 门店
     */
    public static final String HOME_STORES = "?m=api&act=merchant&detail=merchant_list";
    /**
     * 下车卷支付
     */
    public static final String CARWASH_PAY = "?m=api&act=pay&detail=wash_coupon_pay";
    /**
     * 洗车订单
     */
    public static final String CARWASH_ORDER = "?m=api&act=order&type=wash_car&detail=create_wash_car_pay";
    /**
     * Utils
     * 洗车订单支付
     */
    public static final String CARWASH_ORDER_PAY = "?m=api&act=order&type=wash_car&detail=update_wash_car_order";
    /**
     * 获取洗车订单支付
     */
    public static final String WASH_CAR_PAY = "?m=api&act=order&type=wash_car&detail=wash_car_order";
    /**
     * 完全抵扣
     */
    public static final String ALL_DK = "?m=api&act=order&type=wash_car&detail=all_dk";
    /**
     * 获取特惠洗车banner图
     */
    public static final String CARWASH_BANNER = "?m=api&act=wash&detail=header_img";
    /**
     * 附近的服务门店
     */
    public static final String MAY_SEVER = "?m=api&act=merchant&detail=get_goods_merchant_list";

    /**
     * 获取二级分类和商品目录
     */
    public static final String SUBCATEGORIES = "?m=api&act=shop&detail=get_cate_goods";

    /**
     * 获取购物车数量
     */
    public static final String SHOPPING_NUMBER = "?m=api&act=shop&detail=get_cart_count";

    /**
     * 获取我的油卡列表
     */
    public static final String MY_OIL_CARD = "?m=api&act=oil&detail=oil_list";
    /**
     * 设置默认油卡
     */
    public static final String DEFAULT_OIL_CARD = "?m=api&act=oil&detail=set_oil_default";
    /**
     * 根据油卡账号获取名字
     */
    public static final String GET_CARD_USER_NAME = "?m=api&act=oil&detail=query_cardname";
    /**
     * 添加油卡
     */
    public static final String ADD_OIL_CARD = "?m=api&act=oil&detail=act_add_oilcard";
    /**
     * 删除油卡
     */
    public static final String DELETE_OIL_CARD = "?m=api&act=oil&detail=del_oilcard";
    /**
     * 获取加油卡充值记录
     */
    public static final String OIL_PAY_HISTORY = "?m=api&act=oil&detail=amend_oil_charge_detail_list";
    /**
     * 获取页面详情的数据
     */
    public static final String RECHARGE_DETAILS = "?m=api&act=oil&detail=amend_oil_charge_detail_tj";

}
