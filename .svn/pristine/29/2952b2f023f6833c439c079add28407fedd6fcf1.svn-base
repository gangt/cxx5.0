package com.xi6666.network;

import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.common.Constant;
import com.xi6666.databean.AddOilDataBean;
import com.xi6666.databean.AddOilPayTypeBean;
import com.xi6666.databean.AddOilPopuBean;
import com.xi6666.databean.AddressBean;
import com.xi6666.databean.CouponBean;
import com.xi6666.databean.DefaultOilCardBean;
import com.xi6666.databean.HappyBeansBean;
import com.xi6666.databean.HomeBannerBean;
import com.xi6666.databean.HomeSpecialBean;


import com.xi6666.databean.IllegaBagListBean;
import com.xi6666.databean.IllegaHomeListBean;
import com.xi6666.databean.IllegaPayBean;
import com.xi6666.databean.RechargeDetialBean;
import com.xi6666.databean.RechargeListBean;
import com.xi6666.databean.TokenBean;
import com.xi6666.databean.UseCouponBean;
import com.xi6666.databean.WashCardDetialBean;
import com.xi6666.databean.WashCardInfoBean;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Mr_yang on 2016/10/12.
 */

public interface ApiRest {
    // String baseUrl = Constant.BaseUrl;
    String baseUrl = Constant.BaseUrl;

    String WECHATAPPID = "wx30dcd64646422aac";

    //商户号
    String WECHATMCHID = "1427694302";
    //周四加油活动
    String ADDOILTHU = "index.php/Oilcard/oil_active?get_device_type=android";
    //加油界面说明
    String ADDOILTHAT = "index.php/Oilcard/oli_explain";
    //证明合同
    String ADDOILCONTRACT = "index.php/Oilcard/oli_service?get_device_type=android";
    //关于车小喜
    String ABOUTCXX = "index.php/User/user_about";
    //邀请好友
    String INVITFRI = "index.php/User/user_invitation?get_device_type=android";
    //赚喜豆
    String MAKEHAPPYBEAN = "index.php/Xidou/xidou_make?get_device_type=android";
    //联盟商加盟
    String UNION = "index.php/Jiameng/jiameng_index";
    //加油模块用户协议
    String AGRRMENT = "index.php/Oilcard/oli_agreement";
    // 代驾
    String DAIJIA = "http://h5.edaijia.cn/app/index.html?from=01051396";
    //客服帮助
    String SERVER = "index.php/User/user_kefu";
    /*//加油卡充值
    String ADDOILCARD = "index.php/Oilcard/oil_active?get_device_type=android";*/
    //洗车卡抢购
    String WASHCARD = "index.php/Washcard/index?get_device_type=android";
    //违章卡活动
    String ILLEGA = Constant.BaseUrl + "Illegalcard/index";
    //客服
    String KEFU = Constant.BaseUrl + "index.php/User/kefu?get_device_type=android";



    //获取服务器版本信息
    @GET("xiaoxiv4.php?m=api&act=index&detail=get_brand_list")
    Call<ResponseBody> getBrand();

    //获取服务器版本信息
    @GET("xiaoxiv4.php?m=api&act=index&detail=get_brand_list")
    Observable<ResponseBody> getBrandTwo();

    //获取消息
    @FormUrlEncoded
    @POST("index.php/User/get_user_msg")
    Observable<ResponseBody> getMessage(@Field("page") String page,
                                        @Field("user_id") String useId, @Field("user_token") String useToken);

    //获取服务器版本信息
    @GET("xiaoxiv4.php?m=api&act=index&detail=get_user_msg")
    Call<ResponseBody> getMessage2(@Query("page") String page, @Query("user_id") String useId);

    //获取默认油卡
    @FormUrlEncoded
    @POST("xiaoxiv4.php?m=api&act=oil&detail=defaultoil")
    Call<ResponseBody> getDefaultOilCard(@Field("user_id") String uuserId);


    @GET("xiaoxiv4.php?m=api&act=oil&detail=amend_oil_index")
    Call<ResponseBody> getAddOil();


    //homepage的轮播图
    @GET("xiaoxiv4.php?m=api&act=index&detail=index_banner&by=android")
    Call<HomeBannerBean> getHomePageBanner();


    //address获取省份的列表
    @GET("{baseurl}")
    Observable<ResponseBody> getProvince(@Path("baseurl") String baseurl);

    //登录时获取Token
    @GET("login/send_token")
    Observable<TokenBean> getLoginToken();

    //获取验证码
    @FormUrlEncoded
    @POST("/login/send_yzm")
    Observable<ResponseBody> getYzm(@Field("mobile") String mobile, @Field("token") String token);

    //手机验证码登录
    @FormUrlEncoded
    @POST("index.php/Login/login_by_mobile")
    Observable<ResponseBody> phoneLogin(@Field("user_mobile") String mobile, @Field("yzm_code") String code);

    //账号密码登录
    @FormUrlEncoded
    @POST("/index.php/Login/login_by_pass")
    Observable<ResponseBody> accountLogin(@Field("user_name") String username, @Field("user_password") String pwd);

    //用户退出
    @FormUrlEncoded
    @POST("index.php/Login/login_out")
    Observable<ResponseBody> signOut(@Field("user_id") String userId, @Field("user_token") String userToken);

    //修改密码
    @FormUrlEncoded
    @POST("index.php/User/update_user_pwd")
    Observable<ResponseBody> modifyPassWord(@Field("user_id") String userId,
                                            @Field("user_password") String passWord,
                                            @Field("user_token") String userToken,
                                            @Field("user_name") String userName
    );

    //获取个人中心的数据
    @FormUrlEncoded
    @POST("index.php/User/get_user_info")
    Observable<ResponseBody> getUserInfo(@Field("user_id") String useId, @Field("user_token") String useToken);

    //设置个人资料
    @GET("index.php/User/update_user_info")
    Observable<ResponseBody> setUserData(@Query("user_birthday") String user_birthday, @Query("user_face") String user_face
            , @Query("user_nickname") String user_nickname, @Query("user_sex") String user_sex,
                                         @Query("user_id") String user_id, @Query("user_token") String user_token);

    //喜豆界面
    @FormUrlEncoded
    @POST("index.php/Xidou/get_user_xidou")
    Observable<HappyBeansBean> getHappyBeans(@Field("option") String option, @Field("page") String page,
                                             @Field("user_id") String user_id, @Field("user_token") String user_token);

    //洗车卡信息
    @FormUrlEncoded
        @POST("index.php/Washcard/get_user_wash_card")
    Observable<WashCardInfoBean> getWashCardInfo(@Field("user_id") String user_id, @Field("user_token") String user_token);

    //获取洗车卡交易信息
    @FormUrlEncoded
    @POST("index.php/Washcard/get_washcard_pay_info")
    Observable<WashCardDetialBean> getWashCardDetial(@Field("option") String option, @Field("page")
            String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    //获取省份和地址
    @GET("region/all_data")
    Observable<AddressBean> getCityAddress();

    //获取加油banner
    @GET("index.php/Oilcard/oil_index")
    Observable<AddOilDataBean> getAddOilBean();

    //获取默认油卡
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_default_oil")
    Observable<DefaultOilCardBean> getDefaultOilCard(@Field("user_id") String user_id, @Field("user_token") String user_token);

    //获取首页头部数据
    @FormUrlEncoded
    @POST("index.php/Index/index")
    Observable<ResponseBody> getHomeHeadData(@Field("get_device_type") String device);

    //获取首页商品流
    @FormUrlEncoded
    @POST("index.php/Index/get_hot_goods")
    Observable<ResponseBody> getHomeHotGoods(@Field("page") String page);

    //获取专题模块数据
    @GET("index.php/Index/get_zhuanti_goods")
    Observable<HomeSpecialBean> getSpecial();

    //获取商品详情的界面
    @FormUrlEncoded
    @POST("shop/goods_detail")
    Observable<ResponseBody> getProduct(@Field("goods_id") String goodId, @Field("user_id") String userId, @Field("user_token") String userToken);

    //设置收藏
    @FormUrlEncoded
    @POST("collect/goods_add_collect")
    Observable<ResponseBody> sendCollect(@Field("goods_id") String goods_id,
                                         @Field("user_id") String userId, @Field("user_token") String userToken);

    //取消收藏
    @FormUrlEncoded
    @POST("collect/delete_collect")
    Observable<ResponseBody> collectCancle(@Field("goods_id_arr") String goods_id_arr, @Field("user_id") String userId,
                                           @Field("user_token") String userToken);

    //商品详情获取购物车商品数量
    @FormUrlEncoded
    @POST("cart/get_num")
    Observable<ResponseBody> getGoodsCarNum(@Field("user_id") String userId, @Field("user_token") String userToken);

    //根据颜色的sku获取尺寸
    @FormUrlEncoded
    @POST("shop/get_goods_info")
    Observable<ResponseBody> getSku(@Field("goods_id") String goodsId, @Field("sku_value_id") String skuValue);

    //添加到购物车
    @FormUrlEncoded
    @POST("cart/add")
    Observable<ResponseBody> addGoodsCar(
            @Field(" goods_id") String goods_id,
            @Field(" sku_value_id") String sku_value_id,
            @Field(" user_id") String user_id,
            @Field(" user_toke") String user_token
    );

    //获取商品首页分类
    @GET("shop/get_goods_cate")
    Observable<ResponseBody> getCateGories();

    //意见反馈
    @FormUrlEncoded
    @POST("index.php/User/act_user_feedback")
    Observable<ResponseBody> feedBack(@Field("msg_text") String msg_text, @Field("user_id") String usreID, @Field("user_token") String userToken);

    //获取爱车logo
    @FormUrlEncoded
    @POST("index.php/Index/get_user_car")
    Observable<ResponseBody> getCarLogoIcon(@Field("user_id") String userId);

    //洗车卡订单生成
    @FormUrlEncoded
    @POST("index.php/Washcard/wash_order_info")
    Observable<ResponseBody> getCleanCar(@Field("order_sn") String order_sn, @Field("user_id") String usreID,
                                         @Field("user_token") String userToken);

    //洗车卡修改支付方式
    @FormUrlEncoded
    @POST("index.php/Washcard/update_wash_coupon_pay_type")
    Observable<ResponseBody> changePayType(@Field("order_sn") String order_sn, @Field("pay_id") String payType, @Field("user_id") String usreID,
                                           @Field("user_token") String userToken);


    //生成洗车卡订单
    @FormUrlEncoded
    @POST("index.php/Washcard/act_wash_records")
    Observable<ResponseBody> creatOrder(@Field("package_id") String package_id, @Field("user_id") String usreID,
                                        @Field("user_token") String userToken);

    //版本更新
    @GET("index.php/Index/get_version_android")
    Observable<ResponseBody> loadVersion();

    //获取附近门店
    @FormUrlEncoded
    @POST("Store/store_washcar")
    Observable<ResponseBody> NearbyStores(@Field("city") String cityId, @Field("lat") String lat,
                                          @Field("lng") String lng, @Field("order_by") String order_by);

    //加油卡参数生成
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_oil_package_info")
    Observable<ResponseBody> addOilPayInfo(@Field("package_id") String package_id,
                                           @Field("package_cash") String package_cash,
                                           @Field("user_id") String user_id,
                                           @Field("user_token") String user_token);

    //加油卡生成订单
    @FormUrlEncoded
    @POST("index.php/Oilcard/add_oil_charge")
    Observable<ResponseBody> createAddOilOrder(@Field("card_id") String card_id, @Field("package_cash") String package_cash,
                                               @Field("package_id") String package_id, @Field("pay_id") String pay_id,
                                               @Field("user_id") String user_id, @Field("user_token") String user_token);

    //加油卡修改支付方式
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_oil_pay")
    Observable<ResponseBody> changeOilPayType(@Field("order_sn") String order, @Field("pay_id") String pay_id,
                                              @Field("user_id") String user_id, @Field("user_token") String user_token);


    //加油卡获取支付方式
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_oil_pay")
    Observable<AddOilPayTypeBean> getOilPayOrder(@Field("order_sn") String out_trade_no,
                                                 @Field("pay_id") String payId,
                                                 @Field("user_id") String user_id,
                                                 @Field("user_token") String user_token);

    //加油卡修改支付方式
    @FormUrlEncoded
    @POST("index.php/Oilcard/update_oil_pay_type")
    Observable<ResponseBody> modifyOilPayType(@Field("order_sn") String order, @Field("pay_id") String payType,
                                              @Field("user_id") String userId, @Field("user_token") String userToken,
                                              @Field("coupon_id") String CouPonId);


    //加油活动弹窗
    @FormUrlEncoded
    @POST("index.php/Oilcard/check_user_isnew")
    Observable<AddOilPopuBean> addOilPopu(@Field("user_id") String user_id,
                                          @Field("user_token") String user_token);

    //加油优惠券领取

    @GET("index.php/Oilcard/receive_oil_coupon")
    Observable<ResponseBody> receiveAddOilCard(@Query("user_id") String user_id, @Query("user_token") String user_token);

    //获取用户登录名
    @FormUrlEncoded
    @POST("index.php/User/get_user_pwd")
    Observable<ResponseBody> getUserName(@Field("user_id") String user_id, @Field("user_token") String user_token);

    //生成微信支付订单
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> createOilOrder(
            @Url String url,
            @Field("attach") String attach,
            @Field("body") String body,
            @Field("out_trade_no") String out_trade_no,
            @Field("pay_id") String pay_id,
            @Field("pay_name") String pay_name,
            @Field("total_fee") String total_fee
    );

    //头像上传
    @Multipart
    @POST("login/user_face_upload")
    Observable<ResponseBody> setUserFace(@PartMap Map<String, RequestBody> imgs);

    //用户登录时长
    @FormUrlEncoded
    @POST("index.php/Login/get_user_session")
    Observable<ResponseBody> getUserLoginTime(@Field("user_id") String userId, @Field("user_token") String userToken);

    //设置卡券包里面的数量
    @FormUrlEncoded
    @POST("index.php/User/get_user_package")
    Observable<ResponseBody> getCardNum(@Field("user_id") String userId);

    //
    @FormUrlEncoded
    @POST("index.php/Washcard/get_wash_coupon_pay")
    Observable<AddOilPayTypeBean> getCleanCarOrder(@Field("order_sn") String out_trade_no,
                                                   @Field("pay_id") String payId,
                                                   @Field("user_id") String user_id,
                                                   @Field("user_token") String user_token);

    //加油活动
    @GET("Oilcard/oil_charge_num")
    Observable<ResponseBody> getOilPerson();

    //获取优惠前
    @FormUrlEncoded
    @POST("Coupon/get_user_coupon")
    Observable<CouponBean> getCouponListData(@Field("page") String page,
                                             @Field("money") String money, @Field("user_id") String userid,
                                             @Field("user_token") String user_token);

    //删除优惠券
    @FormUrlEncoded
    @POST("index.php/Coupon/del_user_coupon")
    Observable<ResponseBody> deleteCouponCard(@Field("coupon_id") String coupon_id,
                                              @Field("user_id") String user_id,
                                              @Field("user_token") String user_token);

    //加油优惠券列表
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_user_oilcounpon")
    Observable<UseCouponBean> userCouponItem(@Field("user_id") String userId, @Field("user_token") String userToken, @Field("page") String page);

    //数据埋点
    @FormUrlEncoded
    @POST("Datastat/add_data")
    Observable<ResponseBody> getYuriedPoint(@Field("data_sn") String data_sn,
                                            @Field("data_name") String data_name,
                                            @Field("data_remark") String data_remark,
                                            @Field("get_device_type") String get_device_type,
                                            @Field("user_id") String user_id);

    //秒杀商品详情
    @FormUrlEncoded
    @POST("Seckill/goods_detail")
    Observable<ResponseBody> secKill(@Field("seckill_id") String seckill_id,
                                     @Field("user_id") String user_id,
                                     @Field("user_token") String user_token);

    //去报名
    @FormUrlEncoded
    @POST("Seckill/sign_up")
    Observable<ResponseBody> seckKillSignUp(@Field("user_id") String user_id,
                                            @Field("user_token") String user_token,
                                            @Field("get_device_type") String get_device_type,
                                            @Field("seckill_id") String seckill_id
    );

    //去秒杀
    @FormUrlEncoded
    @POST("Seckill/act_seckill_post")
    Observable<ResponseBody> seckKillState(@Field("user_id") String user_id,
                                           @Field("user_token") String user_token,
                                           @Field("get_device_type") String get_device_type,
                                           @Field("seckill_id") String seckill_id);

    //违章卡列表
    @FormUrlEncoded
    @POST("index.php/Illegalcard/illegal_card_list")
    Observable<IllegaBagListBean> illageCardBag(@Field("user_id") String user_id,
                                                @Field("user_token") String user_token,
                                                @Field("page") String page);

    //违章查询首页
    @FormUrlEncoded
    @POST("illegal/get_list")
    Observable<IllegaHomeListBean> illegaHomeList(@Field("user_id") String userId,
                                                  @Field("user_token") String userToken,
                                                  @Field("page") String page);

    //购买违章卡收银台
    @FormUrlEncoded
    @POST("index.php/Illegalcard/get_illegal_card_order_detail")
    Observable<ResponseBody> IllegaOrder(@Field("order_sn") String orderSn);

    //违章卡支付方式修改
    @FormUrlEncoded
    @POST("index.php/Illegalcard/update_illegal_card_pay_type")
    Observable<ResponseBody> changePayType(@Field("user_id") String userId, @Field("pay_id")
            String payId, @Field("order_sn") String orderSn);

    //违章卡获取支付信息
    @FormUrlEncoded
    @POST("index.php/Illegalcard/get_illegal_card_pay")
    Observable<IllegaPayBean> IllegaPayInfo(@Field("user_id") String userId, @Field("pay_id")
            String payId, @Field("order_sn") String orderSn);

    //删除违章信息
    @FormUrlEncoded
    @POST("illegal/illegal_delete_item")
    Observable<ResponseBody> deleteIllega(@Field("car_no") String car_no,
                                          @Field("city_code") String city_code,
                                          @Field("province_code") String province_code,
                                          @Field("user_id") String user_id);

    //个人中心红点
    @FormUrlEncoded
    @POST("index.php/User/user_card_user")
    Observable<ResponseBody> minePointer(@Field("user_id") String user_id, @Field("user_token") String user_token);

    //删除商品订单
    @FormUrlEncoded
    @POST("index.php/Orderlist/del_goods_order")
    Call<ResponseBody> deleteGoodsOrder(@Field("user_id") String user_id, @Field("order_sn") String order_sn);

    //删除商品订单
    @FormUrlEncoded
    @POST("index.php/Orderlist/del_service_order")
    Call<ResponseBody> deleteServerOrder(@Field("user_id") String user_id, @Field("order_sn") String order_sn);

    @FormUrlEncoded
    @POST("index.php/Oilcard/get_user_oil_charge_detail")
    Observable<RechargeListBean> getOilCardChargeList(@Field("user_id") String userId,
                                                      @Field("card_id") String card_id,
                                                      @Field("page") int page,
                                                      @Field("user_token") String userToken);

    @FormUrlEncoded
    @POST("index.php/Oilcard/get_user_oil_charge_list")
    Observable<RechargeDetialBean> getOilCardChargeDetails(@Field("user_id") String card_id,
                                                           @Field("card_id") String userId,
                                                           @Field("user_token") String userToken);

    /**
     * 设置默认油卡
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/update_default_oil")
    Observable<OilCardDeleteBean> oilCardDefualt(@Field("card_id") String card_id, @Field("user_id") String userId, @Field("user_token") String userToken);

    //获取切换的Title
    @GET("index.php/Login/login_name")
    Observable<ResponseBody> getLoginTitle();


    //加油套餐检测
    @FormUrlEncoded
    @POST("index.php/Oilcard/oil_type_check")
    Observable<ResponseBody> testingAddOil(@Field("package_id") String package_id,
                                           @Field("user_id") String userId,
                                           @Field("user_token") String user_token);

}
