package com.xi6666.seckill.contract;

import com.xi6666.app.baset.BaseTModel;
import com.xi6666.app.baset.BaseTPresenter;
import com.xi6666.app.baset.BaseTView;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.SkuListBean;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Mr_yang on 2017/1/16.
 */

public interface SecKillContract {
    interface Module extends BaseTModel {

        Observable<ResponseBody> getProduct(String goodId, String useId, String userToken);

        Observable<ResponseBody> getSku(String goodId, String skuValue);

        Observable<ResponseBody> NearbyStores();

        Observable<ResponseBody> secKillSingUp(String userId, String userToken, String deviceType, String secKillId);

        Observable<ResponseBody> secKillSure(String userId, String userToken, String deviceType, String secKillId);

        Observable<ResponseBody> addShopCar(String goods_id, String sku_value_id, String user_id, String user_token);
    }

    interface View extends BaseTView {

        void setBanner(List<String> banner);

        void setNamePrice(String name, String xiPrice, String netPrice, String yuanPrice);


        void showError();

        void hideState();

        void showStore();

        void hideStore();

        void showColorText(String colorText);

        void showSizeText(String sizeText);


        void setColorType(List<SkuListBean.ListBean> mColor,
                          ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean);

        void setSizeType(List<SkuListBean.ListBean> mSizeData, ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean);

        void loadProductDetial(String picUrl, String pramUrl);

        void showOffShelf();


        void setSkuValue(String skuValue);


        void hideColorAndSize();


        void loadShare(String webPageUrl,
                       String title,
                       String content,
                       String imageUrl);


        void showSkuPopu(String imageUrl);

        void setTime(long mday, long mhour, long mmin, long msecond, String state);

        void goToSecKill(boolean flag);

        void orderPay();


        void setProductId(String productID);

    }

    abstract class Persenter extends BaseTPresenter<Module, View> {


        public abstract void loadData(String goodId, String userId, String goodsToken);


        public abstract void loadSku(String goodId, String skuValue);

        public abstract void addGoodsCard(
                String goods_id, String sku_value_id, String user_id, String user_token
        );

        public abstract void loadSkuSize(String goodId, String skuValue);

        public abstract void signUp(String user_id, String user_token, String get_device_type, String seckill_id);

        public abstract void secKillSure(String user_id, String user_token, String get_device_type, String seckill_id);

        public abstract void loadNearNearBy();
    }
}
