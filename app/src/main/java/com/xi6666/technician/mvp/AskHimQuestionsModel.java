package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.store.bean.FileBean;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午5:07.
 * 个人公众号 ardays
 */
public class AskHimQuestionsModel implements AskHimQuestionsContract.Model {


    @Override
    public Observable<FileBean> commitImages(List<String> images) {
        Map<String, RequestBody> mRequestBody = new HashMap<>();
        for (int i = 0; i < images.size(); i++) {
            File file = new File(images.get(i));
            String substring = images.get(i).substring(images.get(i).lastIndexOf("/") + 1, images.get(i).length());
            mRequestBody.put("file_" + i + "\"; filename=" + substring, RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        return Api.getInstance()
                .mAppUrls
                .uploadImages(mRequestBody)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<BaseBean> commitAsk(String imagePaths, String content, String technician_id) {
        return Api.getInstance()
                .mAppUrls
                .getQuestionComment(technician_id, content, imagePaths)
                .compose(RxSchedulers.io_main());
    }
}
