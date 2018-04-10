package com.xi6666.app.baset;

import android.widget.Toast;

import com.xi6666.app.SuperFrgm;

/**
 * Created by Mr_yang on 2017/1/17.
 */

public abstract class BaseTFrgm<P extends BaseTPresenter, M extends BaseTModel> extends SuperFrgm {
    public P mPresent;
    public M mMdole;
    public Toast mToast;

    @Override
    protected void init() {
        mPresent = InstanceUtil.getInstance(this, 0);
        mMdole = InstanceUtil.getInstance(this, 1);
        if (this instanceof BaseTView) {
            mPresent.setModelAndView(mMdole, this);
        }
        this.initData();
    }

    public void showToast(String message) {
        if (mToast == null) {
            mToast = Toast.makeText(mActivity, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
        }
        mToast.show();
    }

    protected abstract void initData();
}
