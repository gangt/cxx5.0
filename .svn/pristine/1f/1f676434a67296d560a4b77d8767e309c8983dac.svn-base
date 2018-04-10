package com.xi6666.illegal.net_data;

import com.xi6666.network.ApiRest;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 作者： qsdsn on 2016/10/31
 * 描述：${DESC}
 */

public interface NetAddress {
//    String baseUrl = "http://dev-app.xiaoxi6.com/";
    String baseUrl = ApiRest.baseUrl;

    //获取优惠券
    @FormUrlEncoded
    @POST("Coupon/get_user_coupon")
    Call<ResponseBody> getCouponList(@Field("page") String page,@Field("money") String money, @Field("user_id") String userid, @Field("user_token") String user_token);

    //获取违章记录
    @FormUrlEncoded
    @POST("illegal/get_list")
    Call<ResponseBody> getBreakList(@Field("page") String page, @Field("user_id") String userid, @Field("user_token") String user_token);

    //-----------------违章查询---------------//
    //获取省份的列表
    @GET("illegal/get_citys")
    Call<ResponseBody> getProvince();

    //违章卡绑定
    @FormUrlEncoded
    @POST("Illegalcard/add_bangding")
    Call<ResponseBody> bindCard(@Field("car_no") String car_no, @Field("card_id") String card_id,
                                @Field("engineno") String engineno, @Field("classno") String classno,@Field("user_id") String user_id,
                    @Field("user_token") String user_token,@Field("province_code") String province_code);

    //违章查询
    @FormUrlEncoded
    @POST("illegal/illegal_detail")
    Call<ResponseBody> getBreak(@Field("car_no") String car_no, @Field("city_code") String city_code,
                                @Field("engineno") String engineno, @Field("classno") String classno,
                                @Field("is_default") String is_save, @Field("user_id") String user_id,
                    @Field("user_token") String user_token,@Field("province_code") String province_code,@Field("query_id") String query_id);

    /*//违章历史记录详情
    @FormUrlEncoded
    @POST("xiaoxiv4.php?m=api&act=traffic&detail=traffic_log")
    Call<ResponseBody> getBreakHistory(@Field("user_id") String userid, @Field("carno") String carno, @Field("city") String query_city);*/

    //违章删除
    @FormUrlEncoded
    @POST("illegal/illegal_delete_item")
    Call<ResponseBody> getBreakDelete(@Field("user_id") String userid, @Field("user_token") String user_token,@Field("car_no") String car_no, @Field("city_code") String city_code, @Field("province_code") String province_code);

    //是否可以使用违章卡
    @FormUrlEncoded
    @POST("illegalcard/can_use_illegalcard")
    Call<ResponseBody> canUseIllegalCard(@Field("user_id") String userid, @Field("user_token") String user_token,@Field("card_no") String card_no, @Field("log_id_str") String log_id_str,@Field("city_code") String city_code);

    //确认使用违章卡
    @FormUrlEncoded
    @POST("illegalcard/illegalcard_order")
    Call<ResponseBody> confirmUseIllegalCard(@Field("user_id") String userid, @Field("user_token") String user_token, @Field("log_id_str") String log_id_str);

    //确认使用违章卡_提交
    @FormUrlEncoded
    @POST("illegalcard/illegalcard_create_order")
    Call<ResponseBody> commitUseIllegalCard(@Field("user_id") String userid, @Field("user_token") String user_token, @Field("card_no") String card_no,@Field("mobile") String mobile,@Field("log_id_str") String log_id_str);

    //违章反馈_提交
    @FormUrlEncoded
    @POST("illegal/submit_feedback")
    Call<ResponseBody> commitFeedBack(@Field("user_id") String userid, @Field("user_token") String user_token, @Field("content") String content,@Field("query_id") String query_id);

    // 商品订单列表
    @FormUrlEncoded
    @POST("index.php/Orderlist/get_user_order_goods")
    Call<ResponseBody> getGoodsOrder(@Field("page") String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 洗车订单列表
    @FormUrlEncoded
    @POST("index.php/Orderlist/get_user_order_wash")
    Call<ResponseBody> getWashOrder(@Field("page") String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 服务订单列表
    @FormUrlEncoded
    @POST("index.php/Orderlist/get_user_order_service")
    Call<ResponseBody> getServerOrder(@Field("page") String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 商品订单详情
    @FormUrlEncoded
    @POST("index.php/Orderdetail/get_order_goods_detail")
    Call<ResponseBody> getGoodsOrderDetails(@Field("order_sn") String order_sn, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 洗车服务订单详情
    @FormUrlEncoded
    @POST("index.php/Orderdetail/get_order_service_detail")
    Call<ResponseBody> getWashAndServerDetails(@Field("order_sn") String order_sn, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 商品订单确认收货
    @FormUrlEncoded
    @POST("index.php/Orderdetail/set_order_sh")
    Call<ResponseBody> comfirmReceive(@Field("order_sn") String order_sn, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 商品订单取消订单
    @FormUrlEncoded
    @POST("index.php/Orderdetail/get_order_goods_cancel")
    Call<ResponseBody> cancelOrder(@Field("order_sn") String order_sn, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 商品订单提醒发货
    @FormUrlEncoded
    @POST("index.php/Orderdetail/get_order_goods_delivery")
    Call<ResponseBody> delivery(@Field("order_sn") String order_sn, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 商品订单查看退款详情
    @FormUrlEncoded
    @POST("index.php/Orderdetail/get_order_goods_refund_detail")
    Call<ResponseBody> drawbackDetails(@Field("order_sn") String order_sn, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 商品订单申请退款
    @FormUrlEncoded
    @POST("index.php/Orderdetail/get_order_goods_refund")
    Call<ResponseBody> applyDrawback(@Field("order_sn") String order_sn, @Field("reason") String reason, @Field("remark") String remark, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 我的收藏列表
    @FormUrlEncoded
    @POST("collect/query")
    Call<ResponseBody> myCollectionList(@Field("user_id") String user_id,@Field("user_token") String user_token);

    // 我的购物车列表
    @FormUrlEncoded
    @POST("cart/get_list_v5_1")
    Call<ResponseBody> myShoppingCart(@Field("user_id") String user_id,@Field("user_token") String user_token);

    // 修改购物车中商品数量
    @FormUrlEncoded
    @POST("cart/set_goods_num")
    Call<ResponseBody> editGoodsNum(@Field("goods_id") String goods_id, @Field("goods_number") String goods_number, @Field("sku_value_id") String sku_value_id
            , @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 删除收藏商品
    @FormUrlEncoded
    @POST("collect/delete_collect")
    Call<ResponseBody> deleteCollections(@Field("goods_id_arr") String goodIds, @Field("user_id") String user_id, @Field("user_token") String user_token);

    // 删除购物车商品
    @FormUrlEncoded
    @POST("cart/delete")
    Call<ResponseBody> deleteCartGoods(@Field("data") String data);

    // 上传多张图片
    @Multipart
    @POST("login/multi_upload")
    Call<ResponseBody> uploadImgs(@PartMap Map<String, RequestBody>  imgs);

    // 上传单张图片
    @Multipart
    @POST("index.php/Comment/do_upload")
    Call<ResponseBody> uploadImg(@Part("file\"; filename=\"image.png\"") RequestBody img);

    // 商品评论
    @FormUrlEncoded
    @POST("index.php/Comment/goods_comment")
    Call<ResponseBody> goodsComment(@Field("content") String content, @Field("is_niming") String is_niming, @Field("level") String level
            , @Field("order_sn") String order_sn, @Field("img") String img, @Field("user_id") String user_id,
                                    @Field("user_token") String user_token);

    // 获取订单确认页面的数据
    @FormUrlEncoded
    @POST("orderconfirm/shop_order")
    Call<ResponseBody> orderConfirm(@Field("address_id") String address_id, @Field("good_arr") String good_arr, @Field("to_supplier_id") String to_supplier_id
            , @Field("user_id") String user_id, @Field("user_token") String user_token,@Field("coupon_platform") String coupon_platform,@Field("coupon_id") String coupon_id);

    // 秒杀商品生成订单
    @FormUrlEncoded
    @POST("/Seckill/seckill_order_info")
    Call<ResponseBody> seckillOrderInfo(@Field("address_id") String address_id,  @Field("to_supplier_id") String to_supplier_id
            , @Field("user_id") String user_id, @Field("user_token") String user_token,@Field("seckill_id") String seckill_id);

    // 秒杀商品生成订单
    @FormUrlEncoded
    @POST("/Seckill/seckill_create_order")
    Call<ResponseBody> seckillGenerateOrder(@Field("address_id") String address_id,  @Field("to_supplier_id") String to_supplier_id
            , @Field("user_id") String user_id, @Field("user_token") String user_token,@Field("seckill_id") String seckill_id);

    //订单确认页面的生成订单
    @FormUrlEncoded
    @POST("orderconfirm/shop_create_order")
    Call<ResponseBody> generateOrder(@Field("address_id") String address_id, @Field("good_arr") String good_arr, @Field("to_supplier_id") String to_supplier_id
            , @Field("user_id") String user_id, @Field("user_token") String user_token,@Field("seckill_id") String seckill_id,@Field("coupon_id") String coupon_id);

    //我的商品评论列表
    @FormUrlEncoded
    @POST("index.php/User/user_goods_comment")
    Call<ResponseBody> getGoodsCommentList(@Field("page") String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    //我的服务评论列表
    @FormUrlEncoded
    @POST("index.php/User/user_server_comment")
    Call<ResponseBody> getServerCommentList(@Field("page") String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    //提问与回答
    @FormUrlEncoded
    @POST("index.php/User/my_question")
    Call<ResponseBody> questionAndAnswer(@Field("page") String page, @Field("user_id") String user_id, @Field("user_token") String user_token);

    //门店服务项目
    @FormUrlEncoded
    @POST("shop/shop_service")
    Call<ResponseBody> shopService(@Field("service_cate_id") String service_cate_id, @Field("shop_id") String shop_id);

    //商城收银台
    @FormUrlEncoded
    @POST("pay/shop_pay")
    Call<ResponseBody> cash(@Field("order_sn") String order_sn);

    //支付成功
    @FormUrlEncoded
    @POST("shop/can_shop_get_xidou")
    Call<ResponseBody> paySuccess(@Field("order_sn") String order_sn);

    //支付成功获取喜豆
    @FormUrlEncoded
    @POST("shop/shop_get_xidou")
    Call<ResponseBody> getXiDou(@Field("order_sn") String order_sn,@Field("user_id") String user_id, @Field("user_token") String user_token);

    //选择评价商品
    @FormUrlEncoded
    @POST("index.php/Comment/get_goods_order_comment")
    Call<ResponseBody> selectCommentGoods(@Field("order_sn") String order_sn,@Field("user_id") String user_id,@Field("user_token") String user_token);

    //获取商品评论详情
    @FormUrlEncoded
    @POST("index.php/Comment/get_goods_omment_detail")
    Call<ResponseBody> getCommentDetails(@Field("goods_id") String goods_id,@Field("order_sn") String order_sn,@Field("user_id") String user_id,@Field("user_token") String user_token);

}
