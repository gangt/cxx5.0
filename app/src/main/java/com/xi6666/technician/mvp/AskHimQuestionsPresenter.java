package com.xi6666.technician.mvp;

import com.xi6666.order.other.Utils;

import java.util.List;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午5:07.
 * 个人公众号 ardays
 */
public class AskHimQuestionsPresenter extends AskHimQuestionsContract.Presenter {


    @Override
    protected void onStart() {

    }

    @Override
    public void requestCommitAsk(List<String> images, String content, String technician_id) {
        if (Utils.isEmpty(images)) {
            requestCommitAsk("", content, technician_id);
        } else {
            //上传图片
            mRxManager.add(mModel.commitImages(images).subscribe(
                    res -> {
                        if(res.success) {
                            //图片地址拼接
                            String imageUrl = "";
                            for (String image : res.data) {
                                imageUrl += image + ",";
                            }
                            requestCommitAsk(imageUrl, content, technician_id);
                        }else{
                            mView.returnError(res.info);
                        }
                    }, e -> {
                        mView.returnError("网络错误");
                    }
            ));
        }
    }

    @Override
    public void requestCommitAsk(String imagePaths, String content, String technician_id) {
        mRxManager.add(mModel.commitAsk(imagePaths, content, technician_id)
                .subscribe(
                        res -> {
                            mView.returnCommitResult(res);
                        }, e -> {
                            mView.returnError("网络错误");
                        }
                ));
    }
}
