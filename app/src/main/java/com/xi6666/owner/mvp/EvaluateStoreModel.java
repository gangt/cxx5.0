package com.xi6666.owner.mvp;

import com.xi6666.carWash.base.api.Api;
import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.RxSchedulers;
import com.xi6666.carWash.mvp.bean.StoreDetailsBean;
import com.xi6666.evaluate.bean.EvaluateServiceBean;
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
 * 时间 2016/11/30 上午11:40.
 * 个人公众号 ardays
 */
public class EvaluateStoreModel implements EvaluateStoreContract.Model {


    @Override
    public Observable<BaseBean> commitEvaluate(String imageFile, String context, String store_id, float star, int type, String order_id) {
        return Api.getInstance()
                .mAppUrls
                .commitEvaluate(context, star, store_id, imageFile, type, order_id)
                .compose(RxSchedulers.io_main());


    }

    @Override
    public Observable<FileBean> commitImages(List<String> imageFile) {
        Map<String, RequestBody> mRequestBody = new HashMap<>();
        for (int i = 0; i < imageFile.size(); i++) {
            File file = new File(imageFile.get(i));
            String substring = imageFile.get(i).substring(imageFile.get(i).lastIndexOf("/") + 1, imageFile.get(i).length());
            mRequestBody.put("file_" + i + "\"; filename=" + substring, RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }

        return Api.getInstance()
                .mAppUrls
                .uploadImages(mRequestBody)
                .compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<EvaluateServiceBean> getServiceList(String store_id) {
        return Api.getInstance()
                .mAppUrls
                .getServiceList(store_id)
                .compose(RxSchedulers.io_main());

    }
}
