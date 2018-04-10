package com.xi6666.car.mp;

import com.xi6666.car.bean.AddCarBean;
import com.xi6666.car.bean.AddCarParams;
import com.xi6666.car.http.HttpUtils;

/**
 * 创建者 sunsun
 * 时间 16/11/15 下午3:10.
 * 个人公众号 ardays
 */

public class AddCarPresenterImpl implements AddCarPresenter,AddCarListener {

    private AddCarSuccessView mView;
    private AddCarInteractor mInteractor;


    public AddCarPresenterImpl(AddCarSuccessView mView){
        this.mView = mView;
        mInteractor = new AddCarInteractorImpl(this);
    }

    /**
     * 上传车辆信息
     */
    @Override
    public void upCarData(AddCarParams params) {
        mInteractor.upCarData(params);
    }

    /**
     *  添加车辆是否成功
     */
    @Override
    public void addDataResult(AddCarBean bean) {
        if(bean.success){
            mView.addCarSuccess();
        }else{
            mView.addCarError(bean.info);
        }
    }
}
