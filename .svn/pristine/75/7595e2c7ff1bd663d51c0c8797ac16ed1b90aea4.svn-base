package com.xi6666.userdata.contract;

import com.xi6666.app.BasePersenter;
import com.xi6666.app.BaseView;
import com.xi6666.databean.UserDataBean;

import java.io.File;

/**
 * Created by Mr_yang on 2016/11/21.
 */

public interface UserDataContract {

    interface View extends BaseView {

        void showToast(String string);

        void clostAct();

        void setUserData(String phoneNum, String userName, String sex, String brithDay);

        void setHead(String imageUrl);

        void setQRCode(String code);

        void setUserDataBean(UserDataBean userDataBean);

        void choicePic(File file);

        void setUserFaceUrl(String userFaceUrl);


        void showLoadingDialog();

        void hidLoadingDialog();

        void setUserCode(String userCode);

        void closeAct();

    }

    interface Presenter extends BasePersenter<View> {
        void loadUserData();

        void setUserData(String user_birthday, String user_face, String user_nickname, String user_sex, String userId, String userToken);

        void showChoiceHead();

        void changeHeadFace(File file);

    }
}
