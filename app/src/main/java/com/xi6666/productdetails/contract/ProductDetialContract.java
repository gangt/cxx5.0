package com.xi6666.productdetails.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.ProductDetialBean;
import com.xi6666.databean.PromotionBean;
import com.xi6666.databean.SkuListBean;


import java.util.List;

import static com.xi6666.R.id.size;

/**
 * Created by Mr_yang on 2016/11/15.
 */

public interface ProductDetialContract {

    interface View extends BaseView {
        void setBanner(List<String> banner);

        void setNamePrice(String name, String xiPrice, String netPrice);

        void showLoading();

        void showError();

        void hideState();

        void setCollection(boolean isCollect);

        void showToast(String string);

        void setGoodsCarNum(String string);

        void showPromotion();

        void hidePromotion();

        void showStore();

        void hideStore();

        void showColorText(String colorText);

        void showSizeText(String sizeText);

        //  void setColorType(List<SkuListBean.ListBean> mColorData);

        void setColorType(List<SkuListBean.ListBean> mColor,
                          ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean);

        void setSizeType(List<SkuListBean.ListBean> mSizeData, ProductDetialBean.DataBean.GoodsInfoBean goodsInfoBean);

        void loadProductDetial(String picUrl, String pramUrl);

        void showOffShelf();

        void showAddCarSuccess();

        void setSkuValue(String skuValue);

        void hideAddCarSuccessPopu();

        void hideColorAndSize();

        void showColorSku();

        void showSizeSku();

        void loadShare(String webPageUrl,
                       String title,
                       String content,
                       String imageUrl);

        void setGoodsCarPointShow(boolean flag);

        void showAddShopCarAnim(boolean show);

        void showSkuPopu(String imageUrl);

        void setPromotion(List<PromotionBean.DiscountListBean> promotion);


    }

    interface Presenter extends BasePersenter<View> {

        void loadData(String goodId, String userId, String goodsToken);

        void loadCollect(String goodId, String userId, String goodsToken);

        void loadSku(String goodId, String skuValue);

        void addGoodsCard(
                String goods_id, String sku_value_id, String user_id, String user_token
        );

        void loadSkuSize(String goodId, String skuValue);

        void loadGoodsCarNum(String userId, String goodsToken);

        void loadNearNearBy();

    }
}
