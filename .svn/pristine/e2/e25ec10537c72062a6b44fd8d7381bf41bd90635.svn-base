package com.xi6666.technician.mvp;

import com.xi6666.carWash.base.network.BaseBean;
import com.xi6666.carWash.base.network.BaseModel;
import com.xi6666.carWash.base.network.BasePresenter;
import com.xi6666.carWash.base.network.BaseView;
import com.xi6666.store.bean.FileBean;

import java.util.List;

import rx.Observable;


/**
 * 创建者 sunsun
 * 时间 2016/11/29 下午5:07.
 * 个人公众号 ardays
 */
public interface AskHimQuestionsContract {

    interface Model extends BaseModel {
        /**
         * 上传图片
         */
        Observable<FileBean> commitImages(List<String> images);

        /**
         * 上传提问
         */
        Observable<BaseBean> commitAsk(String imagePaths, String content, String technician_id);
    }

    interface View extends BaseView {
        /**
         * 返回提交
         * @param bean
         */
        void returnCommitResult(BaseBean bean);


        /**
         * 输出语句
         */
        void returnError(String error);
    }


    abstract class Presenter extends BasePresenter<Model, View> {
        /**
         * 向Ta提问
         *
         * @param images        图片数组
         * @param content       问题描述
         * @param technician_id 技师ID
         */
        public abstract void requestCommitAsk(List<String> images, String content, String technician_id);

        /**
         * 向Ta提问
         *
         * @param imagePaths 图片地址
         * @param content       问题描述
         * @param technician_id 技师ID
         */
        public abstract void requestCommitAsk(String imagePaths, String content, String technician_id);
    }
}
