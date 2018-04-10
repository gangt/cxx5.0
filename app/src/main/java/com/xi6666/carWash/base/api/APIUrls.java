package com.xi6666.carWash.base.api;

import com.xi6666.car.bean.BrandCarBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.address.fragment.mvp.bean.DistributionShopBean;
import com.xi6666.address.fragment.mvp.bean.PersonalAddressBean;
import com.xi6666.car.bean.MyCarBean;
import com.xi6666.carWash.mvp.bean.CarWashListBean;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.carWash.mvp.bean.DetermineOrderBean;
import com.xi6666.carWash.view.mvp.bean.CashierBean;
import com.xi6666.carWash.view.mvp.bean.CashierDiscountBean;
import com.xi6666.cardbag.view.mvp.bean.AddOilCardBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardChargeBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardChargeDetailsBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardListBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardDeleteBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardGetNameBean;
import com.xi6666.cardbag.view.mvp.bean.OilCardNotAlreadyBean;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBean;
import com.xi6666.classification.view.fragment.mvp.bean.ServiceClassificationBrandBean;
import com.xi6666.classification.view.fragment.mvp.bean.ShoppingNumberBean;
import com.xi6666.classification.view.mvp.bean.AllBrandBean;
import com.xi6666.classification.view.mvp.bean.BrandDetailsBean;
import com.xi6666.classification.view.mvp.bean.ShopSearchHotBean;
import com.xi6666.evaluate.bean.EvaluateServiceBean;
import com.xi6666.illegal.see.bean.CancelOrderBean;
import com.xi6666.illegal.see.bean.GenerateRedeemCodeBean;
import com.xi6666.illegal.see.bean.IllegalCardStatusBean;
import com.xi6666.illegal.see.bean.RedeemCodeBean;
import com.xi6666.illegal.see.bean.UsageDetailsBean;
import com.xi6666.illegal.see.bean.UsageRecordBean;
import com.xi6666.owner.mvp.bean.OwnerEvaluationBean;
import com.xi6666.store.bean.FileBean;
import com.xi6666.store.bean.StoreBannerBean;
import com.xi6666.store.mvp.bean.StoreServiceBean;
import com.xi6666.store.mvp.bean.StoreServiceTypeBean;
import com.xi6666.technician.mvp.bean.ItsAnswerBean;
import com.xi6666.technician.mvp.bean.TechnicianDetailsBean;


import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 16/11/21 下午4:34.
 * 个人公众号 ardays
 * <p>
 * 接口管理地址
 */
public interface APIUrls {


    //TODO 调到没有油卡界面
    String HANDLE_OIL_CARD = "index.php/Oilcard/oli_banlicar";

    /**
     * 获取洗车卡首页的洗车列表
     */
    @FormUrlEncoded
    @POST("index.php/Washcard/get_wash_package")
    Observable<CarWashListBean> washCardList();


    /**
     * 获取油卡
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_user_oil_list")
    Observable<OilCardListBean> oilCardList(@Field("page") int page);


    /**
     * 删除油卡
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/del_oil_card")
    Observable<OilCardDeleteBean> oilCardDelete(@Field("card_id") String card_id);

    /**
     * 设置默认油卡
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/update_default_oil")
    Observable<OilCardDeleteBean> oilCardDefualt(@Field("card_id") String card_id);


    /**
     * 根据油卡获取名字
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/oil_query_cardname")
    Observable<OilCardGetNameBean> oilCardGetName(@Field("card_number") String card_number);

    /**
     * 添加油卡
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/act_add_oilcard")
    Observable<AddOilCardBean> addOilCard(@Field("card_name") String card_name, @Field("card_number") String card_number, @Field("user_mobile") String user_mobile);

    /**
     * 油卡充值记录--金额
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_user_oil_charge_list")
    Observable<OilCardChargeBean> getOilCardCharge(@Field("card_id") String card_id);

    /**
     * 油卡充值记录--充值列表明细
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_user_oil_charge_detail")
    Observable<OilCardChargeDetailsBean> getOilCardChargeDetails(@Field("card_id") String card_id, @Field("page") int page);

    /**
     * 获取用户油卡充值未到账记录
     */
    @FormUrlEncoded
    @POST("index.php/Oilcard/get_oil_not_already")
    Observable<OilCardNotAlreadyBean> getOilCardNotAlreday(@Field("card_id") String card_id);


    /**
     * 获取地址列表
     */
    @FormUrlEncoded
    @POST("address/query")
    Observable<PersonalAddressBean> getAddressList(@Field("yaya") String yaya);


    /**
     * 设置默认地址
     */
    @FormUrlEncoded
    @POST("address/set_default")
    Observable<BaseBean> setDeufaltAddress(@Field("address_id") String address_id);

    /**
     * 添加地址
     */
    @FormUrlEncoded
    @POST("address/add")
    Observable<BaseBean> addAddress(@Field("address") String address, @Field("city") String city, @Field("consignee") String consignee, @Field("district") String district, @Field("is_default") String is_default, @Field("mobile") String mobile, @Field("province") String province);

    /**
     * 修改地址
     */
    @FormUrlEncoded
    @POST("address/edit")
    Observable<BaseBean> updateAddress(@Field("address_id") String address_id, @Field("address") String address, @Field("city") String city, @Field("consignee") String consignee, @Field("district") String district, @Field("is_default") String is_default, @Field("mobile") String mobile, @Field("province") String province);

    /**
     * 删除地址
     */
    @FormUrlEncoded
    @POST("address/delete")
    Observable<BaseBean> deleteAddress(@Field("address_id_arr") String address_id_arr);

    /**
     * 获取服务类型
     */
    @FormUrlEncoded
    @POST("Service/get_service")
    Observable<StoreServiceTypeBean> getServiceType(@Field("s") String s);

    /**
     * 门店及门店搜索
     */
    @FormUrlEncoded
    @POST("Store/store_washcar")
    Observable<StoreServiceBean> getStoreServiceList(@Field("city") String city, @Field("keyword") String keyword, @Field("lat") String lat, @Field("lng") String lng, @Field("odds") String odds, @Field("order_by") String order_by, @Field("page") int page, @Field("page_size") int page_size, @Field("service_cate_id") String service_cate_id);


    /**
     * 查询配送门店地址
     */
    @FormUrlEncoded
    @POST("store/query_peisong_store")
    Observable<DistributionShopBean> getDistributionShop(@Field("city") String city, @Field("lat") String lat, @Field("lng") String lng, @Field("page") int page);


    /**
     * 查看门店详情
     */
    @FormUrlEncoded
    @POST("Store/store_detail")
    Observable<StoreDetailsBean> getStoreDetails(@Field("store_id") String store_id, @Field("service_cate_id") String service_cate_id, @Field("lng") String lng, @Field("lat") String lat);


    /**
     * 查询我的爱车
     */
    @FormUrlEncoded
    @POST("brand/get_mycar")
    Observable<MyCarBean> getCarType(@Field("yaya") String ya);


    /**
     * 添加爱车
     *
     * @param car_brand       品牌名称
     * @param car_chexing     车型
     * @param car_displacemen 上路时间
     * @param car_id          添加爱车不用填写；更新爱车才要传爱车id
     * @param car_kilometre   行驶公里
     * @param car_nianfen     年份
     * @param car_pailiang    排量
     * @param car_plate       车牌
     * @param is_default      0 普通 1默认车型
     */
    @FormUrlEncoded
    @POST("Brand/add_mycar")
    Observable<BaseBean> add_mycar(@Field("car_brand") String car_brand, @Field("car_chexing") String car_chexing, @Field("car_displacemen") String car_displacemen, @Field("car_id") String car_id, @Field("car_kilometre") String car_kilometre, @Field("car_nianfen") String car_nianfen, @Field("car_pailiang") String car_pailiang, @Field("car_plate") String car_plate, @Field("is_default") String is_default);


    /**
     * 删除爱车
     *
     * @param car_id_arr 爱车数组
     */
    @FormUrlEncoded
    @POST("Brand/del_mycar")
    Observable<BaseBean> delMyCar(@Field("car_id_arr") String car_id_arr);

    /**
     * 获取全部商品分类
     */
    @FormUrlEncoded
    @POST("shop/get_all_goods_cate")
    Observable<ServiceClassificationBean> getGoodsTypeList(@Field("yoyo") String yoyo);

    /**
     * 获取全部品牌分类
     */
    @FormUrlEncoded
    @POST("advertiseimage/brand_img")
    Observable<ServiceClassificationBrandBean> getBrandList(@Field("yoyo") String yoyo);


    /**
     * 获取所有品牌列表
     */
    @FormUrlEncoded
    @POST("brand/get_all_brands")
    Observable<AllBrandBean> getAllBrand(@Field("yoyo") String yoyo);


    /**
     * 品牌商品和商品分类商品
     *
     * @param brand_id   品牌id。如：388
     * @param cate_id    brand_id和cate_id只传一个。如：830
     * @param page       默认第1页。
     * @param type       排序方式
     * @param goods_name 商品名称
     */
    @FormUrlEncoded
    @POST("brand/get_brand_goods")
    Observable<BrandDetailsBean> getBrandGoods(@Field("brand_id") String brand_id, @Field("cate_id") String cate_id, @Field("page") int page, @Field("type") int type, @Field("goods_name") String goods_name);


    /**
     * 获取技师详情
     *
     * @param technician_id 技师ID
     */
    @FormUrlEncoded
    @POST("Store/get_store_jishi_detail")
    Observable<TechnicianDetailsBean> getTechnicianDetails(@Field("jishi_id") String technician_id);


    /**
     * 向技师提问
     *
     * @param jishi_id     技师ID
     * @param ques_content 问题
     * @param file         图片
     * @return
     */
    @FormUrlEncoded
    @POST("Comment/add_jd_question")
    Observable<BaseBean> getQuestionComment(@Field("jishi_id") String jishi_id, @Field("ques_content") String ques_content, @Field("file") String file);

    /**
     * 获取车主评价
     *
     * @param level   等级/level=1是好评 2是中评 3是差评 0是全部
     * @param page    当前页，传1
     * @param shop_id shop_id
     */
    @FormUrlEncoded
    @POST("Store/get_owner_evaluate")
    Observable<OwnerEvaluationBean> getEvaluationList(@Field("level") int level, @Field("page") int page, @Field("shop_id") String shop_id);


    /**
     * 获取技师(TA的回答)
     *
     * @param js_user_id 技师 Id
     * @param page       分页
     */
    @FormUrlEncoded
    @POST("Store/answer")
    Observable<ItsAnswerBean> getJSAnswerList(@Field("js_user_id") String js_user_id, @Field("page") int page);


    /**
     * 点赞
     *
     * @param function    技师问题点赞function带值是dot/评论点赞传空值
     * @param question_id 问题id/评论id
     * @return
     */
    @FormUrlEncoded
    @POST("Store/dot_js_question")
    Observable<BaseBean> quesLikes(@Field("function") String function, @Field("question_id") String question_id);

    /**
     * 取消点赞
     *
     * @param function    技师问题点赞function带值是dot/评论点赞传空值
     * @param question_id 问题id/评论id
     * @return
     */
    @FormUrlEncoded
    @POST("Store/cancel_dotjs_question")
    Observable<BaseBean> quesUnLikes(@Field("function") String function, @Field("question_id") String question_id);


    /**
     * 评论点赞
     *
     * @param comment_id   评论ID
     * @param shop_user_id 用户ID
     * @return
     */
    @FormUrlEncoded
    @POST("like/add_comment_like")
    Observable<BaseBean> commentLikes(@Field("comment_id") String comment_id, @Field("shop_user_id") String shop_user_id);


    /**
     * 评论取消点赞
     *
     * @param comment_id   评论ID
     * @param shop_user_id 用户ID
     * @return
     */
    @FormUrlEncoded
    @POST("like/cancel_comment_like")
    Observable<BaseBean> cannelLikes(@Field("comment_id") String comment_id, @Field("shop_user_id") String shop_user_id);

    /**
     * 问题点赞
     *
     * @param question_id
     * @return
     */
    @FormUrlEncoded
    @POST("like/add_question_like")
    Observable<BaseBean> questionLikes(@Field("question_id") String question_id);

    /**
     * 问题取消点赞
     *
     * @param question_id 问答id
     * @return
     */
    @FormUrlEncoded
    @POST("like/cancel_question_like")
    Observable<BaseBean> questionUnLikes(@Field("question_id") String question_id);


    /**
     * 获取热门搜索
     *
     * @param yaya
     */
    @FormUrlEncoded
    @POST("shop/get_hot_search")
    Observable<ShopSearchHotBean> getHotSearch(@Field("yaya") String yaya);


    /**
     * 生成服务订单
     *
     * @param service_id 服务订单
     */
    @FormUrlEncoded
    @POST("Orderconfirm/service_order")
    Observable<DetermineOrderBean> generateOrder(@Field("service_id") String service_id);


    /**
     * 获取汽车门店banner图
     */
    @GET("Store/get_Advertising")
    Observable<StoreBannerBean> getStoreBanner();


    /**
     * 上传图片
     */
    @Multipart
    @POST("login/multi_upload")
    Observable<FileBean> uploadImages(@PartMap Map<String, RequestBody> image);


    /**
     * 门店评论上传
     *
     * @param comment_content 评论内容
     * @param comment_level   评论星级
     * @param shop_user_id    门店ID
     * @param file
     * @return
     */
    @FormUrlEncoded
    @POST("Comment/add_comment")
    Observable<BaseBean> commitEvaluate(@Field("comment_content") String comment_content, @Field("comment_level") float comment_level, @Field("shop_user_id") String shop_user_id, @Field("file") String file, @Field("service_cate_id") int service_cate_id, @Field("service_order_sn") String service_order_sn);


    /**
     * 生成洗车订单
     *
     * @param order_id 订单ID
     * @return
     */
    @FormUrlEncoded
    @POST("Pay/service_pay")
    Observable<CashierBean> getCashierDetails(@Field("order_sn") String order_id);

    /**
     * 获取购物车数量
     */
    @FormUrlEncoded
    @POST("cart/get_num")
    Observable<ShoppingNumberBean> getGoodsCarNum(@Field("yoyo") String yoyo);


    /**
     * 获取门店评价服务类型
     *
     * @param store_id 门店ID
     * @return
     */
    @FormUrlEncoded
    @POST("service/get_shop_service")
    Observable<EvaluateServiceBean> getServiceList(@Field("shop_user_id") String store_id);


    /**
     * 获取折扣方式
     *
     * @param service_order_sn 服务订单号
     * @param use_status       1勾选折扣  0 取消勾选
     */
    @FormUrlEncoded
    @POST("pay/update_order_pay")
    Observable<CashierDiscountBean> selectServiceDiscount(@Field("order_sn") String service_order_sn, @Field("use_status") String use_status);


    /**
     * 完全抵扣
     *
     * @param order_sn 服务订单号
     */
    @FormUrlEncoded
    @POST("pay/washcard_pay")
    Observable<BaseBean> fullDeduction(@Field("order_sn") String order_sn);


    /**
     * 获取汽车品牌
     */
    @FormUrlEncoded
    @POST("brand/get_brand_car_v2")
    Observable<BrandCarBean> getBrandCar(@Field("s") String s);


    /**
     * 获取汽车品牌类型
     *
     * @param brand_number 品牌ID
     */
    @FormUrlEncoded
    @POST("brand/get_car_number")
    Observable<CarBrandTypeBean> getBrandTypeCar(@Field("brand_number") String brand_number);


    /**
     * 获取汽车年限
     *
     * @param cartype_number 类型ID
     */
    @FormUrlEncoded
    @POST("brand/get_tyep_year")
    Observable<CarYearTypeBean> getYearCar(@Field("cartype_number") String cartype_number);

    /**
     * 获取机油详情
     */
    @FormUrlEncoded
    @POST("brand/get_displacement")
    Observable<CarEngineBean> getEngineCar(@Field("brand_number") String brand_number, @Field("cartype_number") String cartype_number);


    /**
     * 获取违章卡详情
     *
     * @param card_id 违章卡ID
     */
    @FormUrlEncoded
    @POST("Illegalcard/illegal_redeem_code_info")
    Observable<IllegalCardStatusBean> getIllegalCardStauts(@Field("card_id") String card_id);

    /**
     * 生成违章卡的兑换码
     *
     * @param card_id 违章卡ID
     */
    @FormUrlEncoded
    @POST("Illegalcard/illegal_redeem_code")
    Observable<RedeemCodeBean> getReddemCode(@Field("card_id") String card_id);

    /**
     * 用兑换码兑换"违章卡"
     */
    @FormUrlEncoded
    @POST("Illegalcard/illegal_redeem_code_change")
    Observable<GenerateRedeemCodeBean> getRedeemIllegal(@Field("redeem_code") String redeem_code);


    /**
     * 违章卡的"使用记录"
     *
     * @param card_id 违章卡ID
     * @param page    页码
     */
    @FormUrlEncoded
    @POST("Illegalcard/illegal_card_use_log")
    Observable<UsageRecordBean> getUsageRecord(@Field("card_id") String card_id, @Field("page") int page);

    /**
     * 违章卡的"使用记录详情"
     * @param log_id 违章卡订单id
     */
    @FormUrlEncoded
    @POST("Illegalcard/illegal_card_use_orderinfo")
    Observable<UsageDetailsBean> getUsageDetails(@Field("log_id") String log_id);

    /**
     * 取消违章卡订单
     * @param log_id 违章卡订单id
     */
    @FormUrlEncoded
    @POST("Illegalcard/illegal_card_order_canel")
    Observable<CancelOrderBean> cancelIillegalOrder(@Field("log_id") String log_id);
}
