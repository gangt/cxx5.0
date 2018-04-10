package com.xi6666.owner.mvp;

import android.util.Log;

import com.xi6666.store.bean.FileBean;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/30 上午11:33.
 * 个人公众号 ardays
 */
public class EvaluateStorePresenter extends EvaluateStoreContract.Presenter {


    @Override
    protected void onStart() {

    }


    @Override
    public void requestUpEvaluate(List<String> imageFile, String context, String store_id, float star, int type, String order_id) {
        if (imageFile != null && imageFile.size() > 0) {
            Log.e("TAG", "准备上传图片");
            mRxManager.add(mModel.commitImages(imageFile)
                    .subscribe(
                            res -> {
                                if (res.success) {
                                    //如果上传图片成功
                                    requestEvaluate(res, context, store_id, star, type, order_id);
                                } else {
                                    mView.returnError(res.info);
                                }
                            }, e -> {
                                e.printStackTrace();
                                mView.returnError("网络异常");
                            }
                    ));
        } else {
            requestEvaluate(null, context, store_id, star, type, order_id);
        }
    }

    @Override
    public void requestEvaluate(FileBean imageFile, String context, String store_id, float star, int type, String order_id) {
        //图片地址拼接
        String imageUrl = "";
        if (imageFile != null) {
            for (String image : imageFile.data) {
                imageUrl += image + ",";
            }
        }

        mRxManager.add(mModel.commitEvaluate(imageUrl, context, store_id, star, type, order_id)
                .subscribe(
                        res -> {
                            mView.returnCommitResult(res);
                        }, e -> {
                            e.printStackTrace();
                            mView.returnError("网络异常");
                        }
                ));
    }

    @Override
    public void requestServiceList(String store_id) {
        mRxManager.add(mModel.getServiceList(store_id)
                .subscribe(
                        res -> {
                            mView.returnServiceList(res);
                        }, e -> {
                            e.printStackTrace();
                        }
                ));
    }


}
