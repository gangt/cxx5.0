package com.xi6666.car.mp;

import com.xi6666.car.bean.CarEngineBean;
import com.xi6666.car.bean.CarBrandTypeBean;
import com.xi6666.car.bean.CarYearTypeBean;
import com.xi6666.car.bean.SelectCarBean;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午2:54.
 * 个人公众号 ardays
 */

public class SelectCarTypePresenterImpl implements SelectCarTypePresenter,SelectCarListener {

    private SelectCarTypeView mView;
    private SelectCarTypeInteractor mInteractor;

    public SelectCarTypePresenterImpl(SelectCarTypeView mView){
        this.mView = mView;
        mInteractor = new SelectCarTypeInteractorImpl(this);
    }

    @Override
    public void getCarTypeList() {
        mInteractor.getCarType();
    }

    /**
     * 获取汽车品牌
     */
    @Override
    public void getCarTypeSpList(String brand_number) {
        mInteractor.getCarSpType(brand_number);
    }

    /**
     * 获取汽车年限
     */
    @Override
    public void getCarYearTypeList(String brand_number) {
        mInteractor.getCarYearType(brand_number);
    }

    /**
     * 获取排油量列表
     */
    @Override
    public void getCarEngineList(String brand_number, String cartype_number) {
        mInteractor.getCarEngineType(brand_number, cartype_number);
    }

    @Override
    public void myCarDataList(SelectCarBean bean) {
        mView.carTypeList(bean);
    }

    /**
     * 汽车品牌返回
     * @param bean
     */
    @Override
    public void myCarDataSpList(CarBrandTypeBean bean) {
        mView.myCarDataSpList(bean);
    }

    @Override
    public void myCarYearDataList(CarYearTypeBean bean) {
        mView.myCarYearDataList(bean);
    }

    @Override
    public void myCarEngineList(CarEngineBean bean) {
        mView.myCarEngineList(bean);
    }
}
