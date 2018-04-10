package com.xi6666.classification.view.mvp;

/**
 * 创建者 sunsun
 * 时间 2016/11/28 下午6:41.
 * 个人公众号 ardays
 */
public class BrandDetailsPresenter extends BrandDetailsContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestBrandGoodsList(String brand_id, String goods_id, int page, BrandDetailsContract.Sort sort) {
        int sort_param = 1;
        switch (sort) {
            case Default:
                sort_param = 1;
                break;
            case SalesMany:
                sort_param = 2;
                break;
            case SalesLess:
                sort_param = 3;
                break;
            case MoneyMany:
                sort_param = 5;
                break;
            case MeneyLess:
                sort_param = 4;
                break;
        }

        mRxManager.add(mModel.getBrandGoodsList(brand_id, goods_id, page, sort_param)
                .subscribe(
                        res -> {
                            mView.returnBrandGoodsData(res);
                        }, e -> {
                            mView.returnBrandError();
                        }
                ));
    }


    @Override
    public void requestShoppingNumber() {
        mRxManager.add(mModel.getShoppingNumber()
                .subscribe(
                        res -> {
                            mView.returnShoppingNumber(res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }
}
